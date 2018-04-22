package tree.factory;

import tree.TreeNode;
import tree.TreeOperations;

public final class NodeFactory {

    public static <T extends TreeOperations> TreeNode<T> createNytNode() {
        TreeNode<T> nytTreeNode = new TreeNode<>(null);
        nytTreeNode.nyt = true;
        nytTreeNode.weight = 0;
        return nytTreeNode;
    }

    public static <T extends TreeOperations> TreeNode<T> createNodeOf(T value) {
        TreeNode<T> nytTreeNode = new TreeNode<>(value);
        nytTreeNode.weight = 0;
        return nytTreeNode;
    }
}
