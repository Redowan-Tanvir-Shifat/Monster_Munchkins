package com.example.return_3.entity;


import com.example.return_3.main.Game;

import java.util.Random;


public class NPC_OldMan extends Entity{
    Game gp;
    public NPC_OldMan(Game gp){
        super(gp);
        this.gp=gp;
        direction ="down";
        speed=2;
        //set the collision part
        solidArea.setX(0);
        solidArea.setY(16);
        solidArea.setWidth(48);
        solidArea.setHeight(32);
        solidAreaDefaultX=(int)(solidArea.getX());
        solidAreaDefaultY=(int)(solidArea.getY());

        getNPCImage();
        setDialogue();
    }
    public void getNPCImage(){

        up1=loadImage( "/npc/oldman_up_1.png",gp.tileSize,gp.tileSize);
        up2= loadImage("/npc/oldman_up_2.png",gp.tileSize,gp.tileSize);
        down1= loadImage("/npc/oldman_down_1.png",gp.tileSize,gp.tileSize);
        down2= loadImage("/npc/oldman_down_2.png",gp.tileSize,gp.tileSize);
        left1=loadImage ("/npc/oldman_left_1.png",gp.tileSize,gp.tileSize);
        left2= loadImage("/npc/oldman_left_2.png",gp.tileSize,gp.tileSize);
        right1= loadImage("/npc/oldman_right_1.png",gp.tileSize,gp.tileSize);
        right2= loadImage("/npc/oldman_right_2.png",gp.tileSize,gp.tileSize);
    }

    //set dialogue
    public void setDialogue(){
        dialogue[0]="Hello";
        dialogue[1]="So, you've come to this island to \nfind the treasure";
        dialogue[2]="I used to be a great wizard but now... \ni'm a bit too old for taking an adventure.";
        dialogue[3]="Well, good luck on you.";
    }

    //this is kind of AI work
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

//    public void speak(){
//        super.speak();
//    }

}
