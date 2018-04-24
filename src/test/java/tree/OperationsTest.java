package tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tree.factory.NodeFactory;
import tree.impl.FKGTree;
import tree.util.Trees;

public class OperationsTest {

    @Test
    public void shouldContainValue() {
        FKGTree comparableFKGTree = (FKGTree) createSampleTree();

        TreeNodeValue value = new TreeNodeValue('b');

        boolean doesContain = comparableFKGTree.containsValue(value);

        Assertions.assertEquals(true, doesContain, "tree doesnt contain node with value: " + value);
    }

    @Test
    public void shouldNotContainValue() {
        FKGTree comparableFKGTree = (FKGTree) createSampleTree();

        TreeNodeValue value = new TreeNodeValue('y');

        boolean doesContain = comparableFKGTree.containsValue(value);

        Assertions.assertEquals(false, doesContain, "tree contains node with value: " + value);
    }

    @Test
    public void treeShouldHaveConsistentWeight() {
        Tree tree = createSampleTree();
        int treeRootWeight = Trees.getTreeRootWeight(tree);
        int treeWeightSum = Trees.getTreeWeightSum(tree);

        Assertions.assertEquals(treeRootWeight, treeWeightSum);
    }

    @Test
    public void shouldDivideNytNode() {
        Tree tree = createSampleTree();

        int nodeCountBefore = Trees.nodeCount(tree);

        tree.addValue(NodeFactory.createNodeOf(new TreeNodeValue('j')));

        int nodeCountAfter = Trees.nodeCount(tree);

        Assertions.assertEquals(nodeCountBefore + 2, nodeCountAfter);
    }

    @Test
    public void shouldAddItemAndBePresent() {
        Tree tree = createSampleTree();
        TreeNode<TreeNodeValue> value = NodeFactory.createNodeOf(new TreeNodeValue('j'));

        tree.addValue(value);
        boolean doesContain = tree.containsValue(value);

        Assertions.assertEquals(true, doesContain);
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
