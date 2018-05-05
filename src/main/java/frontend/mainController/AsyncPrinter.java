package frontend.mainController;

import coding.encode.HuffmanEncoder;
import javafx.concurrent.Task;
import lombok.AllArgsConstructor;
import tree.Tree;
import treePrinter.BTreePrinter;
import treePrinter.PrintStrategy;

@AllArgsConstructor
public class AsyncPrinter<T> extends Task<T> {

    private HuffmanEncoder encoder;
    private PrintStrategy strategy;

    @Override
    protected T call() throws Exception {
        encoder.printTree(strategy);
        return null;
    }
}
