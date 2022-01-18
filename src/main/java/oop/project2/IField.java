package oop.project2;

public interface IField {
    FieldContent getContent();

    void placeSymbol(FieldContent Symbol);

    void setToDisabled();

    void setToEnabled();

    boolean isDisabled();

    TableVector2d getPosition();
}
