package oop.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class Menu {
    private final VBox menu;
    private final Label startingPlayer;
    private final Label timerLabel;
    private final TextField timerText;
    private final Button submit;

    public Menu() {
        menu = new VBox(10);
        startingPlayer =  new Label("Crosses start");
        timerLabel = new Label("Time per move in seconds (leave blank or 0 if you don't want it");
        timerText = new TextField("0");
        timerText.setMaxWidth(60);
        submit = new Button("Play!");
        menu.getChildren().addAll(startingPlayer, timerLabel, timerText, submit);
        menu.setAlignment(Pos.CENTER);
    }

    public VBox getMenu() {
        return menu;
    }

    public Button getSubmit() {
        return submit;
    }

    public int getTimerValue() throws IllegalArgumentException {
        String timer = timerText.getText();
        if (!Objects.equals(timer, "") && !isNumeric(timer)) {
            throw new IllegalArgumentException("Not valid timer value given");
        }

        return Integer.parseInt(timer);
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
