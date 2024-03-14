package com.example.return_3.entity;

import com.example.return_3.main.Game;

import java.util.Random;

public class NPC extends Entity{
    Game game;
    public NPC(Game game) {
        super(game);
        this.game=game;
        direction ="down";
        speed=(int) (120*game.targetFrameTime);
        //set the collision part
        solidArea.setX(7);
        solidArea.setY(14);
        solidArea.setWidth(18);
        solidArea.setHeight(16);
        solidAreaDefaultX=(int)(solidArea.getX());
        solidAreaDefaultY=(int)(solidArea.getY());

        //getNPCImage();
        setDialogue();
    }
    public void getNPCImage(){

//        up1=loadImage( "/npc/oldman_up_1.png",game.tileSize,game.tileSize);
//        up2= loadImage("/npc/oldman_up_2.png",game.tileSize,game.tileSize);
//        down1= loadImage("/npc/oldman_down_1.png",game.tileSize,game.tileSize);
//        down2= loadImage("/npc/oldman_down_2.png",game.tileSize,game.tileSize);
//        left1=loadImage ("/npc/oldman_left_1.png",game.tileSize,game.tileSize);
//        left2= loadImage("/npc/oldman_left_2.png",game.tileSize,game.tileSize);
//        right1= loadImage("/npc/oldman_right_1.png",game.tileSize,game.tileSize);
//        right2= loadImage("/npc/oldman_right_2.png",game.tileSize,game.tileSize);
    }
    public void setDialogue(){
        dialogue[0]="Hello";
        dialogue[1]="So, you've come to this island to \nfind the treasure";
        dialogue[2]="I used to be a great wizard but now... \ni'm a bit too old for taking an adventure.";
        dialogue[3]="Well, good luck on you.";
    }

    public void setAction(){
        if(onPath==true){
            searchPath(goalCol, goalRow);
        } else  {
            actionLookCounter++;
            if(actionLookCounter==100){//for two seconds it means
                Random random= new Random();
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

//        public void speak(){
//        super.speak();
//        }

    public void searchPath(int goalCol, int goalRow){

        int startCol=(int)(worldX+solidArea.getX())/game.tileSize;
        int startRow=(int)(worldY+solidArea.getY())/game.tileSize;

        game.pFinder.setNodes(startCol, startRow, goalCol,goalRow);

        if(game.pFinder.search()==true){
            //Next worldX & worldY
            int nextX= game.pFinder.pathList.get(0).col *game.tileSize;
            int nextY= game.pFinder.pathList.get(0).row *game.tileSize;

            //Entity's solidArea position
            int enleftX=(int)(worldX + solidArea.getX());
            int enRightX=(int)(worldX + solidArea.getX()+solidArea.getWidth());
            int enTopY=(int)(worldY+solidArea.getY());
            int enBottomY=(int)(worldY+solidArea.getY()+solidArea.getHeight());

            if(enTopY>nextY && enleftX>=nextX && enRightX< nextX+game.tileSize){
                direction= "up";
            }else if(enTopY<nextY && enleftX>=nextX && enRightX< nextX+game.tileSize){
                direction= "down";
            }else if(enTopY>= nextY && enBottomY<nextY+game.tileSize){
                //left or right
                if(enleftX>nextX){
                    direction="left";
                }if(enleftX<nextX){
                    direction="right";
                }
            } else if (enTopY>nextY && enleftX> nextX) {
                //up or left
                direction="up";
                checkCollision();
                if(collisionOn==true){
                    direction="left";
                }
            } else if (enTopY > nextY && enleftX < nextX) {
                //up or right
                direction="up";
                checkCollision();
                if(collisionOn==true){
                    direction="right";
                }
            }else if(enTopY< nextY && enleftX>nextX) {
                //down or left
                direction="down";
                checkCollision();
                if(collisionOn==true){
                    direction="left";
                }
            }else if(enTopY< nextY && enleftX<nextX) {
                //down or right
                direction="down";
                checkCollision();
                if(collisionOn==true){
                    direction="right";
                }
            }
            int nextCol=game.pFinder.pathList.get(0).col;
            int nextRow=game.pFinder.pathList.get(0).row;

            if(nextCol==goalCol&& nextRow==goalRow){
                onPath=false;
            }

        }
    }


}
