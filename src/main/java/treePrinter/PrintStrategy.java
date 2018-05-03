package treePrinter;

import tree.model.TreeNode;

public interface PrintStrategy {
    <T extends TreeNode> String print(T node);
}
