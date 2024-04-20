package com.example.return_3.Controllers.tictactoe;

import com.example.return_3.gameCenter.ticTacToe.TicTacToe;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class TicTacToeGameOverController {
    public Button playAgainBtn;
    public Button backBtn;
    TicTacToe ticTacToe = TicTacToe.ticTacInstance;
    public void onPlayAgain(ActionEvent actionEvent) {
    }

    public void onBack(ActionEvent actionEvent) throws IOException {
        ticTacToe.showMenuScene();
    }
}
