package tree.util;

import tree.Tree;
import tree.TreeNode;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings({"all"})
public final class Trees {

    public static int getTreeRootWeight(Tree tree) {
        return tree.getRootNode().weight;
    }

    public static int getTreeWeightSum(Tree tree) {
        return calculateSum(tree.getRootNode());
    }

    private static int calculateSum(TreeNode node) {
        if (Objects.isNull(node)) {
            return 0;
        }
        int internalSum = 0;
        internalSum += calculateSum(node.left);
        internalSum += calculateSum(node.right);
        return internalSum == 0 ? node.weight : internalSum;
    }

    public static int nodeCount(Tree tree) {
        return countInternalNodes(tree.getRootNode());
    }

    private static int countInternalNodes(TreeNode node) {
        if (Objects.isNull(node)) {
            return 0;
        }
        int internalSum = 1;
        internalSum += countInternalNodes(node.left);
        internalSum += countInternalNodes(node.right);
        return internalSum;
    }

    public static int getTreeHeight(Tree tree) {
        return getNodeHeight(tree.getRootNode());
    }

    private static int getNodeHeight(TreeNode node) {
        if (Objects.isNull(node)) {
            return 0;
        }
        int leftHeight = getNodeHeight(node.left);
        int rightHeight = getNodeHeight(node.right);

        if (leftHeight > rightHeight) {
            return leftHeight + 1;
        } else {
            return rightHeight + 1;
        }
    }

    public static boolean nodesMakesASequenceOfNonIncreasingWeights(Tree tree) {
        List<TreeNode> nodes = tree.getNodesReversedLevelOrder();
        ListIterator<TreeNode> iterator = nodes.listIterator();
        TreeNode current = iterator.next();
        while (iterator.hasNext()) {
            TreeNode next = iterator.next();
            if (current.weight > next.weight) {
                return false;
            }
            current = next;
        }

        return true;
    }

    public static boolean allInternalNodesHasTwoChildren(Tree tree) {
        return hasTwoChildrenOrIsALeaf(tree.getRootNode());
    }

    private static boolean hasTwoChildrenOrIsALeaf(TreeNode node) {
        if (Objects.isNull(node) || node.isNyt()) {
            return true;
        }
        if (node.isInternalNode())
        if (Objects.isNull(node.left) || Objects.isNull(node.right)) {
            return false;
        }
        boolean leftValue = hasTwoChildrenOrIsALeaf(node.left);
        boolean rightValue = hasTwoChildrenOrIsALeaf(node.right);

        return leftValue || rightValue;
    }

    public static boolean nodesMakesNonIncreasingNodeLeafSequences(Tree tree) {
        List<TreeNode> nodes = tree.getNodesReversedLevelOrder();

        int[] weights = nodes.stream().mapToInt(node -> node.weight).toArray();
        Set<List<TreeNode>> sublistsByWeight = IntStream.of(weights)
                .mapToObj(weight -> getSublistOfWeight(nodes, weight))
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingInt(Trees::getListWeightValue))));

        AtomicBoolean finalResult = new AtomicBoolean(true);
        sublistsByWeight.forEach(sublist -> {
            if (sublist.size() == 1) {
                return;
            }
            ListIterator<TreeNode> sublistIterator = sublist.listIterator();
            TreeNode current = sublistIterator.next();
            while (sublistIterator.hasNext()) {
                TreeNode next = sublistIterator.next();
                if (current.isInternalNode() && !next.isInternalNode()) {
                    finalResult.set(false);
                    return;
                }
            }

        });

        return finalResult.get();
    }

    public static List<TreeNode> getSublistOfWeight(List<TreeNode> list, int weight) {
        return list.stream().filter(node -> node.weight == weight).collect(Collectors.toList());
    }

    public static int getListWeightValue(List<TreeNode> list) {
        return list.stream().map(node -> node.weight).findFirst().orElse(-1);
    }

    //todo: generify this
    public static boolean isNonIncreasingNodeLeafSequence(List<TreeNode> list) {
        if (list.size() == 1) {
            return true;
        }
        ListIterator<TreeNode> sublistIterator = list.listIterator();
        TreeNode current = sublistIterator.next();
        while (sublistIterator.hasNext()) {
            TreeNode next = sublistIterator.next();
            if (current.isInternalNode() && !next.isInternalNode()) {
                return false;
            }
            current = next;
        }

        return true;
    }

}
