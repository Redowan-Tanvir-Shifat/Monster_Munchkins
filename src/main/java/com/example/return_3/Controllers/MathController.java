package com.example.return_3.Controllers;

import com.example.return_3.main.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MathController {
    public Button algebraBtn;
    public Button geometryBtn;
    public Button conversionBtn;
    public Button triginometryBtn;
    public Button backButton;
    Game game;

  

    @FXML
    void back(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/school.fxml"));
        Parent root = loader.load();
        Scene scene= new Scene(root);
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("school.css")).toExternalForm());
        Game.primaryStage.setScene(scene);
    }
    
    public void goToAlgebra(ActionEvent event) {
    }
    
    public void goToGeometry(ActionEvent event) {
    }
    
    public void goToConversion(ActionEvent event) {
    }
    
    public void goToTrigonometry(ActionEvent event) {
    }
}
