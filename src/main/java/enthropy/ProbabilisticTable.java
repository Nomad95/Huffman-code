package enthropy;

import tree.Tree;
import tree.model.TreeNode;
import tree.util.Trees;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProbabilisticTable {
    private Map<String, BigDecimal> probabilisticTable;

    public ProbabilisticTable(ProbabilisticTableCreator creator) {
        this.probabilisticTable = creator.getProbabilisticMap();
    }

    public BigDecimal getProbabilityFor(String value) {
        return probabilisticTable.get(value);
    }

    public static class ProbabilisticTableCreator {
        private Map<String, BigDecimal> probabilisticMap;
        private double totalWeight;

        public ProbabilisticTableCreator () {
            this.probabilisticMap = new HashMap<>();
        }

        private void setProbabilisticValueFor(String value, BigDecimal probability) {
            this.probabilisticMap.put(value, probability);
        }

        public ProbabilisticTableCreator createProbabilisticTable(Tree tree) {
            this.totalWeight = (double) Trees.getTreeRootWeight(tree);
            getProbabilityRecursive(tree.getRootNode());
            return this;
        }

        private void getProbabilityRecursive(TreeNode current) {
            if (Objects.isNull(current)) {
                return;
            }
            if (Objects.nonNull(current.value)) {
                double probability = ((double) current.weight ) / (totalWeight);
                setProbabilisticValueFor(current.value.printValue(), new BigDecimal(probability).setScale(4, RoundingMode.HALF_EVEN));
            }
            getProbabilityRecursive(current.left);
            getProbabilityRecursive(current.right);
        }

        private Map<String, BigDecimal> getProbabilisticMap() {
            return probabilisticMap;
        }

        public ProbabilisticTable create() {
            return new ProbabilisticTable(this);
        }

    }
}
