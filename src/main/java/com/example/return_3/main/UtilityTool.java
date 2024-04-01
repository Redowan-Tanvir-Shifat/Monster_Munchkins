package com.example.return_3.main;

import com.example.return_3.entity.Entity;
import com.example.return_3.object.*;
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
        } else if (entity.worldX + Game.gameInstance.tileSize > x+width) {
            entity.worldX =x+ width - Game.gameInstance.tileSize; // Prevent player from moving beyond the right boundary
        }

        if (entity.worldY < y) {
            entity.worldY = y; // Prevent player from moving beyond the top boundary
        } else if (entity.worldY + Game.gameInstance.tileSize >y+ height) {
            entity.worldY = y+height- Game.gameInstance.tileSize; // Prevent player from moving beyond the bottom boundary
        }
    }
    public static Entity inventoryItem(int itemCode){
        return switch (itemCode) {
            case 101 -> new OBJ_Sword_Normal(Game.gameInstance);
            case 102 -> new OBJ_Axe(Game.gameInstance);
            case 103 -> new OBJ_Shield_Wood(Game.gameInstance);
            case 104 -> new OBJ_Shield_Blue(Game.gameInstance);
            case 301 -> new OBJ_Potion_Red(Game.gameInstance);
            default -> null;
        };
    }
}
