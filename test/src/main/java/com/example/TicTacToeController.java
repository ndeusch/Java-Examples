package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TicTacToeController implements Initializable {

    @SuppressWarnings("exports")
    public GridPane gpTTT;
    @SuppressWarnings("exports")
    public Label Message;
    private boolean resultFlag;
    private boolean resultFlagRows;
    private boolean resultFlagColumns;
    private boolean resultFlagDiag;

    private boolean round;
    private Button[][] buttons;
    private int counter;
    DataSingleton data = DataSingleton.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize");
        initTicTacToePlayground();
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("HomeScreen");
    }

    @FXML
    private void initTicTacToePlayground() {
        counter = 0;
        round = false;
        buttons = new Button[3][3];
        gpTTT.getChildren().clear();
       
        Message.setText(data.getPlayer1());

        createNewButtons(buttons);
        gpTTT.setPadding(new Insets(15));
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                gpTTT.add(buttons[i][k], i, k);
            }
        }
    }

    @FXML
    private void createNewButtons(Button[][] buttons) {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                Button button = new Button();
                buttons[i][k] = button;
                button.setPrefWidth(90);
                button.setMaxWidth(90);
                button.setPrefHeight(90);
                button.setMaxHeight(90);
                checkButton(button);
                // button.setText(i + " / " + k);
            }
        }
    }

    @FXML
    private void checkButton(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (button.getText().isEmpty()) {
                    if (!round) {
                        button.setText("X");
                        button.getStyleClass().add("buttonPlayerFalse");
                        round = true;
                        Message.setText(data.getPlayer2());

                    } else {
                        button.setText("O");
                        button.getStyleClass().add("buttonPlayerTrue");
                        round = false;
                        Message.setText(data.getPlayer1());

                    }
                    counter++;
                }
                checkResult();
            }
        });
    }

    @FXML
    private void checkResult() {
        resultFlag = false;
        resultFlagColumns = false;
        resultFlagRows = false;
        resultFlagDiag = false;

        for (int i = 0; i < 3; i++) {
            if ((buttons[i][0].getText().equals(buttons[i][1].getText())
                    && buttons[i][0].getText().equals(buttons[i][2].getText()))
                    && !buttons[i][0].getText().isEmpty()) {
                resultFlagRows = true;
                break;
            }
        }

        for (int i = 0; i < 3; i++) {
            if ((buttons[0][i].getText().equals(buttons[1][i].getText())
                    && buttons[0][i].getText().equals(buttons[2][i].getText()))
                    && !buttons[0][i].getText().isEmpty()) {
                resultFlagColumns = true;
                break;
            }
        }

        if (((buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText()))
                && !buttons[0][0].getText().isEmpty()) ||
                ((buttons[0][2].getText().equals(buttons[1][1].getText())
                        && buttons[1][1].getText().equals(buttons[2][0].getText()))
                        && !buttons[1][1].getText().isEmpty())) {
            resultFlagDiag = true;
        }

        if (resultFlagRows || resultFlagColumns || resultFlagDiag) {
            resultFlag = true;
        }

        if (counter < 9) {
            if (resultFlag) {
                if (!round) {
                    Message.setText(data.getPlayer2() + " hat gewonnen");
                    removeEventListener();
                } else {
                    Message.setText(data.getPlayer1() + " hat gewonnen");
                    removeEventListener();
                }
            }
        } else {
            Message.setText("Unentschieden!");
            removeEventListener();
        }
    }

    @FXML
    private void removeEventListener() {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {            
                buttons[i][k].setOnAction(null);
            }
        }
    }

    @FXML
    private void back() throws IOException {
        App.setRoot("TicTacToeSetup");
    }

}