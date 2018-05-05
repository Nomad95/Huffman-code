package frontend.mainController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

public class HuffmanApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL resource = getClass().getResource("/fxml/huffmannMain.fxml");
        System.out.println(resource);
        Parent root = FXMLLoader.load(resource);

        Scene scene = new Scene(root, 480, 480);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Huffman Beta");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
