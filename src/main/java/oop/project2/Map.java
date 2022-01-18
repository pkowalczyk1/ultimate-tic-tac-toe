package oop.project2;

public class Map extends AbstractMap {
    private FieldContent currentSymbol;

    public Map(FieldContent currentSymbol) {
        this.currentSymbol = currentSymbol;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields.put(new TableVector2d(i, j), new BigField(new TableVector2d(i, j)));
            }
        }
    }

    public void nextCurrentSymbol() {
        currentSymbol = currentSymbol.next();
    }

    public FieldContent getCurrentSymbol() {
        return currentSymbol;
    }

    public FieldContent clicked(BigField field, TableVector2d position) {
        field.getFields().get(position).placeSymbol(currentSymbol);
        FieldContent fieldWinner = field.checkIfWon();
        boolean complete = field.checkIfComplete();

        if (fieldWinner != null) {
            field.placeSymbol(fieldWinner);
            FieldContent winner = checkIfWon();
            if (winner != null) {
                setAllToDisabled();
                return winner;
            }
        }

        if (complete) {
            field.setToDisabled();
        }

        setAllToDisabled();

        if (fields.get(position).getContent() != null) {
            setAllToEnabled();
        }
        else {
            fields.get(position).setToEnabled();
        }

        nextCurrentSymbol();

        return null;
    }

    @Override
    public boolean checkIfComplete() {
        if (super.checkIfComplete()) {
            return true;
        }

        for (IField field : fields.values()) {
            if (!field.isComplete()) {
                return false;
            }
        }

        return true;
    }
}
