package com.example.return_3.object.food;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Berries extends Entity {
    Game game;
    public OBJ_Berries(Game game) {
        super(game);
        this.game = game;
        //Type will write here later
        type= type_consumable;
        name="Berries";
        value = 5;
        price = 10*value;
        itemCode = 203;
        down1 = uTool.loadImage("/objects/food/berries.png",game.tileSize-7,game.tileSize-7);
        description = "This is "+name;
    }
    public boolean use(Entity entity){
        game.ui.uiMainGame.addMessage("Energy increase: "+value);
        game.player.energy += value;
        return true;
    }
}
