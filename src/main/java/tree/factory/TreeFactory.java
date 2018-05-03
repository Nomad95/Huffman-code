package tree.factory;

import tree.model.TreeNodeValue;
import tree.impl.FKGTree;
import tree.impl.VitterTree;

public final class TreeFactory {

    public static FKGTree createSampleFKGTree() {
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

    public static VitterTree createSampleVitterTree() {
        TreeNodeValue value1 = new TreeNodeValue('a');
        TreeNodeValue value2 = new TreeNodeValue('b');
        TreeNodeValue value3 = new TreeNodeValue('a');
        TreeNodeValue value4 = new TreeNodeValue('v');
        TreeNodeValue value5 = new TreeNodeValue('g');

        VitterTree comparableVitterTree = new VitterTree();

        comparableVitterTree.addValue(value1);
        comparableVitterTree.addValue(value2);
        comparableVitterTree.addValue(value3);
        comparableVitterTree.addValue(value4);
        comparableVitterTree.addValue(value5);

        return comparableVitterTree;
    }
}
