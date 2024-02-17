package com.example.return_3.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SchoolController {

    @FXML
    private Button backButton;

    @FXML
    void back(ActionEvent event) {
        Game.showGameScene();
    }

}
