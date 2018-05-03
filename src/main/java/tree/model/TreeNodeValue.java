package tree.model;

import lombok.Getter;
import tree.Codeable;

public class TreeNodeValue implements Codeable {

    @Getter
    char letter;

    private String byteRepresentation;

    public TreeNodeValue(char letter) {
        this.letter = letter;
    }

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
        return Character.toString(letter);
    }

    @Override
    public void setEncodedValue(String valueRepresentation) {
        this.byteRepresentation = valueRepresentation;
    }

    @Override
    public String getEncodedValue() {
        return this.byteRepresentation;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof TreeNodeValue && ((TreeNodeValue) obj).letter == this.letter;
    }
}
