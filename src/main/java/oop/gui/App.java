package oop.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import oop.project2.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class App extends Application {
    private final int bigFieldRow = 3;
    private final int bigFieldColumn = 3;
    private final int smallFieldRow = 3;
    private final int smallFieldColumn = 3;
    private GridPane grid = new GridPane();
    private Map map;

    public void start(Stage primaryStage) {
        map = new Map(FieldContent.CROSS);
        makeGrid();
        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    private void makeGrid() {
        grid.setGridLinesVisible(false);

        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.getChildren().clear();

        grid.setGridLinesVisible(true);

        for (int i=0; i<bigFieldRow; i++) {
            grid.getRowConstraints().add(new RowConstraints(90));
        }

        for (int i=0; i<bigFieldColumn; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(90));
        }

        for (int i=0; i<bigFieldRow; i++) {
            for (int j=0; j<bigFieldColumn; j++) {
                StackPane pane = new StackPane();
                GridPane innerGrid = new GridPane();
                pane.getChildren().add(innerGrid);
                BigField field = map.getFields().get(new TableVector2d(i, j));
                if (field.getContent() != null) {
                    String path;
                    if (field.getContent() == FieldContent.CIRCLE) {
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
                    imageView.setFitHeight(80);
                    imageView.setFitWidth(80);
                    pane.getChildren().add(imageView);
                }

                innerGrid.setGridLinesVisible(true);
                makeInnerGrid(innerGrid, field);

                for (int k=0; k<smallFieldRow; k++) {
                    innerGrid.getRowConstraints().add(new RowConstraints(30));
                }

                for (int k=0; k<smallFieldColumn; k++) {
                    innerGrid.getColumnConstraints().add(new ColumnConstraints(30));
                }

                grid.add(pane, i, j);
            }
        }
    }

    private void makeInnerGrid(GridPane grid, BigField field) {
        for (int i=0 ;i<3; i++) {
            for (int j=0; j<3; j++) {
                StackPane pane = new StackPane();
                SmallField smallField = field.getSmallFields().get(new TableVector2d(i, j));

                if (!field.isDisabled() && smallField.canClick()) {
                    Rectangle background = new Rectangle();
                    background.setWidth(28);
                    background.setHeight(28);
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
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    pane.getChildren().add(imageView);
                }

                int row = i;
                int column = j;
                pane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    boolean isWon = false;
                    if (!field.isDisabled() && smallField.canClick()) {
                        isWon = map.clicked(field, new TableVector2d(row, column));
                    }

                    makeGrid();
                });

                grid.add(pane, row, column);
            }
        }
    }
}
