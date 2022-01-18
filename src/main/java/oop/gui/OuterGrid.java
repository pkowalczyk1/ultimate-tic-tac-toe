package oop.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import oop.project2.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class OuterGrid {
    private final GridPane grid;
    private final VBox XMoves;
    private final VBox OMoves;
    private final HBox wrapper;
    private final Map map;

    public final Timeline timer;

    private final HashMap<TableVector2d, InnerGrid> innerGrids = new LinkedHashMap<>();

    public OuterGrid(Map map) {
        this.map = map;
        timer = new Timeline(new KeyFrame(Duration.seconds(20),
                event -> map.nextCurrentSymbol()));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        grid = new GridPane();

        XMoves = new VBox(10);
        XMoves.setAlignment(Pos.CENTER);
        OMoves = new VBox(10);
        OMoves.setAlignment(Pos.CENTER);
        XMoves.getChildren().add(new Label("Cross"));
        OMoves.getChildren().add(new Label("Circle"));

        ScrollPane XMovesList = new ScrollPane();
        ScrollPane OMovesList = new ScrollPane();
        XMovesList.setContent(XMoves);
        OMovesList.setContent(OMoves);

        wrapper = new HBox(20, grid, XMovesList, OMovesList);
        wrapper.setAlignment(Pos.TOP_CENTER);

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                innerGrids.put(new TableVector2d(i, j), new InnerGrid(this));
            }
        }

        makeGrid();
    }

    public void makeGrid() {
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.getChildren().clear();

        for (int i=0; i<3; i++) {
            grid.getRowConstraints().add(new RowConstraints(240));
        }

        for (int i=0; i<3; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(240));
        }

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                StackPane pane = new StackPane();
                InnerGrid innerGrid = innerGrids.get(new TableVector2d(i, j));
                pane.getChildren().add(innerGrid.getGrid());
                IField field = map.getFields().get(new TableVector2d(i, j));

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
                    imageView.setFitHeight(200);
                    imageView.setFitWidth(200);
                    pane.getChildren().add(imageView);
                }

                innerGrid.makeInnerGrid((BigField) field);

                pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY, new BorderWidths(2, 2, 2, 2))));
                grid.add(pane, i, j);
            }
        }
    }

    public Map getMap() {
        return map;
    }

    public HBox getWrapper() {
        return wrapper;
    }

    public void addMove(TableVector2d bigFieldPosition, TableVector2d smallFieldPosition, FieldContent symbol) {
        int row = smallFieldPosition.getRow() + bigFieldPosition.getRow() * 3;
        int column = smallFieldPosition.getColumn() + bigFieldPosition.getColumn() * 3;
        String move = "(" + row + ", " + column + ")";
        Label newMove = new Label(move);

        if (symbol == FieldContent.CIRCLE) {
            OMoves.getChildren().add(newMove);
        }
        else {
            XMoves.getChildren().add(newMove);
        }
    }

    public void innerGridClick(BigField bigField, IField smallField) {
        map.clicked(bigField, smallField.getPosition());
        addMove(bigField.getPosition(), smallField.getPosition(), smallField.getContent());
        timer.stop();
        timer.playFromStart();
    }
}
