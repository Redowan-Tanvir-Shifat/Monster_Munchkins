package com.example.return_3.Controllers;

import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.StageStyle;

public class GlobalChatController {
    public TextArea textField;
    public Button sendBtn;
    public Label label1;
    public Label label2;
    public Label label3;
    public Label label4;
    public Label label5;
    public Label label6;
    public Label label7;
    public Label label8;
    public Label label9;
    public Label label10;
    public Label label11;
    public Button exitBtn;

    public void onSend(ActionEvent actionEvent) {
        Game.gameInstance.client.clientWriterThread.start();
    }

    public void onExit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");

        alert.setHeaderText("You are about to exit!");
        alert.setContentText("Are you sure you want to exit?");

        // Add "Yes" and "No" buttons to the confirmation dialog
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // Custom CSS for the dialog
        alert.getDialogPane().getStylesheets().add(
                Game.class.getResource("/com/example/return_3/alert.css").toExternalForm());
        alert.initStyle(StageStyle.TRANSPARENT); // Transparent style for a sleek look

        // Handle button actions
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeYes) {
                Game.gameInstance.keyHandler.setBooleanAll(false);

                Game.showGameScene();
            }
        });
    }
}
