package tree;

public class TreeNode<T extends TreeOperations> implements TreeOperations{

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

    @Override
    public int compareTo(Object o) {
        if (o instanceof TreeNode) {
            TreeNode node = (TreeNode) o;
            if (node.isNyt())
                return Tree.NYT_COMPARE_VALUE;
            return value.compareTo(node.value);
        }
        return 1;
    }

    @Override
    public String printValue() {
        if (this.isNyt()) {
            return "NYT";
        }
        return value.printValue();
    }
}
