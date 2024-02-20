package com.example.return_3.Controllers;

import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class GameController {
    public Button snakey;
    public Button backButton;

    public void goTosnakey(ActionEvent event) {
    }

    public void back(ActionEvent event) {
        Game.showGameScene();
    }
}
