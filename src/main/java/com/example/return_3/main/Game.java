package com.example.return_3.main;


import com.example.return_3.entity.Player;
import com.example.return_3.tile.TileManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Application {
    // GAME SETTINGS

    private static Stage primaryStage;
    private static Scene menuScene;
    private static Scene gameScene;


    //SCREEN SETTINGS
    public final int tileSize =48; //SO everytiles will be 32 pixels
    public final int maxScreenCol=20; //here will be 20 column of titles  =>1024 pixel width
    public final int maxScreenRow=14; //here will be 25 row of titles => 800 pixel height
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
    public CollisionChecker cChecker = new CollisionChecker(this);
    KeyHandler keyHandler;
    public Player player ;
    public Scene scene;

    int spriteCounter=0;
    int spriteNum=1;

    String direction="down1";











    @Override
    public void start(Stage stage) throws Exception {
//        Pane root = new Pane();
//        scene = new Scene(root,screenWidth,screenHeight);
//        stage.setScene(scene);
//        stage.show();
//        //Add a background color
//       // root.setStyle("-fx-background-color: black");
//
//
//        keyHandler= new KeyHandler(this);
//
//        // Initialize player
//        player = new Player(this, new KeyHandler(this));
//        // Add player's ImageView to the root pane
//        root.getChildren().add(canvas);
//
//
//        lastNanoTime=System.nanoTime();
//
//        GameAnimationTimer gameTimer= new GameAnimationTimer(this);
//        gameTimer.start();

        primaryStage = stage;
        loadMenuScene();
        primaryStage.setTitle("Game Menu");
        primaryStage.setScene(menuScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(windowEvent -> exit(primaryStage));
    }

    void exit(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit");
        alert.setContentText("Do you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Game.exitGame();
        }

    }


    private void loadMenuScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/menu.fxml"));
        //loader.setController(new MenuController());
        Parent menuRoot = loader.load();
        menuScene = new Scene(menuRoot, screenWidth, screenHeight);
    }

    public static void showGameScene() {
        if (gameScene == null) {
            System.out.println("GameScene is null");
            try {
                Game game = new Game();
                game.startGame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            primaryStage.setScene(gameScene);
        }
    }




//    private void startGame() throws Exception {
//        Pane root = new Pane();
//        gameScene = new Scene(root, screenWidth, screenHeight);
//        player = new Player(this, new KeyHandler(this));
//        root.getChildren().add(canvas);
//        lastNanoTime = System.nanoTime();
//        GameAnimationTimer gameTimer = new GameAnimationTimer(this);
//        gameTimer.start();
//        primaryStage.setScene(gameScene);
//    }


//    private void startGame() throws Exception {
//        Pane root = new Pane();
//        gameScene = new Scene(root, screenWidth, screenHeight);
//        player = new Player(this, new KeyHandler(this)); // KeyHandler depends on game.scene
//        root.getChildren().add(canvas);
//        lastNanoTime = System.nanoTime();
//
//        // Set the scene before creating KeyHandler
//        scene = gameScene;
//
//        GameAnimationTimer gameTimer = new GameAnimationTimer(this);
//        gameTimer.start();
//
//        primaryStage.setScene(gameScene);
//    }


    private void startGame() throws Exception {
        Pane root = new Pane();
        scene = new Scene(root, screenWidth, screenHeight); // Set the scene before creating KeyHandler
        player = new Player(this, new KeyHandler(this)); // KeyHandler depends on game.scene
        root.getChildren().add(canvas);
        lastNanoTime = System.nanoTime();

        GameAnimationTimer gameTimer = new GameAnimationTimer(this);
        gameTimer.start();

        primaryStage.setScene(scene);
    }

    static void exitGame() {
        Platform.exit();
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

