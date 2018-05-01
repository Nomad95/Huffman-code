package tree;

import org.junit.jupiter.api.Test;
import tree.impl.FKGTree;
import tree.impl.VitterTree;
import treePrinter.BTreePrinter;
import treePrinter.PrintStrategyImpl.WeightPrintStrategy;

import static tree.factory.TreeFactory.createSampleFKGTree;
import static tree.factory.TreeFactory.createSampleVitterTree;

public class PrintTest {

    @Test
    public void shouldPrintTree() {
        FKGTree tree = createSampleFKGTree();

        BTreePrinter.printNode(tree.getRootNode(), new WeightPrintStrategy());
    }

    @Test
    public void shouldPrintVitterTree() {
        VitterTree tree = createSampleVitterTree();

        BTreePrinter.printNode(tree.getRootNode(), new WeightPrintStrategy());
    }

    @Test
    public void testChars() {
        char letter = 'a';
        for (int i = 0; i < 26; i++, letter++) {
            System.out.println(letter + " -> " + Integer.toBinaryString(letter));
        }
    }
}
