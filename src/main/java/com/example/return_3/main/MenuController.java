package com.example.return_3.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {

    @FXML
    private void startGame(ActionEvent event) {
        Game.showGameScene();
    }
    @FXML
    private void exitGame(ActionEvent event) {
        Game.exitGame();
    }
}
