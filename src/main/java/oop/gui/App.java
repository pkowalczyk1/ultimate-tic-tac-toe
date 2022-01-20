package oop.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import oop.project2.*;

import java.util.Objects;

public class App extends Application {
    public void start(Stage primaryStage) throws IllegalArgumentException {
        VBox main = new VBox(8);
        main.setAlignment(Pos.CENTER);
        Label title = new Label("Ultimate TicTacToe");
        title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 20));
        main.getChildren().add(title);

        Menu menu = new Menu();
        main.getChildren().add(menu.getMenu());

        menu.getSubmit().setOnAction(event -> {
            try {
                Map map = new Map(FieldContent.CROSS);
                int timer = menu.getTimerValue();
                OuterGrid grid = new OuterGrid(map, timer);
                main.getChildren().removeAll(menu.getMenu());
                main.getChildren().add(grid.getWrapper());
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
                System.exit(1);
            }
        });

        Scene scene = new Scene(main, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
