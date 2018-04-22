package tree;

import lombok.Getter;
import lombok.Setter;
import tree.factory.NodeFactory;

import java.util.Objects;

@SuppressWarnings("unchecked")
@Getter
@Setter
public abstract class Tree<T extends TreeNode, V extends TreeOperations> {

    public static int NYT_COMPARE_VALUE = 2; //todo: maybe not needed

    protected T rootNode;
    
    public void addValue(V value) {
        if (containsValueRecursive(getRootNode(), value)) {
            addRecursive(getRootNode(), value);
        } else {
            divideNytAndAddValue(getRootNode(), value);
        }
    }

    public void addRecursive(TreeNode current, V value) {
        if (Objects.nonNull(current)){
            if (Objects.nonNull(current.value) && value.compareTo(current.value) == 0) {
                addWeightTillRootPath(current);
                return;
            }
            addRecursive(current.left, value);
            addRecursive(current.right, value);
        }
    }

    public void divideNytAndAddValue(TreeNode current, V value) {
        if (Objects.nonNull(current)) {
            if (current.isNyt()) {
                divideNyt(current, value);
                addWeightTillRootPath(current.right);
                return;
            }
            divideNytAndAddValue(current.left, value);
            divideNytAndAddValue(current.right, value);
        }
    }

    private void divideNyt(TreeNode current, V value) {
        current.nyt = false;

        current.left = NodeFactory.createNytNode();
        current.left.parent = current;

        current.right = NodeFactory.createNodeOf(value);
        current.right.parent = current;
    }

    public boolean containsValueRecursive(TreeNode current, V value) {
        if (Objects.isNull(current)) {
            return false;
        }
        if (Objects.nonNull(current.value) && value.compareTo(current.value) == 0) {
            return true;
        }

        return containsValueRecursive(current.left, value) || containsValueRecursive(current.right, value);
    }

    private void addWeightTillRootPath(TreeNode current) {
        current.weight++;
        if (Objects.nonNull(current.parent)) {
            addWeightTillRootPath(current.parent);
        }
    }
}
