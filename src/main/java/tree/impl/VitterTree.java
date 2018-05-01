package tree.impl;

import tree.Tree;
import tree.TreeNode;
import tree.TreeNodeValue;
import tree.TreeOperations;
import tree.factory.NodeFactory;
import tree.util.NodeSwapper;
import tree.util.Trees;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static tree.util.Trees.getSublistOfWeight;

public class VitterTree extends Tree<TreeNode, TreeNodeValue> {

    public VitterTree() {
        TreeNode<TreeOperations> rootNode = NodeFactory.createNytNode();
        this.setRootNode(rootNode);
    }

    @Override
    protected void updateTree(TreeNode current) {
        addWeightTillRootPath(current);
        updateRecursive(current);
    }

    private void updateRecursive(TreeNode current) {
        if (Objects.isNull(current.parent)) {
            return;
        }

        List<TreeNode> nodes = getNodesReversedLevelOrder();
        Set<List<TreeNode>> sublistsByWeight = sliceNodesByWeight(nodes);
        sublistsByWeight.forEach(this::sortToNonIncreasingNodeLeafSequence);

        updateRecursive(current.parent);
    }

    private void sortToNonIncreasingNodeLeafSequence(List<TreeNode> sublist) {
        if (sublist.size() == 1) {
            return;
        }
        while (!Trees.isNonIncreasingNodeLeafSequence(sublist)) {
            ListIterator<TreeNode> sublistIterator = sublist.listIterator();
            TreeNode previous = sublistIterator.next();
            while (sublistIterator.hasNext()) {
                TreeNode next = sublistIterator.next();
                if (previous.isInternalNode() && !next.isInternalNode()) {
                    NodeSwapper.swapNodes(previous, next);
                    return;
                }
                previous = next;
            }
        }
    }

    private Set<List<TreeNode>> sliceNodesByWeight(List<TreeNode> nodes) {
        int[] weights = nodes.stream().mapToInt(node -> node.weight).toArray();
        return IntStream.of(weights)
                .mapToObj(weight -> getSublistOfWeight(nodes, weight))
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingInt(Trees::getListWeightValue))));
    }

}
