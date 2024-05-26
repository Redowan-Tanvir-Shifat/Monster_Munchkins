package com.example.return_3.thread;

import com.example.return_3.main.Game;
import com.example.return_3.monster.Mon_Green;

public class SlimeDeadThread extends Thread{
    Game game;

    public SlimeDeadThread(Game game){
    this.game=game;

    }
    public void run(){
        for (int i = 0; i < game.monster[game.currentMap].length; i++) {
            if (game.monster[game.currentMap][i] instanceof Mon_Green) {
                game.monster[game.currentMap][i].slimeDeathOn=true;
            }
        }
    }
}
