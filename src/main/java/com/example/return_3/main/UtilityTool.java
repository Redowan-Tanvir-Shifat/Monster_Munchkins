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
            if(entity.type!=Entity.type_npc) {
                entity.direction = "right";
            }
        } else if (entity.worldX + Game.gameInstance.tileSize > x+width) {
            entity.worldX =x+ width - Game.gameInstance.tileSize; // Prevent player from moving beyond the right boundary
            if(entity.type!=Entity.type_npc) {
                entity.direction = "left";
            }        }

        if (entity.worldY < y) {
            entity.worldY = y; // Prevent player from moving beyond the top boundary
            if(entity.type!=Entity.type_npc) {
                entity.direction = "down";
            }
        } else if (entity.worldY + Game.gameInstance.tileSize >y+ height) {
            entity.worldY = y+height- Game.gameInstance.tileSize; // Prevent player from moving beyond the bottom boundary
            if(entity.type!=Entity.type_npc) {
                entity.direction = "up";
            }
        }
    }
    public static Entity getInventoryItem(int itemCode) {
        Game game = Game.gameInstance;
        Entity item = null;

        switch (itemCode) {
            case 101: item = new OBJ_Sword_Normal(game);break;
            case 102: item = new OBJ_Axe(game);break;
            case 103: item = new OBJ_Shield_Wood(game);break;
            case 104: item = new OBJ_Shield_Blue(game);break;
            case 105: item = new OBJ_Sword_Special(game);break;
            case 106: item = new OBJ_FireSword(game);break;
            case 107: item = new OBJ_Fireball(game);break;
            case 108: item = new OBJ_IceSword(game);break;
            case 109: item = new OBJ_Tomahawk(game);break;
            case 201: item = new OBJ_Apple(game);break;
            case 202: item = new OBJ_Banana(game);break;
            case 203: item = new OBJ_Berries(game);break;
            case 204: item = new OBJ_Orange(game);break;
            case 205: item = new OBJ_Cheese(game);break;
            case 206: item = new OBJ_Egg(game);break;
            case 207: item = new OBJ_Fish(game);break;
            case 208: item = new OBJ_Meat(game);break;
            case 303: item = new OBJ_Potion_Red(game);break;
            case 304: item = new OBJ_Key(game);break;
            case 305: item = new OBJ_PowerPotion(game);break;
            case 306: item = new OBJ_DefensePotion(game);break;
            case 307: item = new OBJ_SpeedPotion(game);break;
            case 308: item = new OBJ_BlueKey(game);break;
            default:
                // Handle invalid itemCode if necessary
                break;
        }
        return item;
    }

//    public static void updateItemInventory(
//
//    )
}
