package tree;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TreeNodeValue implements TreeOperations {

    char letter;

    @Override
    public int compareTo(Object o) {
        if (letter < ((TreeNodeValue) o).letter)
            return -1;
        else if (letter > ((TreeNodeValue) o).letter)
            return 1;
        return 0;
    }

    @Override
    public String printValue() {
        return "" + letter;
    }
}