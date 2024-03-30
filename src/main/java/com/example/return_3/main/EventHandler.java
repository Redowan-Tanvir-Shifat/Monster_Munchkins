//package com.example.return_3.main;
//
//public class EventHandler {
//    Game gp;
//    int previousEventX, previousEventY;
//    boolean canTouchEvent=true;
//    int tempMap,tempCol,tempRow;
//    EventRect eventRect[][][];
//    public EventHandler(Game gp){
//        this.gp = gp;
//
//        //initialize the array of event Rect
//        eventRect=new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
//        int map=0;
//
//        int col=0;
//        int row=0;
//        while(map<gp.maxMap&&col<gp.maxWorldCol && row<gp.maxWorldRow){
//            eventRect[map][col][row]= new EventRect();
//            eventRect[map][col][row].setX(23);
//            eventRect[map][col][row].setY(23);
//            eventRect[map][col][row].setWidth(2);
//            eventRect[map][col][row].setHeight(2);
//            eventRect[map][col][row].eventRectDefaultX=(int)(eventRect[map][col][row].getX());
//            eventRect[map][col][row].eventRectDefaultY=(int)(eventRect[map][col][row].getY());
//
//            col++;
//            if(col==gp.maxWorldCol){
//                col=0;
//                row++;
//                if(row==gp.maxWorldRow){
//                    row=0;
//                    map++;
//                }
//            }
//
//        }
//    }
//
//    public void checkEvent(){
//
//        //Check if the player character is more than 1 tile away from the last event
//        int xDistance= Math.abs(gp.player.worldX-previousEventX);
//        int yDistance= Math.abs(gp.player.worldY-previousEventY);
//        int distance=Math.max(xDistance, yDistance);
//        if(distance> gp.tileSize){
//            canTouchEvent=true;
//        }
//
//        if(canTouchEvent) {
//            //Event happened
////            if (hit(0,27, 15, "right")) {damagePit(27, 15, gp.dialogueState);}
////            else if (hit(0,23, 12, "up")) {healingPool(23, 12, gp.dialogueState);}
////            else if (hit(0, 80, 89, "any")) {teleport(1,79,88,gp.transitionState);}
////            else if (hit(1, 79, 88, "any")) {teleport(0,80,89,gp.transitionState);}
////            else if(hit(1,59,88,"up")){speak(gp.npc[1][0]);}
//        }
//    }
//    public boolean hit( int map,int col,int row, String reqDirection){
//        boolean hit= false;
//
//        if(map==gp.currentMap) { // this effect will only work in the current map.
//            gp.player.solidArea.setX(gp.player.worldX + gp.player.solidArea.getX() );
//            gp.player.solidArea.setY( gp.player.worldY + gp.player.solidArea.getY());
//            eventRect[map][col][row].setX( col * gp.tileSize + eventRect[map][col][row].getX()) ;
//            eventRect[map][col][row].setY(row * gp.tileSize + eventRect[map][col][row].getY()) ;
//
//
//
//
//
//            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
//                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
//                    hit = true;
//                    previousEventX = gp.player.worldX;
//                    previousEventY = gp.player.worldY;
//                }
//            }
//
//            //Reset the values;
//            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
//            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
//            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
//            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
//        }
//        return hit;
//    }
//
////    public void damagePit(int col, int row ,int gameState){
////        gp.gameState = gameState;
////        gp.playSE(6);
////        gp.ui.currentDialogue = "You Fall into a pit!";
////        gp.player.life-=1;
////        //       eventRect[col][row].eventDone=true;
////        canTouchEvent=false;
////    }
////    public void healingPool(int col, int row ,int gameState){
////        if(gp.keyHandler.enterPressed== true){
////            gp.gameState=gameState;
////            gp.player.attackCanceled=true;
////            gp.playSE(2);
////            gp.ui.currentDialogue="You dring the water.\nYour life and mana \nhave been recovered";
////            gp.player.life= gp.player.maxLife;
////            gp.player.mana= gp.player.maxMana;
////        }
////    }
//    public void teleport(int map, int col,int row){
//      //  gp.gameState=gameState;
//        tempMap=map;
//        tempCol=col;
//        tempRow=row;
//
////        gp.currentMap=map;
////        gp.ui.currentDialogue="You teleport";
////        gp.player.worldX=gp.tileSize*col;
////        gp.player.worldY=gp.tileSize*row;
////        previousEventX=gp.player.worldX;
////        previousEventY=gp.player.worldY;
//        canTouchEvent=false;
////        gp.playSE(13);
//    }
//
////    public void speak(Entity entity){
////        if(gp.keyHandler.enterPressed==true){
////            gp.gameState=gp.dialogueState;
////            gp.player.attackCanceled=true;
////            entity.speak();
////        }
////    }
//}




package com.example.return_3.main;

import com.example.return_3.entity.Entity;
import com.example.return_3.shop.ClothShop;
import com.example.return_3.shop.FoodShop;
import com.example.return_3.shop.StuffShop;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class EventHandler {
    //test code
    int counter=0;
    boolean  tl=true;
    Game gp;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;
    EventRect[][][] eventRect;

    public EventHandler(Game gp) {
        this.gp = gp;

        // initialize the array of event Rect
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].setX(12);
            eventRect[map][col][row].setY(12);
            eventRect[map][col][row].setWidth(8);
            eventRect[map][col][row].setHeight(8);
            eventRect[map][col][row].eventRectDefaultX = (int) (eventRect[map][col][row].getX());
            eventRect[map][col][row].eventRectDefaultY = (int) (eventRect[map][col][row].getY());
            col++;
            
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent() throws IOException {
        // Check if the player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        // check the distance `(distance > gp.tileSize*3)` so if distance from the event is greater than 3 tile size then the canTouchEvent will be true
        if (distance > gp.tileSize*3) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {

            // <--------School-------->
            if(hit(0,82,144,"any") || hit(0,83,144,"any")){
                    try {
                        gp.showSchoolScene();
                        canTouchEvent = false;

                    } catch (IOException e) {
                        e.printStackTrace();
                        System.err.println("Error loading school.fxml: " + e.getMessage());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                System.out.println("welcome to school");
            }


            // <--------Game Center-------->
            if(hit(0,28,154,"any") || hit(0,29,154,"any")){
                try {
                    gp.showGameCenter();
                    canTouchEvent = false;

                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Error loading gameCenter.fxml: " + e.getMessage());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                System.out.println("welcome to Game Center");
            }









            // <---------Hospital--------->
            if(hit(gp.currentMap, 154, 135, "any") || hit(0,155,135,"any")) {
                gp.gameState = gp.hospitalState;
                System.out.println("Hit");
                canTouchEvent = false;
            }



//            if(hit(gp.currentMap,11,47,"right")){
//
//               if(tl){
//                   teleport(gp.currentMap,24,20);
//                   tl=false;
//               }
//                System.out.println("hit teleport");
//            }
//
//            if(hit(gp.currentMap,24,20,"right")){
//
//               ; if(tl){
//                    teleport(gp.currentMap,11,47);
//                    tl=false;
//                }
//                System.out.println("hit teleport");
//            }
            if(hit(gp.currentMap,128,146,"any")){
//                speak(gp.npc[gp.currentMap][0]);
                new FoodShop(gp).use();
                canTouchEvent = false;

                System.out.println("Trade Place hit");

            }

        }
        counter++;
        if(counter > 1800){
            counter=0;
            if(tl == false){
                tl = true;
            }
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;

        if (map == gp.currentMap) { // this effect will only work in the current map.
            Rectangle playerArea = new Rectangle(gp.player.worldX + gp.player.solidArea.getX(),
                    gp.player.worldY + gp.player.solidArea.getY(), gp.player.solidArea.getWidth(),
                    gp.player.solidArea.getHeight());

            Rectangle eventRectangle = new Rectangle(col * gp.tileSize + eventRect[map][col][row].getX(),
                    row * gp.tileSize + eventRect[map][col][row].getY(), eventRect[map][col][row].getWidth(),
                    eventRect[map][col][row].getHeight());

            if (playerArea.intersects(eventRectangle.getBoundsInLocal()) && eventRect[map][col][row].eventDone==false) {
                if (gp.player.direction.equals(reqDirection) || reqDirection.equals("any")) {
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }
        }
        return hit;
    }

    public void teleport(int map, int col, int row) {
//        tempMap = map;
//        tempCol = col;
//        tempRow = row;
//        canTouchEvent = false;
        gp.currentMap=map;
        gp.player.worldX=gp.tileSize*col;
        gp.player.worldY=gp.tileSize*row;
        previousEventX=gp.player.worldX;
        previousEventY=gp.player.worldY;

    }
    public void speak(Entity entity){
        if(gp.keyHandler.isEnterPressed()==true){
            gp.gameState=gp.dialogueState;
            entity.speak();
        }
    }
}
