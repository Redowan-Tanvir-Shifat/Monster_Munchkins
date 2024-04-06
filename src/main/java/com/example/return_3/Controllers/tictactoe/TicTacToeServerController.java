package com.example.return_3.Controllers.tictactoe;

import com.example.return_3.gameCenter.ticTacToe.TicTacToe;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TicTacToeServerController {
    public TextField portNumberField;
    public Button startGameBtn;
    public Button backBtn;

    public void onStartGameBtn(ActionEvent actionEvent) {
    }

    public void onBackBtn(ActionEvent actionEvent) throws IOException {
        new TicTacToe().showTicTacToeMenuPage();
    }
}
