package com.example.return_3.gameCenter.ticTacToe;

import com.example.return_3.main.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class TicTacToe {



    public void showTicTacToeMenuPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/return_3/tictactoe/tictactoeMenu.fxml"));
        Scene menuScene = new Scene(fxmlLoader.load());
        Game.gameInstance.primaryStage.setScene(menuScene);
    }
    public void showTicTacToeServerPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/return_3/tictactoe/tictactoeServer.fxml"));
        Scene menuScene = new Scene(fxmlLoader.load());
        Game.gameInstance.primaryStage.setScene(menuScene);
    }
    public void showTicTacToeRoomPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/return_3/tictactoe/tictactoeRoom.fxml"));
        Scene menuScene = new Scene(fxmlLoader.load());
        Game.gameInstance.primaryStage.setScene(menuScene);
    }
}
