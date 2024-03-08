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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Objects;


public class Player extends Entity{
    //VARIABLES
    public KeyHandler keyHandler;



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
        double screenWidth = Game.gameScene.getWidth();
        double screenHeight = Game.gameScene.getHeight();
        // Calculate the middle point
        screenX = (int) (screenWidth / 2);
        screenY = (int) (screenHeight / 2);

        //part 6 collision part starts
        solidArea= new Rectangle(); //we can skip this session. (as we already make it in Entity class)
        solidArea.setX(9);
        solidArea.setY(14);

        //part 8 Object Interaction part starts
        solidAreaDefaultX=(int)(solidArea.getX());
        solidAreaDefaultY=(int)(solidArea.getY());
        //part 8 Object Interaction part  ends

        solidArea.setWidth(14);
        solidArea.setHeight(10);
        //part 6 collision part ends


        attackArea.setWidth(32);
        attackArea.setHeight(28);

        setDefaultValues();
        loadPlayerImages();    // Load player images and initialize ImageView
        loadPlayerAttackImages();





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

        //System.out.println("Player loaded");
    }
    private void loadPlayerAttackImages() {
        attackUp1 = loadImage("/player/attacking_up_1.png", game.tileSize, game.tileSize*2);
        attackUp2 = loadImage("/player/attacking_up_2.png", game.tileSize, game.tileSize*2);
        attackDown1 = loadImage("/player/attacking_down_1.png", game.tileSize, game.tileSize*2);
        attackDown2 = loadImage("/player/attacking_down_2.png", game.tileSize, game.tileSize*2);
        attackLeft2 = loadImage("/player/attacking_left_1.png", game.tileSize*2, game.tileSize);
        attackLeft2 = loadImage("/player/attacking_left_2.png", game.tileSize*2, game.tileSize);
        attackRight1 = loadImage("/player/attacking_right_1.png", game.tileSize*2, game.tileSize);
        attackRight2 = loadImage("/player/attacking_right_2.png", game.tileSize*2, game.tileSize);

        //System.out.println("Player loaded");
    }




    public void setDefaultValues(){
        setDefaultPositions();
         speed=(int) (250*game.targetFrameTime); //pixel per second


        //Player Status;
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        //ammo=10;
        strength=1; //the more strength he has the more damage he gives
        dexterity=1;// the more dexterity he has the less damage he receives
        exp=20;
        nextLevelExp=50;
        coin=0;
        energy=180;
        maxEnergy=200;
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
        worldX= game.tileSize * 82;
        worldY= game.tileSize * 148;
        direction="down";
    }

    public void update(){
        if (attacking == true) {
            attacking();
        }
        else if (keyHandler.isMoveUp() || keyHandler.isMoveDown() || keyHandler.isMoveRight() || keyHandler.isMoveLeft() || keyHandler.isEnterPressed() || keyHandler.isSpacePressed()) {
            // Move player based on key inputs
            if (keyHandler.isMoveUp()) {
                direction = "up";
            }
            else if (keyHandler.isMoveDown()) {
                direction = "down";
            }
            else if (keyHandler.isMoveLeft()) {
                direction = "left";
            }
            else if (keyHandler.isMoveRight()) {
                direction = "right";
            }

            collisionOn = false;

            //Now check for the colliosion here.

            game.cChecker.checkTile(this);
//            System.out.println("Collision: " + collisionOn);

            //CHeck NPC collision
            int npcIndex=game.cChecker.checkEntity(this,game.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = game.cChecker.checkEntity(this,game.monster);
            contactMonster(monsterIndex);
            interactMonster(monsterIndex);

            //new code


            if (collisionOn == false && keyHandler.isEnterPressed() == false && keyHandler.isSpacePressed() == false) {
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
              game.keyHandler.setEnterPressed(false);
              game.keyHandler.setSpacePressed(false);

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


        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }



    private void attacking() {
        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            // SAVE THE CURRENT DATA......
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = (int) solidArea.getWidth();
            int solidAreaHeight = (int) solidArea.getHeight();

            // ADJUST PLAYRER S WORLD X/Y FOR THE ATTACK AREA
            switch (direction) {
                case "up" : worldY -= (int) attackArea.getHeight(); break;
                case "down" : worldY += (int) attackArea.getHeight(); break;
                case "left" : worldX -= (int) attackArea.getWidth(); break;
                case "right" : worldX += (int) attackArea.getWidth(); break;
            }
            // ATTACK BECOME SOLID AREA
            solidArea.setWidth(attackArea.getWidth());
            solidArea.setHeight(attackArea.getHeight());
            //CHECK monster collision with the updated worldX, worldY and solidArea....
            int monsterIndex = game.cChecker.checkEntity(this, game.monster);
            damagedMonster(monsterIndex);

            // After checking collision restore the original data...
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.setWidth(solidAreaWidth);
            solidArea.setHeight(solidAreaHeight);




        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    private void damagedMonster(int i) {
        if (i != 999) {
            System.out.println("Hit");
        }else {
            System.out.println("miss");
        }
    }


    private void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false) {
                life -= 1;
                invincible = true;
            }
        }


    }

    private void interactMonster(int i) {
        if (game.keyHandler.isSpacePressed() == true) {
            attacking = true;
        }
    }

    public void interactNPC(int i){
        if(keyHandler.isEnterPressed() == true){
            if(i != 999){
                //attackCanceled=true;
                game.gameState = game.dialogueState;
                game.npc[game.currentMap][i].speak();
            }
            //gp.playSE(7);
        }
    }


    public void draw(GraphicsContext gc){
        Image image = null;

        //temp variable
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (attacking == false) {
                    if (spriteNum == 1){
                        //playerImageView.setImage(up1);
                        image = up1;
                    }
                    else if(spriteNum == 2){
                        image = up2;
                    }
                }
                if (attacking == true) {
                    tempScreenY = screenY - game.tileSize;
                    if (spriteNum == 1){
                        image = attackUp1;
                    }
                    else if(spriteNum == 2){
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if (attacking == false) {
                    if (spriteNum == 1){

                        image = down1;
                    }
                    else if(spriteNum == 2){
                        image = down2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1){

                        image = attackDown1;
                    }
                    else if(spriteNum == 2){
                        image = attackDown2;
                    }
                }
                break;
            case "right":
                if (attacking == false) {
                    if (spriteNum == 1){

                        image = right1;
                    }
                    else if(spriteNum == 2){
                        image = right2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1){

                        image = attackRight1;
                    }
                    else if(spriteNum == 2){
                        image = attackRight2;
                    }
                }
                break;
            case "left":
                if (attacking == false) {
                    if (spriteNum == 1){

                        image = left1;
                    }
                    else if(spriteNum == 2){
                        image = left2;
                    }
                }
                if (attacking == true) {
                    tempScreenX = screenX - game.tileSize;
                    if (spriteNum == 1){

                        image = attackLeft1;
                    }
                    else if(spriteNum == 2){
                        image = attackLeft2;
                    }
                }
                break;
            // Handle other directions similarly
        }

        if (invincible == true) {
            gc.setGlobalAlpha(0.3);
        }
        gc.drawImage(image, tempScreenX, tempScreenY);

        //RESET ALPHA
        gc.setGlobalAlpha(1);


        //DEBUG...
//        gc.setFont(new Font("Arial", 26.0));
//        gc.setFill(Color.WHITE);
//        String text = "Invincible: " + invincibleCounter;
//        gc.fillText(text, 10, 400);
    }
}


