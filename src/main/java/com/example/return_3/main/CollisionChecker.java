package com.example.return_3.main;

import com.example.return_3.entity.Entity;

public class CollisionChecker {
    Game game;
    public CollisionChecker(Game game){
        this.game=game;
    }
    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + (int)(entity.solidArea.getX());
        int entityRightWorldX = entity.worldX + (int)(entity.solidArea.getX() + entity.solidArea.getWidth());
        int entityTopWorldY =entity.worldY +(int)( entity.solidArea.getY());
        int entityBottomWorldY = entity.worldY +(int)( entity.solidArea.getY() + entity.solidArea.getHeight());

        int entityLeftCol=entityLeftWorldX/game.tileSize;
        int entityRightCol= entityRightWorldX/game.tileSize;
        int entityTopRow= entityTopWorldY/game.tileSize;
        int entityBottomRow= entityBottomWorldY/game.tileSize;

        int tileNum1, tileNum2;

        //checking for the direction
        switch (entity.direction){
            case "up":
                System.out.println(" speed"+ entity.speed);
                entityTopRow=(entityTopWorldY-entity.speed)/game.tileSize;
                // in above case what will happen is that we kind of predict where the player will be after he movied
                // in there subtract the speed because in up direction y value will be less as much we go up.
                tileNum1= game.tileM.mapTileNum[game.currentMap][entityLeftCol][entityTopRow];
                tileNum2= game.tileM.mapTileNum[game.currentMap][entityRightCol][entityTopRow];

                if(game.tileM.tile[tileNum1].collision == true|| game.tileM.tile[tileNum2].collision == true){ // here we are checking the collision of that two tile if they are solid tiles then we will do any action or not
                    //that means it will hit any solid tile so the player can not move in this direction .
                    entity.collisionOn=true;

                }
                break;
            case "down":
                System.out.println("speed"+ entity.speed);

                entityBottomRow=(entityBottomWorldY+entity.speed)/game.tileSize;
                tileNum1= game.tileM.mapTileNum[game.currentMap][entityLeftCol][entityBottomRow];
                tileNum2= game.tileM.mapTileNum[game.currentMap][entityRightCol][entityBottomRow];
                if(game.tileM.tile[tileNum1].collision == true|| game.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn=true;
                }
                break;
            case "left":
                System.out.println("speed"+ entity.speed);

                entityLeftCol=(entityLeftWorldX-entity.speed)/game.tileSize;
                tileNum1= game.tileM.mapTileNum[game.currentMap][entityLeftCol][entityTopRow];
                tileNum2= game.tileM.mapTileNum[game.currentMap][entityLeftCol][entityBottomRow];
                if(game.tileM.tile[tileNum1].collision == true|| game.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn=true;
                }
                break;
            case "right":
                System.out.println("speed"+ entity.speed);

                entityRightCol=(entityRightWorldX+entity.speed)/game.tileSize;
                tileNum1= game.tileM.mapTileNum[game.currentMap][entityRightCol][entityTopRow];
                tileNum2= game.tileM.mapTileNum[game.currentMap][entityRightCol][entityBottomRow];
                if(game.tileM.tile[tileNum1].collision == true|| game.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn=true;
                }
                break;
        }
    }






    //part 8 Object Interaction part starts
//
//    public int checkObject(Entity entity, boolean player){
//        int index =999;
//
//        for(int i=0; i<game.obj[game.currentMap].length;i++){
//            if(game.obj[game.currentMap][i]!= null){
//                //Get entity solid Area Position
//                entity.solidArea.setX(entity.worldX + entity.solidArea.getX());
//                entity.solidArea.setY(entity.worldY + entity.solidArea.getY());
//                //Get the object's solid Area position
//                game.obj[game.currentMap][i].solidArea.x=game.obj[game.currentMap][i].worldX + game.obj[game.currentMap][i].solidArea.x;
//                game.obj[game.currentMap][i].solidArea.y=game.obj[game.currentMap][i].worldY + game.obj[game.currentMap][i].solidArea.y;
//
//                switch (entity.direction){
//                    case "up":
//                        entity.solidArea.y-=entity.speed;
//                        break;
//                    case "down":
//                        entity.solidArea.y+=entity.speed;
//                        break;
//                    case "left":
//                        entity.solidArea.x-=entity.speed;
//                        break;
//                    case "right":
//                        entity.solidArea.x+=entity.speed;
//                        break;
//                }
//
//                //rectangle class has a method named intersects to check two rectangeles are overlapperd or not
//                if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
//
//                    //check the object is solid or not
//                    if(gp.obj[gp.currentMap][i].collision==true){
//                        entity.collisionOn=true;
//                    }
//                    //check this is a player or not (it could be a monster or other npc)
//                    if(player==true){
//                        index=i;
//                    }
//                }
//
//                // reset the value
//                entity.solidArea.x= entity.solidAreaDefaultX;
//                entity.solidArea.y= entity.solidAreaDefaultY;
//
//                gp.obj[gp.currentMap][i].solidArea.x=gp.obj[gp.currentMap][i].solidAreaDefaultX;
//                gp.obj[gp.currentMap][i].solidArea.y=gp.obj[gp.currentMap][i].solidAreaDefaultY ;
//            }
//        }
//
//        return index;
//    }
//    //part 8 Object Interaction part ends
    //

    // This method is for check NPC or Monster Collision
    //this is identical of checkObject method
//    public int checkEntity(Entity entity,Entity[][] target) {
//        int index =999;
//
//        for(int i=0; i<target[gp.currentMap].length;i++){
//            if(target[gp.currentMap][i]!= null){
//                //Get entity solid Area Position
//                entity.solidArea.x=entity.worldX + entity.solidArea.x;
//                entity.solidArea.y=entity.worldY + entity.solidArea.y;
//                //Get the object's solid Area position
//                target[gp.currentMap][i].solidArea.x=target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
//                target[gp.currentMap][i].solidArea.y=target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;
//
//                switch (entity.direction){
//                    case "up":
//                        entity.solidArea.y-=entity.speed;
//                        break;
//                    case "down":
//                        entity.solidArea.y+=entity.speed;
//                        break;
//                    case "left":
//                        entity.solidArea.x-=entity.speed;
//                        break;
//                    case "right":
//                        entity.solidArea.x+=entity.speed;
//                        break;
//                }
//                if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea)){
//
//                    if(target[gp.currentMap][i]!= entity){
//                        //check the object is solid or not
//                        entity.collisionOn=true;
//                        index=i;
//                    }
//                }
//                // reset the value
//                entity.solidArea.x= entity.solidAreaDefaultX;
//                entity.solidArea.y= entity.solidAreaDefaultY;
//
//                target[gp.currentMap][i].solidArea.x=target[gp.currentMap][i].solidAreaDefaultX;
//                target[gp.currentMap][i].solidArea.y=target[gp.currentMap][i].solidAreaDefaultY ;
//            }
//        }
//
//        return index;
//    }

//    public boolean checkPlayer(Entity entity){
//        boolean contactPlayer=false;
//        //Get entity solid Area Position
//        entity.solidArea.x=entity.worldX + entity.solidArea.x;
//        entity.solidArea.y=entity.worldY + entity.solidArea.y;
//        //Get the object's solid Area position
//        gp.player.solidArea.x=gp.player.worldX + gp.player.solidArea.x;
//        gp.player.solidArea.y=gp.player.worldY + gp.player.solidArea.y;
//
//        switch (entity.direction){
//            case "up":
//                entity.solidArea.y-=entity.speed;
//                break;
//            case "down":
//                entity.solidArea.y+=entity.speed;
//                break;
//            case "left":
//                entity.solidArea.x-=entity.speed;
//                break;
//            case "right":
//                entity.solidArea.x+=entity.speed;
//                break;
//        }
//        if(entity.solidArea.intersects(gp.player.solidArea)){
//            entity.collisionOn=true;
//            contactPlayer=true;
//        }
//        // reset the value
//        entity.solidArea.x= entity.solidAreaDefaultX;
//        entity.solidArea.y= entity.solidAreaDefaultY;
//
//        gp.player.solidArea.x=gp.player.solidAreaDefaultX;
//        gp.player.solidArea.y=gp.player.solidAreaDefaultY ;
//
//        return contactPlayer;
//    }


}
