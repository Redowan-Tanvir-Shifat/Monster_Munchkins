package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Helmet extends Entity{
    Game game;
    public OBJ_Helmet(Game game) {
        super(game);
        this.game = game;
        //Type will erite here later
        name="Helmet";
        price =500;

        down1=loadImage("/objects/test_helmet.png",game.tileSize,game.tileSize);
        description="this is "+name;
    }
}
