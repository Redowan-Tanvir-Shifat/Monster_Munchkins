package com.example.return_3.entity;

import com.example.return_3.main.Game;

public class SignBoard extends NPC{
    Game game;
    public SignBoard(Game game) {
        super(game);
        this.game=game;
        getNPCImage();
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
        up1=loadImage( "/tiles_interactive/testBoard.png",75,75);
        up2=up1;
        down1=up1;
        down2= up1;
        left1=up1;
        left2= up1;
        right1= up1;
        right2= up1;
    }

    public void update(){}


}
