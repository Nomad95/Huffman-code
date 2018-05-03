package treePrinter.PrintStrategyImpl;

import tree.model.TreeNode;
import treePrinter.PrintStrategy;

public class WeightPrintStrategy implements PrintStrategy {

    @Override
    public <T extends TreeNode> String print(T node) {
        if (node.value != null || node.isNyt()) {
            System.out.print(node.printValue() + node.weight);
        } else {
            System.out.print(node.weight);
        }

        return "0";
    }
}
