package oop.project2;

public class BigField extends AbstractMap implements IField {
    private final TableVector2d position;
    private FieldContent content = null;
    private boolean isDisabled = false;

    public BigField(TableVector2d position) {
        this.position = position;
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                fields.put(new TableVector2d(i, j), new SmallField(new TableVector2d(i, j)));
            }
        }
    }

    public void placeSymbol(FieldContent symbol) {
        content = symbol;
        setToDisabled();
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public FieldContent getContent() {
        return content;
    }

    public void setToEnabled() {
        isDisabled = false;
    }

    public void setToDisabled() {
        isDisabled = true;
    }

    public TableVector2d getPosition() {
        return position;
    }
}
