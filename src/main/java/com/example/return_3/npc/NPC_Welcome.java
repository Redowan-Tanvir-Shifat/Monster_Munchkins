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
        goalCol=84;
        goalRow=113;
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
        up1=loadImage( "/npc/oldman_up_1.png",game.tileSize,game.tileSize);
        up2= loadImage("/npc/oldman_up_2.png",game.tileSize,game.tileSize);
        down1= loadImage("/npc/oldman_down_1.png",game.tileSize,game.tileSize);
        down2= loadImage("/npc/oldman_down_2.png",game.tileSize,game.tileSize);
        left1=loadImage ("/npc/oldman_left_1.png",game.tileSize,game.tileSize);
        left2= loadImage("/npc/oldman_left_2.png",game.tileSize,game.tileSize);
        right1= loadImage("/npc/oldman_right_1.png",game.tileSize,game.tileSize);
        right2= loadImage("/npc/oldman_right_2.png",game.tileSize,game.tileSize);

    }
    //set dialogue
    public void setDialogue(){
        dialogue[0]="Greetings, brave hunter!\nWelcome to the island. Our island is in grave danger. Monsters have overrun our home" ;
        dialogue[1]="Your mission is to kill all the monsters and set us free.\n Here are the guidelines to help you: " ;
        dialogue[2]="1. Use W/A/S/D or Arrow Keys to move.\n2. Press Enter to interact with game properties.\n" ;
        dialogue[3]="3. Press Space to attack, V to defend.\n4. Press C to see your inventory.\n" ;
        dialogue[4]="Please, we are counting on you to save us.\n Good luck, brave hunter!";
    }
}
