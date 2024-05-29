package com.example.return_3.main;

import com.example.return_3.entity.Entity;
import com.example.return_3.object.*;
import com.example.return_3.object.food.*;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.util.Objects;

public class UtilityTool {
    public Image scaleImage(Image original, int width, int height) {
        WritableImage scaledImage = new WritableImage(width, height);
        PixelReader pixelReader = original.getPixelReader();
        PixelWriter pixelWriter = scaledImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            double yRatio = (double) y / height;
            int sourceY = (int) (yRatio * original.getHeight());

            for (int x = 0; x < width; x++) {
                double xRatio = (double) x / width;
                int sourceX = (int) (xRatio * original.getWidth());

                pixelWriter.setArgb(x, y, pixelReader.getArgb(sourceX, sourceY));
            }
        }

        return scaledImage;
    }
    public  Image loadImage(String imagePath, int width, int height) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)), width, height, true, true);
    }

    public void areaSetup(Entity entity, int x, int y, int width, int height){
        if (entity.worldX < x) {
            entity.worldX = x; // Prevent player from moving beyond the left boundary
            entity.direction="right";
        } else if (entity.worldX + Game.gameInstance.tileSize > x+width) {
            entity.worldX =x+ width - Game.gameInstance.tileSize; // Prevent player from moving beyond the right boundary
            entity.direction="left";
        }

        if (entity.worldY < y) {
            entity.worldY = y; // Prevent player from moving beyond the top boundary
            entity.direction="down";
        } else if (entity.worldY + Game.gameInstance.tileSize >y+ height) {
            entity.worldY = y+height- Game.gameInstance.tileSize; // Prevent player from moving beyond the bottom boundary
            entity.direction="up";
        }
    }
    public static Entity getInventoryItem(int itemCode){
        Game game = Game.gameInstance;
        Entity item=null;
        if(itemCode==101){
            item= new OBJ_Sword_Normal(game);
        } else if (itemCode==102) {
            item= new OBJ_Axe(game);
        } else if (itemCode==103) {
            item= new OBJ_Shield_Wood(game);
        } else if (itemCode==104) {
            item= new OBJ_Shield_Blue(game);
        } else if (itemCode==105) {
            item= new OBJ_Sword_Special(game);
        } else if (itemCode==106) {
            item= new OBJ_FireSword(game);
        } else if (itemCode==107) {
            item= new OBJ_Fireball(game);
        } else if (itemCode==108) {
            item = new OBJ_IceSword(game);
        } else if (itemCode==201) {
            item= new OBJ_Apple(game);
        } else if (itemCode==202) {
            item= new OBJ_Banana(game);
        } else if (itemCode==203) {
            item= new OBJ_Berries(game);
        } else if (itemCode==204) {
            item= new OBJ_Orange(game);
        } else if (itemCode==205) {
            item= new OBJ_Cheese(game);
        } else if (itemCode==206) {
            item= new OBJ_Egg(game);
        } else if (itemCode==207) {
            item= new OBJ_Fish(game);
        } else if (itemCode==208) {
            item= new OBJ_Meat(game);
        } else if (itemCode==303) {
            item= new OBJ_Potion_Red(game);
        }  else if (itemCode==304) {
            item= new OBJ_Key(game);
        }  else if (itemCode==305) {
            item= new OBJ_PowerPotion(game);
        }  else if (itemCode==306) {
            item= new OBJ_DefensePotion(game);
        }  else if (itemCode==307) {
            item= new OBJ_SpeedPotion(game);
        }
        return item;
    }

//    public static void updateItemInventory(
//
//    )
}
