package oop.project2;

public class SmallField implements IField{
    private TableVector2d position;
    private FieldContent content = null;
    private boolean isDisabled = false;

    public SmallField(TableVector2d position) {
        this.position = position;
    }

    public void placeSymbol(FieldContent symbol) {
        content = symbol;
        setToDisabled();
    }

    public FieldContent getContent() {
        return content;
    }

    public void setToDisabled() {
        isDisabled = true;
    }

    public void setToEnabled() {
        isDisabled = false;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public TableVector2d getPosition() {
        return position;
    }
}
