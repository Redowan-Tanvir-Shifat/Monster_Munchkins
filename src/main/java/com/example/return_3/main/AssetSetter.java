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
//        game.obj[mapNum][i]=new OBJ_Coin(game);
//        game.obj[mapNum][i].worldX=game.tileSize*41;
//        game.obj[mapNum][i].worldY=game.tileSize*142;
//        i++;
//        game.obj[mapNum][i]=new OBJ_PowerPotion(game);
//        game.obj[mapNum][i].worldX=game.tileSize*42;
//        game.obj[mapNum][i].worldY=game.tileSize*142;
//        i++;
//        game.obj[mapNum][i]=new OBJ_Key(game);
//        game.obj[mapNum][i].worldX=game.tileSize*42;
//        game.obj[mapNum][i].worldY=game.tileSize*145;
////        i++;
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
//        game.obj[mapNum][14]=new OBJ_Door(game);
//        game.obj[mapNum][14].worldX=game.tileSize * 28;
//        game.obj[mapNum][14].worldY=game.tileSize * 161;
//        game.obj[mapNum][13]=new OBJ_PowerPotion(game);
//        game.obj[mapNum][13].worldX=game.tileSize*47;
//        game.obj[mapNum][13].worldY=game.tileSize*144;
//        game.obj[mapNum][15]=new OBJ_Key(game);
//        game.obj[mapNum][15].worldX=game.tileSize*46;
//        game.obj[mapNum][15].worldY=game.tileSize*144;
        game.obj[mapNum][16]=new OBJ_Key(game);
        game.obj[mapNum][16].worldX=game.tileSize*45;
        game.obj[mapNum][16].worldY=game.tileSize*144;
        i=17;
                game.obj[mapNum][i]=new OBJ_Key(game);
        game.obj[mapNum][i].worldX=game.tileSize*43;
        game.obj[mapNum][i].worldY=game.tileSize*143;
        i++;              game.obj[mapNum][i]=new OBJ_Key(game);
        game.obj[mapNum][i].worldX=game.tileSize*44;
        game.obj[mapNum][i].worldY=game.tileSize*143;
        i++;

       // i++;



    }
    public void setNPC(){
        int mapNum = 0;
        int i = 0;

//        game.npc[mapNum][i]= new NPC_Trade(game);
//        game.npc[mapNum][i].worldX=game.tileSize*48;
//        game.npc[mapNum][i].worldY=game.tileSize*135;
        game.interactNpc[mapNum][i]= new NPC_Welcome(game);
        game.interactNpc[mapNum][i].worldX=game.tileSize*81;
        game.interactNpc[mapNum][i].worldY=game.tileSize*117;
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
        MyJDBC.addObject(userId,objecttype,Game.gameInstance.shieldWood.itemCode,67,126,mapNum);
        MyJDBC.addObject(userId,objecttype,Game.gameInstance.speedPotion.itemCode,71,119,mapNum);
        MyJDBC.addObject(userId,objecttype,Game.gameInstance.redPotion.itemCode,85,128,mapNum);
        MyJDBC.addObject(userId,objecttype,Game.gameInstance.powerPotion.itemCode,76,128,mapNum);
        MyJDBC.addObject(userId,objecttype,Game.gameInstance.coin.itemCode,77,118,mapNum);
        MyJDBC.addObject(userId,objecttype,Game.gameInstance.coin.itemCode,47,128,mapNum);
        MyJDBC.addObject(userId,objecttype,Game.gameInstance.key.itemCode,57,120,mapNum);
        MyJDBC.addObject(userId,objecttype,Game.gameInstance.door.itemCode,28,161,mapNum);


        //FOR INTERACTIVE TILE
         objecttype= Game.gameInstance.type_interactiveTIle;

        MyJDBC.addObject(userId,objecttype,itemCode,53,142,mapNum);
        MyJDBC.addObject(userId,objecttype,itemCode,54,142,mapNum);

        //for monster types
        Entity entity=new Entity(Game.gameInstance);
        int monIndex=0;

        // <-----------------Area TownHall 1-------------------->
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_1,64,122,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_1,43,125,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_1,57,130,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_1,67,135,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_1,45,140,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_1,45,142,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_1,41,147,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_1,67,141,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_1,56,145,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_1,62,129,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_1,70,130,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_1,51,133,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_1,43,134,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_1,57,136,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_1,64,140,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_1,66,146,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_1,62,149,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_1,55,154,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_1,65,155,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_1,49,157,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_1,39,158,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_1,55,158,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_1,68,160,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_1,61,142,mapNum,monIndex);monIndex++;

        // <-----------------Area TownHall 2-------------------->
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_2,103,128,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_2,124,125,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_2,123,129,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_2,96,134,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_2,104,134,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_2,113,128,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_2,122,136,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_th_2,96,126,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_th_2,106,127,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_th_2,99,135,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_th_2,107,133,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_th_2,129,125,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_2,101,131,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_2,114,135,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_2,124,131,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_2,124,134,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_th_2,108,130,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spiderBrown,entity.area_th_2,129,135,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_redFly,entity.area_th_2,118,130,mapNum,monIndex);monIndex++;


        // <-----------------Area TownHall 3-------------------->
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_3,147,155,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_3,156,177,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_3,170,177,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_3,161,155,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_3,164,165,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_3,160,163,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_3,164,168,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_3,154,165,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_3,150,169,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_3,162,172,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_3,175,169,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_3,175,169,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_3,183,156,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_3,182,173,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_3,142,177,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_3,144,181,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_3,153,180,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_3,162,182,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_3,176,181,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_th_3,148,180,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_th_3,166,182,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_3,166,164,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_3,160,168,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_3,157,185,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spiderBrown,entity.area_th_3,155,167,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spiderBrown,entity.area_th_3,183,168,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_th_3,150,162,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_th_3,172,172,mapNum,monIndex);monIndex++;

        //<-----------------Area TownHall 4--------------------->
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_4,93,179,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_4,81,164,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_4,88,172,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_4,107,163,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_4,114,174,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_4,132,174,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_4,86,158,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_4,91,167,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_4,83,173,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_4,96,173,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_4,92,182,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_4,112,160,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_4,113,164,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_4,116,170,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_4,125,162,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_4,123,182,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_4,126,171,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_th_4,81,163,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_th_4,99,177,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_th_4,133,169,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_4,117,163,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_4,113,181,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_4,132,181,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_4,106,173,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_4,91,165,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_4,82,183,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_th_4,83,158,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_th_4,83,175,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spider,entity.area_th_4,98,166,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spiderBrown,entity.area_th_4,120,161,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spiderBrown,entity.area_th_4,132,162,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_spiderBrown,entity.area_th_4,121,177,mapNum,monIndex);monIndex++;

        //<-----------------Area TownHall 5--------------------->
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_5,19,173,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_5,37,165,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_5,40,168,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_5,49,171,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_worm,entity.area_th_5,43,183,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_5,21,167,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_5,29,169,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_5,26,173,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_5,32,165,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_5,42,165,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_5,43,172,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_5,54,170,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_5,60,169,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_5,60,176,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_5,54,182,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_slime,entity.area_th_5,64,183,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_5,20,165,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_5,25,173,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_5,36,168,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_5,48,168,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_5,53,174,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_5,59,180,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_5,68,172,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacmanGreen,entity.area_th_5,63,173,mapNum,monIndex);monIndex++;
        MyJDBC.addMonster(userId,entity.type_pacman,entity.area_th_5,68,180,mapNum,monIndex);monIndex++;


//         MyJDBC.addInteractiveTile(userId, 53, 142, mapNum); // Add interactive tiles
//         MyJDBC.addInteractiveTile(userId, 53, 143, mapNum); // Add interactive tiles
//         MyJDBC.addInteractiveTile(userId, 54, 142, mapNum); // Add interactive tiles
//         MyJDBC.addInteractiveTile(userId, 54, 143, mapNum); // Add interactive tiles
    }
}