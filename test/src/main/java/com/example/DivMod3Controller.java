package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DivMod3Controller {

    @SuppressWarnings("exports")
    public Label resultMod3;
    @SuppressWarnings("exports")
    public TextField Zahl;
    private int input;
    private int result;
    private int modResult;
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("HomeScreen");
    }

    @FXML
    private void calcMod3(){
        input = Integer.valueOf(Zahl.getText());
        result = (input - 1)/3;
        modResult =(input-1)%3;
        resultMod3.setText(String.valueOf(result) + " --- " + String.valueOf(modResult));
    }
}