package com.example.return_3.Controllers;

import com.example.return_3.gameCenter.spaceInvaders.GameSpaceInvaders;
import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class GameController {
    public Button snakey;
    public Button backButton;

    public void goTosnakey(ActionEvent event) {
        System.out.println("ENterign space invaders game");
        Game.gameSpaceInvaders = new GameSpaceInvaders(Game.gameInstance, Game.gameInstance.gc);
        //Game.gameInstance.keyHandler.setBooleanAll(false);
        try {
            Game.gameSpaceInvaders.startGameSpaceInvaders();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void back(ActionEvent event) {
        System.out.println("Before entry: "+ Game.gameInstance.player.worldY);
        //Game.gameInstance.player.worldY+=Game.gameInstance.tileSize*5;

        System.out.println("after entry: "+ Game.gameInstance.player.worldY);
        Game.gameInstance.keyHandler.setBooleanAll(false);
        Game.showGameScene();
    }
}
