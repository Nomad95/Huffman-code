package tree;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tree.factory.NodeFactory;
import tree.impl.FKGTree;
import tree.util.Trees;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

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

    @Test
    public void shouldGetTreeHeight() {
        Tree tree = createSampleTree();

        int treeHeight = Trees.getTreeHeight(tree);

        Assertions.assertEquals(5, treeHeight);
    }

    @Test
    public void shouldGetReverseLevelOrderedNodesList() {
        Tree tree = createSampleTree();

        List<TreeNode> nodes = tree.getNodesReversedLevelOrder();

        List<String> valuesList = nodes.stream().map(TreeNode::printValue).collect(Collectors.toList());
        assertThat(valuesList, Matchers.contains(
                "NYT",
                "g",
                "1",
                "v",
                "b",
                "2",
                "a",
                "3",
                "5"
        ));
    }

    @Test
    public void shouldBeHuffmanTree() {
        Tree tree = createSampleTree();

        boolean isHuffmanTree = Trees.isHuffmanTree(tree);

        Assertions.assertEquals(true, isHuffmanTree);
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
