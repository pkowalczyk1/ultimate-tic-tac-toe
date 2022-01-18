package oop.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.project2.*;

public class App extends Application {
    public void start(Stage primaryStage) {
        Map map = new Map(FieldContent.CROSS);
        OuterGrid grid = new OuterGrid(map);
        Scene scene = new Scene(grid.getWrapper(), 500, 500);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
