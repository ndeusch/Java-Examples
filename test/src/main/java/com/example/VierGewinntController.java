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

public class VierGewinntController implements Initializable {

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
    private int clickedColumn;
    DataSingleton data = DataSingleton.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize");
        initVierGewinntPlayground();
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("HomeScreen");
    }

    @FXML
    private void initVierGewinntPlayground() {
        counter = 0;
        round = false;
        buttons = new Button[7][6];
        gpTTT.getChildren().clear();
        Message.setText(data.getPlayer1());

        createNewButtons(buttons);
        gpTTT.setPadding(new Insets(15));
        for (int i = 0; i < 7; i++) {
            for (int k = 0; k < 6; k++) {
                gpTTT.add(buttons[i][k], i, k);
            }
        }
    }

    @FXML
    private void createNewButtons(Button[][] buttons) {
        for (int i = 0; i < 7; i++) {
            for (int k = 0; k < 6; k++) {
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
        // System.out.println("checkButton erreicht!");
        button.setOnAction(new EventHandler<ActionEvent>() {

            @SuppressWarnings("static-access")
            @Override
            public void handle(ActionEvent event) {
                Button colButton;
                clickedColumn = gpTTT.getColumnIndex(button);
                for (int i = 5; i >= 0; i--) {
                    colButton = buttons[clickedColumn][i];
                    //System.out.println(i);
                    if (colButton.getText().isEmpty()) {
                        //System.out.println("freies Feld gefunden!");
                        colButton = buttons[clickedColumn][i++];
                        if (!round) {
                            colButton.setText("X");
                            colButton.getStyleClass().add("buttonPlayerFalse");
                            round = true;
                            Message.setText(data.getPlayer2());

                        } else {
                            colButton.setText("O");
                            colButton.getStyleClass().add("buttonPlayerTrue");
                            round = false;
                            Message.setText(data.getPlayer1());

                        }
                        counter++;
                        break;
                    }
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

        //Überprüfung der Spalten
        for (int i = 0; i < 7; i++) {
            for (int k = 0; k < 3; k++) {
                if ((buttons[i][0 + k].getText().equals(buttons[i][1 + k].getText())
                        && buttons[i][0 + k].getText().equals(buttons[i][2 + k].getText())
                        && buttons[i][0 + k].getText().equals(buttons[i][3 + k].getText()))
                        && !buttons[i][0 + k].getText().isEmpty()) {
                    resultFlagColumns = true;
                    break;
                }
            }
        }

        //Überprüfung der Zeilen
        for (int i = 0; i < 6; i++) {
            for (int k = 0; k < 4; k++) {
                if ((buttons[0 + k][i].getText().equals(buttons[1 + k][i].getText())
                    && buttons[0 + k][i].getText().equals(buttons[2 + k][i].getText())
                    && buttons[0 + k][i].getText().equals(buttons[3 + k][i].getText()))
                    && !buttons[0 + k][i].getText().isEmpty()) {
                    resultFlagRows = true;
                    break;
                }
            }
        }

        //Überprüfung der Diagonalen von links oben nach rechts unten
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (((buttons[i][j].getText().equals(buttons[i + 1][j + 1].getText())
                && buttons[i][j].getText().equals(buttons[i + 2][j + 2].getText())
                && buttons[i][j].getText().equals(buttons[i + 3][j + 3].getText()))
                && !buttons[i][j].getText().isEmpty())){
                    resultFlagDiag = true;
                    break;
                }        
            }
        }

        //Überprüfung der Diagonalen von links unten nach rechts oben, aber nur wenn resultFlagDiag = false
        if (!resultFlagDiag){
        for (int i = 0; i < 4; i++) {
            for (int j = 5; j > 2; j--) {
                if (((buttons[i][j].getText().equals(buttons[i + 1][j - 1].getText())
                && buttons[i][j].getText().equals(buttons[i + 2][j - 2].getText())
                && buttons[i][j].getText().equals(buttons[i + 3][j - 3].getText()))
                && !buttons[i][j].getText().isEmpty())){
                    resultFlagDiag = true;
                    break;
                }        
            }
        }
    }

       
        if (resultFlagRows || resultFlagColumns || resultFlagDiag) {
            resultFlag = true;
        }

        if (counter < 42) {
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
        for (int i = 0; i < 7; i++) {
            for (int k = 0; k < 6; k++) {
                buttons[i][k].setOnAction(null);
            }
        }
    }

    @FXML
    private void back() throws IOException {
        App.setRoot("TicTacToeSetup");
    }

}