package com.example.return_3.object.food;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Fish extends Entity{
    Game game;
    public OBJ_Fish(Game game) {
        super(game);
        this.game = game;
        //Type will write here later
        type = type_consumable;
        name ="Fish";
        value = 12;
        itemCode = 205;
        price = 25*value;
        down1 = uTool.loadImage("/objects/food/fish.png",game.tileSize-7,game.tileSize-7);
        description = "This is " + name;
    }
    public boolean use(Entity entity){
        game.ui.uiMainGame.addMessage("Life gain: "+value);
        game.player.life += value;
        return true;
    }
}
