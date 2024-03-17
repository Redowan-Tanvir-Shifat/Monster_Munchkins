package com.example.return_3.main;


import com.example.return_3.entity.NPC_School;
import com.example.return_3.entity.NPC_Trade;
import com.example.return_3.entity.NPC_Wizard;
import com.example.return_3.interactiveTile.CuttableTree;
import com.example.return_3.monster.Mon_GreenSlime;
import com.example.return_3.monster.Mon_RedSlime;
import com.example.return_3.npc.*;

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
    public void setObject() {
        int mapNum = 0; //For map 1  //to create object for map 2 then we need to ser mapNum 2 under the code.
        int i = 0;
//        gp.obj[mapNum][i]=new OBJ_Key(gp);
//        gp.obj[mapNum][i].worldX=gp.tileSize*76;
//        gp.obj[mapNum][i].worldY=gp.tileSize*90;
//        i++;



    }
    public void setNPC(){
        int mapNum = 0;
        int i = 0;
        game.npc[mapNum][i]= new NPC_Trade(game);
        game.npc[mapNum][i].worldX=game.tileSize*48;
        game.npc[mapNum][i].worldY=game.tileSize*135;
        i++;
        game.npc[mapNum][i] = new NPC_School(game);
        game.npc[mapNum][i].worldX = game.tileSize * 40;
        game.npc[mapNum][i].worldY = game.tileSize * 134;

        i++;
        game.npc[mapNum][i]= new NPC_Wizard(game);
        game.npc[mapNum][i].worldX=game.tileSize*45;
        game.npc[mapNum][i].worldY=game.tileSize*133;
i++;
        game.npc[mapNum][i]= new NPC_Pig(game);
        game.npc[mapNum][i].worldX=game.tileSize*46;
        game.npc[mapNum][i].worldY=game.tileSize*135;
        i++;
        game.npc[mapNum][i]= new NPC_Girl(game);
        game.npc[mapNum][i].worldX=game.tileSize*43;
        game.npc[mapNum][i].worldY=game.tileSize*133;
        i++;
        game.npc[mapNum][i]= new NPC_Girl2(game);
        game.npc[mapNum][i].worldX=game.tileSize*43;
        game.npc[mapNum][i].worldY=game.tileSize*132;
        i++;
        game.npc[mapNum][i]= new NPC_Boy(game,"boy");
        game.npc[mapNum][i].worldX=game.tileSize*50;
        game.npc[mapNum][i].worldY=game.tileSize*132;
        i++;
        game.npc[mapNum][i]= new NPC_Boy(game,"boy2");
        game.npc[mapNum][i].worldX=game.tileSize*52;
        game.npc[mapNum][i].worldY=game.tileSize*132;


//        New Map;
//        mapNum=1;
//        i=0;
//        gp.npc[mapNum][i]= new NPC_Merchant(gp);
//        gp.npc[mapNum][i].worldX=gp.tileSize*59;
//        gp.npc[mapNum][i].worldY=gp.tileSize*86;
//        i++;

    }
    public void setMonster(){
        int i = 0;

        game.monster[game.currentMap][i] = new Mon_GreenSlime(game);
        game.monster[game.currentMap][i].worldX = game.tileSize * 80;
        game.monster[game.currentMap][i].worldY = game.tileSize * 144;
        i++;

        game.monster[game.currentMap][i] = new Mon_GreenSlime(game);
        game.monster[game.currentMap][i].worldX = game.tileSize * 84;
        game.monster[game.currentMap][i].worldY = game.tileSize * 144;
        i++;

        game.monster[game.currentMap][i] = new Mon_RedSlime(game);
        game.monster[game.currentMap][i].worldX = game.tileSize * 81;
        game.monster[game.currentMap][i].worldY = game.tileSize * 144;
        i++;

        game.monster[game.currentMap][i] = new Mon_RedSlime(game);
        game.monster[game.currentMap][i].worldX = game.tileSize * 84;
        game.monster[game.currentMap][i].worldY = game.tileSize * 142;
        i++;
    }


    //For Interactive Tile
    public void setInteractiveTile(){
        int mapNum=0;
        int i=0;
        game.iTile[mapNum][i]=new CuttableTree(game,45,138);i++;
        game.iTile[mapNum][i]=new CuttableTree(game,46,138);i++;
        game.iTile[mapNum][i]=new CuttableTree(game,47,137);i++;
        game.iTile[mapNum][i]=new CuttableTree(game,48,137);i++;
    }
}