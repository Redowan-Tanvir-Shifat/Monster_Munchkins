package com.example.return_3.main;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {
    boolean keyRunning =false;

    public static final double targetFrameRate=60.0;
    public static final double targetFrameTime=1/targetFrameRate; //0.01666666666 it means per second e frame 60. so 0.01666666666 second e ekbar frame change

    public double accumulatedTime=0.0; // accumulated time
    public long lastNanoTime;


    private double playerSpeed=120; //pixel per second
    Image down1= new Image("boy_down_1.png");
    ImageView imageView;
    Image down2= new Image("boy_down_2.png");
    Image up1= new Image("boy_up_1.png");
    Image up2= new Image("boy_up_2.png");
    Image left1= new Image("boy_left_1.png");
    Image left2= new Image("boy_left_2.png");
    Image right1= new Image("boy_right_1.png");
    Image right2= new Image("boy_right_2.png");
    int playerCounter=0;
    KeyHandler keyHandler;

    int spriteCounter=0;
    int spriteNum=1;

    String direction="down1";











    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane(); //Pane is work as a panel
        Scene scene = new Scene(root,1000,600);
        stage.setScene(scene);
        stage.show();
        //Add a background color
        root.setStyle("-fx-background-color: black");



        imageView = new ImageView(down1);
        imageView.setX(scene.getWidth()/2);
        imageView.setY(scene.getHeight()/2);
        root.getChildren().add(imageView);


        keyHandler= new KeyHandler(this,scene);

        lastNanoTime=System.nanoTime();

        GameAnimationTimer gameTimer= new GameAnimationTimer(this,scene);
        gameTimer.start();
    }


    public void update( double deltaTime) {

        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        // Move player based on key inputs
        if (keyHandler.isMoveUp()) {
            imageView.setY(imageView.getY() - (playerSpeed*deltaTime));
            direction = "up";
        }
        if (keyHandler.isMoveDown()) {
            imageView.setY(imageView.getY() + (playerSpeed*deltaTime));
            direction = "down";
        }
        if (keyHandler.isMoveRight()) {
            imageView.setX(imageView.getX() + (playerSpeed*deltaTime));
            direction = "right";
        }
        if (keyHandler.isMoveLeft()) {
            imageView.setX(imageView.getX() - (playerSpeed*deltaTime));
            direction = "left";
        }

        // Update player sprite based on direction
        if(keyHandler.isMoveUp() || keyHandler.isMoveDown() || keyHandler.isMoveRight() || keyHandler.isMoveLeft()) {
            updatePlayerSprite();
        }

//        // Reset player sprite counter if needed
//        if (spriteCounter >= 2)
//            spriteCounter = 0;
//        else
//            spriteCounter++;
    }

    // Method to update player sprite based on direction
    private void updatePlayerSprite() {
        switch (direction) {
            case "up":
                if (spriteNum == 1){

                    imageView.setImage(up1);
                }
                else if(spriteNum == 2){
                    imageView.setImage(up2);
                }
                break;
            case "down":
                if (spriteNum == 1){

                    imageView.setImage(down1);
                }
                else if(spriteNum == 2){
                    imageView.setImage(down2);
                }
                break;
            case "right":
                if (spriteNum == 1){

                    imageView.setImage(right1);
                }
                else if(spriteNum == 2){
                    imageView.setImage(right2);
                }
                break;

            case "left":
                if (spriteNum == 1){

                    imageView.setImage(left1);
                }
                else if(spriteNum == 2){
                    imageView.setImage(left2);
                }
                break;
            // Handle other directions similarly
        }
    }

    public void render(){}


    public static void main(String[] args) {
        launch(args);
    }
}

