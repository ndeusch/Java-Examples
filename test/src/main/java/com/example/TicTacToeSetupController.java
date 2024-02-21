package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class TicTacToeSetupController {

    @SuppressWarnings("exports")
    public TextField tfPlayer1;
    @SuppressWarnings("exports")
    public TextField tfPlayer2;
    @SuppressWarnings("exports")
    public Label errMessage;
   
    DataSingleton data = DataSingleton.getInstance();
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("HomeScreen");
    }

    @FXML
    private void playGame() throws IOException {
        if (tfPlayer1.getText().isEmpty() || tfPlayer2.getText().isEmpty()) {
            errMessage.setText("Bitte geben Sie beiden Spielern einen Namen!");
            errMessage.setTextFill(Color.RED);
            return;
        }
        data.setPlayer1(tfPlayer1.getText());
        data.setPlayer2(tfPlayer2.getText());
        App.setRoot("TicTacToe");
    }

    @FXML
    private void tfPlayer1KeyPressed(KeyEvent event) throws IOException {
        errMessage.setText("");
    }

    @FXML
    private void tfPlayer2KeyPressed(KeyEvent event) throws IOException {
        errMessage.setText("");
    }
}