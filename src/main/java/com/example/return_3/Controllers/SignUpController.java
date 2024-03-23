package com.example.return_3.Controllers;

import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Objects;

import static com.example.return_3.main.Game.primaryStage;

public class SignUpController {
    public TextField username;
    public Button signUpBtn;
    public PasswordField password1;
    public PasswordField password2;
    public Button closeBtn;
    public Label failedText;

    public void signUp(ActionEvent event) throws IOException {
        if (Objects.equals(password1.getText(), password2.getText())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/login.fxml"));
            //loader.setController(new MenuController());
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } else {
            failedText.setText("Please enter same password!");
        }
    }

    public void close(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit");
        alert.setContentText("Do you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Game.exitGame();
        }
    }
}
