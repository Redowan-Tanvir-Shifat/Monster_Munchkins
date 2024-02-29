package com.example.return_3.main;


import com.example.return_3.entity.NPC_GameCenter;
import com.example.return_3.entity.NPC_OldMan;
import com.example.return_3.entity.NPC_School;

import java.util.Random;

//This class is made for setting Asset to the Game map.
public class AssetSetter {
    Game game;
    public AssetSetter(Game game ){
        this.game = game;
    }

    //HERE IS SOME METHOD OF
    //setObject [key, shield, weapon, etc]
    //setNPC
    //setMonster
    //setInteractiveTile [dry tree, breakable tree, trunk.]
    public void setObject(){
        int mapNum=0; //For map 1  //to create object for map 2 then we need to ser mapNum 2 under the code.
        int i=0;
//        gp.obj[mapNum][i]=new OBJ_Key(gp);
//        gp.obj[mapNum][i].worldX=gp.tileSize*76;
//        gp.obj[mapNum][i].worldY=gp.tileSize*90;
//        i++;



    }
    public void setNPC(){
        int mapNum=0;
        int i=0;
        game.npc[mapNum][i]= new NPC_School(game);
        game.npc[mapNum][i].worldX=game.tileSize*38;
        game.npc[mapNum][i].worldY=game.tileSize*14;

        i++;
        game.npc[mapNum][i]= new NPC_GameCenter(game);
        game.npc[mapNum][i].worldX=game.tileSize*38;
        game.npc[mapNum][i].worldY=game.tileSize*14;

        //New Map;
//        mapNum=1;
//        i=0;
//        gp.npc[mapNum][i]= new NPC_Merchant(gp);
//        gp.npc[mapNum][i].worldX=gp.tileSize*59;
//        gp.npc[mapNum][i].worldY=gp.tileSize*86;
//        i++;

    }
    public void setMonster(){
//        int mapNum=0;
//        int i=0;
//        gp.monster[mapNum][i]= new MON_GreenSlime(gp);
//        gp.monster[mapNum][i].worldX=gp.tileSize*65;
//        gp.monster[mapNum][i].worldY=gp.tileSize*90;
//        i++;

    }
    //For Interactive Tile
    public void setInteractiveTile(){
//        int mapNum=0;
//        int i=0;
//        gp.iTile[mapNum][i]=new IT_DryTree(gp,79,83);i++;
//        gp.iTile[mapNum][i]=new IT_DryTree(gp,81,83);i++;
//        gp.iTile[mapNum][i]=new IT_DryTree(gp,69,54);i++;
//        gp.iTile[mapNum][i]=new IT_DryTree(gp,69,56);i++;
    }
}