package tree.util;

import tree.model.TreeNode;

import java.util.Objects;

public class NodeSwapper {

    public static void swapNodes(TreeNode current, TreeNode nodeToSwap) {
        if (nodesAreNotRoot(current, nodeToSwap)) {
            if (current.parent == nodeToSwap.parent) {
                swapCaseSameParents(current, nodeToSwap);
            } else {
                swapCaseDifferentParents(current, nodeToSwap);
            }
        }
    }

    private static boolean nodesAreNotRoot(TreeNode current, TreeNode nodeToSwap) {
        return Objects.nonNull(nodeToSwap.parent) && Objects.nonNull(current.parent);
    }

    private static void swapCaseSameParents(TreeNode current, TreeNode nodeToSwap) {
        if (Objects.nonNull(current.parent.right) && current.parent.right == current) {
            current.parent.right = nodeToSwap;
            current.parent.left = current;
        } else {
            current.parent.left = nodeToSwap;
            current.parent.right = current;
        }
    }

    private static void swapCaseDifferentParents(TreeNode current, TreeNode nodeToSwap) {
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
}
