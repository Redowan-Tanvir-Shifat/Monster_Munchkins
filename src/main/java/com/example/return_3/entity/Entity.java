package com.example.return_3.entity;

import com.example.return_3.main.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;


public class Entity {
    //VARIABLES
    public Image image,image2,image3;
    public String name;
    public boolean collision = false;
    public int worldX,worldY;
    public int speed;

    public Image up1,up2,down1, down2,left1,left2,right1,right2;
    public Image attackUp1,attackUp2,attackDown1, attackDown2,attackLeft1,attackLeft2,attackRight1,attackRight2;
    public String direction="down";
    public int spriteCounter=0;
    public  int spriteNum=1;

  //  COLLision
  //  part 6 collision
    public Rectangle solidArea = new Rectangle(0,0,32,32);
    //part 8 Object Interaction part starts
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX,solidAreaDefaultY;
    //part 8 Object Interaction part  ends
    public boolean collisionOn= false;
    //to move npc good way
    public int actionLookCounter=0;
    String[] dialogue =new String[20];
    int dialogueIndex=0;

    //CHARACTER STATUS
    public int maxLife;
    public int life;

    public int maxMana;
    public int mana;
    public  int ammo;

    public boolean invincible=false;
    public int invincibleCounter=0;
    //Character ATributes
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    //public Projectile projectile;

    //ITEM ATTRIBUTES
    public ArrayList<Entity> inventory= new ArrayList<>();
    public final int maxInventorySize=20;
    public int attackValue;
    public int defenseValue;
    public String description="";
    public int useCost;
    public int value;

    //Type of character;
    public int type; // 0 for player, 1 for npc, 2 for monster
    public final int type_player=0;
    public final int type_npc=1;
    public final int type_monster=2;
    public final int type_sword=3;
    public final int type_axe=4;
    public final int type_shield=5;
    public final int type_consumable=6;
    public final int type_pickupOnly=7;

    //Monster
    //Attack
    public boolean attacking= false;
    public boolean alive= true;
    public boolean dying= false;
    public boolean hpBarOn=false;

    int dyingCounter=0;
    int hpBarCounter=0;
    public int shotAvailableCounter=0;
    Game game;
    public Entity(Game game){
        this.game=game;
    }

//    public void speak(){
//        if(dialogue[dialogueIndex]==null){
//            dialogueIndex=0;
//        }
//       // gp.ui.currentDialogue=dialogue[dialogueIndex];
//        dialogueIndex++;
//        switch (game.player.direction){
//            case "up":
//                direction="down";
//                break;
//            case "left":
//                direction="right";
//                break;
//            case "right":
//                direction="left";
//                break;
//            case "down":
//                direction="up";
//                break;
//        }
//    }
//

    public void use(Entity entity){}
    public void checkDrop(){}

    //create two method for running our NPC
    public void setAction(){}

    public void damageReaction(){}
    public void update(){
        setAction();
        collisionOn=false;
        //CHeching part of collision so that entity got collision and can not move
        game.cChecker.checkTile(this);
//        gp.cChecker.checkObject(this,false); //its not player so its remain false
//        gp.cChecker.checkEntity(this,gp.npc);
//        gp.cChecker.checkEntity(this,gp.monster);
//        gp.cChecker.checkEntity(this,gp.iTile);
        boolean contactPlayer= game.cChecker.checkPlayer(this);

//
//        if(this.type ==type_monster && contactPlayer==true){
//            damagePlayer(attack);
//        }


        //if collisionOn is false then player can be able to move
        if(collisionOn==false){
            switch (direction){
                case "up":worldY -= speed;
                    break;
                case "down":worldY+= speed;
                    break;
                case "left":worldX -= speed;
                    break;
                case "right":worldX += speed;
                    break;
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
                        image= up1;
                    }
                    if(spriteNum==2){
                        image=up2;
                    }
                    break;
                case"down":
                    if(spriteNum==1){
                        image= down1;
                    }
                    if(spriteNum==2){
                        image=down2;
                    }
                    break;
                case"left":
                    if(spriteNum==1){
                        image= left1;
                    }
                    if(spriteNum==2){
                        image=left2;
                    }
                    break;
                case"right":
                    if(spriteNum==1){
                        image= right1;
                    }
                    if(spriteNum==2){
                        image=right2;
                    }
                    break;
            }

            //MONSTER HEALTH BAR
//            if(type==type_monster && hpBarOn==true){ //type 2 for monster
//                double oneScale= (double)game.tileSize/maxLife;
//                double hpPerValue=oneScale*life;
//
//                //set background color
//                g2.setColor(new Color(35,35,35));
//                g2.fillRect(screenX-1,screenY-16,gp.tileSize+2,12);
//
//                g2.setColor(new Color(255,0,30));
//                g2.fillRect(screenX,screenY-15,(int)hpPerValue,10);
//                hpBarCounter++;
//                if(hpBarCounter>600){
//                    hpBarCounter=0;
//                    hpBarOn=false;
//
//                }
//            }


//
//            if(invincible==true){
//                hpBarOn=true;
//                hpBarCounter=0;
//                changeAlpha(g2,0.4F);
//            }
//            if(dying==true){
//                dyingAnimation(g2);
//            }
            gc.drawImage(image,screenX,screenY);

            //Reset opacity
//            changeAlpha(g2,1F);
        }
    }



    public void speak(){
        if(dialogue[dialogueIndex]==null){
            dialogueIndex=0;
        }
        game.ui.currentDialogue=dialogue[dialogueIndex];
        dialogueIndex++;
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
