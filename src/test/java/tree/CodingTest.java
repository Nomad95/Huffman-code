package tree;

import coding.CodeTable;
import coding.decode.HuffmanDecoder;
import coding.encode.HuffmanEncoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tree.factory.TreeFactory;
import tree.impl.FKGTree;

import java.io.IOException;
import java.io.InputStream;

public class CodingTest {

    @Test
    public void shouldCreateCodeTable() {
        FKGTree tree = TreeFactory.createSampleFKGTree();
        tree.computeBinaryValues();

        CodeTable codeTable = new CodeTable.CodeTableCreator().createCodeTable(tree).create();

        Assertions.assertTrue(codeTable.getEncodedValueFor("a").equals("0"));
        Assertions.assertTrue(codeTable.getEncodedValueFor("b").equals("10"));
        Assertions.assertTrue(codeTable.getEncodedValueFor("v").equals("111"));
        Assertions.assertTrue(codeTable.getEncodedValueFor("g").equals("1101"));
    }

    @Test
    public void shouldEncodeSomeTextFKG() throws IOException {
        InputStream fileInputStream = HuffmanEncoder.class.getClassLoader().getResourceAsStream("examples/toBe");
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();
        huffmanEncoder.encodeByFKG(fileInputStream);

        Assertions.assertEquals("110100100110101000110011011000100111101110010011010", huffmanEncoder.getEncodedText());
    }

    @Test
    public void shouldDecodeSomeTextFKG() throws IOException {
        InputStream fileInputStream = HuffmanEncoder.class.getClassLoader().getResourceAsStream("examples/toBe");
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();
        huffmanEncoder.encodeByFKG(fileInputStream);
        String textToEncode = huffmanEncoder.getEncodedText();

        HuffmanDecoder huffmanDecoder = huffmanEncoder.getDecoder();
        huffmanDecoder.setStringToEncode(textToEncode);
        String decodedText = huffmanDecoder.decode();

        Assertions.assertEquals("To be or not to be", decodedText);
    }

    @Test
    public void shouldEncodeSomeTextVitter() throws IOException {
        InputStream fileInputStream = HuffmanEncoder.class.getClassLoader().getResourceAsStream("examples/toBe");
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();
        huffmanEncoder.encodeByVitter(fileInputStream);

        Assertions.assertEquals("100001110110101110011011111100001011111101100111011010", huffmanEncoder.getEncodedText());
    }

    @Test
    public void shouldDecodeSomeTextVitter() throws IOException {
        InputStream fileInputStream = HuffmanEncoder.class.getClassLoader().getResourceAsStream("examples/toBe");
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder();
        huffmanEncoder.encodeByVitter(fileInputStream);
        String textToEncode = huffmanEncoder.getEncodedText();

        HuffmanDecoder huffmanDecoder = huffmanEncoder.getDecoder();
        huffmanDecoder.setStringToEncode(textToEncode);
        String decodedText = huffmanDecoder.decode();

        Assertions.assertEquals("To be or not to be", decodedText);
    }
}
