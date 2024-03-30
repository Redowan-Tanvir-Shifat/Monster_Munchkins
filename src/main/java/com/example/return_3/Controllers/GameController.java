package com.example.return_3.Controllers;

import com.example.return_3.gameCenter.snakey.Snakey;
import com.example.return_3.gameCenter.spaceInvaders.GameSpaceInvaders;
import com.example.return_3.main.Game;
import javafx.beans.value.ObservableStringValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameController {

    Game game;
    @FXML
    public Button backButton;
    @FXML
    public Button puzzle;
    @FXML
    public Button spaceInvaders;
    @FXML
    public Button tictactoe;
    public Label coin;
    public Label energy;
    public Button snakeyBtn;


    @FXML
    void back(ActionEvent event) {
        System.out.println("Before entry: "+ Game.gameInstance.player.worldY);
        //Game.gameInstance.player.worldY+=Game.gameInstance.tileSize*5;

        System.out.println("after entry: "+ Game.gameInstance.player.worldY);
        Game.gameInstance.keyHandler.setBooleanAll(false);
        Game.showGameScene();
    }

    @FXML
    void goToPuzzle(ActionEvent event) {

    }

    @FXML
    void goToSpaceInvaders(ActionEvent event) {
        System.out.println("ENterign space invaders game");
        Game.gameSpaceInvaders = new GameSpaceInvaders(Game.gameInstance, Game.gameInstance.gc);
        //Game.gameInstance.keyHandler.setBooleanAll(false);
        try {
            Game.gameSpaceInvaders.startGameSpaceInvaders();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToTicTacToe(ActionEvent event) {

    }

    @FXML
    void goToSnakey(ActionEvent event) {
        System.out.println("ENterign Snakey game");
        Game.gameSnakey = new Snakey(Game.gameInstance, Game.gameInstance.gc);
        //Game.gameInstance.keyHandler.setBooleanAll(false);
        try {
            Game.gameSnakey.startGameSnakey();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setCoin(int c){
        coin.setText(String.valueOf(c));
    }

}
