package oop.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import oop.project2.BigField;
import oop.project2.FieldContent;
import oop.project2.IField;
import oop.project2.TableVector2d;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InnerGrid {
    private final GridPane grid;
    private final OuterGrid parentGrid;

    public InnerGrid(OuterGrid parentGrid) {
        this.parentGrid = parentGrid;
        grid = new GridPane();

        for (int i=0; i<3; i++) {
            grid.getRowConstraints().add(new RowConstraints(80));
        }

        for (int i=0; i<3; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(80));
        }
    }

    public void makeInnerGrid(BigField field) {
        grid.getChildren().clear();

        for (int i=0 ;i<3; i++) {
            for (int j=0; j<3; j++) {
                StackPane pane = new StackPane();
                pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                IField smallField = field.getFields().get(new TableVector2d(i, j));

                if (!field.isDisabled() && !smallField.isDisabled()) {
                    Rectangle background = new Rectangle();
                    background.setWidth(76);
                    background.setHeight(76);
                    background.setFill(Color.rgb(153, 204, 255));
                    pane.getChildren().add(background);
                }

                if (smallField.getContent() != null) {
                    String path;
                    if (smallField.getContent() == FieldContent.CIRCLE) {
                        path = "src\\main\\resources\\circle_image.png";
                    }
                    else {
                        path = "src\\main\\resources\\cross_image.png";
                    }

                    Image image = null;
                    try {
                        image = new Image(new FileInputStream(path));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(75);
                    imageView.setFitHeight(75);
                    pane.getChildren().add(imageView);
                }

                int row = i;
                int column = j;
                pane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    if (!field.isDisabled() && !smallField.isDisabled()) {
                        parentGrid.innerGridClick(field, smallField);
                    }

                    parentGrid.makeGrid();
                });

                grid.add(pane, row, column);
            }
        }
    }

    public GridPane getGrid() {
        return grid;
    }
}
