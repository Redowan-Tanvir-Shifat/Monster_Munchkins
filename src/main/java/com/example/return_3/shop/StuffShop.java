package com.example.return_3.shop;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.object.OBJ_Axe;
import com.example.return_3.object.OBJ_Shield_Blue;
import com.example.return_3.object.OBJ_Shield_Wood;
import com.example.return_3.object.OBJ_Sword_Normal;

public class StuffShop extends Entity {
    Game game;
    public StuffShop(Game game) {
        super(game);
        this.game = game;
        setItems();
    }

    public void setItems(){
        inventory.add(new OBJ_Sword_Normal(game));
        inventory.add(new OBJ_Shield_Wood(game));
        inventory.add(new OBJ_Axe(game));
        inventory.add(new OBJ_Shield_Blue(game));
    }
}
