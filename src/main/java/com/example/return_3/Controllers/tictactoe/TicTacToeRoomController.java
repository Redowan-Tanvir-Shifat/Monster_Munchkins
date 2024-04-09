package com.example.return_3.Controllers.tictactoe;

import com.example.return_3.gameCenter.ticTacToe.TicTacToe;
import com.example.return_3.main.Game;
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
        //Close the Client if it is not closed
        if (Game.gameInstance.ticTacToeGame.socket != null && !Game.gameInstance.ticTacToeGame.socket.isClosed()) {
            Game.gameInstance.ticTacToeGame.socket.close();
            System.out.println("Client socket closed");
        }
        Game.gameInstance.ticTacToeGame.showTicTacToeMenuPage();
    }

    public void onJoinRoomBtn(ActionEvent actionEvent) {
        String ip=serverIPField.getText();
        int port=Integer.parseInt(serverPortField.getText());
        if(Game.gameInstance.ticTacToeGame.connect(ip,port)){
        Game.gameInstance.ticTacToeGame.startClient();
        }
    }
}
