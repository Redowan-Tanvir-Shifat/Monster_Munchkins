package com.example.return_3.Controllers;

import com.example.return_3.db.MyJDBC;
import com.example.return_3.main.AssetSetter;
import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Objects;

import static com.example.return_3.main.Game.loginScene;
import static com.example.return_3.main.Game.primaryStage;

public class SignUpController {
    public TextField username;
    public Button signUpBtn;
    public PasswordField password1;
    public PasswordField password2;
    public Button closeBtn;
    public Label failedText;
    public Button loginBtn;

    public void signUp(ActionEvent event) throws IOException {
        if(validateUserInput(username.getText(), password1.getText(), password2.getText())) {
            int userId = MyJDBC.register(username.getText(), password1.getText());
            if(userId != -1) { // Registration successful
               // MyJDBC.addInteractiveTile(userId, col, row, mapNum, destroyed); // Add interactive tiles
                //setup interactive Tile
                AssetSetter.addObjectToDB(userId);
                AssetSetter.addInventoryToDB(userId);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
            } else {
                failedText.setText("This username is already taken!");
            }
        }
    }

    public void close(ActionEvent event) {
        Game.exitWithConfirmation();
    }

    private boolean validateUserInput(String username,String password, String rePassword){
        if(username.length()==0||password.length()==0||rePassword.length()==0)return false;

        //username has to be at least 6 characters long
        if(username.length()<5){
            failedText.setText("username has to be at least 5 characters long!");
        return false;
        }
        //password and repassword must be the same

        if(!password.equals(rePassword)){
            failedText.setText("Password does not match!");
            return false;
        }

        //passes validation
        return true;
    }

    public void goToLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/return_3/login.fxml"));
        loginScene = new Scene(fxmlLoader.load());
        primaryStage.setScene(loginScene);
    }
}
