package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Heart extends Entity {

    Game gp;
    public OBJ_Heart(Game gp){

        super(gp);
        this.gp = gp;
        type = type_pickupOnly;
        name="Heart";
        value=2;

        down1 = loadImage("/objects/heart_full.png",gp.tileSize,gp.tileSize);
        image = loadImage("/objects/heart_full.png",gp.tileSize,gp.tileSize);
        image2 = loadImage("/objects/heart_half.png",gp.tileSize,gp.tileSize);
        image3 = loadImage("/objects/heart_blank.png",gp.tileSize,gp.tileSize);
    }
    public void use(Entity entity){
        //gp.playSE(2);
        gp.ui.addMessage("Life + "+value);
        entity.life+=value;
    }
}
