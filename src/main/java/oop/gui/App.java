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

public class App extends Application {
    public void start(Stage primaryStage) {
        VBox main = new VBox(5);
        main.setAlignment(Pos.CENTER);
        Label title = new Label("Ultimate TicTacToe");
        title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 20));
        main.getChildren().add(title);

        VBox menu = new VBox(5);
        Label startingPlayer = new Label("Crosses start");
        Label timerLabel = new Label("Time per move in seconds (leave blank or 0 if you don't want it");
        TextField timerText = new TextField("0");
        timerText.setMaxWidth(60);
        Button submit = new Button("Play!");
        menu.getChildren().addAll(startingPlayer, timerLabel, timerText, submit);
        menu.setAlignment(Pos.CENTER);
        main.getChildren().add(menu);

        submit.setOnAction(event -> {
            Map map = new Map(FieldContent.CROSS);
            OuterGrid grid = new OuterGrid(map, Integer.parseInt(timerText.getText()));
            System.out.println(Integer.parseInt(timerText.getText()));
            main.getChildren().removeAll(menu);
            main.getChildren().add(grid.getWrapper());
        });

        Scene scene = new Scene(main, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
