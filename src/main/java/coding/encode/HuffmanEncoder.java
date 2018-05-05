package coding.encode;

import averageCodeLength.AverageCodeComputor;
import coding.CodeTable;
import coding.decode.HuffmanDecoder;
import enthropy.EntropyComputor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tree.Tree;
import tree.impl.FKGTree;
import tree.impl.VitterTree;
import tree.model.TreeNodeValue;
import treePrinter.BTreePrinter;
import treePrinter.PrintStrategy;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import static coding.CodingUtils.streamToString;

@Slf4j
@NoArgsConstructor
public class HuffmanEncoder {

    private Tree tree;

    @Getter
    private String encodedText;

    @Getter
    private String originalText;

    public void encodeByFKG(InputStream inputStream) throws IOException {
        String text = streamToString(inputStream);
        this.tree = new FKGTree();
        encode(text);
    }

    public void encodeByVitter(InputStream inputStream) throws IOException {
        String text = streamToString(inputStream);
        this.tree = new VitterTree();
        encode(text);
    }

    public void encodeByFKG(String text) throws IOException {
        this.tree = new FKGTree();
        encode(text);
    }

    public void encodeByVitter(String text) throws IOException {
        this.tree = new VitterTree();
        encode(text);
    }

    private void encode(String text) {
        this.originalText = text;
        for (char c : text.toCharArray()){
            tree.addValue(new TreeNodeValue(c));
        }
        tree.computeBinaryValues();

        CodeTable codeTable = new CodeTable.CodeTableCreator().createCodeTable(tree).create();

        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()){
            result.append(codeTable.getEncodedValueFor(Character.toString(c)));
        }

        this.encodedText = result.toString();
    }

    public HuffmanDecoder getDecoder() {
        return new HuffmanDecoder(tree, encodedText);
    }

    public void printTree(PrintStrategy printStrategy) {
        BTreePrinter.printNode(tree.getRootNode(), printStrategy);
    }

    public BigDecimal getEntropy() {
        if (Objects.isNull(originalText)) {
            System.out.println("Cannot get entropy");
            return new BigDecimal("0");
        }

        return EntropyComputor.computeEntropy(tree, originalText);
    }

    public BigDecimal getAverageCodeLength() {
        if (Objects.isNull(originalText)) {
            System.out.println("Cannot get average code length");
            return new BigDecimal("0");
        }
        return AverageCodeComputor.computeAverageLength(tree, originalText).setScale(4, RoundingMode.HALF_EVEN);
    }
}
