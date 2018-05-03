package coding.decode;

import tree.Tree;
import tree.model.TreeNode;

public class HuffmanDecoder {

    private char[] encoded;
    private Tree tree;
    private int counter;
    private StringBuilder result;


    public HuffmanDecoder(Tree tree, String encoded) {
        this.encoded = encoded.toCharArray();
        this.tree = tree;
        this.result = new StringBuilder();
        this.counter = 0;
    }

    public HuffmanDecoder(Tree tree) {
        this.tree = tree;
        this.result = new StringBuilder();
        this.counter = 0;
    }

    public String decode() {
        if (encoded == null) {
            System.out.println("No value to decode");
            return "";
        }
        decodeLetter(tree.getRootNode());
        String resultString = result.toString();
        reset();
        return resultString;
    }

    private void decodeLetter(TreeNode node) {

        if (!node.isInternalNode()) {
            result.append(node.printValue());
            decodeLetter(tree.getRootNode());
        } else if (counter == encoded.length) {
            return;
        } else if (encoded[counter] == '0') {
            counter++;
            decodeLetter(node.left);
        } else if (encoded[counter] == '1') {
            counter++;
            decodeLetter(node.right);
        }
    }

    private void reset() {
        result = new StringBuilder();
        counter = 0;
    }

    public void newValues(Tree tree, String encoded) {
        reset();
        this.encoded = encoded.toCharArray();
        this.tree = tree;
    }

    public void setTree(Tree tree) {
        reset();
        this.tree = tree;
    }

    public void setStringToEncode(String stringToEncode) {
        reset();
        this.encoded = stringToEncode.toCharArray();
    }
}
