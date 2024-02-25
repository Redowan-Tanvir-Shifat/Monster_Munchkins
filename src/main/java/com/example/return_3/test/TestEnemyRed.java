package com.example.return_3.test;

import java.util.Random;

public class TestEnemyRed extends TestEnemy{
    public TestEnemyRed(TestGame game) {
        super(game);
    }


    public void loadPlayerImages() {
        up1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyRed1.png", game.tileSize, game.tileSize);
        up2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyRed1.png", game.tileSize, game.tileSize);
        down1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyRed1.png", game.tileSize, game.tileSize);
        down2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyRed1.png", game.tileSize, game.tileSize);
        left1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyRed1.png", game.tileSize, game.tileSize);
        left2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyRed1.png", game.tileSize, game.tileSize);
        right1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyRed1.png", game.tileSize, game.tileSize);
        right2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyRed1.png", game.tileSize, game.tileSize);
    }
    public void setAction(){
        actionLookCounter++;
        if(actionLookCounter == 120){ // Every two seconds
            // Get player position
            int playerX = game.testPlayer.posX;
            int playerY = game.testPlayer.posY;

            // Calculate the direction towards the player
            int dx = playerX - posX;
            int dy = playerY - posY;

            // Determine the primary direction based on the largest difference
            if(Math.abs(dx) > Math.abs(dy)){ // Horizontal movement
                direction = dx > 0 ? "right" : "left";
            } else { // Vertical movement
                direction = dy > 0 ? "down" : "up";
            }

            actionLookCounter = 0;
        }
    }
}
