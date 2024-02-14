package com.example.return_3.main;


import com.example.return_3.entity.Player;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {
    //SCREEN SETTINGS
    public final int tileSize =32; //SO everytiles will be 32 pixels
//    public final int maxScreenCol=32; //here will be 20 column of titles  =>1024 pixel width
//    public final int maxScreenRow=25; //here will be 25 row of titles => 800 pixel height
//    public final int screenWidth= tileSize*maxScreenCol; //1024 pixel width
//    public final int screenHeight= tileSize*maxScreenRow; //800 pixel height


    // $$$$$$$$$  World Setting $$$$$$$$$
    public  int maxWorldRow;
    public  int maxWorldCol;
    public final int maxMap=10;
    public int currentMap=1;



    public static final double targetFrameRate=60.0;
    public static final double targetFrameTime=1/targetFrameRate; //0.01666666666 it means per second e frame 60. so 0.01666666666 second e ekbar frame change

    public double accumulatedTime=0.0; // accumulated time
    public long lastNanoTime;


    int playerCounter=0;
    KeyHandler keyHandler;
    Player player ;
    public Scene scene;

    int spriteCounter=0;
    int spriteNum=1;

    String direction="down1";











    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane(); //Pane is work as a panel
         scene = new Scene(root,1000,600);
        stage.setScene(scene);
        stage.show();
        //Add a background color
        root.setStyle("-fx-background-color: black");


        keyHandler= new KeyHandler(this);

        // Initialize player
        player = new Player(this, new KeyHandler(this));
        // Add player's ImageView to the root pane
        root.getChildren().add(player.getPlayerImageView());


        lastNanoTime=System.nanoTime();

        GameAnimationTimer gameTimer= new GameAnimationTimer(this);
        gameTimer.start();
    }


    public void update( double deltaTime) {
        player.update(deltaTime);
    }

    // Method to update player sprite based on direction

    public void render(){
        player.draw();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

