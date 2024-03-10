package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Shield_Wood extends Entity {
    public OBJ_Shield_Wood(Game game) {
        super(game);

        name = "Wood Shield";
        down1 = loadImage("/objects/shield_wood.png", game.tileSize, game.tileSize);
        defenseValue = 1;
    }
}
