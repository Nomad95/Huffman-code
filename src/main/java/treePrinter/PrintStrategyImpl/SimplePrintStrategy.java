package treePrinter.PrintStrategyImpl;

import tree.TreeNode;
import tree.TreeOperations;
import treePrinter.PrintStrategy;

public class SimplePrintStrategy implements PrintStrategy {

    @Override
    public <T extends TreeNode> String print(T node) {
        if (node.value != null || node.isNyt()) {
            System.out.print(node.printValue());
        } else {
            System.out.print(node.weight);
        }

        return "";
    }
}
