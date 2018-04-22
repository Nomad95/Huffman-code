package treePrinter;

import tree.TreeNode;
import tree.TreeOperations;

public interface PrintStrategy {
    <T extends TreeNode> String print(T node);
}
