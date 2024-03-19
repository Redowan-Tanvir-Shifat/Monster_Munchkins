package com.example.return_3.monster;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.object.OBJ_Coin;
import com.example.return_3.object.OBJ_Heart;
import com.example.return_3.object.OBJ_Potion_Red;
import com.example.return_3.object.OBJ_Rock;

import java.util.Random;



public class Mon_RedSlime extends Entity {
    Game game;
    public Mon_RedSlime(Game game) {
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
        up1 = loadImage( "/monster/redslime_down_1.png",game.tileSize,game.tileSize);
        up2 = loadImage("/monster/redslime_down_2.png",game.tileSize,game.tileSize);
        down1 = loadImage("/monster/redslime_down_1.png",game.tileSize,game.tileSize);
        down2 = loadImage("/monster/redslime_down_2.png",game.tileSize,game.tileSize);
        left1 =loadImage ("/monster/redslime_down_1.png",game.tileSize,game.tileSize);
        left2 = loadImage("/monster/redslime_down_2.png",game.tileSize,game.tileSize);
        right1 = loadImage("/monster/redslime_down_1.png",game.tileSize,game.tileSize);
        right2 = loadImage("/monster/redslime_down_2.png",game.tileSize,game.tileSize);
    }
    public void setAction(){
        actionLookCounter++;
        Random random= new Random();
        int actionLookCounterLimit =random.nextInt(40)+100;
        if(actionLookCounter > actionLookCounterLimit){//for two seconds it means

            int i = random.nextInt(100) + 1; //we add 1 because otherwise it will catch 0 to 99.. we want to avoid 0 here
            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction="down";
            }
            if(i > 50 && i<= 75){
                direction="left";
            }
            if(i > 75 && i <= 100){
                direction="right";
            }
            actionLookCounter=0;
        }


 //       AI method for throwing rock
        int i=new Random().nextInt(100)+1;
        if(i>99 && projectile.alive==false&&shotAvailableCounter==30){
            projectile.set(worldX,worldY,direction,true,this);
            //game.projectileList.add(projectile);

            for (int ii = 0; ii < game.projectile[game.currentMap].length; ii++) {
                if (game.projectile[game.currentMap][ii] == null) {
                    game.projectile[game.currentMap][ii] = projectile;
                    break;
                }
            }

            shotAvailableCounter=0;

        }
    }
    public void damageReaction(){
        actionLookCounter = 0;
        direction = game.player.direction;

//        if(actionLookCounter<50){
//            speed=4;
//        }else{
//            speed=1;
//        }
    }
    public void checkDrop(){
        //CAST A DIE
        int i=new Random().nextInt(100)+1;
        //SET THE MONSTER DROP
        if(i<50){
            dropItem(new OBJ_Coin(game));
        }if(i>=50&&i<75){
            dropItem(new OBJ_Heart(game));
        }if(i>=75&&i<100){
            dropItem(new OBJ_Potion_Red(game));
        }
    }
}

