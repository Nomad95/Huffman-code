package tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tree.impl.FKGTree;

public class OperationsTest {

    @Test
    public void shouldContainLetter() {
        FKGTree comparableFKGTree = (FKGTree) createSampleTree();

        TreeNodeValue value = new TreeNodeValue('b');
        TreeNode<TreeNodeValue> node1 = new TreeNode<>(value);

        boolean doesContain = comparableFKGTree.containsValueRecursive(comparableFKGTree.getRootNode(), node1.value);
        Assertions.assertEquals(true, doesContain, "tree doesnt contain node with value: " + node1.printValue());
    }

    @Test
    public void shouldNotContainLetter() {
        FKGTree comparableFKGTree = (FKGTree) createSampleTree();

        TreeNodeValue value2 = new TreeNodeValue('y');
        TreeNode<TreeNodeValue> node2 = new TreeNode<>(value2);

        boolean doesContain = comparableFKGTree.containsValueRecursive(comparableFKGTree.getRootNode(), node2.value);
        Assertions.assertEquals(false, doesContain, "tree contains node with value: " + node2.printValue());
    }

    public static Tree createSampleTree() {
        TreeNodeValue value1 = new TreeNodeValue('a');
        TreeNodeValue value2 = new TreeNodeValue('b');
        TreeNodeValue value3 = new TreeNodeValue('a');
        TreeNodeValue value4 = new TreeNodeValue('v');
        TreeNodeValue value5 = new TreeNodeValue('g');

        FKGTree comparableFKGTree = new FKGTree();

        comparableFKGTree.addValue(value1);
        comparableFKGTree.addValue(value2);
        comparableFKGTree.addValue(value3);
        comparableFKGTree.addValue(value4);
        comparableFKGTree.addValue(value5);

        return comparableFKGTree;
    }
}
