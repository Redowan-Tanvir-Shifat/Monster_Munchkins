package com.example.return_3.Controllers.tictactoe;

import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class TicTacToeMenuController {
    public Button serverBtn;
    public Button joinBtn;
    public Button back;

    public void onServerBtn(ActionEvent actionEvent) {
    }

    public void onJoinBtn(ActionEvent actionEvent) {
    }

    public void onBack(ActionEvent actionEvent) throws Exception {
        Game.gameInstance.showGameCenter();

    }
}
