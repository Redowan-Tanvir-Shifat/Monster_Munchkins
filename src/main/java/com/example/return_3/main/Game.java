package com.example.return_3.main;


import com.example.return_3.entity.Player;
import com.example.return_3.tile.TileManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {
    //SCREEN SETTINGS
    public final int tileSize =32; //SO everytiles will be 32 pixels
    public final int maxScreenCol=30; //here will be 20 column of titles  =>1024 pixel width
    public final int maxScreenRow=18; //here will be 25 row of titles => 800 pixel height
    public final int screenWidth= tileSize*maxScreenCol; //1024 pixel width
    public final int screenHeight= tileSize*maxScreenRow; //800 pixel height


    // $$$$$$$$$  World Setting $$$$$$$$$
    public  int maxWorldRow;
    public  int maxWorldCol;
    public final int maxMap=10;
    public int currentMap=1;



    public static final double targetFrameRate=60.0;
    public static final double targetFrameTime=1/targetFrameRate; //0.01666666666 it means per second e frame 60. so 0.01666666666 second e ekbar frame change

    public double accumulatedTime=0.0; // accumulated time
    public long lastNanoTime;


    // $$$$$$$$$ System $$$$$$$$$
    //instantiates new instances
    Canvas canvas = new Canvas(screenWidth, screenHeight);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    TileManager tileM= new TileManager(this);
    KeyHandler keyHandler;
    public Player player ;
    public Scene scene;

    int spriteCounter=0;
    int spriteNum=1;

    String direction="down1";











    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane(); //Pane is work as a panel
         scene = new Scene(root,screenWidth,screenHeight);
        stage.setScene(scene);
        stage.show();
        //Add a background color
       // root.setStyle("-fx-background-color: black");


        keyHandler= new KeyHandler(this);

        // Initialize player
        player = new Player(this, new KeyHandler(this));
        // Add player's ImageView to the root pane
        root.getChildren().add(canvas);


        lastNanoTime=System.nanoTime();

        GameAnimationTimer gameTimer= new GameAnimationTimer(this);
        gameTimer.start();
    }


    public void update() {
        player.update();

    }

    // Method to update player sprite based on direction

    public void render(){
        tileM.draw(gc);
        player.draw(gc);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

