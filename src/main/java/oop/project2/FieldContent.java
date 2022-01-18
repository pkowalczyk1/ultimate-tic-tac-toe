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

    public String toString() {
        return switch(this) {
            case CROSS -> "Cross";
            case CIRCLE -> "Circle";
        };
    }
}
