package tree.impl;

import tree.Tree;
import tree.TreeNode;
import tree.TreeNodeValue;
import tree.TreeOperations;
import tree.factory.NodeFactory;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class FKGTree extends Tree<TreeNode, TreeNodeValue> {

    public FKGTree() {
        TreeNode<TreeOperations> rootNode = NodeFactory.createNytNode();
        this.setRootNode(rootNode);
    }

    @Override
    protected void updateTree(TreeNode current) {
        if (Objects.isNull(current.parent)) {
            return;
        }

        List<TreeNode> orderedNodes = getNodesReversedLevelOrder();
        Optional<TreeNode> currentNodeFromList = findCurrentNode(current, orderedNodes);
        if (currentNodeFromList.isPresent()) {
            List<TreeNode> listCutFromTheCurrentNode = getListStartingFromCurrentNode(orderedNodes, currentNodeFromList.get());
            TreeNode nodeToSwap = searchNodeToSwap(current, listCutFromTheCurrentNode);
            if (Objects.nonNull(nodeToSwap)) {
                swapNodes(current, nodeToSwap);
            }
        }

        updateTree(current.parent);
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

    private void swapNodes(TreeNode current, TreeNode nodeToSwap) {
        if (nodesAreNotRoot(current, nodeToSwap)) {
            if (current.parent == nodeToSwap.parent) {
                swapCaseSameParents(current, nodeToSwap);
            } else {
                swapCaseDifferentParents(current, nodeToSwap);
            }
        }
    }

    private boolean nodesAreNotRoot(TreeNode current, TreeNode nodeToSwap) {
        return Objects.nonNull(nodeToSwap.parent) && Objects.nonNull(current.parent);
    }

    private void swapCaseSameParents(TreeNode current, TreeNode nodeToSwap) {
        if (Objects.nonNull(current.parent.right) && current.parent.right == current) {
            current.parent.right = nodeToSwap;
            current.parent.left = current;
        } else {
            current.parent.left = nodeToSwap;
            current.parent.right = current;
        }
    }

    private void swapCaseDifferentParents(TreeNode current, TreeNode nodeToSwap) {
        TreeNode currentParent = current.parent;
        TreeNode swapParent = nodeToSwap.parent;

        if (Objects.nonNull(currentParent.right) && currentParent.right == current) {
            currentParent.right = nodeToSwap;
        } else {
            currentParent.left = nodeToSwap;
        }

        if (Objects.nonNull(swapParent.right) && swapParent.right == nodeToSwap) {
            swapParent.right = current;
        } else {
            swapParent.left = current;
        }

        current.parent = swapParent;
        nodeToSwap.parent = currentParent;
    }

    private Predicate<TreeNode> getCurrentNodeFromList(TreeNode current) {
        return node -> node == current;
    }

}
