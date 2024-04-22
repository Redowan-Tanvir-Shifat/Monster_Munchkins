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
    public Mon_RedSlime(Game game,int area) {
        super(game);
        this.game=game;
        monster_area=area;
        name = "Red Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        type = type_monster;
        maxLife = 10;
        life = maxLife;
        attack = 4;
        defense = 0;
        exp = 3;
        projectile = new OBJ_Rock(game);

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

        if (onPath == true) {

            // Check if stop chasing...
            checkStopChasingOrNot(game.player, 10, 100);

            // Search the direction to go...
            searchPath(getGoalCol(game.player), getGoalRow(game.player));

            // Check if shoot a projectile...
            checkShootOrNot(100, 30);

        }
        else {

            // Check if it starts chasing...
            checkStartChasingOrNot(game.player, 5, 100);

            // Get a random direction...
            getRandomDirection();
        }
    }


    public void damageReaction(){
        actionLookCounter = 0;
        switch (game.player.direction){
            case "up": direction = "down"; break;
            case "left": direction = "right"; break;
            case "right": direction = "left"; break;
            case "down": direction = "up"; break;
        }
    }

    public void checkDrop(){
        // CAST A DIE
        int i = new Random().nextInt(100)+1;
        // SET THE MONSTER DROP
        if (i < 50){
            dropItem(new OBJ_Coin(game));
        }
        if (i >= 50 && i < 75){
            dropItem(new OBJ_Heart(game));
        }
        if (i >= 75 && i < 100){
            dropItem(new OBJ_Potion_Red(game));
        }
    }

}

