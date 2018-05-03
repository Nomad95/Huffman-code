package coding;

import tree.Tree;
import tree.model.TreeNode;

import java.util.*;

public class CodeTable {
    private Map<String, String> codeMap;

    public CodeTable() {
        this.codeMap = new HashMap<>();
    }

    private CodeTable(CodeTableCreator creator) {
        this.codeMap = creator.getCodeMap();
    }

    public void setEncodedValueFor(String value, String encodedValue) {
        this.codeMap.put(value, encodedValue);
    }

    public String getEncodedValueFor(String value) {
        return this.codeMap.get(value);
    }

    public static class CodeTableCreator {
        private Map<String, String> codeMap;

        public CodeTableCreator() {
            this.codeMap = new HashMap<>();
        }

        private void setEncodedValueFor(String value, String encodedValue) {
            this.codeMap.put(value, encodedValue);
        }

        public CodeTableCreator createCodeTable(Tree tree) {
            getCodeRecursive(tree.getRootNode());
            return this;
        }

        private void getCodeRecursive(TreeNode current) {
            if (Objects.isNull(current)) {
                return;
            }
            if (Objects.nonNull(current.value)) {
                setEncodedValueFor(current.value.printValue(), current.value.getEncodedValue());
            }
            getCodeRecursive(current.left);
            getCodeRecursive(current.right);
        }

        private Map<String, String> getCodeMap() {
            return codeMap;
        }

        public CodeTable create() {
            return new CodeTable(this);
        }

    }
}
