package tree.impl;

import tree.Tree;
import tree.TreeNode;
import tree.TreeNodeValue;
import tree.TreeOperations;
import tree.factory.NodeFactory;

public class FKGTree extends Tree<TreeNode, TreeNodeValue> {

    public FKGTree() {
        TreeNode<TreeOperations> rootNode = NodeFactory.createNytNode();
        this.setRootNode(rootNode);
    }
}
