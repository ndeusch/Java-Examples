package com.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class HomeScreenController {

    @FXML
    private void PlayTicTacToe() throws IOException {
        App.setRoot("TicTacToeSetup");
    }

    @FXML
   private void PlayDivMod3() throws IOException {
        App.setRoot("DivMod3");
    }

    @FXML
    private void PlayVierGewinnt() throws IOException {
         App.setRoot("VierGewinntSetup");
     }
 
     @FXML
     private void PlaySudoku() throws IOException {
          App.setRoot("Sudoku");
      }
  
 }
