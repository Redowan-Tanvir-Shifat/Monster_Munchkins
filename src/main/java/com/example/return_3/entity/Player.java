package com.example.return_3.entity;

import com.example.return_3.main.Game;
import com.example.return_3.main.KeyHandler;

public class Player extends Entity{
    //VARIABLES
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    //part 8 Object Interaction part starts
//    public  int hasKey=0;
    //part 8 Object Interaction part ends
    public boolean attackCanceled=false;
    Player(Game game) {
        super(game);
        // Get the width and height of the screen
        double screenWidth = game.scene.getWidth();
        double screenHeight = game.scene.getHeight();
        // Calculate the middle point
        screenX = (int) (screenWidth / 2);
        screenY = (int) (screenHeight / 2);
    }
}
