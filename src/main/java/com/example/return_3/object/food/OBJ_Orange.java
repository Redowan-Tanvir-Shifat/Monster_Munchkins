package com.example.return_3.object.food;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Orange extends Entity {
    Game game;
    public OBJ_Orange(Game game) {
        super(game);
        this.game = game;
        //Type will erite here later
        type= type_consumable;
        name="Orange";
        value =10;
        price= 2*value;
        itemCode=204;
        down1=uTool.loadImage("/objects/food/orange.png",game.tileSize-7,game.tileSize-7);
        description="this is "+name;
    }
    public boolean use(Entity entity){
        game.ui.uiMainGame.addMessage("life gain: "+value);
        game.player.life+=value;
        return true;
    }
}
