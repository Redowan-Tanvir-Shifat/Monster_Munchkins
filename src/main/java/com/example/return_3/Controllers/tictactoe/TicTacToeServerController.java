package com.example.return_3.Controllers.tictactoe;

import com.example.return_3.gameCenter.ticTacToe.TicTacToe;
import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TicTacToeServerController {
    public TextField portNumberField;
    public Button startGameBtn;
    public Button backBtn;

    public void onStartGameBtn(ActionEvent actionEvent) {

        Game.gameInstance.ticTacToeGame.port= Integer.parseInt(portNumberField.getText());
        Game.gameInstance.ticTacToeGame.startServer();
    }

    public void onBackBtn(ActionEvent actionEvent) throws IOException {
        //Close the Server if it is not closed
        if (Game.gameInstance.ticTacToeGame.serverSocket != null && !Game.gameInstance.ticTacToeGame.serverSocket.isClosed()) {
            Game.gameInstance.ticTacToeGame.serverSocket.close();
            System.out.println("Server socket closed");
        }
        Game.gameInstance.ticTacToeGame.showTicTacToeMenuPage();
    }
}
