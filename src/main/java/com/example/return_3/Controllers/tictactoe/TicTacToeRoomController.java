package com.example.return_3.Controllers.tictactoe;

import com.example.return_3.gameCenter.ticTacToe.TicTacToe;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TicTacToeRoomController {
    public TextField serverIPField;
    public TextField serverPortField;
    public Button joinRoomBtn;
    public Button backBtn;

    public void onBackBtn(ActionEvent actionEvent) throws IOException {
        new TicTacToe().showTicTacToeMenuPage();
    }

    public void onJoinRoomBtn(ActionEvent actionEvent) {
    }
}
