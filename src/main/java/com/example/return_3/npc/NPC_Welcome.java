package com.example.return_3.npc;

import com.example.return_3.entity.NPC;
import com.example.return_3.main.Game;
import com.example.return_3.object.OBJ_ChatBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class NPC_Welcome extends NPC {
    Game game;
    public NPC_Welcome(Game game) {
        super(game);
        this.game=game;
        type= type_npc;
        speed=1;
        getNPCImage();
        setDialogue();
    }
    public void checkCollision(){
        collisionOn=false;
        //CHeching part of collision so that entity got collision and can not move
        game.cChecker.checkTile(this);
        game.cChecker.checkPlayer(this);
//        gp.cChecker.checkObject(this,false); //its not player so its remain false
        game.cChecker.checkEntity(this,game.npc);
        game.cChecker.checkEntity(this,game.monster);
        game.cChecker.checkEntity(this,game.iTile);



    }
    public void getNPCImage(){
        up1=loadImage( "/npc/npc_townHall.png",game.tileSize,game.tileSize);
//        up2= loadImage("/npc/oldman_up_2.png",game.tileSize,game.tileSize);
//        down1= loadImage("/npc/oldman_down_1.png",game.tileSize,game.tileSize);
//        down2= loadImage("/npc/oldman_down_2.png",game.tileSize,game.tileSize);
//        left1=loadImage ("/npc/oldman_left_1.png",game.tileSize,game.tileSize);
//        left2= loadImage("/npc/oldman_left_2.png",game.tileSize,game.tileSize);
//        right1= loadImage("/npc/oldman_right_1.png",game.tileSize,game.tileSize);
//        right2= loadImage("/npc/oldman_right_2.png",game.tileSize,game.tileSize);
        up2=up1;
        down1=up1;
        down2= up1;
        left1=up1;
        left2= up1;
        right1= up1;
        right2= up1;
    }
    //set dialogue
    public void setDialogue(){
        dialogue[0]="Hello "+Game.gameInstance.user.getUsername()+"! \nWelcome to the Mysterious Island";
        dialogue[1]="People of this Island are very disturb\n of dangerous monster they killed villagers";
        dialogue[2]="Please save us from these dangerous monsters\nPeople can not live here in sound";
        dialogue[3]="Please help us by free the island from monsters!";
    }
    public void update(){
//        setAction();
//        checkCollision();

        //if collisionOn is false then player can be able to move
//        if(collisionOn == false){
//
//            switch (direction){
//                case "up":worldY -= speed; break;
//                case "down":worldY+= speed; break;
//                case "left":worldX -= speed; break;
//                case "right":worldX += speed; break;
//            }
//        }

//        spriteCounter++;
//        if (spriteCounter > 24) {
//            if (spriteNum == 1) {
//                spriteNum = 2;
//            } else if (spriteNum == 2) {
//                spriteNum = 1;
//            }
//            spriteCounter = 0;
//        }


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

    }

}
