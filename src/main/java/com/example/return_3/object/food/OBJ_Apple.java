package com.example.return_3.object.food;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Apple extends Entity {
    Game game;
    public OBJ_Apple(Game game) {
        super(game);
        this.game = game;
        //Type will erite here later
        type= type_consumable;
        name="Apple";
        value =15;
        price= 2*value;
        down1=uTool.loadImage("/objects/food/apple.png",game.tileSize-7,game.tileSize-7);
        description="this is "+name;
    }
    public void use(Entity entity){
        game.ui.uiMainGame.addMessage("life gain: "+value);
        game.player.life+=value;
    }
}