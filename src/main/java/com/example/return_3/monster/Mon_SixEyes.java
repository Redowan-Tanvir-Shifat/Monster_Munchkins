package com.example.return_3.monster;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.object.OBJ_Coin;
import com.example.return_3.object.OBJ_Rock;

import java.util.Random;

public class Mon_SixEyes extends Entity {
    Game game;
    String imgName;
    int speedCounter=0;
    Entity entity0;
    public Mon_SixEyes(Game game, int area) {
        super(game);
        this.game=game;
        monster_area=area;
        monster_type=type_sixEyes;
        name = "Six Eyes";
        defaultSpeed = 1;
        speed = defaultSpeed;
        type = type_monster;
        maxLife = 50;
        life = maxLife;
        attack = 15;
        defense = 0;
        exp = 7;
        projectile=new OBJ_Rock(game);

        //set the SOLID AREA for  collision part
        solidArea.setX(1);
        solidArea.setY(10);
        solidArea.setWidth(30);
        solidArea.setHeight(23);
        solidAreaDefaultX = (int)(solidArea.getX());
        solidAreaDefaultY = (int)(solidArea.getY());
        imgName="sixEyes";
        getImage();
        entity0= new Entity(game);
    }
    public void getImage(){
        up1 = loadImage( "/monster/" +

                imgName +
                "_up_1.png",game.tileSize,game.tileSize);
        up2 = loadImage("/monster/" +
                imgName +
                "_up_2.png",game.tileSize,game.tileSize);
        down1 = loadImage("/monster/" +
                imgName +
                "_down_1.png",game.tileSize,game.tileSize);
        down2 = loadImage("/monster/" +
                imgName +
                "_down_2.png",game.tileSize,game.tileSize);
        left1 =loadImage ("/monster/" +
                        imgName+
                "_left_1.png",game.tileSize,game.tileSize);
        left2 = loadImage("/monster/" +
                imgName+
                "_left_2.png",game.tileSize,game.tileSize);
        right1 = loadImage("/monster/" +
                imgName+
                "_right_1.png",game.tileSize,game.tileSize);
        right2 = loadImage("/monster/" +
                imgName+
                "_right_2.png",game.tileSize,game.tileSize);
    }


    public void increaseSpeed(int speed1,int speed2, int counter1,int counter2,int counter3,int counter4){
        speedCounter++;
        if(speedCounter>counter1&&speedCounter<counter2){
            speed=speed1;
        }else if(speedCounter>counter2&&speedCounter<counter3){
            speed=speed2;
        }else if(speedCounter>counter3&&speedCounter<counter4){
            speed=speed1;
        }
        else if (speedCounter>counter4) {
            speed=defaultSpeed;
            speedCounter=0;
        }
    }

    public void setAction(){
        if (onPath == true) {
        increaseSpeed(2,3,145,160,180,195);

            // Check if id stop chasing...
            checkStopChasingOrNot(game.player, 12, 100);

            // Search the direction to go...
            searchPath(getGoalCol(game.player), getGoalRow(game.player));

            // Check if shoot a projectile...
            //checkShootOrNot(200, 30);

        }
        else {
            // Check if it starts chasing...
            checkStartChasingOrNot(game.player, 8, 100);

            // Get a random direction...
            getRandomDirection();
        }

    }
//    public void getRandomDirection() {
//        actionLookCounter++;
//        Random random= new Random();
//        int actionLookCounterLimit = random.nextInt(50)+90;
//
//        if(actionLookCounter > actionLookCounterLimit){   // for two seconds it means
//
//            int x=random.nextInt(6)+1; //we add 1 because otherwise it will catch 0 to 99.. we want to avoid 0 here
//            int y=random.nextInt(6)+1; //we add 1 because otherwise it will catch 0 to 99.. we want to avoid 0 here
//            int z=random.nextInt(30)+1; //we add 1 because otherwise it will catch 0 to 99.. we want to avoid 0 here
//            if(z%4==0){
//                x=(-x);
//            }
//            else if(z%3==0){
//                y=(-y);
//            }else if(z%2==0){
//                x=(-x);
//                y=(-y);
//            }
//
//            entity0.worldX=this.worldX+(x*game.tileSize);
//            entity0.worldY=this.worldY+(y*game.tileSize);
//            checkCollision2();
//
//            if(collisionOn==true){
//                int col=entity0.worldX*game.tileSize;
//                int row=entity0.worldY*game.tileSize;
//                searchPath(col,row);
//            }
//
//        }
//    }

//    public void checkCollision2(){
//        collisionOn=false;
//        //CHeching part of collision so that entity got collision and can not move
//        game.cChecker.checkTile(entity0);
//        game.cChecker.checkPlayer(entity0);
////        gp.cChecker.checkObject(this,false); //its not player so its remain false
////        game.cChecker.checkEntity(this,game.npc);
//        game.cChecker.checkEntity(entity0,game.interactNpc);
//        game.cChecker.checkEntity(entity0,game.monster);
//        game.cChecker.checkEntity(entity0,game.iTile);
//
//    }
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
        //CAST A DIE
        int i = new Random().nextInt(100)+1;
        //SET THE MONSTER DROP
        if (i < 50) {
            dropItem(new OBJ_Coin(game));
        }
    }
}
