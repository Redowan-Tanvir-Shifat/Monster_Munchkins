package com.example.return_3.entity;

import com.example.return_3.main.EventHandler;
import com.example.return_3.main.Game;
import com.example.return_3.main.KeyHandler;
import com.example.return_3.object.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
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


        inventory.add(new OBJ_Helmet(game));
        inventory.add(new OBJ_Ladi(game));

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
//        if(currentWeapon.type==type_sword) {
//            attackUp1 = loadImage("/player/attacking_up_1.png", game.tileSize, game.tileSize * 2);
//            attackUp2 = loadImage("/player/attacking_up_2.png", game.tileSize, game.tileSize * 2);
//            attackDown1 = loadImage("/player/attacking_down_1.png", game.tileSize, game.tileSize * 2);
//            attackDown2 = loadImage("/player/attacking_down_2.png", game.tileSize, game.tileSize * 2);
//            attackLeft1 = loadImage("/player/attacking_left_1.png", game.tileSize * 2, game.tileSize);
//            attackLeft2 = loadImage("/player/attacking_left_2.png", game.tileSize * 2, game.tileSize);
//            attackRight1 = loadImage("/player/attacking_right_1.png", game.tileSize * 2, game.tileSize);
//            attackRight2 = loadImage("/player/attacking_right_2.png", game.tileSize * 2, game.tileSize);
//
//            //System.out.println("Player loaded");
//        }
        attackUp1 = loadImage("/player/attacking_up_1.png", game.tileSize, game.tileSize * 2);
        attackUp2 = loadImage("/player/attacking_up_2.png", game.tileSize, game.tileSize * 2);
        attackDown1 = loadImage("/player/attacking_down_1.png", game.tileSize, game.tileSize * 2);
        attackDown2 = loadImage("/player/attacking_down_2.png", game.tileSize, game.tileSize * 2);
        attackLeft1 = loadImage("/player/attacking_left_1.png", game.tileSize * 2, game.tileSize);
        attackLeft2 = loadImage("/player/attacking_left_2.png", game.tileSize * 2, game.tileSize);
        attackRight1 = loadImage("/player/attacking_right_1.png", game.tileSize * 2, game.tileSize);
        attackRight2 = loadImage("/player/attacking_right_2.png", game.tileSize * 2, game.tileSize);
    }




    public void setDefaultValues(){
        setDefaultPositions();
         speed=(int) (250*game.targetFrameTime); //pixel per second


        // <---------Player Status--------->
        level = 1;
        maxLife = 100;
        life = maxLife;
        strength = 1;    //The more strength he has, the more damage he gives...
        dexterity = 1;   // The more dexterity he has, the less damage he receives...
        exp = 0;
        nextLevelExp = 20;
        coin = 8000;
        currentWeapon = new OBJ_Sword_Normal(game);
        currentShield = new OBJ_Shield_Wood(game);
        projectile = new OBJ_Fireball(game);
        attack = getAttack();    // The total attack value is decided by strength and weapon...
        defense = getDefense();   // The total defence value is decided by dexterity and shield...

        energy = 180;
        maxEnergy = 200;
        maxMana = 4;
        mana = maxMana;
        //ammo=10;


//        currentWeapon=new OBJ_Axe(gp);
//        projectile= new OBJ_Fireball(gp);
//        //this is for testing that you can use anything to throw .
//        //projectile= new OBJ_Rock(gp);
//        attack=getAttack(); //the total attack value is decided by the strength and weapon
//        defense=getDefense();// the total defense value is decided by the dexterity and shield
    }

    private int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }
    private int getAttack() {
        return attack = strength * currentWeapon.attackValue;
    }

    public void setDefaultPositions(){
        worldX= game.tileSize * 41;
        worldY= game.tileSize * 135;
        direction="down";
    }
    public void setHospitalPosition() {
        worldX = game.tileSize * 151;
        worldY = game.tileSize * 136;
        direction = "down";
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

        }//END  of IF statement

        if(game.keyHandler.isFKeyPressed()==true && projectile.alive==false
                && shotAvailableCounter==30 && projectile.haveResource(this)==true){
            //Set default COORDINATES, DIRECTION AND USER
            projectile.set(worldX,worldY,direction, true, this);

            //SUBTRACT THE COST MANA/ AMMO ETC
            projectile.subtractResource(this);

            // ADD IT TO THE LIST
            game.projectileList.add(projectile);

            shotAvailableCounter=0;
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
        if(shotAvailableCounter<30){
            shotAvailableCounter++;
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
            damagedMonster(monsterIndex,attack);
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

    public void damagedMonster(int i,int attack) {
        if (i != 999) {
            if (game.monster[game.currentMap][i].invincible == false) {

                int damage = attack - game.monster[game.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }

                game.monster[game.currentMap][i].life -= damage;
                game.monster[game.currentMap][i].invincible = true;
                game.monster[game.currentMap][i].damageReaction();

                if (game.monster[game.currentMap][i].life <= 0) {
                    game.monster[game.currentMap][i].dying = true;


                    game.ui.uiMainGame.addMessage("Killed the " + game.monster[game.currentMap][i].name + "!");
                    exp += game.monster[game.currentMap][i].exp;
                    game.ui.uiMainGame.addMessage(" EXP + " + game.monster[game.currentMap][i].exp);

                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(30), event -> {
                        game.assetSetter.setMonster();
                    }));

                    timeline.play();


                }
            }

        }
    }


    private void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false && game.monster[game.currentMap][i].dying==false) {

                int damage = game.monster[game.currentMap][i].attack - defense;
                if (damage < 0) {
                    damage = 0;
                }

                life -= damage;
                invincible = true;

                if (life >= 0 && life <= 20) {
                    game.ui.uiMainGame.addMessage( "Careful, " + life + " % Life left!");
                }
                if (life <= 0) {
                    dying = true;
                    setHospitalPosition();
                    keyHandler.setBooleanAll(false);
                    life = maxLife;
                    if (coin >= 300) {
                        coin -= 300;
                    }
                    else {
                        coin = 0;
                    }
                }
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

                game.npc[game.currentMap][i].speak();
            }
            //gp.playSE(7);
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp + 30;   // We will use fibonacci series
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();
            game.gameState = game.dialogueState;
            game.ui.uiMainGame.currentDialogue = " Congratulations! \nYou are level in " + level + " now.";
        }
    }

    public void selectItem(){
        int itemIndex=game.ui.uiMainGame.getItemIndexOnSlot(game.ui.uiMainGame.playerSlotCol,game.ui.uiMainGame.playerSlotRow);
        if(itemIndex<inventory.size()){
            Entity selectedItem=inventory.get(itemIndex);
            //We need to fix this type_sword or something else
            if(selectedItem.type==type_sword|| selectedItem.type==type_axe){
                currentWeapon=selectedItem;
                //update the attack method with proper power
                attack=getAttack();
                //loadPlayerAttackImages();
            }
            if(selectedItem.type==type_shield){
                currentShield=selectedItem;
                //update the defense method with proper defense power
                defense=getDefense();
            }
            if(selectedItem.type==type_consumable){
                //WE are gonna use this later
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
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
                    if(spriteNum == 2){
                        image = up2;
                    }
                }
                if (attacking == true) {
                    tempScreenY = screenY - game.tileSize;
                    if (spriteNum == 1){
                        image = attackUp1;
                    }
                    if(spriteNum == 2){
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if (attacking == false) {
                    if (spriteNum == 1){

                        image = down1;
                    }
                    if(spriteNum == 2){
                        image = down2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1){

                        image = attackDown1;
                    }
                    if(spriteNum == 2){
                        image = attackDown2;
                    }
                }
                break;
            case "left":
                if (attacking == false) {
                    if (spriteNum == 1){

                        image = left1;
                    }
                    if(spriteNum == 2){
                        image = left2;
                    }
                }
                if (attacking == true) {
                    tempScreenX = screenX - game.tileSize;
                    if (spriteNum == 1){
                        image = attackLeft1;
                    }
                    if(spriteNum == 2){
                        image = attackLeft2;
                    }
                }
                break;
            case "right":
                if (attacking == false) {
                    if (spriteNum == 1){

                        image = right1;
                    }
                    if(spriteNum == 2){
                        image = right2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1){

                        image = attackRight1;
                    }
                    if(spriteNum == 2){
                        image = attackRight2;
                    }
                }
                break;



            // Handle other directions similarly
        }

        if (invincible == true) {
            gc.setGlobalAlpha(0.3);
        }
        if (dying == true) {
            dyingAnimation(game.gc);
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


