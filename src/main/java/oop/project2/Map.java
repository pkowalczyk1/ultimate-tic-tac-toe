package oop.project2;

import java.util.HashMap;
import java.util.LinkedHashMap;

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

    public boolean clicked(BigField field, TableVector2d position) {
        field.getFields().get(position).placeSymbol(currentSymbol);
        if (field.checkIfWon() || field.checkIfComplete()) {
            field.setAllToDisabled();
        }

        setAllToDisabled();

        if (fields.get(position).isDisabled()) {
            setAllToEnabled();
        }
        else {
            fields.get(position).setToEnabled();
        }

        nextCurrentSymbol();

        return checkIfWon();
    }
}
