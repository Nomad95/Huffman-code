package tree.model;

import tree.Codeable;
import tree.TreeOperations;

import java.util.Objects;

public class TreeNode<T extends Codeable> implements TreeOperations {

    public T value;
    public TreeNode<T> left;
    public TreeNode<T> right;
    public TreeNode<T> parent;
    public boolean nyt;
    public int weight;

    public TreeNode(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.weight = 0;
    }

    public boolean isNyt() {
        return nyt;
    }

    public boolean isInternalNode() {
        return !isNyt() && Objects.isNull(value);

    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof TreeNode) {
            TreeNode node = (TreeNode) o;
            return value.compareTo(node.value);
        }
        return 1;
    }

    @Override
    public String printValue() {
        if (this.isNyt()) {
            return "NYT";
        }
        if (Objects.isNull(value)) {
            return String.valueOf(weight);
        }

        return value.printValue();
    }
}
