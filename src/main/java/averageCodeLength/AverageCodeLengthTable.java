package averageCodeLength;

import lombok.Getter;
import tree.Tree;
import tree.model.TreeNode;
import tree.util.Trees;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AverageCodeLengthTable {
    @Getter
    private Map<String, BigDecimal> averageLengthTable;

    public AverageCodeLengthTable(AverageCodeLengthTableCreator creator) {
        this.averageLengthTable = creator.getProbabilisticMap();
    }

    public BigDecimal getLengthFor(String value) {
        return averageLengthTable.get(value);
    }

    public static class AverageCodeLengthTableCreator {
        private Map<String, BigDecimal> averageLengthMap;
        private double totalWeight;

        public AverageCodeLengthTableCreator () {
            this.averageLengthMap = new HashMap<>();
        }

        private void setLengthFor(String value, BigDecimal length) {
            this.averageLengthMap.put(value, length);
        }

        public AverageCodeLengthTableCreator createAverageCodeLengthTable(Tree tree) {
            this.totalWeight = (double) Trees.getTreeRootWeight(tree);
            getLengthRecursive(tree.getRootNode());
            return this;
        }

        private void getLengthRecursive(TreeNode current) {
            if (Objects.isNull(current)) {
                return;
            }
            if (Objects.nonNull(current.value)) {
                double probability = ((double) current.weight ) / (totalWeight);
                double lengthAverage = probability * current.printValue().length();
                setLengthFor(current.value.printValue(), new BigDecimal(lengthAverage).setScale(4, RoundingMode.HALF_EVEN));
            }
            getLengthRecursive(current.left);
            getLengthRecursive(current.right);
        }

        private Map<String, BigDecimal> getProbabilisticMap() {
            return averageLengthMap;
        }

        public AverageCodeLengthTable create() {
            return new AverageCodeLengthTable(this);
        }

    }
}
