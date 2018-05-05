package frontend.mainController;

import coding.decode.HuffmanDecoder;
import coding.encode.HuffmanEncoder;
import frontend.util.ExampleFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import frontend.util.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.StopWatch;
import treePrinter.PrintStrategyImpl.SimplePrintStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class MainController implements Initializable {

    @FXML
    private MenuButton exampleAlgorithmMenu;
    @FXML
    private Button exampleCodeDecodeButton;
    @FXML
    private RadioButton toBeExampleRadio;
    @FXML
    private RadioButton loremIpsumRadio;

    @FXML
    private MenuButton menuEnterAlgorithmButton;
    @FXML
    private Button encodeDecodeEnterButton;
    @FXML
    private TextArea enterTextTextArea;

    @FXML
    private MenuButton menuFIleAlgorithmButton;
    @FXML
    private Button selectFileButton;
    @FXML
    private Button encodeDecodeFileButton;
    @FXML
    private Label okLabel;

    @FXML
    private Label originalTextLabel;
    @FXML
    private Label originalTextLabelResult;
    @FXML
    private Label encodedLabel;
    @FXML
    private Label encodedLabelResult;
    @FXML
    private Label decodedLabel;
    @FXML
    private Label decodedLabelResult;
    @FXML
    private Label lengthDifferenceLabel;
    @FXML
    private Label lengthDifferenceLabelResult;
    @FXML
    private Label executionTimeLabel;
    @FXML
    private Label executionTimeLabelResult;
    @FXML
    private Label enthropyLabel;
    @FXML
    private Label enthropyLabelResult;
    @FXML
    private Label averageLengthLabel;
    @FXML
    private Label averageLengthLabelResult;

    private Algorithm algorithm = Algorithm.FKG;
    private ExampleFile exampleFile = ExampleFile.TO_BE;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initMenuButtons();
        initRadios();
    }

    @FXML
    public void encodeExample(ActionEvent event) throws Exception {
        StopWatch stopWatch = new StopWatch();

        InputStream fileInputStream = getExampleFileInputStream();
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();
        log.debug("Algorithm started");
        stopWatch.start();

        encodeByAlgorithm(huffmanEncoder, fileInputStream);
        HuffmanDecoder huffmanDecoder = huffmanEncoder.getDecoder();
        String decoded = huffmanDecoder.decode();

        stopWatch.stop();
        log.debug("Algorithm finished");

        setResultLabelText(originalTextLabelResult, huffmanEncoder.getOriginalText());
        setResultLabelText(encodedLabelResult, huffmanEncoder.getEncodedText());
        setResultLabelText(decodedLabelResult, decoded);
        setResultLabelText(enthropyLabelResult, huffmanEncoder.getEntropy().toString());
        setResultLabelText(averageLengthLabelResult, huffmanEncoder.getAverageCodeLength().toString());
        computeLengthDifference(huffmanEncoder);
        computeAlgorithmRuntime(stopWatch);

        new AsyncPrinter<String>(huffmanEncoder, new SimplePrintStrategy()).call();
    }

    private void computeAlgorithmRuntime(StopWatch stopWatch) {
        setResultLabelText(executionTimeLabelResult, String.valueOf(stopWatch.getTime()) + " ms");
        stopWatch.reset();
    }

    private InputStream getExampleFileInputStream() {
        if (ExampleFile.TO_BE.equals(exampleFile)) {
            return getClass().getClassLoader().getResourceAsStream("examples/toBe");
        } else {
            return getClass().getClassLoader().getResourceAsStream("examples/lorem");
        }
    }

    private void computeLengthDifference(HuffmanEncoder huffmanEncoder) {
        int decodedBytesLength = huffmanEncoder.getOriginalText().length() * 8;
        int encodedBytesLength = huffmanEncoder.getEncodedText().length();
        setResultLabelText(lengthDifferenceLabelResult, String.valueOf(decodedBytesLength - encodedBytesLength));
    }

    private void encodeByAlgorithm(HuffmanEncoder encoder, InputStream inputStream) throws IOException {
        if (Algorithm.FKG.equals(algorithm)) {
            encoder.encodeByFKG(inputStream);
        } else {
            encoder.encodeByVitter(inputStream);
        }
    }

    private void setResultLabelText(Label label, String text) {
        label.setText(text);
        label.setVisible(true);
    }

    private void initRadios() {
        loremIpsumRadio.setOnAction(r -> {
            exampleFile = ExampleFile.LOREM_IPSUM;
            log.info("Example file set to Lorem ipsum");
        });

        toBeExampleRadio.setOnAction(r -> {
            exampleFile = ExampleFile.TO_BE;
            log.info("Example file set to To be");
        });
    }

    private void initMenuButtons() {
        MenuItem fkgMenuItem = new MenuItem("FKG");
        fkgMenuItem.setOnAction(i -> {
            algorithm = Algorithm.FKG;
            log.info("Algorithm set to FKG");
        });

        MenuItem vitterMenuItem = new MenuItem("Vitter");
        vitterMenuItem.setOnAction(i -> {
            algorithm = Algorithm.VITTER;
            log.info("Algorithm set to Vitter");
        });

        menuEnterAlgorithmButton.getItems().addAll(fkgMenuItem, vitterMenuItem);
        menuFIleAlgorithmButton.getItems().addAll(fkgMenuItem, vitterMenuItem);
        exampleAlgorithmMenu.getItems().addAll(fkgMenuItem, vitterMenuItem);
    }

}
