package com.example.return_3.object.food;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Meat extends Entity {
    Game game;
    public OBJ_Meat(Game game) {
        super(game);
        this.game = game;
        //Type will write here later
        type = type_consumable;
        name ="Meat";
        value = 15;
        exp = 3;
        itemCode = 208;
        price = 25*value;
        down1 = uTool.loadImage("/objects/food/legPiece.png",game.tileSize-7,game.tileSize-7);
        description = "This is " + name;
    }
    public boolean use(Entity entity){
        game.ui.uiMainGame.addMessage("Life gain: "+value);
        game.player.life += value;
        return true;
    }
}
