package com.example.return_3.gameCenter.spaceInvaders;

import com.example.return_3.main.Game;
import com.example.return_3.main.GameAnimationTimer;
import com.example.return_3.main.KeyHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.util.Objects;

public class GameSpaceInvaders {

    Game game;
    //SCREEN SETTINGS
    public final int tileSize = 32; //SO every tile will be 32 pixels
    public final int maxScreenCol = 28; //here will be 20 column of titles  =>1024 pixel width
    public final int maxScreenRow = 18; //here will be 25 row of titles => 800 pixel height
    public final int screenWidth= tileSize*maxScreenCol; //1024 pixel width
    public final int screenHeight= tileSize*maxScreenRow; //800 pixel height
    public static final double targetFrameRate=60.0;
    public static final double targetFrameTime=1/targetFrameRate; //0.01666666666 it means per second e frame 60. so 0.01666666666 second e ekbar frame change
    public double accumulatedTime=0.0; // accumulated time
    public long lastNanoTime;

    // $$$$$$$$$ System $$$$$$$$$
    //instantiates new instances
    Canvas canvas = new Canvas(screenWidth, screenHeight);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    SpaceShip spaceShip ;
    Image backgroundImage;


    public GameSpaceInvaders( Game game){
        this.game = game;
    }



    public void startGameSpaceInvaders() throws Exception {
        game.gameStatus=game.gameSpaceInvadersStatus;
        Pane root = new Pane();
        Game.gameScene = new Scene(root, screenWidth, screenHeight); // Set the scene before creating KeyHandler
        //gameScene=scene;
//        player = new Player(this, new KeyHandler(this)); // KeyHandler depends on game.scene
        spaceShip=new SpaceShip(game,new KeyHandler(game));


        root.getChildren().add(canvas);
//        assetSetter.setNPC();
        lastNanoTime = System.nanoTime();
//        assetSetter.setNPC();
        Game.gameTimer = new GameAnimationTimer(game);
        Game.gameTimer.start();

        game.gameState=game.playState;

        game.primaryStage.setScene(Game.gameScene);
    }



    public void update(){
        //System.out.println("update working");
        spaceShip.update();
    }

    public void draw( GraphicsContext gc){
        System.out.println("GameSpaceInvaders Class: `drawMethod`  draw working");
        //Now drawing the background


        //Drawing spaceShip
        spaceShip.draw(this.gc);
    }

    public Image loadImage(String imagePath, int width, int height) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)), width, height, true, true);
    }

}
