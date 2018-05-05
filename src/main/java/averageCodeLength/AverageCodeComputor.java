package averageCodeLength;

import com.google.common.math.DoubleMath;
import tree.Tree;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

public class AverageCodeComputor {

    public static BigDecimal computeAverageLength(Tree tree, String text) {
        AverageCodeLengthTable averageCodeLengthTable = new AverageCodeLengthTable.AverageCodeLengthTableCreator()
                .createAverageCodeLengthTable(tree)
                .create();

//        AtomicReference<BigDecimal> result = new AtomicReference<>(new BigDecimal("0"));
//        averageCodeLengthTable.getAverageLengthTable()
//                .forEach((letter, avgLength) -> {
//                    System.out.println("avgLength " + letter + " " + avgLength);
//                    result.set(result.get().add(avgLength));
//                });

        BigDecimal result = new BigDecimal("0");
        for (char c : text.toCharArray()) {
            double avgLengthOfChar = averageCodeLengthTable.getLengthFor(Character.toString(c)).doubleValue();
            result = result.add(new BigDecimal(avgLengthOfChar));
        }

        return result;
    }
}
