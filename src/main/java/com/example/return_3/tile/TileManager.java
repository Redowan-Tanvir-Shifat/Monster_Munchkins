package com.example.return_3.tile;

import com.example.return_3.main.Game;
import com.example.return_3.main.UtilityTool;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class TileManager {
    Game game;
    public Tile[] tile;
    public int mapTileNum[][][];
    ArrayList<String> filenames=new ArrayList<String>();
    ArrayList<String> collisionStatus=new ArrayList<String>();
    public TileManager(Game game){
        this.game=game;

        // Read the tile data file first
        InputStream is = getClass().getResourceAsStream("/maps/finalMap_data.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        // Getting Tile data and collision q from the file
        String line;
        try{
            while((line=br.readLine())!=null){
                filenames.add(line);
                collisionStatus.add(br.readLine());
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        // ININITIALIZE the ARRAY BASED ON THE FILENAMES SIZE
        tile= new Tile[filenames.size()];
        getTileImage();

        // Get the max world col and row
        is = getClass().getResourceAsStream("/maps/finalMap.txt");
        br = new BufferedReader(new InputStreamReader(is));
        try{
            String line2 = br.readLine();
            String maxTile[]=line2.split(" ");
            game.maxWorldCol=maxTile.length;
            game.maxWorldRow=maxTile.length;
            mapTileNum= new int[game.maxMap][game.maxWorldCol][game.maxWorldRow];
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }

         loadMap("/maps/finalMap.txt",0);
         //loadMap("/maps/gameMap.txt",1);
       // loadMap("/maps/map03.txt",2);
    }

    public void getTileImage(){
        for(int i=0; i<filenames.size();i++){
            String filename;
            boolean collision;

            // Get a file name
            filename= filenames.get(i);
            // Get Collision status
            collision = collisionStatus.get(i).equals("true");

            setup(i,filename,collision);
        }
    }

    // Create a setup function to set the tile so that it will saved its height and width.
    public void setup(int index,String imageName,boolean collision){
        UtilityTool uTool= new UtilityTool();
        try {
            tile[index] = new Tile();
            //tile[index].image = new Image("/tiles/" + imageName);
            tile[index].image = new Image(getClass().getResource("/tiles/" + imageName).toString());

            tile[index].image=uTool.scaleImage(tile[index].image,game.tileSize,game.tileSize);

            tile[index].collision = collision;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
//
//    public void loadMap(String filePath, int map){
//        try{
//            InputStream is = getClass().getResourceAsStream(filePath);
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
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


//    public void loadMap(String filePath, int map) {
//        try {
//            InputStream is = getClass().getResourceAsStream(filePath);
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            int col = 0;
//            int row = 0;
//            while (row < game.maxWorldRow) {
//                String line = br.readLine();
//                String numbers[] = line.split(" ");
//                for (String number : numbers) {
//                    int num = Integer.parseInt(number);
//                    mapTileNum[map][col][row] = num;
//                    col++;
//                    if (col == game.maxWorldCol) {
//                        col = 0;
//                        row++;
//                        if (row == game.maxWorldRow) {
//                            break;
//                        }
//                    }
//                }
//            }
//            br.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void loadMap(String filePath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            String line;
            while ((line = br.readLine()) != null) { // Check if line is not null
                String numbers[] = line.split(" ");
                for (String number : numbers) {
                    int num = Integer.parseInt(number);
                    mapTileNum[map][col][row] = num;
                    col++;
                    if (col == game.maxWorldCol) {
                        col = 0;
                        row++;
                        if (row == game.maxWorldRow) {
                            break;
                        }
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void draw(GraphicsContext gc){
        // Draw tiles based on mapTileNum, similar to the Swing code
        int worldCol=0;
        int worldRow=0;
//        int x=0;
//        int y=0;


        while (worldCol<game.maxWorldCol && worldRow<game.maxWorldRow){
            int tileNum= mapTileNum[game.currentMap][worldCol][worldRow];

            int worldX=worldCol* game.tileSize;
            int worldY=worldRow* game.tileSize;
            int screenX= worldX-game.player.worldX + game.player.screenX;
            int screenY= worldY-game.player.worldY + game.player.screenY;


            //this process is for not drawing the whole world map but the map tiles we needed only
            if(worldX+ game.tileSize>game.player.worldX-game.player.screenX&&
                    worldX-game.tileSize<game.player.worldX+game.player.screenX&&
                    worldY+ game.tileSize>game.player.worldY-game.player.screenY&&
                    worldY- game.tileSize<game.player.worldY+game.player.screenY
            ){

                gc.drawImage(tile[tileNum].image,screenX,screenY);
            }

            worldCol++;
//            x+=gp.tileSize;


            if(worldCol==game.maxWorldCol){
                worldCol=0;
//                x=0;
                worldRow++;
//                y+=gp.tileSize;
            }
        }
    }
}
