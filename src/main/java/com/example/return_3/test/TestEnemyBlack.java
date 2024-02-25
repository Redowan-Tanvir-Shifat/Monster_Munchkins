package com.example.return_3.test;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class TestEnemyBlack extends TestEntity {

    Image shotImage;
    int shotSpeed;

    public TestEnemyBlack(TestGame game) {
        super(game);
        loadPlayerImages();
        setDefaultValues();
        speed=(int) (130*game.targetFrameTime);
        shots = new ArrayList<>();
        shotSpeed=(int) (130*game.targetFrameTime);

    }




    public void loadPlayerImages() {
        up1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlack1.png", game.tileSize, game.tileSize);
        up2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlack1.png", game.tileSize, game.tileSize);
        down1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlack1.png", game.tileSize, game.tileSize);
        down2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlack1.png", game.tileSize, game.tileSize);
        left1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlack1.png", game.tileSize, game.tileSize);
        left2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlack1.png", game.tileSize, game.tileSize);
        right1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlack1.png", game.tileSize, game.tileSize);
        right2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlack1.png", game.tileSize, game.tileSize);
        shotImage=loadImage("/gameCenter/spaceInvaders/fire/fire19.png", game.tileSize, game.tileSize);
    }
    public void setAction(){
        actionLookCounter++;
        if(actionLookCounter == 120){ // Every two seconds
            // Get player position
            int playerX = game.testPlayer.posX;
            int playerY = game.testPlayer.posY;

            // Calculate the direction towards the player
            int dx = playerX - posX;
            int dy = playerY - posY;

            if(Math.max(Math.abs(dx), Math.abs(dy))<300){
                // Determine the primary direction based on the largest difference
                if(Math.abs(dx) > Math.abs(dy)){ // Horizontal movement
                    direction = dx > 0 ? "right" : "left";
                } else { // Vertical movement
                    direction = dy > 0 ? "down" : "up";
                }
            }else{
                Random random= new Random();
                int i=random.nextInt(100)+1; //we add 1 because otherwise it will catch 0 to 99.. we want to avoid 0 here
                if(i<=50){
                    direction="down";
                }
                if(i>50&&i<=75){
                    direction="left";
                }
                if(i>75&&i<=100){
                    direction="right";
                }
            }

            actionLookCounter = 0;
        }
    }




    public void update(){
        super.update();
        for (TestShot shot : shots) {
            shot.update();
        }
        // Remove inactive shots
        shots.removeIf(shot -> !shot.isActive());
        // Shoot at player
        shootAtPlayer();
//        System.out.println("enemyBlack posX: "+posX + " posY: "+posY);
//        System.out.println("enemyBlack solidX: "+solidAreaDefaultX + " solidY: "+solidAreaDefaultY);

    }

    private void shootAtPlayer() {
        shotAvailableCounter++;

        // Logic to determine when to shoot at the player
        // Create shot instances and add them to the shots list
        // Example:
        int i=new Random().nextInt(100)+1;
        if (i>99&&shotAvailableCounter>50) {
            TestShot shot = new TestShot(game,posX, posY, shotSpeed, direction, shotImage, screenWidth, screenHeight);
            shots.add(shot);
            shotAvailableCounter=0;
        }
    }


    public void draw(GraphicsContext gc) {
        super.draw(gc);
        // Draw shots
        for (TestShot shot : shots) {
            shot.draw(gc);
        }
    }


}
