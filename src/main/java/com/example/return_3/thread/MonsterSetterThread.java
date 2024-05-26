package com.example.return_3.thread;

import com.example.return_3.main.Game;

public class MonsterSetterThread extends Thread{
    Game game;
    int i;
    String name;
    public MonsterSetterThread(Game game,int i, String name){
    this.game=game;
    this.i=i;
    this.name=name;
    }
    public void run(){
//        try {
//            Thread.sleep(5000);
//            if(name.equals("Red Slime")){
//                game.monster[game.currentMap][i] = new Mon_RedSlime(game);
//            }else if(name.equals("Green Slime")){
//                game.monster[game.currentMap][i] = new Mon_GreenSlime(game);
//            }
//            game.monster[game.currentMap][i].worldX = game.tileSize * 80;
//            game.monster[game.currentMap][i].worldY = game.tileSize * 144;
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
