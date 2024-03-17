package com.example.return_3.interactiveTile;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

import java.awt.*;

public class CuttableTree extends InteractiveTile{
    Game game;
    public CuttableTree(Game game, int col, int row) {
        super(game,col,row);
        this.game=game;
        life=3;
        this.worldX=game.tileSize*col;
        this.worldY=game.tileSize*row;
        down1=loadImage("/tiles_interactive/drytree.png",game.tileSize,game.tileSize);
        destructible=true;
        life=3;
    }


    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem=false;
        if(entity.currentWeapon.type==type_axe){
            isCorrectItem=true;
        }
        return isCorrectItem;

    }
//    public void playSE(){
//        gp.playSE(11);
//    }
    public InteractiveTile getDestryoedForm(){
        InteractiveTile tile= new CuttableTree_Parts(game, worldX/game.tileSize, worldY/game.tileSize);
        return tile;
    }
    public Color getParticleColor(){ //this indicates the color of the particle
        Color color= new Color(65,50,30);
        return color;
    }
    public int getParticleSize(){// this indicates the size of the particle
        int size=6;
        return size;
    }
    public int getParticleSpeed(){ //this indicates the speed of the particle
        int speed=1;
        return speed;
    }

    public int getParticleMaxLife(){ //this indicates how long the particle will last
        int maxLife=20;
        return maxLife;
    }


}


