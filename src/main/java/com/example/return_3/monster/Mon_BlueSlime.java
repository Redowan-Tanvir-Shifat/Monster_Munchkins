package com.example.return_3.monster;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.object.OBJ_Rock;

import java.util.Random;

public class Mon_BlueSlime extends Entity {
    Game game;
    public Mon_BlueSlime(Game game) {
        super(game);
        this.game=game;
        name = "Red Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        type = type_monster;
        maxLife = 8;
        life = maxLife;
        attack = 20;
        defense = 0;
        exp = 2;
        projectile=new OBJ_Rock(game);

        //set the SOLID AREA for  collision part
        solidArea.setX(1);
        solidArea.setY(10);
//        solidArea.width=42;
//        solidArea.height=30;
        solidArea.setWidth(30);
        solidArea.setHeight(23);
        solidAreaDefaultX = (int)(solidArea.getX());
        solidAreaDefaultY = (int)(solidArea.getY());

        getImage();
    }
    public void getImage(){
        up1 = loadImage( "/monster/blueslime_down_1.png",game.tileSize,game.tileSize);
        up2 = loadImage("/monster/blueslime_down_2.png",game.tileSize,game.tileSize);
        down1 = loadImage("/monster/blueslime_down_1.png",game.tileSize,game.tileSize);
        down2 = loadImage("/monster/blueslime_down_2.png",game.tileSize,game.tileSize);
        left1 =loadImage ("/monster/blueslime_down_1.png",game.tileSize,game.tileSize);
        left2 = loadImage("/monster/blueslime_down_2.png",game.tileSize,game.tileSize);
        right1 = loadImage("/monster/blueslime_down_1.png",game.tileSize,game.tileSize);
        right2 = loadImage("/monster/blueslime_down_2.png",game.tileSize,game.tileSize);
    }
    public void setAction(){
        if(onPath==true){
            goalCol=game.player.worldX/game.tileSize;
            goalRow=game.player.worldY/game.tileSize;
            searchPath(goalCol, goalRow);
        } else  {
            actionLookCounter++;
            Random random= new Random();
            int actionLookCounterLimit =random.nextInt(50)+90;

            if(actionLookCounter>actionLookCounterLimit){//for two seconds it means

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


    }
    public void damageReaction(){
        actionLookCounter = 0;
        onPath=true;
    }
}
