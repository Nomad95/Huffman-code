package treePrinter.PrintStrategyImpl;

import tree.model.TreeNode;
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
