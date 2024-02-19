package com.example.return_3.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Objects;

public class LoginController {
    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    Label failedText;
    public Button closeButtion;
    public Button loginButton;

    public void closeWindow(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit");
        alert.setContentText("Do you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Game.exitGame();
        }
    }

    public void startGame(ActionEvent event) {
        if (Objects.equals(username.getText(), "redowan19") && Objects.equals(password.getText(), "return_3;")) {
            Game.showGameScene();
        } else {
            failedText.setText("Please enter right password!");
        }

    }
}
