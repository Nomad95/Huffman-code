package coding.encode;

import coding.CodeTable;
import coding.decode.HuffmanDecoder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tree.Tree;
import tree.impl.FKGTree;
import tree.impl.VitterTree;
import tree.model.TreeNodeValue;

import java.io.*;

import static coding.CodingUtils.streamToString;

@NoArgsConstructor
public class HuffmanEncoder {

    private Tree tree;

    @Getter
    private String encodedText;

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
        return new HuffmanDecoder(tree);
    }

}
