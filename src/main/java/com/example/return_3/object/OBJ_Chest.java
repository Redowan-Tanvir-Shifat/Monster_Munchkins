package com.example.return_3.object;

import com.example.return_3.db.MyJDBC;
import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;


public class OBJ_Chest extends Entity {
    Game game;
    Entity loot;
    public OBJ_Chest(Game game) {
        super(game);
        this.game = game;
        this.loot = new OBJ_FireSword(game);
        type= type_obstacle;
        name= "chest";
        itemCode=321;
        exp = 20;
        image1=uTool.loadImage("/objects/chest.png",game.tileSize,game.tileSize);
        image2=uTool.loadImage("/objects/chest_opened.png",game.tileSize,game.tileSize);
        down1=image1;

        description="this is "+name;
        collision=true;
        solidArea.setX(0);
        solidArea.setY(10);
        solidArea.setWidth(32);
        solidArea.setHeight(22);
        solidAreaDefaultX= (int) solidArea.getX();
        solidAreaDefaultY= (int) solidArea.getY();
    }
    public void interact(){
//        if(!destroyed){
//        game.gameState=game.messageState;
//        game.ui.uiMainGame.currentDialogue="You need a key to open the door";
//        game.playSoundEffect(game.soundEffect.doorLocked);
//
//        }
        game.gameState=game.messageState;
        if(destroyed==false){
            game.playSoundEffect(3);
            StringBuilder sb=new StringBuilder();
            sb.append("You opened the chest and find a ").append(loot.name).append(" !");
            if(game.player.inventory.size() == game.player.maxInventorySize){
                sb.append("\n... But you can not carry any more. Your inventory is full!");
            }else{
                sb.append("\nYou obtained the ").append(loot.name).append("!");
                game.player.inventory.add(loot);
                MyJDBC.addItemToInventory(game.player.playerId,loot.itemCode);
                down1 = image2;
                destroyed = true;
                game.player.exp += game.chest.exp;
                game.player.checkLevelUp();
                int row=worldY/game.tileSize;
                int col=worldX/game.tileSize;
                MyJDBC.updateObjectDestroyedStatus(game.player.playerId,game.currentMap,row,col,game.type_object,true);


            }
            game.ui.uiMainGame.currentDialogue=sb.toString();
        }else{
          game.ui.uiMainGame.currentDialogue="Already opened and there is no loot!";
        }
    }

}
