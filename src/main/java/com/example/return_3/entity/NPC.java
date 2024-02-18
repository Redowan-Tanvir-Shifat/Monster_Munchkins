package com.example.return_3.entity;

import com.example.return_3.main.Game;

import java.util.Random;

public class NPC extends Entity{
    Game game;
    NPC(Game game) {
        super(game);
        this.game=game;
        direction ="down";
        speed=(int) (100*game.targetFrameTime);
        //set the collision part
        solidArea.setX(0);
        solidArea.setY(10);
        solidArea.setWidth(32);
        solidArea.setHeight(22);
        solidAreaDefaultX=(int)(solidArea.getX());
        solidAreaDefaultY=(int)(solidArea.getY());

        //getNPCImage();
        setDialogue();
    }
    public void getNPCImage(){

//        up1=loadImage( "/npc/oldman_up_1.png",game.tileSize,game.tileSize);
//        up2= loadImage("/npc/oldman_up_2.png",game.tileSize,game.tileSize);
//        down1= loadImage("/npc/oldman_down_1.png",game.tileSize,game.tileSize);
//        down2= loadImage("/npc/oldman_down_2.png",game.tileSize,game.tileSize);
//        left1=loadImage ("/npc/oldman_left_1.png",game.tileSize,game.tileSize);
//        left2= loadImage("/npc/oldman_left_2.png",game.tileSize,game.tileSize);
//        right1= loadImage("/npc/oldman_right_1.png",game.tileSize,game.tileSize);
//        right2= loadImage("/npc/oldman_right_2.png",game.tileSize,game.tileSize);
    }
    public void setDialogue(){
        dialogue[0]="Hello";
        dialogue[1]="So, you've come to this island to \nfind the treasure";
        dialogue[2]="I used to be a great wizard but now... \ni'm a bit too old for taking an adventure.";
        dialogue[3]="Well, good luck on you.";
    }

    public void setAction(){
        actionLookCounter++;
        if(actionLookCounter==100){//for two seconds it means
            Random random= new Random();
            int i=random.nextInt(100)+1; //we add 1 because otherwise it will catch 0 to 99.. we want to avoid 0 here
            if(i<=25){
                direction="up";
            }
            if(i>25&&i<=50){
                direction="down";
            }
            if(i>50&&i<=75){
                direction="left";
            }
            if(i>75&&i<=100){
                direction="right";
            }
            actionLookCounter=0;
        }
    }

//        public void speak(){
//        super.speak();
//        }
}
