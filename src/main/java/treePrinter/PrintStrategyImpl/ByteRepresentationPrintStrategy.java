package treePrinter.PrintStrategyImpl;

import tree.model.TreeNode;
import treePrinter.PrintStrategy;

public class ByteRepresentationPrintStrategy implements PrintStrategy {

    @Override
    public <T extends TreeNode> String print(T node) {
        if (node.value != null) {
            System.out.print(node.value.getEncodedValue());
        } else {
            System.out.print("x");
        }
        return "x";
    }

}
