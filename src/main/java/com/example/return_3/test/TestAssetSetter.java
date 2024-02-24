package com.example.return_3.test;

import com.example.return_3.main.Game;

import java.util.Random;

public class TestAssetSetter {
    TestGame game;
    private static final Random RAND = new Random();
    public int posX,posY;
    public TestAssetSetter(TestGame game ){
        this.game = game;
    }
    
    public void setEnemy(){


        for (int i =0 ; i < game.enemies.length ; i++){
            posX = RAND.nextInt(game.screenWidth-game.tileSize)+game.tileSize;
            game.enemies[i]=new TestEnemy(game);
            game.enemies[i].posX=posX;
            game.enemies[i].posY=0;
        }




    }
}
