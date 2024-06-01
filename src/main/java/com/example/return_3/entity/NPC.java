package com.example.return_3.entity;

import com.example.return_3.main.Game;
import com.example.return_3.object.OBJ_ChatBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class NPC extends Entity{
    Game game;
    public NPC(Game game) {
        super(game);
        this.game=game;
        direction ="down";
        speed=(int) (120*game.targetFrameTime);
        //set the collision part
        solidArea.setX(7);
        solidArea.setY(14);
        solidArea.setWidth(18);
        solidArea.setHeight(16);
        solidAreaDefaultX=(int)(solidArea.getX());
        solidAreaDefaultY=(int)(solidArea.getY());

        //getNPCImage();
        setDialogue();
    }
    public void getNPCImage(){

    }
    public void setDialogue(){

    }

    public void setAction(){
        if(onPath==true){
            searchPath(goalCol, goalRow);
            if(onPath==false){
                npcGone=true;
                System.out.println("NPC WORK DONE");
            }
        } else  {
            getRandomDirection();
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

        spriteCounter++;
        if (spriteCounter > 24) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        set_NPC_Area();

        if(type==type_npc){
            int xDistance=Math.abs(worldX-game.player.worldX);
            int yDistance=Math.abs(worldY-game.player.worldY);
            int tileDistance=(xDistance+yDistance)/game.tileSize;

            if(tileDistance<3 && npcGoneCommand==false){
                chatOnStatus=true;
            }else{
                chatOnStatus=false;
            }

            //npcGoneCommand==false
            if(chatOnStatus){
                chatCounter++;
                if (chatCounter <15 ) {
                    chatNum=1;


                } else if (chatCounter<30) {
                    chatNum=2;

                } else if (chatCounter<45) {
                    chatNum=3;

                } else if (chatCounter<60) {
                    chatNum=4;

                }else {
                    chatCounter = 0;
                }
            }

        }
    }


    public void checkCollision(){
        collisionOn=false;
        //CHeching part of collision so that entity got collision and can not move
        game.cChecker.checkTile(this);
//        game.cChecker.checkPlayer(this);
//        gp.cChecker.checkObject(this,false); //its not player so its remain false
        game.cChecker.checkEntity(this,game.npc);
//        game.cChecker.checkEntity(this,game.monster);
        game.cChecker.checkEntity(this,game.iTile);
    }

        public void speak(){
            game.gameState = game.dialogueState;
            game.ui.uiMainGame.npc=this;
            game.ui.uiMainGame.currentDialogue=dialogue[dialogueIndex];
//             dialogueIndex++;
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

    public void draw(GraphicsContext gc){
        super.draw(gc);

        if(chatOnStatus==true){
            int screenX= worldX-game.player.worldX + game.player.screenX;
            int screenY= worldY-game.player.worldY + game.player.screenY;
            Image image= null;
            //System.out.println("draw method working");
            if(chatNum==1){
                image= new OBJ_ChatBox(game).down1;
            } else if (chatNum==2) {
                image= new OBJ_ChatBox(game).up1;
            }else if(chatNum==3){
                image= new OBJ_ChatBox(game).left1;
            } else if (chatNum==4) {
                image= new OBJ_ChatBox(game).right1;
            }
            gc.drawImage(image,screenX,screenY-(game.tileSize/2));
        }
    }


}
