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
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"girl");
        game.npc[mapNum][i].worldX=game.tileSize*78;
        game.npc[mapNum][i].worldY=game.tileSize*116;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"girl2");
        game.npc[mapNum][i].worldX=game.tileSize*88;
        game.npc[mapNum][i].worldY=game.tileSize*121;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"boy");
        game.npc[mapNum][i].worldX=game.tileSize*110;
        game.npc[mapNum][i].worldY=game.tileSize*150;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"boy2");
        game.npc[mapNum][i].worldX=game.tileSize*125;
        game.npc[mapNum][i].worldY=game.tileSize*159;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"oldman");
        game.npc[mapNum][i].worldX=game.tileSize*112;
        game.npc[mapNum][i].worldY=game.tileSize*176;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"boy2");
        game.npc[mapNum][i].worldX=game.tileSize*165;
        game.npc[mapNum][i].worldY=game.tileSize*172;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"oldman");
        game.npc[mapNum][i].worldX=game.tileSize*171;
        game.npc[mapNum][i].worldY=game.tileSize*151;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"girl");
        game.npc[mapNum][i].worldX=game.tileSize*153;
        game.npc[mapNum][i].worldY=game.tileSize*139;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"girl2");
        game.npc[mapNum][i].worldX=game.tileSize*148;
        game.npc[mapNum][i].worldY=game.tileSize*125;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"boy");
        game.npc[mapNum][i].worldX=game.tileSize*127;
        game.npc[mapNum][i].worldY=game.tileSize*127;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"boy2");
        game.npc[mapNum][i].worldX=game.tileSize*131;
        game.npc[mapNum][i].worldY=game.tileSize*113;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"oldman");
        game.npc[mapNum][i].worldX=game.tileSize*142;
        game.npc[mapNum][i].worldY=game.tileSize*176;
        i++;

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
        MyJDBC.addObject(userId,objecttype,Game.gameInstance.sword.itemCode,93,118,mapNum);
        MyJDBC.addObject(userId,objecttype,Game.gameInstance.redPotion.itemCode,144,149,mapNum);
        MyJDBC.addObject(userId,objecttype,Game.gameInstance.shieldWood.itemCode,144,149,mapNum);



        //FOR INTERACTIVE TILE
         objecttype= Game.gameInstance.type_interactiveTIle;

        MyJDBC.addObject(userId,objecttype,itemCode,53,142,mapNum);
        MyJDBC.addObject(userId,objecttype,itemCode,54,142,mapNum);

        //for monster types
        Entity entity=new Entity(Game.gameInstance);
        int monIndex=0;

        //Area TownHall
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_townHall,39,124,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_townHall,47,128,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_townHall,44,131,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_townHall,68,120,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_townHall,41,134,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_townHall,45,152,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_townHall,42,161,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_townHall,58,165,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_townHall,69,142,mapNum,monIndex);monIndex++;

        //AREA Village1
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_village_1,112,160,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_village_1,128,164,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_village_1,70,163,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_village_1,67,184,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_village_1,103,169,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_village_1,115,171,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_village_1,123,176,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_village_1,112,183,mapNum,monIndex);monIndex++;

        //Area Village2

        MyJDBC.addMonster(userId,entity.type_spider,entity.area_village_2,170,172,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_village_2,145,166,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_village_2,149,163,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_village_2,172,162,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_village_2,170,172,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_village_2,175,165,mapNum,monIndex);monIndex++;

        // <--------------ADD SLIME MONSTER ------------->
        MyJDBC.addMonster(userId,entity.type_slime,0,19,174,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,0,22,165,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,0,93,142,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,0,115,151,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,0,133,144,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,0,152,149,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,0,184,142,mapNum,monIndex);monIndex++;



//         MyJDBC.addInteractiveTile(userId, 53, 142, mapNum); // Add interactive tiles
//         MyJDBC.addInteractiveTile(userId, 53, 143, mapNum); // Add interactive tiles
//         MyJDBC.addInteractiveTile(userId, 54, 142, mapNum); // Add interactive tiles
//         MyJDBC.addInteractiveTile(userId, 54, 143, mapNum); // Add interactive tiles
    }
}