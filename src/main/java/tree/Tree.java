package tree;

import lombok.Getter;
import lombok.Setter;
import tree.factory.NodeFactory;
import tree.util.FulfilledRecursionGoal;
import tree.util.Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unchecked")
@Getter
@Setter
public abstract class Tree<T extends TreeNode, V extends TreeOperations> {

    protected T rootNode;

    public void addValue(V value) {
        try {
            if (containsValueRecursive(getRootNode(), value)) {
                addRecursive(getRootNode(), value);
            } else {
                divideNytAndAddValue(getRootNode(), value);
            }
        } catch (FulfilledRecursionGoal ignored) {}
    }

    public void addRecursive(TreeNode current, V value) {
        if (Objects.nonNull(current)){
            if (Objects.nonNull(current.value) && value.compareTo(current.value) == 0) {
                updateTree(current);
                return;
            }
            addRecursive(current.left, value);
            addRecursive(current.right, value);
        }
    }

    public void divideNytAndAddValue(TreeNode current, V value) throws FulfilledRecursionGoal {
        if (Objects.nonNull(current)) {
            if (current.isNyt()) {
                TreeNode newNode = divideNyt(current, value);
                updateTree(newNode);
                throw new FulfilledRecursionGoal();
            }
            divideNytAndAddValue(current.left, value);
            divideNytAndAddValue(current.right, value);
        }
    }

    private TreeNode divideNyt(TreeNode current, V value) {
        current.nyt = false;

        current.left = NodeFactory.createNytNode();
        current.left.parent = current;

        current.right = NodeFactory.createNodeOf(value);
        current.right.parent = current;

        return current.right;
    }

    public boolean containsValue(V value) {
        return containsValueRecursive(getRootNode(), value);
    }

    private boolean containsValueRecursive(TreeNode current, V value) {
        if (Objects.isNull(current)) {
            return false;
        }
        if (Objects.nonNull(current.value) && value.compareTo(current.value) == 0) {
            return true;
        }

        return containsValueRecursive(current.left, value) || containsValueRecursive(current.right, value);
    }

    protected void addWeightTillRootPath(TreeNode current) {
        current.weight++;
        if (Objects.nonNull(current.parent)) {
            addWeightTillRootPath(current.parent);
        }
    }

    public List<TreeNode> getNodesReversedLevelOrder() {
        List<TreeNode> orderedNodes = new ArrayList<>();
        int treeHeight = Trees.getTreeHeight(this);
        for (int i = treeHeight; i >= 1; i--) {
            addNode(orderedNodes, getRootNode(), i);
        }

        return orderedNodes;
    }

    private void addNode(List<TreeNode> nodesList, TreeNode node, int level) {
        if (Objects.isNull(node)) {
            return;
        }
        if (level == 1) {
            nodesList.add(node);
        } else if (level > 1) {
            addNode(nodesList, node.left, level - 1);
            addNode(nodesList, node.right, level - 1);
        }
    }

    abstract protected void updateTree(TreeNode current);
}
