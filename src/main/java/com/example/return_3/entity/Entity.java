package com.example.return_3.entity;

import com.example.return_3.main.Game;
import com.example.return_3.object.OBJ_ChatBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;


public class Entity {
    Game game;

    // <----------VARIABLES---------->
    public String name;
    public int speed;
    public int worldX, worldY;
    public String direction = "down";
    public  int spriteNum = 1;
    public int chatNum = 1;
    public int goalCol;
    public int goalRow;
    public int dialogueIndex = 0;
    public String[] dialogue =new String[20];

    //for space invaders
    public int posX,posY;



    // <----------IMAGE--------->
    public Image image1, image2, image3;
    public Image up1, up2, down1, down2, left1, left2, right1, right2;
    public Image attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;



    // <--------Boolean-------->
    public boolean collision = false;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean chatOnStatus = false;

    //For SpaceInvadors
    public boolean destroyed=false;
    public boolean bottomTouched=false;



    // <---------COUNTER--------->
    public int spriteCounter = 0;
    public int chatCounter = 0;
    public int actionLookCounter=0;
    public int invincibleCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int shotAvailableCounter = 0;



    // <---------CHARACTER STATUS---------->
    public int maxLife;
    public int life;

    public int maxMana;
    public int mana;
    public  int ammo;



    // <------------CHARACTER ATTRIBUTES----------->
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int energy;
    public int maxEnergy;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;



    // <----------ITEM ATTRIBUTES---------->
    public int attackValue;
    public int defenseValue;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public String description = "";
    public int useCost;
    public int value;



    // <---------Type of All Element--------->
    public int type; // 0 for player, 1 for npc, 2 for monster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_wizard = 2;
    public final int type_monster = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;


  // <-------------COLLiSION------------->
    public Rectangle solidArea = new Rectangle(0,0,32,32);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX,solidAreaDefaultY;



    public Entity(Game game){
        this.game=game;
    }

    public void use(Entity entity){}
    public void checkDrop(){}

    //create two method for running our NPC
    public void setAction(){}
    public void damageReaction(){}
    public void checkCollision(){
        collisionOn=false;
        //CHeching part of collision so that entity got collision and can not move
        game.cChecker.checkTile(this);
//        gp.cChecker.checkObject(this,false); //its not player so its remain false
        game.cChecker.checkEntity(this,game.npc);
        game.cChecker.checkEntity(this,game.monster);
//        gp.cChecker.checkEntity(this,gp.iTile);
        boolean contactPlayer= game.cChecker.checkPlayer(this);

//
        if(this.type == type_monster && contactPlayer == true){
//            damagePlayer(attack);
            if (game.player.invincible == false) {
                int damage = attack - game.player.defense;
                if (damage < 0) {
                    damage = 0;
                }
                game.player.life -= damage;
                game.player.invincible = true;


            }
        }

    }
    public void update(){
        setAction();
        checkCollision();

        //if collisionOn is false then player can be able to move
        if(collisionOn == false){
            switch (direction){
                case "up":worldY -= speed; break;
                case "down":worldY+= speed; break;
                case "left":worldX -= speed; break;
                case "right":worldX += speed; break;
            }
        }

        //part 6 collision part ends


        spriteCounter++;
        if (spriteCounter > 24) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if(type==type_npc){

            int xDistance=Math.abs(worldX-game.player.worldX);
            int yDistance=Math.abs(worldY-game.player.worldY);
            int tileDistance=(xDistance+yDistance)/game.tileSize;

            if(tileDistance<3){
                chatOnStatus=true;
            }else{
                chatOnStatus=false;
            }


            if( chatOnStatus==true){
                chatCounter++;
                if (chatCounter <15 ) {
                    chatNum=1;


                } else if (chatCounter >=15&& chatCounter<30 ) {
                    chatNum=2;

                } else if (chatCounter >=30&& chatCounter<45 ) {
                    chatNum=3;

                } else if (chatCounter >=45&& chatCounter<60 ) {
                    chatNum=4;

                }else {
                    chatCounter = 0;
                }
            }

        }

//        if(invincible==true){
//            invincibleCounter++;
//            if(invincibleCounter>40){
//                invincible=false;
//                invincibleCounter=0;
//            }
//        }
//        if(shotAvailableCounter<30){
//            shotAvailableCounter++;
//        }

    }


    public void draw(GraphicsContext gc){
        Image image= null;
        int screenX= worldX-game.player.worldX + game.player.screenX;
        int screenY= worldY-game.player.worldY + game.player.screenY;


        //this process is for not drawing the whole world map but the map tiles we needed only
        if(worldX+ game.tileSize>game.player.worldX-game.player.screenX&&
                worldX-game.tileSize<game.player.worldX+game.player.screenX&&
                worldY+ game.tileSize>game.player.worldY-game.player.screenY&&
                worldY- game.tileSize<game.player.worldY+game.player.screenY
        ){
            switch (direction){
                case"up":
                    if(spriteNum==1){
                        image = up1;
                    }
                    if(spriteNum==2){
                        image = up2;
                    }
                    break;
                case"down":
                    if(spriteNum==1){
                        image = down1;
                    }
                    if(spriteNum==2){
                        image = down2;
                    }
                    break;
                case"left":
                    if(spriteNum==1){
                        image = left1;
                    }
                    if(spriteNum==2){
                        image = left2;
                    }
                    break;
                case"right":
                    if(spriteNum==1){
                        image = right1;
                    }
                    if(spriteNum==2){
                        image = right2;
                    }
                    break;
            }


            //Monster HP Bar......
            if (type == type_monster && hpBarOn == true) {
                double oneScale = (double)game.tileSize/maxLife;
                double hpBarValue = oneScale*life;


                gc.setFill(Color.RED);
                gc.fillRect(screenX, screenY - 5, (int)hpBarValue, 10);
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(1); // Width of the outline
                gc.strokeRect(screenX, screenY - 5, game.tileSize, 10);

                hpBarCounter++;
                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }


            if (invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                gc.setGlobalAlpha(0.4);
            }
            if (dying == true) {
                dyingAnimation(gc);
            }

            gc.drawImage(image,screenX,screenY, game.tileSize, game.tileSize);

            //<<<<<RESET ALPHA>>>>>
            gc.setGlobalAlpha(1);




        }
    }

    public void dyingAnimation(GraphicsContext gc) {
        dyingCounter++;
        int i = 5;
        if (dyingCounter <= i) {gc.setGlobalAlpha(0);}
        if (dyingCounter > i && dyingCounter <= i*2) {gc.setGlobalAlpha(1);}
        if (dyingCounter > i*2 && dyingCounter <= i*3) {gc.setGlobalAlpha(0);}
        if (dyingCounter > i*3 && dyingCounter <= i*4) {gc.setGlobalAlpha(1);}
        if (dyingCounter > i*4 && dyingCounter <= i*5) {gc.setGlobalAlpha(0);}
        if (dyingCounter > i*5 && dyingCounter <= i*6) {gc.setGlobalAlpha(1);}
        if (dyingCounter > i*6 && dyingCounter <= i*7) {gc.setGlobalAlpha(0);}
        if (dyingCounter > i*7 && dyingCounter <= i*8) {gc.setGlobalAlpha(1);}
        if (dyingCounter > i*8) {
            dying = false;
            alive = false;
        }
    }


    public void speak(){
        game.ui.npc=this;
        game.ui.currentDialogue=dialogue[dialogueIndex];
        // dialogueIndex++;
        switch (game.player.direction){
            case "up":
                direction="down";
                break;
            case "left":
                direction="right";
                break;
            case "right":
                direction="left";
                break;
            case "down":
                direction="up";
                break;
        }
    }



    public Image loadImage(String imagePath, int width, int height) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)), width, height, true, true);
    }
}
