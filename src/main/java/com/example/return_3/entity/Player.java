package com.example.return_3.entity;

import com.example.return_3.main.Game;
import com.example.return_3.main.KeyHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;


public class Player extends Entity{
    //VARIABLES
    KeyHandler keyHandler;
    private double playerSpeed=120; //pixel per second

    public final int screenX;
    public final int screenY;
    //part 8 Object Interaction part starts
//    public  int hasKey=0;
    //part 8 Object Interaction part ends

    private ImageView playerImageView;
    public boolean attackCanceled=false;
    public Player(Game game, KeyHandler keyHandler) {
        super(game);
        this.keyHandler = keyHandler;
        // Get the width and height of the screen
        double screenWidth = game.scene.getWidth();
        double screenHeight = game.scene.getHeight();
        // Calculate the middle point
        screenX = (int) (screenWidth / 2);
        screenY = (int) (screenHeight / 2);
        // Load player images and initialize ImageView
        loadPlayerImages();
        playerImageView = new ImageView();
        playerImageView.setImage(down1); // Default image
        // Set initial position of the player image view
        playerImageView.setX(screenX);
        playerImageView.setY(screenY);

        // Set initial position of the player image view
//        playerImageView.setX(screenX - playerImageView.getImage().getWidth() / 2);
//        playerImageView.setY(screenY - playerImageView.getImage().getHeight() / 2);

    }


    private void loadPlayerImages() {
        up1 = loadImage("/player/boy_up_1.png", game.tileSize, game.tileSize);
        up2 = loadImage("/player/boy_up_2.png", game.tileSize, game.tileSize);
        down1 = loadImage("/player/boy_down_1.png", game.tileSize, game.tileSize);
        down2 = loadImage("/player/boy_down_2.png", game.tileSize, game.tileSize);
        left1 = loadImage("/player/boy_left_1.png", game.tileSize, game.tileSize);
        left2 = loadImage("/player/boy_left_2.png", game.tileSize, game.tileSize);
        right1 = loadImage("/player/boy_right_1.png", game.tileSize, game.tileSize);
        right2 = loadImage("/player/boy_right_2.png", game.tileSize, game.tileSize);
//        up1 = new Image(Objects.requireNonNull(getClass().getResource("/player/boy_up_1.png")).toString());
//        up2 = new Image(Objects.requireNonNull(getClass().getResource("/player/boy_up_2.png")).toString());
//        down1 = new Image(Objects.requireNonNull(getClass().getResource("/player/boy_down_1.png")).toString());
//        down2 = new Image(Objects.requireNonNull(getClass().getResource("/player/boy_down_2.png")).toString());
//        left1 = new Image(Objects.requireNonNull(getClass().getResource("/player/boy_left_1.png")).toString());
//        left2 = new Image(Objects.requireNonNull(getClass().getResource("/player/boy_left_2.png")).toString());
//        right1 = new Image(Objects.requireNonNull(getClass().getResource("/player/boy_right_1.png")).toString());
//        right2 = new Image(Objects.requireNonNull(getClass().getResource("/player/boy_right_2.png")).toString());
        //
        System.out.println("Player loaded");
    }

    public ImageView getPlayerImageView() {
        return playerImageView;
    }


    public void update(double deltaTime){

        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        // Move player based on key inputs
        if (keyHandler.isMoveUp()) {
            playerImageView.setY(playerImageView.getY() - (playerSpeed*deltaTime));
            direction = "up";
        }
        if (keyHandler.isMoveDown()) {
            playerImageView.setY(playerImageView.getY() + (playerSpeed*deltaTime));
            direction = "down";
        }
        if (keyHandler.isMoveRight()) {
            playerImageView.setX(playerImageView.getX() + (playerSpeed*deltaTime));
            direction = "right";
        }
        if (keyHandler.isMoveLeft()) {
            playerImageView.setX(playerImageView.getX() - (playerSpeed*deltaTime));
            direction = "left";
        }
    }


    public void draw(){
        if(keyHandler.isMoveUp() || keyHandler.isMoveDown() || keyHandler.isMoveRight() || keyHandler.isMoveLeft()) {
            updatePlayerSprite();
        }
    }


    private void updatePlayerSprite() {
        switch (direction) {
            case "up":
                if (spriteNum == 1){

                    playerImageView.setImage(up1);
                }
                else if(spriteNum == 2){
                    playerImageView.setImage(up2);
                }
                break;
            case "down":
                if (spriteNum == 1){

                    playerImageView.setImage(down1);
                }
                else if(spriteNum == 2){
                    playerImageView.setImage(down2);
                }
                break;
            case "right":
                if (spriteNum == 1){

                    playerImageView.setImage(right1);
                }
                else if(spriteNum == 2){
                    playerImageView.setImage(right2);
                }
                break;

            case "left":
                if (spriteNum == 1){

                    playerImageView.setImage(left1);
                }
                else if(spriteNum == 2){
                    playerImageView.setImage(left2);
                }
                break;
            // Handle other directions similarly
        }
    }


}
