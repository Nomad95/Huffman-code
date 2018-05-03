package tree.impl;

import tree.*;
import tree.factory.NodeFactory;
import tree.model.TreeNode;
import tree.model.TreeNodeValue;
import tree.util.NodeSwapper;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class FKGTree extends Tree<TreeNode, TreeNodeValue> {

    public FKGTree() {
        TreeNode<Codeable> rootNode = NodeFactory.createNytNode();
        this.setRootNode(rootNode);
    }

    @Override
    protected void updateTree(TreeNode current) {
        updateRecursive(current);
        addWeightTillRootPath(current);
    }

    private void updateRecursive(TreeNode current) {
        if (Objects.isNull(current.parent)) {
            return;
        }

        List<TreeNode> orderedNodes = getNodesReversedLevelOrder();
        Optional<TreeNode> currentNodeFromList = findCurrentNode(current, orderedNodes);
        if (currentNodeFromList.isPresent()) {
            List<TreeNode> listCutFromTheCurrentNode = getListStartingFromCurrentNode(orderedNodes, currentNodeFromList.get());
            TreeNode nodeToSwap = searchNodeToSwap(current, listCutFromTheCurrentNode);
            if (Objects.nonNull(nodeToSwap)) {
                NodeSwapper.swapNodes(current, nodeToSwap);
            }
        }

        updateRecursive(current.parent);
    }

    private Optional<TreeNode> findCurrentNode(TreeNode current, List<TreeNode> orderedNodes) {
        return orderedNodes.stream()
                .filter(getCurrentNodeFromList(current))
                .findFirst();
    }

    private TreeNode searchNodeToSwap(TreeNode current, List<TreeNode> listCutFromTheCurrentNode) {
        for (ListIterator<TreeNode> iterator = listCutFromTheCurrentNode.listIterator(); iterator.hasNext();) {
            TreeNode node = iterator.next();
            if (nodeWeightIsLesserThanPending(current, node)) {
                if (isNotSelfOrCurrentParent(current, node)){
                    return node;
                }
            } else break;
        }
        return null;
    }

    private boolean nodeWeightIsLesserThanPending(TreeNode current, TreeNode node) {
        return node.weight < current.weight + 1;
    }

    private boolean isNotSelfOrCurrentParent(TreeNode current, TreeNode node) {
        return node != current && current.parent != node;
    }

    private List<TreeNode> getListStartingFromCurrentNode(List<TreeNode> orderedNodes, TreeNode currentNodeFromList) {
        return orderedNodes.subList(orderedNodes.indexOf(currentNodeFromList), orderedNodes.size());
    }

    private Predicate<TreeNode> getCurrentNodeFromList(TreeNode current) {
        return node -> node == current;
    }

}
