package tree;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tree.factory.NodeFactory;
import tree.impl.FKGTree;
import tree.model.TreeNode;
import tree.model.TreeNodeValue;
import tree.util.Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static tree.factory.TreeFactory.createSampleFKGTree;
import static tree.factory.TreeFactory.createSampleVitterTree;

public class OperationsTest {

    public static final int EXPECTED_TREE_ROOT_WEIGHT = 5;

    @Test
    public void shouldContainValue() {
        FKGTree comparableFKGTree =  createSampleFKGTree();

        TreeNodeValue value = new TreeNodeValue('b');

        boolean doesContain = comparableFKGTree.containsValue(value);

        Assertions.assertTrue(doesContain, "tree doesnt contain node with value: " + value);
    }

    @Test
    public void shouldNotContainValue() {
        FKGTree comparableFKGTree = createSampleFKGTree();

        TreeNodeValue value = new TreeNodeValue('y');

        boolean doesContain = comparableFKGTree.containsValue(value);

        Assertions.assertFalse(doesContain, "tree contains node with value: " + value);
    }

    @Test
    public void treeShouldHaveConsistentWeight() {
        Tree tree = createSampleFKGTree();
        int treeRootWeight = Trees.getTreeRootWeight(tree);
        int treeWeightSum = Trees.getTreeWeightSum(tree);

        Assertions.assertEquals(treeRootWeight, treeWeightSum);
    }

    @Test
    public void shouldDivideNytNode() {
        Tree tree = createSampleFKGTree();

        int nodeCountBefore = Trees.nodeCount(tree);

        tree.addValue(new TreeNodeValue('j'));

        int nodeCountAfter = Trees.nodeCount(tree);

        Assertions.assertEquals(nodeCountBefore + 2, nodeCountAfter);
    }

    @Test
    public void shouldAddItemAndBePresent() {
        Tree tree = createSampleFKGTree();
        TreeNodeValue value = new TreeNodeValue('j');

        tree.addValue(value);
        boolean doesContain = tree.containsValue(value);

        Assertions.assertTrue(doesContain);
    }

    @Test
    public void shouldGetTreeHeight() {
        Tree tree = createSampleFKGTree();

        int treeHeight = Trees.getTreeHeight(tree);

        Assertions.assertEquals(EXPECTED_TREE_ROOT_WEIGHT, treeHeight);
    }

    @Test
    public void shouldGetReverseLevelOrderedNodesList() {
        Tree tree = createSampleFKGTree();

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
    public void shouldBeProperFKGTree() {
        Tree tree = createSampleFKGTree();

        boolean hasNonIncreasingOrder = Trees.nodesMakesASequenceOfNonIncreasingWeights(tree);
        boolean allInternalNodesHas2Children = Trees.allInternalNodesHasTwoChildren(tree);
        boolean hasCorrectWeights = Trees.getTreeRootWeight(tree) == EXPECTED_TREE_ROOT_WEIGHT;

        Assertions.assertTrue(
                hasCorrectWeights
                        && allInternalNodesHas2Children
                        && hasNonIncreasingOrder);
    }

    @Test
    public void shouldBeProperVitterTree() {
        Tree tree = createSampleVitterTree();
        boolean isVitterTree = Trees.nodesMakesNonIncreasingNodeLeafSequences(tree);

        Assertions.assertTrue(isVitterTree);
    }

    @Test
    public void shouldBeNonIncreasingNodeLeafSequence() {
        List<TreeNode> nodes = new ArrayList<>(5);

        nodes.add(NodeFactory.createNodeWithWeightOf(new TreeNodeValue('a'), 3));
        nodes.add(NodeFactory.createNodeWithWeightOf(new TreeNodeValue('b'), 3));
        nodes.add(NodeFactory.createNodeWithWeightOf(new TreeNodeValue('c'), 3));
        nodes.add(NodeFactory.createInternalWithWeight(3));
        nodes.add(NodeFactory.createInternalWithWeight(3));

        boolean isNonIncreasingNodeLeafSequence = Trees.isNonIncreasingNodeLeafSequence(nodes);

        Assertions.assertTrue(isNonIncreasingNodeLeafSequence);
    }

    @Test
    public void shouldNotBeNonIncreasingNodeLeafSequence() {
        List<TreeNode> nodes = new ArrayList<>(5);

        nodes.add(NodeFactory.createNodeWithWeightOf(new TreeNodeValue('c'), 3));
        nodes.add(NodeFactory.createInternalWithWeight(3));
        nodes.add(NodeFactory.createNodeWithWeightOf(new TreeNodeValue('a'), 3));
        nodes.add(NodeFactory.createInternalWithWeight(3));
        nodes.add(NodeFactory.createNodeWithWeightOf(new TreeNodeValue('b'), 3));

        boolean isNonIncreasingNodeLeafSequence = Trees.isNonIncreasingNodeLeafSequence(nodes);

        Assertions.assertFalse(isNonIncreasingNodeLeafSequence);
    }

}
