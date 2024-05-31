package com.example.return_3.object.food;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Banana extends Entity {
    Game game;
    public OBJ_Banana(Game game) {
        super(game);
        this.game = game;
        //Type will write here later
        type= type_consumable;
        name ="Banana";
        value = 10;
        exp = 3;
        itemCode = 202;
        price = 10*value;
        down1 = uTool.loadImage("/objects/food/banana.png",game.tileSize-7,game.tileSize-7);
        description = "This is "+name;
    }
    public boolean use(Entity entity){
        game.ui.uiMainGame.addMessage("Energy increase: "+value);
        game.player.energy += value;
        return true;
    }
}
