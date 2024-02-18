package com.example.return_3.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import static com.example.return_3.main.Game.primaryStage;

public class SchoolController {

    @FXML
    private Button backButton;

    @FXML
    void back(ActionEvent event) {

        Game.showGameScene();
    }

    @FXML
    void goToMath(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/math.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

}
