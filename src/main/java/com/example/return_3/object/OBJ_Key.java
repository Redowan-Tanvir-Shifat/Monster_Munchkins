package com.example.return_3.object;

import com.example.return_3.db.MyJDBC;
import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Key extends Entity {
    Game game;
    public OBJ_Key(Game game) {
        super(game);
        this.game = game;
        type= type_consumable;
        itemCode=304;
        name= "key";
        down1=uTool.loadImage("/objects/key.png",game.tileSize,game.tileSize);
        description="this is "+name;

    }
    public boolean use(Entity entity) {
        game.gameState=game.messageState;
        int objectIndex=getDetected(entity,game.obj,"door");
        if(objectIndex!=999 &&game.obj[game.currentMap][objectIndex].destroyed!=true){
            game.ui.uiMainGame.currentDialogue="You use the "+name+"and opened the door";
            Entity entity2=game.obj[game.currentMap][objectIndex];
            int row=entity2.worldY/game.tileSize;
            int col=entity2.worldX/game.tileSize;
            MyJDBC.updateObjectDestroyedStatus(playerId,game.currentMap,row,col,game.type_object,true);
            entity2.down1=entity2.image2;
            entity2.collision=false;
            entity2.destroyed=true;



            return true;
        }
        else {
            game.ui.uiMainGame.currentDialogue="r e vai dorjar samne use kor!";
        return false;
        }
    }
}
