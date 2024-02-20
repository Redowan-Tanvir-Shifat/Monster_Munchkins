package com.example.return_3.entity;

import com.example.return_3.main.Game;

public class NPC_School extends NPC{
    Game game;
    public NPC_School(Game game) {
        super(game);
        this.game=game;
        getNPCImage();
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

    public void speak(){
        super.speak();
    }
}
