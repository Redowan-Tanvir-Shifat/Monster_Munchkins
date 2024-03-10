package com.example.return_3.main;

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
    public Image loadImage(String imagePath, int width, int height) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)), width, height, true, true);
    }
}
