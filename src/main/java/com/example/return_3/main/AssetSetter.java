package com.example.return_3.main;


import com.example.return_3.db.MyJDBC;
import com.example.return_3.entity.NPC_School;
import com.example.return_3.entity.NPC_Trade;
import com.example.return_3.entity.NPC_Wizard;
import com.example.return_3.interactiveTile.CuttableTree;
import com.example.return_3.monster.*;
import com.example.return_3.npc.*;
import com.example.return_3.object.*;

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
//        int i = 0;
//        game.obj[mapNum][i]=new OBJ_Shield_Blue(game);
//        game.obj[mapNum][i].worldX=game.tileSize*42;
//        game.obj[mapNum][i].worldY=game.tileSize*140;
//        i++;
//        game.obj[mapNum][i]=new OBJ_Heart(game);
//        game.obj[mapNum][i].worldX=game.tileSize*44;
//        game.obj[mapNum][i].worldY=game.tileSize*140;
//        i++;
//        game.obj[mapNum][i]=new OBJ_Shield_Wood(game);
//        game.obj[mapNum][i].worldX=game.tileSize*47;
//        game.obj[mapNum][i].worldY=game.tileSize*140;
//        i++;
//        game.obj[mapNum][i]=new OBJ_Sword_Normal(game);
//        game.obj[mapNum][i].worldX=game.tileSize*49;
//        game.obj[mapNum][i].worldY=game.tileSize*140;
//        i++;
//        game.obj[mapNum][i]=new OBJ_Coin(game);
//        game.obj[mapNum][i].worldX=game.tileSize*41;
//        game.obj[mapNum][i].worldY=game.tileSize*142;
//        i++;
//        game.obj[mapNum][i]=new OBJ_Axe(game);
//        game.obj[mapNum][i].worldX=game.tileSize*42;
//        game.obj[mapNum][i].worldY=game.tileSize*145;
//        i++;
//        game.obj[mapNum][i]=new OBJ_Potion_Red(game);
//        game.obj[mapNum][i].worldX=game.tileSize*44;
//        game.obj[mapNum][i].worldY=game.tileSize*142;
//        i++; game.obj[mapNum][i]=new OBJ_Axe(game);
//        game.obj[mapNum][i].worldX=game.tileSize*43;
//        game.obj[mapNum][i].worldY=game.tileSize*145;
//        i++;
//        game.obj[mapNum][i]=new OBJ_Potion_Red(game);
//        game.obj[mapNum][i].worldX=game.tileSize*45;
//        game.obj[mapNum][i].worldY=game.tileSize*142;
//        i++;
//        game.obj[mapNum][i]=new OBJ_Axe(game);
//        game.obj[mapNum][i].worldX=game.tileSize*42;
//        game.obj[mapNum][i].worldY=game.tileSize*144;
//        i++;
        game.obj[mapNum][14]=new OBJ_Door(game);
        game.obj[mapNum][14].worldX=game.tileSize*46;
        game.obj[mapNum][14].worldY=game.tileSize*142;
        game.obj[mapNum][15]=new OBJ_Key(game);
        game.obj[mapNum][15].worldX=game.tileSize*47;
        game.obj[mapNum][15].worldY=game.tileSize*142;
        game.obj[mapNum][16]=new OBJ_Key(game);
        game.obj[mapNum][16].worldX=game.tileSize*45;
        game.obj[mapNum][16].worldY=game.tileSize*142;

       // i++;



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
        game.npc[mapNum][i]= new NPC_Universal(game,"girl");
        game.npc[mapNum][i].worldX=game.tileSize*43;
        game.npc[mapNum][i].worldY=game.tileSize*133;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"girl2");
        game.npc[mapNum][i].worldX=game.tileSize*42;
        game.npc[mapNum][i].worldY=game.tileSize*133;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"boy");
        game.npc[mapNum][i].worldX=game.tileSize*50;
        game.npc[mapNum][i].worldY=game.tileSize*132;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"boy2");
        game.npc[mapNum][i].worldX=game.tileSize*52;
        game.npc[mapNum][i].worldY=game.tileSize*132;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"oldman");
        game.npc[mapNum][i].worldX=game.tileSize*54;
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

        game.monster[game.currentMap][i] = new Mon_Pac(game);
        game.monster[game.currentMap][i].worldX = game.tileSize * 127;
        game.monster[game.currentMap][i].worldY = game.tileSize * 32;
        i++;game.monster[game.currentMap][i] = new Mon_Worm(game);
        game.monster[game.currentMap][i].worldX = game.tileSize * 128;
        game.monster[game.currentMap][i].worldY = game.tileSize * 32;
        i++;game.monster[game.currentMap][i] = new Mon_Worm(game);
        game.monster[game.currentMap][i].worldX = game.tileSize * 127;
        game.monster[game.currentMap][i].worldY = game.tileSize * 31;
//        i++;game.monster[game.currentMap][i] = new Mon_ORC(game);
//        game.monster[game.currentMap][i].worldX = game.tileSize * 63;
//        game.monster[game.currentMap][i].worldY = game.tileSize * 145;

        i++;game.monster[game.currentMap][i] = new Mon_RedORC(game);
        game.monster[game.currentMap][i].worldX = game.tileSize * 64;
        game.monster[game.currentMap][i].worldY = game.tileSize * 146;
        //worm
//        i++;game.monster[game.currentMap][i] = new Mon_Worm(game);
//        game.monster[game.currentMap][i].worldX = game.tileSize * 129;
//        game.monster[game.currentMap][i].worldY = game.tileSize * 30;
//        i++;game.monster[game.currentMap][i] = new Mon_Worm(game);
//        game.monster[game.currentMap][i].worldX = game.tileSize * 127;
//        game.monster[game.currentMap][i].worldY = game.tileSize * 34;
        i++;game.monster[game.currentMap][i] = new Mon_GreenSlime(game);
        game.monster[game.currentMap][i].worldX = game.tileSize * 125;
        game.monster[game.currentMap][i].worldY = game.tileSize * 33;
        i++;

        game.monster[game.currentMap][i] = new Mon_Green(game);
        game.monster[game.currentMap][i].worldX = game.tileSize * 135;
        game.monster[game.currentMap][i].worldY = game.tileSize * 26;
        i++;game.monster[game.currentMap][i] = new Mon_Green(game);
        game.monster[game.currentMap][i].worldX = game.tileSize * 136;
        game.monster[game.currentMap][i].worldY = game.tileSize * 26;
        i++;game.monster[game.currentMap][i] = new Mon_Spider(game);
        game.monster[game.currentMap][i].worldX = game.tileSize * 137;
        game.monster[game.currentMap][i].worldY = game.tileSize * 25;

        i++;
//
//        game.monster[game.currentMap][i] = new Mon_RedSlime(game);
//        game.monster[game.currentMap][i].worldX = game.tileSize * 83;
//        game.monster[game.currentMap][i].worldY = game.tileSize * 143;
//        i++;
//
//        game.monster[game.currentMap][i] = new Mon_ORC(game);
//        game.monster[game.currentMap][i].worldX = game.tileSize * 87;
//        game.monster[game.currentMap][i].worldY = game.tileSize * 143;
        i++;
    }


    //For Interactive Tile
    public void setInteractiveTile(){
        int mapNum=0;
//        int i=0;
        MyJDBC.setObjects(game.player.playerId,mapNum);
    }



    public static void addInteractiveTileToDB(int userId){
                //  FOR THE FIRST MAP
        int mapNum=0;
        int itemCode=0;
        // FOR OBJECT
        int objecttype= Game.gameInstance.type_object;
        MyJDBC.addObject(userId,objecttype,Game.gameInstance.axe.itemCode,42,145,mapNum);
        MyJDBC.addObject(userId,objecttype,Game.gameInstance.redPotion.itemCode,44,142,mapNum);



        //FOR INTERACTIVE TILE
         objecttype= Game.gameInstance.type_interactiveTIle;

        MyJDBC.addObject(userId,objecttype,itemCode,53,142,mapNum);
        MyJDBC.addObject(userId,objecttype,itemCode,54,142,mapNum);



//         MyJDBC.addInteractiveTile(userId, 53, 142, mapNum); // Add interactive tiles
//         MyJDBC.addInteractiveTile(userId, 53, 143, mapNum); // Add interactive tiles
//         MyJDBC.addInteractiveTile(userId, 54, 142, mapNum); // Add interactive tiles
//         MyJDBC.addInteractiveTile(userId, 54, 143, mapNum); // Add interactive tiles
    }
}