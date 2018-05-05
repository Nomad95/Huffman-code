package enthropy;

import com.google.common.math.DoubleMath;
import tree.Tree;

import java.math.BigDecimal;

public class EntropyComputor {

    public static BigDecimal computeEntropy(Tree tree, String text) {
        ProbabilisticTable probabilisticTable = new ProbabilisticTable.ProbabilisticTableCreator()
                .createProbabilisticTable(tree)
                .create();
        BigDecimal result = new BigDecimal("0");
        for (char c : text.toCharArray()) {
            double probabilityOfChar = probabilisticTable.getProbabilityFor(Character.toString(c)).doubleValue();
            if (probabilityOfChar == 0) {
                continue;
            }
            double sumElement = probabilityOfChar * DoubleMath.log2(1.0 / probabilityOfChar);
            result = result.add(new BigDecimal(sumElement));
        }

        return result;
    }
}
