package tree.factory;

import tree.Codeable;
import tree.model.TreeNode;

public final class NodeFactory {

    public static <T extends Codeable> TreeNode<T> createNytNode() {
        TreeNode<T> nytTreeNode = new TreeNode<>(null);
        nytTreeNode.nyt = true;
        nytTreeNode.weight = 0;
        return nytTreeNode;
    }

    public static <T extends Codeable> TreeNode<T> createNodeOf(T value) {
        TreeNode<T> nytTreeNode = new TreeNode<>(value);
        nytTreeNode.weight = 0;
        return nytTreeNode;
    }

    public static <T extends Codeable> TreeNode<T> createNodeWithWeightOf(T value, int weight) {
        TreeNode<T> nytTreeNode = new TreeNode<>(value);
        nytTreeNode.weight = weight;
        return nytTreeNode;
    }

    public static <T extends Codeable> TreeNode<T> createInternalWithWeight(int weight) {
        TreeNode<T> nytTreeNode = new TreeNode<>(null);
        nytTreeNode.weight = weight;
        return nytTreeNode;
    }
}
