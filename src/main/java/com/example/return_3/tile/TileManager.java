//package com.example.return_3.tile;
//
//import com.example.return_3.main.Game;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.image.Image;
//
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//
//public class TileManager {
//    Game game;
//    public Tile[] tile;
//    public int mapTileNum[][][];
//    ArrayList<String> filenames=new ArrayList<String>();
//    ArrayList<String> collisionStatus=new ArrayList<String>();
//    public TileManager( Game game){
//        this.game=game;
//
//        //Read the tile data file first
//        InputStream is= getClass().getResourceAsStream("/maps/map01_titleData.txt");
//        BufferedReader br=new BufferedReader(new InputStreamReader(is));
//
//        //Getting Tile data and collision q from the file
//        String line;
//        try{
//            while((line=br.readLine())!=null){
//                filenames.add(line);
//                collisionStatus.add(br.readLine());
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//
//
//        //ININITIALIZE the ARRAY BASED ON THE FILENAMES SIZE
//        tile= new Tile[filenames.size()];
//        getTileImage();
//
//        //Get the max world col and row
//        is=getClass().getResourceAsStream("/maps/map01.txt");
//        br=new BufferedReader(new InputStreamReader(is));
//        try{
//            String line2 = br.readLine();
//            String maxTile[]=line2.split(" ");
//            game.maxWorldCol=maxTile.length;
//            game.maxWorldRow=maxTile.length;
//            mapTileNum= new int[game.maxMap][game.maxWorldCol][game.maxWorldRow];
//
//            br.close();
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//
//
//        loadMap("/maps/map01.txt",0);
//        loadMap("/maps/map03.txt",1);
//    }
//
//
//
//
//    public void getTileImage(){
//
//        for(int i=0; i<filenames.size();i++){
//            String filename;
//            boolean collision;
//
//            //get a file name;
//            filename= filenames.get(i);
//            //get Collision status
//            if(collisionStatus.get(i).equals("true")){
//                collision=true;
//            }else{
//                collision=false;
//            }
//            setup(i,filename,collision);
//        }
//
//    }
//
//
//    //create a setup funciton to set the tile so that it will saved its height and width.
//    public void setup(int index,String imageName,boolean collision){
//        UtilityTool uTool= new UtilityTool();
//        try {
//            tile[index]=new Tile();
//            tile[index].image= new Image("/tiles/" + imageName);
//            tile[index].image=uTool.scaleImage(tile[index].image,gp.tileSize,gp.tileSize);
//            tile[index].collision=collision;
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//
//    public void loadMap( String filePath,int map){
//        try{
//            InputStream is= getClass().getResourceAsStream(filePath);
//            BufferedReader br= new BufferedReader(new InputStreamReader(is));
//            int col=0;
//            int row=0;
//            while (col<game.maxWorldCol && row<game.maxWorldRow){
//                String line= br.readLine();
//                while(col<game.maxWorldCol){
//                    String numbers[]= line.split(" ");
//                    int num= Integer.parseInt(numbers[col]);
//                    mapTileNum[map][col][row]=num;
//                    col++;
//                }
//
//                if(col==game.maxWorldCol){
//                    col=0;
//                    row++;
//                }
//            }
//            br.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//    public void draw(GraphicsContext gc){
//        int worldCol=0;
//        int worldRow=0;
////        int x=0;
////        int y=0;
//
//
//        while (worldCol<game.maxWorldCol && worldRow<game.maxWorldRow){
//            int tileNum= mapTileNum[game.currentMap][worldCol][worldRow];
//
//            int worldX=worldCol* game.tileSize;
//            int worldY=worldRow* game.tileSize;
//            int screenX= worldX-game.player.worldX + game.player.screenX;
//            int screenY= worldY-game.player.worldY + game.player.screenY;
//
//
//            //this process is for not drawing the whole world map but the map tiles we needed only
//            if(worldX+ game.tileSize>game.player.worldX-game.player.screenX&&
//                    worldX-game.tileSize<game.player.worldX+game.player.screenX&&
//                    worldY+ game.tileSize>game.player.worldY-game.player.screenY&&
//                    worldY- game.tileSize<game.player.worldY+game.player.screenY
//            ){
//
//                gc.drawImage(tile[tileNum].image,screenX,screenY);
//            }
//
//            worldCol++;
////            x+=gp.tileSize;
//
//
//            if(worldCol==game.maxWorldCol){
//                worldCol=0;
////                x=0;
//                worldRow++;
////                y+=gp.tileSize;
//            }
//        }
//    }
//}
