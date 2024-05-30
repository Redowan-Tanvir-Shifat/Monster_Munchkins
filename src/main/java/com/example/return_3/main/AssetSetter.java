package com.example.return_3.main;


import com.example.return_3.db.MyJDBC;
import com.example.return_3.db.ObjectData;
import com.example.return_3.entity.*;
import com.example.return_3.npc.*;
import com.example.return_3.object.*;

import java.util.ArrayList;
import java.util.List;

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

        game.obj[mapNum][16]=new OBJ_Chest(game);
        game.obj[mapNum][16].worldX=game.tileSize*45;
        game.obj[mapNum][16].worldY=game.tileSize*144;
        i=17;
        game.obj[mapNum][18]=new OBJ_Chest(game);
        game.obj[mapNum][18].worldX=game.tileSize*43;
        game.obj[mapNum][18].worldY=game.tileSize*143;
//        game.obj[mapNum][18]=new OBJ_BlueKey(game);
//        game.obj[mapNum][18].worldX=game.tileSize*21;
//        game.obj[mapNum][18].worldY=game.tileSize*103;
        game.obj[mapNum][19]=new OBJ_BlueKey(game);
        game.obj[mapNum][19].worldX=game.tileSize*21;
        game.obj[mapNum][19].worldY=game.tileSize*101;
        i++;


    }
    public void setNPC(){
        int mapNum = 0;
        int i = 0;

        game.interactNpc[mapNum][i]= new NPC_Welcome(game);
        game.interactNpc[mapNum][i].worldX=game.tileSize*81;
        game.interactNpc[mapNum][i].worldY=game.tileSize*117;
        i++;
        game.interactNpc[mapNum][i]= new NPC_FisherMan(game);
        game.interactNpc[mapNum][i].worldX=game.tileSize*188;
        game.interactNpc[mapNum][i].worldY=game.tileSize*113;
        i++;
        game.interactNpc[mapNum][i]= new NPC_ShipThisSide(game);
        game.interactNpc[mapNum][i].worldX=game.tileSize*23;
        game.interactNpc[mapNum][i].worldY=game.tileSize*85;
        i++;
        game.interactNpc[mapNum][i]= new NPC_ShipOtherSide(game);
        game.interactNpc[mapNum][i].worldX=game.tileSize*29;
        game.interactNpc[mapNum][i].worldY=game.tileSize*11;
        i++;
        //Direction
        game.interactNpc[mapNum][i]= new DirectionBoard(game,1);
        game.interactNpc[mapNum][i].worldX=game.tileSize*90;
        game.interactNpc[mapNum][i].worldY=game.tileSize*120;
        i++;
        game.interactNpc[mapNum][i]= new DirectionBoard(game,2);
        game.interactNpc[mapNum][i].worldX=game.tileSize*64;
        game.interactNpc[mapNum][i].worldY=game.tileSize*156;
        i++;
        game.interactNpc[mapNum][i]= new DirectionSingleBoard(game,3);
        game.interactNpc[mapNum][i].worldX=game.tileSize*93;
        game.interactNpc[mapNum][i].worldY=game.tileSize*153;
        i++;
        game.interactNpc[mapNum][i]= new DirectionSingleBoard(game,4);
        game.interactNpc[mapNum][i].worldX=game.tileSize*165;
        game.interactNpc[mapNum][i].worldY=game.tileSize*111;
        i++;
        game.interactNpc[mapNum][i]= new DirectionSingleBoard(game,4);
        game.interactNpc[mapNum][i].worldX=game.tileSize*142;
        game.interactNpc[mapNum][i].worldY=game.tileSize*141;
        i++;
        game.interactNpc[mapNum][i]= new DirectionSingleBoard(game,5);
        game.interactNpc[mapNum][i].worldX=game.tileSize*177;
        game.interactNpc[mapNum][i].worldY=game.tileSize*141;
        i++;
        game.interactNpc[mapNum][i]= new DirectionSingleBoard(game,6);
        game.interactNpc[mapNum][i].worldX=game.tileSize*148;
        game.interactNpc[mapNum][i].worldY=game.tileSize*72;
        i++;
        game.interactNpc[mapNum][i]= new DirectionSingleBoard(game,7);
        game.interactNpc[mapNum][i].worldX=game.tileSize*136;
        game.interactNpc[mapNum][i].worldY=game.tileSize*153;
        i++;



        //set Npc
        i=0;
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



//    public static void addObjectToDB(int userId){
//                //  FOR THE FIRST MAP
//        int mapNum=0;
//        int itemCode=0;
//        // FOR OBJECT
//        int objecttype= Game.gameInstance.type_object;
//        MyJDBC.addObject(userId,objecttype,Game.gameInstance.sword.itemCode,93,118,mapNum);
//        MyJDBC.addObject(userId,objecttype,Game.gameInstance.redPotion.itemCode,144,149,mapNum);
//        MyJDBC.addObject(userId,objecttype,Game.gameInstance.shieldWood.itemCode,144,149,mapNum);
//        MyJDBC.addObject(userId,objecttype,Game.gameInstance.shieldWood.itemCode,67,126,mapNum);
//        MyJDBC.addObject(userId,objecttype,Game.gameInstance.speedPotion.itemCode,71,119,mapNum);
//        MyJDBC.addObject(userId,objecttype,Game.gameInstance.redPotion.itemCode,85,128,mapNum);
//        MyJDBC.addObject(userId,objecttype,Game.gameInstance.powerPotion.itemCode,76,128,mapNum);
//        MyJDBC.addObject(userId,objecttype,Game.gameInstance.coin.itemCode,77,118,mapNum);
//        MyJDBC.addObject(userId,objecttype,Game.gameInstance.coin.itemCode,47,128,mapNum);
//        MyJDBC.addObject(userId,objecttype,Game.gameInstance.key.itemCode,93,110,mapNum);
//        MyJDBC.addObject(userId,objecttype,Game.gameInstance.door.itemCode,28,161,mapNum);
//        MyJDBC.addObject(userId,objecttype,Game.gameInstance.axe.itemCode,172,158,mapNum);
//
//
//        //FOR INTERACTIVE TILE
//         objecttype= Game.gameInstance.type_interactiveTIle;
//
//        MyJDBC.addObject(userId,objecttype,itemCode,53,142,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,54,142,mapNum);
//
//        // Key Hill Area
//        MyJDBC.addObject(userId,objecttype,itemCode,95,113,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,97,113,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,99,113,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,98,114,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,101,114,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,97,115,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,98,115,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,99,115,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,100,115,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,102,115,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,105,115,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,99,116,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,100,116,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,105,117,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,108,117,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,108,118,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,112,118,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,102,119,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,103,119,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,107,119,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,108,119,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,101,120,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,102,120,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,107,120,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,112,120,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,114,120,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,116,120,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,111,121,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,112,121,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,113,121,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,115,121,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,117,121,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,111,122,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,113,122,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,114,122,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,117,122,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,111,123,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,115,123,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,116,123,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,112,124,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,113,124,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,114,124,mapNum);
//        MyJDBC.addObject(userId,objecttype,itemCode,115,124,mapNum);
//
//
//
//
//
//
//        // <---------- MONSTER ---------->
//
//
//        // Adding monsters
//        addMonsters(userId, mapNum);
//
//
//
//
//    }


    public static void addObjectToDB(int userId) {
        //  FOR THE FIRST MAP
        int mapNum = 0;
        int objecttype = Game.gameInstance.type_object;

        List<ObjectData> objects = new ArrayList<>();

        // Add objects to the list
        addObjectToList(objects, userId, objecttype, Game.gameInstance.sword.itemCode, 93, 118, mapNum);
        addObjectToList(objects, userId, objecttype, Game.gameInstance.redPotion.itemCode, 144, 149, mapNum);
        addObjectToList(objects, userId, objecttype, Game.gameInstance.shieldWood.itemCode, 144, 149, mapNum);
        addObjectToList(objects, userId, objecttype, Game.gameInstance.shieldWood.itemCode, 67, 126, mapNum);
        addObjectToList(objects, userId, objecttype, Game.gameInstance.speedPotion.itemCode, 71, 119, mapNum);
        addObjectToList(objects, userId, objecttype, Game.gameInstance.redPotion.itemCode, 85, 128, mapNum);
        addObjectToList(objects, userId, objecttype, Game.gameInstance.powerPotion.itemCode, 76, 128, mapNum);
        addObjectToList(objects, userId, objecttype, Game.gameInstance.coin.itemCode, 77, 118, mapNum);
        addObjectToList(objects, userId, objecttype, Game.gameInstance.coin.itemCode, 47, 128, mapNum);
        addObjectToList(objects, userId, objecttype, Game.gameInstance.key.itemCode, 93, 110, mapNum);
        addObjectToList(objects, userId, objecttype, Game.gameInstance.blueKey.itemCode, 68, 37, mapNum);
        addObjectToList(objects, userId, objecttype, Game.gameInstance.door.itemCode, 28, 161, mapNum);
        addObjectToList(objects, userId, objecttype, Game.gameInstance.axe.itemCode, 172, 158, mapNum);
        addObjectToList(objects, userId, objecttype, Game.gameInstance.chest.itemCode, 19, 121, mapNum);

        // FOR INTERACTIVE TILE
        objecttype = Game.gameInstance.type_interactiveTIle;
        addInteractiveTilesToList(objects, userId, objecttype, mapNum);

        // Insert all objects in a single batch
        MyJDBC.addObjectBatch(objects);

        // <---------- MONSTER ---------->


        // Adding monsters
        addMonsters(userId, mapNum);
    }

    private static void addObjectToList(List<ObjectData> objects, int userId, int objectType, int itemCode, int col, int row, int mapNum) {
        objects.add(new ObjectData(userId, objectType, itemCode, col, row, mapNum));
    }

    private static void addInteractiveTilesToList(List<ObjectData> objects, int userId, int objectType, int mapNum) {
        int itemCode = 0; // Assuming itemCode is 0 for interactive tiles
        int[][] tilePositions = {
                {53, 142}, {54, 142}, // Add more positions as needed

                // Key Hill Area positions
                {95, 113}, {97, 113}, {99, 113}, {98, 114}, {101, 114},
                {97, 115}, {98, 115}, {99, 115}, {100, 115}, {102, 115}, {105, 115},
                {99, 116}, {100, 116}, {105, 117}, {108, 117}, {108, 118}, {112, 118},
                {102, 119}, {103, 119}, {107, 119}, {108, 119}, {101, 120}, {102, 120},
                {107, 120}, {112, 120}, {114, 120}, {116, 120}, {111, 121}, {112, 121},
                {113, 121}, {115, 121}, {117, 121}, {111, 122}, {113, 122}, {114, 122},
                {117, 122}, {111, 123}, {115, 123}, {116, 123}, {112, 124}, {113, 124},
                {114, 124}, {115, 124},
                // Add more positions as needed
                //pihi
                //port Area
                {16, 102}, {17, 102}, {19, 102}, {21, 102},
                {17, 103}, {20, 103}, {23, 103},
                {16, 104},{17, 104},{18, 104},{20, 104},{21, 104},{24, 104},{27, 104},{28, 104},{29, 104},
                {16, 105},{18, 105},{19, 105},{20, 105},{22, 105},{25, 105},{26, 105},{30, 105},{32, 105},{34, 105},
                {16, 106},{17, 106},{18, 106},{19, 106},{20, 106},{22, 106},{24, 106},{26, 106},{28, 106},{33, 106},{36, 106},
                {19, 107},{21, 107},{23, 107},{26, 107},{30, 107},{31, 107},{34, 107},{35, 107},{37, 107},{39, 107},{40, 107},{42, 107},{43, 107},
                {17, 108},{18, 108},{20, 108},{22, 108},{24, 108},{25, 108},{27, 108},{29, 108},{30, 108},{31, 108},{32, 108},{34, 108},{36, 108},{37, 108},{42, 108},{44, 108},
                {16, 109},{18, 109},{19, 109},{21, 109},{22, 109},{23, 109},{24, 109},{25, 109},{27, 109},{28, 109},{30, 109},{32, 109},{33, 109},{34, 109},{35, 109},{38, 109},{40, 109},{41, 109},{43, 109},{45, 109},{46, 109},
                {17, 110},{19, 110},{20, 110},{21, 110},{22, 110},{23, 110},{24, 110},{25, 110},{26, 110},{27, 110},{29, 110},{31, 110},{33, 110},{35, 110},{36, 110},{37, 110},{38, 110},{41, 110},{42, 110},{44, 110},{45, 110},{46, 110},{47, 110},{48, 110},{49, 110},
                {16, 111},{17, 111},{18, 111},{19, 111},{20, 111},{21, 111},{22, 111},{23, 111},{24, 111},{25, 111},{27, 111},{28, 111},{29, 111},{30, 111},{32, 111},{33, 111},{34, 111},{35, 111},{36, 111},{37, 111},{38, 111},{39, 111},{41, 111},{42, 111},{43, 111},
                {16, 112},{17, 112},{18, 112},{21, 112},{23, 112},{24, 112},{25, 112},{26, 112},{27, 112},{29, 112},{30, 112},{31, 112},{32, 112},{34, 112},{36, 112},{38, 112},{40, 112},
                {16, 113},{31, 113},{32, 113},{33, 113},{34, 113},{35, 113},{36, 113},{37, 113},{38, 113},
                {33, 114}, {35, 114}, {33, 115}, {34, 115}, {35, 115}, {36, 115}, {33, 116}, {34, 116}, {34, 117},
        };
        for (int[] pos : tilePositions) {
            objects.add(new ObjectData(userId, objectType, itemCode, pos[0], pos[1], mapNum));
        }
    }

    private static void addMonsters(int userId, int mapNum) {
        Entity entity = new Entity(Game.gameInstance);
        int monIndex = 0;

        int[][] townHall1Positions = {
                {entity.type_worm, entity.area_th_1, 64, 122}, {entity.type_worm, entity.area_th_1, 43, 125},
                {entity.type_worm, entity.area_th_1, 57, 130}, {entity.type_worm, entity.area_th_1, 67, 135},
                {entity.type_worm, entity.area_th_1, 45, 140}, {entity.type_worm, entity.area_th_1, 45, 142},
                {entity.type_worm, entity.area_th_1, 41, 147}, {entity.type_worm, entity.area_th_1, 67, 141},
                {entity.type_worm, entity.area_th_1, 56, 145}, {entity.type_slime, entity.area_th_1, 62, 129},
                {entity.type_slime, entity.area_th_1, 70, 130}, {entity.type_slime, entity.area_th_1, 51, 133},
                {entity.type_slime, entity.area_th_1, 43, 134}, {entity.type_slime, entity.area_th_1, 57, 136},
                {entity.type_slime, entity.area_th_1, 64, 140}, {entity.type_slime, entity.area_th_1, 66, 146},
                {entity.type_slime, entity.area_th_1, 62, 149}, {entity.type_slime, entity.area_th_1, 55, 154},
                {entity.type_slime, entity.area_th_1, 65, 155}, {entity.type_slime, entity.area_th_1, 49, 157},
                {entity.type_slime, entity.area_th_1, 39, 158}, {entity.type_slime, entity.area_th_1, 55, 158},
                {entity.type_slime, entity.area_th_1, 68, 160}, {entity.type_pacmanGreen, entity.area_th_1, 61, 142}
        };

        int[][] townHall2Positions = {
                {entity.type_worm, entity.area_th_2, 103, 128},
                {entity.type_worm, entity.area_th_2, 123, 129},
                {entity.type_slime, entity.area_th_2, 96, 134},
                {entity.type_slime, entity.area_th_2, 122, 136},
                {entity.type_pacman, entity.area_th_2, 107, 133},
                {entity.type_pacman, entity.area_th_2, 129, 125},
                {entity.type_pacmanGreen, entity.area_th_2, 114, 135},
                {entity.type_pacmanGreen, entity.area_th_2, 124, 131},
                {entity.type_spider, entity.area_th_2, 108, 130},
                {entity.type_spiderBrown, entity.area_th_2, 129, 135},
                {entity.type_redFly, entity.area_th_2, 118, 130}
        };

        int[][] townHall3Positions = {
                {entity.type_worm, entity.area_th_3, 147, 155}, {entity.type_worm, entity.area_th_3, 156, 177},
                {entity.type_worm, entity.area_th_3, 170, 177}, {entity.type_worm, entity.area_th_3, 161, 155},
                {entity.type_worm, entity.area_th_3, 164, 165}, {entity.type_slime, entity.area_th_3, 160, 163},
                {entity.type_slime, entity.area_th_3, 164, 168}, {entity.type_slime, entity.area_th_3, 154, 165},
                {entity.type_slime, entity.area_th_3, 150, 169}, {entity.type_slime, entity.area_th_3, 162, 172},
                {entity.type_slime, entity.area_th_3, 175, 169}, {entity.type_slime, entity.area_th_3, 175, 169},
                {entity.type_slime, entity.area_th_3, 183, 156}, {entity.type_slime, entity.area_th_3, 164, 156},
                {entity.type_pacman, entity.area_th_3, 164, 156}, {entity.type_pacman, entity.area_th_3, 157, 174},
                {entity.type_pacmanGreen, entity.area_th_3, 162, 160}, {entity.type_pacmanGreen, entity.area_th_3, 148, 168},
                {entity.type_pacmanGreen, entity.area_th_3, 153, 169}, {entity.type_spiderBrown, entity.area_th_3, 160, 176}
        };

        int[][] townHall4Positions = {
                {entity.type_worm, entity.area_th_4, 169, 104}, {entity.type_worm, entity.area_th_4, 160, 91},
                {entity.type_worm, entity.area_th_4, 148, 93}, {entity.type_worm, entity.area_th_4, 137, 90},
                {entity.type_worm, entity.area_th_4, 130, 91}, {entity.type_worm, entity.area_th_4, 128, 100},
                {entity.type_worm, entity.area_th_4, 116, 90}, {entity.type_slime, entity.area_th_4, 173, 94},
                {entity.type_slime, entity.area_th_4, 167, 97}, {entity.type_slime, entity.area_th_4, 147, 97},
                {entity.type_slime, entity.area_th_4, 135, 101}, {entity.type_slime, entity.area_th_4, 139, 96},
                {entity.type_slime, entity.area_th_4, 128, 105}, {entity.type_slime, entity.area_th_4, 122, 97},
                {entity.type_slime, entity.area_th_4, 119, 99}, {entity.type_slime, entity.area_th_4, 117, 104},
                {entity.type_slime, entity.area_th_4, 110, 94}, {entity.type_pacman, entity.area_th_4, 157, 100},
                {entity.type_pacman, entity.area_th_4, 151, 92}, {entity.type_pacmanGreen, entity.area_th_4, 122, 105},
                {entity.type_pacmanGreen, entity.area_th_4, 131, 97}, {entity.type_pacmanGreen, entity.area_th_4, 127, 108},
                {entity.type_spider, entity.area_th_4, 121, 101}
        };


        // Town Hall 5
        int[][] townHall5Positions = {
                {entity.type_worm, entity.area_th_5, 19, 173},
                {entity.type_worm, entity.area_th_5, 43, 183},
                {entity.type_slime, entity.area_th_5, 21, 167},
                {entity.type_slime, entity.area_th_5, 64, 183},
                {entity.type_pacmanGreen, entity.area_th_5, 20, 165},
                {entity.type_pacmanGreen, entity.area_th_5, 63, 173},
                {entity.type_pacman, entity.area_th_5, 68, 180}
        };

        // Hill 1
        int[][] hill1Positions = {
                {entity.type_slime, entity.area_hill_1, 147, 103},
                {entity.type_slime, entity.area_hill_1, 155, 108},
                {entity.type_slime, entity.area_hill_1, 154, 116},
                {entity.type_slime, entity.area_hill_1, 161, 121},
                {entity.type_slime, entity.area_hill_1, 174, 119},
                {entity.type_slime, entity.area_hill_1, 171, 108},
                {entity.type_worm, entity.area_hill_1, 161, 113},
                {entity.type_redFly, entity.area_hill_1, 167, 113},
                {entity.type_blueGhost, entity.area_hill_1, 167, 105}
        };


        // Hill 2
        int[][] hill2Positions = {
                {entity.type_worm, entity.area_hill_2, 128, 97},
                {entity.type_worm, entity.area_hill_2, 106, 89},
                {entity.type_slime, entity.area_hill_2, 131, 101},
                {entity.type_slime, entity.area_hill_2, 102, 89},
                {entity.type_slime, entity.area_hill_2, 107, 80},
                {entity.type_slime, entity.area_hill_2, 123, 85},
                {entity.type_redFly, entity.area_hill_2, 97, 80},
                {entity.type_redFly, entity.area_hill_2, 121, 89},
                {entity.type_redFly, entity.area_hill_2, 137, 101},
                {entity.type_spider, entity.area_hill_2, 109, 87},
                {entity.type_spider, entity.area_hill_2, 128, 87},
                {entity.type_spider, entity.area_hill_2, 102, 98},
                {entity.type_blueGhost, entity.area_hill_2, 99, 89},
                {entity.type_blueGhost, entity.area_hill_2, 115, 86},
                {entity.type_blueGhost, entity.area_hill_2, 119, 94},
                {entity.type_blueGhost, entity.area_hill_2, 98, 101},
                {entity.type_blueGhost, entity.area_hill_2, 124, 101},
                {entity.type_blueGhost, entity.area_hill_2, 134, 89},
                {entity.type_blueGhost, entity.area_hill_2, 128, 82},
                {entity.type_pacmanGreen, entity.area_hill_2, 103, 96}
        };

        // Hill 3
        int[][] hill3Positions = {
                {entity.type_arc, entity.area_hill_3, 89, 68},
                {entity.type_worm, entity.area_hill_3, 81, 74},
                {entity.type_spiderBrown, entity.area_hill_3, 77, 69},
                {entity.type_slime, entity.area_hill_3, 74, 78},
                {entity.type_blueGhost, entity.area_hill_3, 82, 79},
                {entity.type_redFly, entity.area_hill_3, 87, 80},
                {entity.type_redFly, entity.area_hill_3, 66, 74},
                {entity.type_blueGhost, entity.area_hill_3, 61, 68},
                {entity.type_slime, entity.area_hill_3, 59, 71},
                {entity.type_worm, entity.area_hill_3, 52, 74},
                {entity.type_spiderBrown, entity.area_hill_3, 60, 81},
                {entity.type_blueGhost, entity.area_hill_3, 51, 78}
        };

        // Hill 4
        int[][] hill4Positions = {
                {entity.type_redFly, entity.area_hill_4, 71, 105},
                {entity.type_redFly, entity.area_hill_4, 29, 100},
                {entity.type_spider, entity.area_hill_4, 46, 104},
                {entity.type_blueGhost, entity.area_hill_4, 59, 106}
        };


        // Monster Island 1
        int[][] mi1positions = {
                {entity.type_redOrc, entity.area_mi_1, 110, 18},
                {entity.type_spider, entity.area_mi_1, 118, 16},
                {entity.type_spider, entity.area_mi_1, 114, 26},
                {entity.type_redFly, entity.area_mi_1, 121, 27},
                {entity.type_spider, entity.area_mi_1, 128, 18},
                {entity.type_redFly, entity.area_mi_1, 131, 21},
                {entity.type_spiderBrown, entity.area_mi_1, 140, 23},
                {entity.type_worm, entity.area_mi_1, 131, 27},
                {entity.type_arc, entity.area_mi_1, 139, 27},
                {entity.type_spider, entity.area_mi_1, 130, 36}
        };

        // Monster Island 2
        int[][] mi2positions = {
                {entity.type_redOrc, entity.area_mi_2, 153, 18},
                {entity.type_blueGhost, entity.area_mi_2, 162, 18},
                {entity.type_redFly, entity.area_mi_2, 157, 23},
                {entity.type_arc, entity.area_mi_2, 153, 28},
                {entity.type_redOrc, entity.area_mi_2, 158, 30},
                {entity.type_redOrc, entity.area_mi_2, 168, 22},
                {entity.type_blueGhost, entity.area_mi_2, 166, 27},
                {entity.type_skeleton, entity.area_mi_2, 167, 36},
                {entity.type_spider, entity.area_mi_2, 173, 36},
                {entity.type_redOrc, entity.area_mi_2, 181, 35},
                {entity.type_redOrc, entity.area_mi_2, 182, 27},
                {entity.type_redFly, entity.area_mi_2, 182, 20},
                {entity.type_redFly, entity.area_mi_2, 172, 27},
        };


        // Monster Island 3
        int[][] mi3positions = {
                {entity.type_blueGhost, entity.area_mi_3, 167, 42},
                {entity.type_redOrc, entity.area_mi_3, 159, 46},
                {entity.type_arc, entity.area_mi_3, 165, 48},
                {entity.type_blueGhost, entity.area_mi_3, 168, 52},
                {entity.type_skeleton, entity.area_mi_3, 164, 54},
                {entity.type_skeleton, entity.area_mi_3, 171, 56},
                {entity.type_redFly, entity.area_mi_3, 177, 62},
                {entity.type_skeleton, entity.area_mi_3, 182, 60},
                {entity.type_skeleton, entity.area_mi_3, 183, 56},
                {entity.type_skeleton, entity.area_mi_3, 182, 49},
                {entity.type_redFly, entity.area_mi_3, 177, 44},
                {entity.type_redOrc, entity.area_mi_3, 177, 53}
        };



        // Monster setup without area
        int[][] noAreaPositions = {
                {entity.type_slime, 0, 94, 110},
                {entity.type_slime, 0, 98, 112},
                {entity.type_slime, 0, 104, 121},
                {entity.type_slime, 0, 110, 119},
                {entity.type_slime, 0, 112, 123},
                {entity.type_worm, 0, 101, 117},
                {entity.type_worm, 0, 114, 119},
                {entity.type_pacmanGreen, 0, 106, 118},

                {entity.type_slimeMother,0,31,32},
                {entity.type_slime, 0, 39, 34},
                {entity.type_slime, 0, 21, 32},
                {entity.type_slime, 0, 29, 39},
                {entity.type_slime, 0, 18, 22},
                {entity.type_slime, 0, 44, 22},


                {entity.type_arc, 0, 71, 24},
                {entity.type_redFly, 0, 67, 27},
                {entity.type_blueGhost, 0, 86, 24},
                {entity.type_arc, 0, 66, 33},
                {entity.type_slime, 0, 70, 36},
                {entity.type_worm, 0, 78, 33},
                {entity.type_slime, 0, 94, 34},
                {entity.type_pacmanGreen, 0, 99, 27},
                {entity.type_arc, 0, 102, 48},
                {entity.type_blueGhost, 0, 96, 57},
                {entity.type_slime, 0, 105, 54},
                {entity.type_spider, 0, 115, 62},
                {entity.type_spider, 0,111, 49},
                {entity.type_spider, 0, 129, 57},
                {entity.type_spiderBrown, 0, 128, 50},
                {entity.type_spiderBrown, 0, 114, 57},
                {entity.type_redFly, 0, 121, 53},
                {entity.type_slime, 0, 122, 62},
                {entity.type_slime, 0, 135, 72},
                {entity.type_slime, 0, 163, 94},
                {entity.type_arc, 0, 156, 85},
                {entity.type_pacman, 0, 169, 81},
                {entity.type_slime, 0, 148, 79},
                {entity.type_redFly, 0, 54, 45},
                {entity.type_blueGhost, 0, 64, 47},
                {entity.type_redFly, 0, 64, 40},


                // Soil area...
                {entity.type_sixEyes, 0, 161, 71}, {entity.type_sixEyes, 0, 156, 63},
                {entity.type_sixEyes, 0, 169, 71}, {entity.type_sixEyes, 0, 156, 77},


                // Port area...
                {entity.type_sixEyes, 0, 17, 116},
                {entity.type_blueGhost, 0 , 24, 119},
                {entity.type_sixEyes, 0, 31, 119},

        };







        int[][][] allPositions = {townHall1Positions,  townHall3Positions,  hill1Positions,  hill3Positions,   mi3positions, noAreaPositions};
//        int[][][] allPositions = {townHall1Positions, townHall2Positions, townHall3Positions, townHall4Positions, townHall5Positions, hill1Positions, hill2Positions, hill3Positions, hill4Positions, mi1positions, mi2positions, mi3positions, noAreaPositions};

        List<int[]> allMonsters = new ArrayList<>();

        for (int i = 0; i < allPositions.length; i++) {
            for (int[] pos : allPositions[i]) {
                allMonsters.add(new int[]{userId, monIndex++, pos[0], pos[1], pos[2], pos[3], mapNum});
            }
        }

        MyJDBC.addMonsters(allMonsters);
    }

    //initial adding object in inventory
//    public static void addInventoryToDB(int userId){
////Now i declare all item code in an array that i will need.
//        int itemCode[]=new int[]{101,102,103,104,105,106,107,201,202,203,204,205,206,207,208,303,304,305,306,307};
//        for(int i=0;i<itemCode.length;i++){
//            MyJDBC.addInventory(userId,itemCode[i]);
//        }
//    }
    public static void addInventoryToDB(int userId) {
        int[] itemCodes = new int[]{101, 102, 103, 104, 105, 106, 107,108,109, 201, 202, 203, 204, 205, 206, 207, 208, 303, 304, 305, 306, 307,308};
        MyJDBC.addInventory(userId, itemCodes);
    }
}
