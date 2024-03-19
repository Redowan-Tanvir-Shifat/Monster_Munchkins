package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Axe extends Entity {
    public OBJ_Axe(Game game) {
        super(game);
        type =type_axe;
        name="Woodcutter's Axe";
        down1=loadImage("/objects/axe.png",game.tileSize,game.tileSize);
        attackValue=2;
        attackArea.setWidth(25);
        attackArea.setHeight(25);
        description="["+name+"]\nA bit rusty but still can \ncut some trees";
        knockBackPower = 15;
    }
}
