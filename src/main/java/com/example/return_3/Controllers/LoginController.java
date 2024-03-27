package com.example.return_3.Controllers;

import com.example.return_3.db.MyJDBC;
import com.example.return_3.db.User;
import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import static com.example.return_3.main.Game.primaryStage;

public class LoginController {
    public Button loginBtn;
    public Button closeBtn;
    public PasswordField password;
    public Label failedText;
    public Label signUpText;
    public Button signUpBtn;
    @FXML
    TextField username;

    String username1;
    String password1;

    //-------------------------- --- IN this `startGame` method our Application will direct you `showGameScene` and then game will start  ----------------------------------------------------------------
    public void login(ActionEvent event) {
        //Validate Login
        Game.gameInstance.user= MyJDBC.validateLogin(username.getText(),password.getText());
        if (Game.gameInstance.user!=null){
            try {
                // Write user ID to file to indicate login
                BufferedWriter writer = new BufferedWriter(new FileWriter("loginStatus.txt"));
                writer.write(String.valueOf(Game.gameInstance.user.getUserId()));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Game.showGameScene();
        }else{
            failedText.setText("Please enter correct password!");
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
    public void signUp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/signUp.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }
}
