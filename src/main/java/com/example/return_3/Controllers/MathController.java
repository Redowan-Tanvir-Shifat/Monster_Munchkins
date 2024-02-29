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
    public Button addBtn;
    public Button multiplicationBtn;
    public Button exponentsBtn;
    public Button decimalBtn;
    public Button algebraBtn;
    public Button numbersBtn;
    public Button geometryBtn;
    public Button geometricBtn;
    public Button conversionBtn;
    public Button percentagesBtn;
    public Button absoluteBtn;
    public Button setsbtn;
    public Button divisibilityBtn;
    Game game;

    @FXML
    private Button backButton;

    @FXML
    void back(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/school.fxml"));
        Parent root = loader.load();
        Scene scene= new Scene(root);
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("school.css")).toExternalForm());
        Game.primaryStage.setScene(scene);
    }

    public void goToAdd_sub(ActionEvent event) {
    }

    public void goToMultiplication(ActionEvent event) {
    }

    public void goToExponents(ActionEvent event) {
    }

    public void goToDecimals(ActionEvent event) {
    }

    public void goToAlgebra(ActionEvent event) {
    }

    public void goToNumbers(ActionEvent event) {
    }

    public void goToGeometry(ActionEvent event) {
    }

    public void goToGeometric(ActionEvent event) {
    }

    public void goToConversion(ActionEvent event) {
    }

    public void goToPercentages(ActionEvent event) {
    }

    public void goToAbsolute(ActionEvent event) {
    }

    public void goToSets(ActionEvent event) {
    }

    public void goTodivisibility(ActionEvent event) {
    }
}
