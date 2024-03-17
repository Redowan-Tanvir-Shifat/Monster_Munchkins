package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.main.UtilityTool;

public class OBJ_Coin extends Entity {
    Game game;
    UtilityTool uTool =new UtilityTool();
    public OBJ_Coin(Game game) {
        super(game);
        this.game = game;
        //Type will erite here later
        type= type_pickupOnly;
        name="Coin";
        value =1;
        down1=uTool.loadImage("/objects/coin3.png",game.tileSize-7,game.tileSize-7);
        description="this is "+name;
    }
    public void use(Entity entity){
        game.ui.uiMainGame.addMessage("Coint: "+value);
        game.player.coin+=value;
    }
}
