package tree.util;

import tree.Tree;
import tree.TreeNode;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

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

    public static boolean isHuffmanTree(Tree tree) {
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
}
