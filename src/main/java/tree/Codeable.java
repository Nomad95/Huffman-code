package tree;

public interface Codeable extends Comparable {
    String printValue();

    void setEncodedValue(String valueRepresentation);

    String getEncodedValue();
}
