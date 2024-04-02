package com.example.return_3.object.food;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Berries extends Entity {
    Game game;
    public OBJ_Berries(Game game) {
        super(game);
        this.game = game;
        //Type will erite here later
        type= type_consumable;
        name="Berries";
        value =5;
        price= 2*value;
        down1=uTool.loadImage("/objects/food/berries.png",game.tileSize-7,game.tileSize-7);
        description="this is "+name;
    }
    public void use(Entity entity){
        game.ui.uiMainGame.addMessage("life gain: "+value);
        game.player.life+=value;
    }
}