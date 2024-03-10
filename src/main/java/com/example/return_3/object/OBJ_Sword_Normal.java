package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(Game game) {
        super(game);

        name = "NormalSword";
        down1 = loadImage("/objects/sword_normal.png", game.tileSize, game.tileSize);
        attackValue = 1;
    }
}
