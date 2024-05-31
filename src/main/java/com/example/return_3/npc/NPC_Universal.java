package com.example.return_3.npc;

import com.example.return_3.entity.NPC;
import com.example.return_3.main.Game;
import com.example.return_3.object.OBJ_ChatBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;



public class NPC_Universal extends NPC {
    Game game;
    String name;
    public NPC_Universal(Game game, String name) {
        super(game);
        this.game=game;
        this.name=name;
//        collisionOn=false;

        //type= type_npc;
        speed=(int) (65*game.targetFrameTime);
        getNPCImage();
        setDialogue();
    }
    public void getNPCImage(){
        int size=game.tileSize-8;
        up1=loadImage( "/npc/npc_"+name+"_up_1.png",size,size);
        up2= loadImage("/npc/npc_"+name+"_up_2.png",size,size);
        down1= loadImage("/npc/npc_"+name+"_down_1.png",size,size);
        down2= loadImage("/npc/npc_"+name+"_down_2.png",size,size);
        left1=loadImage ("/npc/npc_"+name+"_left_1.png",size,size);
        left2= loadImage("/npc/npc_"+name+"_left_2.png",size,size);
        right1= loadImage("/npc/npc_"+name+"_right_1.png",size,size);
        right2= loadImage("/npc/npc_"+name+"_right_2.png",size,size);
    }
    //set dialogue
    public void speak(){
//        super.speak();
    }
    public void checkCollision(){
        collisionOn=false;
        game.cChecker.checkTile(this);
        game.cChecker.checkEntity(this,game.npc);
        game.cChecker.checkEntity(this,game.iTile);
    }
    public void update(){
        getRandomDirection();
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
    }
}

