package com.example.return_3.main;


import com.example.return_3.db.MyJDBC;
import com.example.return_3.entity.Entity;
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
        int i = 0;
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
        game.obj[mapNum][i]=new OBJ_Coin(game);
        game.obj[mapNum][i].worldX=game.tileSize*41;
        game.obj[mapNum][i].worldY=game.tileSize*142;
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
        game.obj[mapNum][14].worldX=game.tileSize * 28;
        game.obj[mapNum][14].worldY=game.tileSize * 161;
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
        int mapNum=0;
        MyJDBC.setMonsters(game.player.playerId,mapNum);
    }


    //For Interactive Tile
    public void setInteractiveTile(){
        int mapNum=0;
//        int i=0;
        MyJDBC.setObjects(game.player.playerId,mapNum);
    }



    public static void addObjectToDB(int userId){
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

        //for monster types
        Entity entity=new Entity(Game.gameInstance);
        int monIndex=0;
//        MyJDBC.addMonster(userId,entity.type_spider,entity.area_townHall,55,149,mapNum,monIndex);monIndex++;
//        MyJDBC.addMonster(userId,entity.type_worm,entity.area_townHall,55,150,mapNum,monIndex);monIndex++;
//        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_village_2,152,167,mapNum,monIndex);monIndex++;
//        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_village_2,165,164,mapNum,monIndex);monIndex++;
//        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_village_2,172,172,mapNum,monIndex);monIndex++;
//        MyJDBC.addMonster(userId,entity.type_worm,entity.area_village_2,161,162,mapNum,monIndex);monIndex++;
//        MyJDBC.addMonster(userId,entity.type_worm,0,159,177,mapNum,monIndex);monIndex++;
//        MyJDBC.addMonster(userId,entity.type_worm,0,158,155,mapNum,monIndex);monIndex++;


        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_village_1,95,172,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_village_1,95,176,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_village_1,67,172,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_village_1,65,169,mapNum,monIndex);monIndex++;

        MyJDBC.addMonster(userId,entity.type_worm,entity.area_monIsland_1,118,17,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_monIsland_1,119,17,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_monIsland_1,116,17,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_arc,entity.area_monIsland_1,115,17,mapNum,monIndex);monIndex++;




//         MyJDBC.addInteractiveTile(userId, 53, 142, mapNum); // Add interactive tiles
//         MyJDBC.addInteractiveTile(userId, 53, 143, mapNum); // Add interactive tiles
//         MyJDBC.addInteractiveTile(userId, 54, 142, mapNum); // Add interactive tiles
//         MyJDBC.addInteractiveTile(userId, 54, 143, mapNum); // Add interactive tiles
    }
}