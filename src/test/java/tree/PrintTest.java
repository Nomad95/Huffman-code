package tree;

import org.junit.jupiter.api.Test;
import tree.impl.FKGTree;
import treePrinter.BTreePrinter;
import treePrinter.PrintStrategyImpl.WeightPrintStrategy;

public class PrintTest {

    @Test
    public void shouldPrintTree() {//print leaves
        FKGTree comparableFKGTree = (FKGTree) OperationsTest.createSampleTree();

        BTreePrinter.printNode(comparableFKGTree.getRootNode(), new WeightPrintStrategy());
    }

    @Test
    public void testChars() {
        char letter = 'a';
        for (int i = 0; i < 26; i++, letter++) {
            System.out.println(Integer.toBinaryString(letter));
        }
    }
}
