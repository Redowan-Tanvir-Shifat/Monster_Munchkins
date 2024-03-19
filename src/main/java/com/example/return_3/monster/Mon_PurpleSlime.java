package com.example.return_3.monster;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.object.OBJ_Rock;

import java.util.Random;

public class Mon_PurpleSlime extends Entity {
    Game game;
    public Mon_PurpleSlime(Game game) {
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
        solidArea.setWidth(30);
        solidArea.setHeight(23);
        solidAreaDefaultX = (int)(solidArea.getX());
        solidAreaDefaultY = (int)(solidArea.getY());

        getImage();
    }
    public void getImage(){
        up1 = loadImage( "/monster/purpleslime_down_1.png",game.tileSize,game.tileSize);
        up2 = loadImage("/monster/purpleslime_down_2.png",game.tileSize,game.tileSize);
        down1 = loadImage("/monster/purpleslime_down_1.png",game.tileSize,game.tileSize);
        down2 = loadImage("/monster/purpleslime_down_2.png",game.tileSize,game.tileSize);
        left1 =loadImage ("/monster/purpleslime_down_1.png",game.tileSize,game.tileSize);
        left2 = loadImage("/monster/purpleslime_down_2.png",game.tileSize,game.tileSize);
        right1 = loadImage("/monster/purpleslime_down_1.png",game.tileSize,game.tileSize);
        right2 = loadImage("/monster/purpleslime_down_2.png",game.tileSize,game.tileSize);
    }
    public void update() {
        super.update();
        int xDistance = Math.abs(worldX - game.player.worldX);
        int yDistance = Math.abs(worldY - game.player.worldY);
        int tileDistance = (xDistance + yDistance) / game.tileSize;
        if (onPath == false && tileDistance < 5) {
            System.out.println("distance is less than 5");
            int i = new Random().nextInt(100) + 1;
            if(i>50){
                onPath =true;
            }
        }

//        if(onPath=true && tileDistance>20){
//            System.out.println("distance is more than 20");
//            onPath=false;
//        }
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
        switch (game.player.direction){
            case "up": direction="down"; break;
            case "left": direction="right"; break;
            case "right": direction="left"; break;
            case "down": direction="up"; break;
        }
    }
}
