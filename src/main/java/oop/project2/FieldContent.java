package oop.project2;

public enum FieldContent {
    CROSS,
    CIRCLE;

    public FieldContent next() {
        return switch(this) {
            case CROSS -> CIRCLE;
            case CIRCLE -> CROSS;
        };
    }
}
