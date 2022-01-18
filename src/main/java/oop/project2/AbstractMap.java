package oop.project2;

import java.util.HashMap;
import java.util.LinkedHashMap;

abstract public class AbstractMap {
    protected final HashMap<TableVector2d, IField> fields = new LinkedHashMap<>();

    public boolean checkIfWon() {
        for (int i=0; i<3; i++) {
            IField col1 = fields.get(new TableVector2d(i, 0));
            IField col2 = fields.get(new TableVector2d(i, 1));
            IField col3 = fields.get(new TableVector2d(i, 2));

            if (col1.getContent() != null && col1.getContent() == col2.getContent() && col1.getContent() == col3.getContent()) {
                return true;
            }
        }

        for (int j=0; j<3; j++) {
            IField row1 = fields.get(new TableVector2d(0, j));
            IField row2 = fields.get(new TableVector2d(1, j));
            IField row3 = fields.get(new TableVector2d(2, j));

            if (row1.getContent() != null && row1.getContent() == row2.getContent() && row1.getContent() == row3.getContent()) {
                return true;
            }
        }

        IField rightDiagonal1 = fields.get(new TableVector2d(0, 0));
        IField rightDiagonal2 = fields.get(new TableVector2d(1, 1));
        IField rightDiagonal3 = fields.get(new TableVector2d(2, 2));

        if (rightDiagonal1.getContent() != null && rightDiagonal1.getContent() == rightDiagonal2.getContent() && rightDiagonal1.getContent() == rightDiagonal3.getContent()) {
            return true;
        }

        IField leftDiagonal1 = fields.get(new TableVector2d(0, 2));
        IField leftDiagonal2 = fields.get(new TableVector2d(1, 1));
        IField leftDiagonal3 = fields.get(new TableVector2d(2, 0));

        if (leftDiagonal1.getContent() != null && leftDiagonal1.getContent() == leftDiagonal2.getContent() && leftDiagonal1.getContent() == leftDiagonal3.getContent()) {
            return true;
        }

        return false;
    }

    public boolean checkIfComplete() {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (fields.get(new TableVector2d(i, j)).getContent() == null) {
                    return false;
                }
            }
        }

        return true;
    }

    public void setAllToDisabled() {
        for (IField field : fields.values()) {
            field.setToDisabled();
        }
    }

    public void setAllToEnabled() {
        for (IField field : fields.values()) {
            if (field.getContent() == null) {
                field.setToEnabled();
            }
        }
    }

    public HashMap<TableVector2d, IField> getFields() {
        return fields;
    }
}
