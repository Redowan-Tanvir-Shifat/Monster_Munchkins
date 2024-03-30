package com.example.return_3.Controllers;

import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.IOException;

import static com.example.return_3.main.Game.primaryStage;

public class SchoolController {

    public Button mathButton;
    public Button histryButton;
    public Button geographybutton;
    public Button englishButton;

    @FXML
    public Button backButton;

    @FXML
    void back(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit");
        alert.setContentText("Do you want to leave school?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Game.gameInstance.keyHandler.setBooleanAll(false);
            Game.showGameScene();
        }
//        Game.gameInstance.keyHandler.setBooleanAll(false);
//        Game.showGameScene();
    }

    @FXML
    void goToMath(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/math.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public void goTohistory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/history.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public void goToGeography(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/geography.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public void goToEnlish(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/english.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }


}
