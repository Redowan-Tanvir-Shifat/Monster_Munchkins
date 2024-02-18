package com.example.return_3.entity;

import com.example.return_3.main.EventHandler;
import com.example.return_3.main.Game;
import com.example.return_3.main.KeyHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;


public class Player extends Entity{
    //VARIABLES
    KeyHandler keyHandler;



    public final int screenX;
    public final int screenY;
    //part 8 Object Interaction part starts
//    public  int hasKey=0;
    //part 8 Object Interaction part ends

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

        //part 6 collision part starts
        solidArea= new Rectangle(); //we can skip this session. (as we already make it in Entity class)
        solidArea.setX(5);
        solidArea.setY(10);

        //part 8 Object Interaction part starts
        solidAreaDefaultX=(int)(solidArea.getX());
        solidAreaDefaultY=(int)(solidArea.getY());
        //part 8 Object Interaction part  ends

        solidArea.setWidth(22);
        solidArea.setHeight(22);
        //part 6 collision part ends


        // Load player images and initialize ImageView
        loadPlayerImages();
//        playerImageView = new ImageView();
//        playerImageView.setImage(down1); // Default image
//        // Set initial position of the player image view
//        playerImageView.setX(screenX);
//        playerImageView.setY(screenY);


        setDefaultValues();


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



    public void setDefaultValues(){
        setDefaultPositions();
         speed=(int) (250*game.targetFrameTime); //pixel per second


        //Player Status;
        level=1;
        maxLife=6;
        life=maxLife;
        maxMana=4;
        mana=maxMana;
        //ammo=10;
        strength=1; //the more strength he has the more damage he gives
        dexterity=1;// the more dexterity he has the less damage he receives
        exp=0;
        nextLevelExp=5;
        coin=0;
        //currentWeapon=new OBJ_Sword_normal(gp);
//        currentWeapon=new OBJ_Axe(gp);
//        currentShield= new OBJ_Shield_Wood(gp);
//        projectile= new OBJ_Fireball(gp);
//        //this is for testing that you can use anything to throw .
//        //projectile= new OBJ_Rock(gp);
//        attack=getAttack(); //the total attack value is decided by the strength and weapon
//        defense=getDefense();// the total defense value is decided by the dexterity and shield
    }

    public void setDefaultPositions(){
        worldX=game.tileSize*28;
        worldY=game.tileSize*25;
        direction="down";
    }

    public void update(){
        if(keyHandler.isMoveUp() || keyHandler.isMoveDown() || keyHandler.isMoveRight() || keyHandler.isMoveLeft()) {
            // Move player based on key inputs
            if (keyHandler.isMoveUp()) {
                direction = "up";
            }
            if (keyHandler.isMoveDown()) {
                direction = "down";
            }
            if (keyHandler.isMoveRight()) {
                direction = "right";
            }
            if (keyHandler.isMoveLeft()) {
                direction = "left";
            }

            collisionOn = false;

            //Now check for the colliosion here.

            game.cChecker.checkTile(this);
//            System.out.println("Collision: " + collisionOn);

            //CHeck NPC collision
            int npcIndex=game.cChecker.checkEntity(this,game.npc);
            interactNPC(npcIndex);


            //new code


            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

//        playerImageView.setX(playerImageView.getX() - (playerSpeed*deltaTime));

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }



        try{
            game.eventHandler.checkEvent();
        }catch(IOException e){
            e.printStackTrace();
        }



    }


    public void draw(GraphicsContext gc){
        Image image= null;

        //temp variable
        int tempScreenX=screenX;
        int tempScreenY=screenY;

        switch (direction) {
            case "up":
                if (spriteNum == 1){

                    //playerImageView.setImage(up1);
                    image=up1;
                }
                else if(spriteNum == 2){
                    image=(up2);
                }
                break;
            case "down":
                if (spriteNum == 1){

                    image=(down1);
                }
                else if(spriteNum == 2){
                    image=(down2);
                }
                break;
            case "right":
                if (spriteNum == 1){

                    image=(right1);
                }
                else if(spriteNum == 2){
                    image=(right2);
                }
                break;

            case "left":
                if (spriteNum == 1){

                    image=(left1);
                }
                else if(spriteNum == 2){
                    image=(left2);
                }
                break;
            // Handle other directions similarly
        }

        gc.drawImage(image,tempScreenX,tempScreenY);
    }




    public void interactNPC(int i){
       // code if we need to handle the interact npc like contact them when pressing enter
    }

}
