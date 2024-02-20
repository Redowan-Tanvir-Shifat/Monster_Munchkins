package com.example.return_3.main;


import com.example.return_3.entity.Entity;
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
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Game extends Application {
    // GAME SETTINGS
    private static Game gameInstance;

    public static Stage primaryStage;
    private static Scene menuScene;
    private static Scene gameScene;


    // $$$$$$$$$  GAME STATE $$$$$$$$$
    public int gameState;
    public final int titleState=0;
    public final int playState=1;
    public final int pauseState=2;
    public final int dialogueState=3;
    public final int characterState=4;
    public final int optionState=5;
    public final int gameOverState=6;
    public final int transitionState=7;
    public final int tradeState=8;



    //SCREEN SETTINGS
    public final int tileSize = 32; //SO every tile will be 32 pixels
    public final int maxScreenCol = 30; //here will be 20 column of titles  =>1024 pixel width
    public final int maxScreenRow = 18; //here will be 25 row of titles => 800 pixel height
    public final int screenWidth= tileSize*maxScreenCol; //1024 pixel width
    public final int screenHeight= tileSize*maxScreenRow; //800 pixel height


    // $$$$$$$$$  World Setting $$$$$$$$$
    public  int maxWorldRow;
    public  int maxWorldCol;
    public final int maxMap=10;
    public int currentMap = 0;



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
    public EventHandler eventHandler= new EventHandler(this);
    public AssetSetter assetSetter= new AssetSetter(this);
    public static GameAnimationTimer gameTimer;
    public Entity npc[][]= new Entity[maxMap][10]; //set the number of 10 NPC Number

    ArrayList<Entity> entityList = new ArrayList<>();

    KeyHandler keyHandler;
    public Player player ;
    public Scene scene;

    int spriteCounter=0;
    int spriteNum=1;

    String direction="down1";



    public void loginPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/return_3/login.fxml"));
        menuScene = new Scene(fxmlLoader.load());
        primaryStage.setScene(menuScene);
    }








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
        loginPage();
//        loadMenuScene();
        primaryStage.setTitle("Powered By return_3;");
        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.setScene(menuScene);
        primaryStage.show();
        //primaryStage.setOnCloseRequest(windowEvent -> exit(primaryStage));
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


    public void loadMenuScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/menu.fxml"));
        //loader.setController(new MenuController());
        Parent menuRoot = loader.load();
        menuScene = new Scene(menuRoot, screenWidth, screenHeight);

    }

    public static void showGameScene() {
        if (gameScene == null) {
//            System.out.println("GameScene is null");
            try {
                Game game = new Game();
                game.startGame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            primaryStage.setScene(gameScene);
            //gameTimer.start();
        }
    }


    public static void showReturnGameScene() {
        if (gameScene == null) {
            System.out.println("GameScene is null");
            return;
        }

        // Update the scene to the game scene
        primaryStage.setScene(gameScene);

        // Notify the existing Game instance to resume
        if (gameInstance != null) {
            gameInstance.resumeGame();
        }
    }




public void resumeGame(){
    primaryStage.setScene(gameScene);
        this.gameTimer.start();
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


    public void startGame() throws Exception {
        gameInstance = this;
        Pane root = new Pane();
        scene = new Scene(root, screenWidth, screenHeight); // Set the scene before creating KeyHandler
        gameScene=scene;
        player = new Player(this, new KeyHandler(this)); // KeyHandler depends on game.scene
        root.getChildren().add(canvas);
        assetSetter.setNPC();
        lastNanoTime = System.nanoTime();
//        assetSetter.setNPC();
        gameTimer = new GameAnimationTimer(this);
        gameTimer.start();

        primaryStage.setX(300);
        primaryStage.setY(120);

        primaryStage.setScene(scene);

    }

    public void showSchoolScene() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/school.fxml"));
        Parent root = loader.load();
        Scene scene= new Scene(root);
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("school.css")).toExternalForm());
        gameTimer.stop();
        Game.primaryStage.setScene(scene);
    }
    public void showStoreScene() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/store.fxml"));
        Parent root = loader.load();
        Scene scene= new Scene(root);
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("school.css")).toExternalForm());
        gameTimer.stop();
        Game.primaryStage.setScene(scene);
    }




    static void exitGame() {
        Platform.exit();
    }

     public void returnGame() throws Exception {
        Pane root = new Pane();
        scene = new Scene(root, screenWidth, screenHeight); // Set the scene before creating KeyHandler
//        player = new Player(this, new KeyHandler(this)); // KeyHandler depends on game.scene
        root.getChildren().add(canvas);
        lastNanoTime = System.nanoTime();

        GameAnimationTimer gameTimer = new GameAnimationTimer(this);
        gameTimer.start();

        primaryStage.setScene(scene);
    }




    public void update() {
        //Player UPDATE
        player.update();

        //NPC UPDATE
        for(int i=0; i<npc[currentMap].length; i++){
            if(npc[currentMap][i] !=null){
                npc[currentMap][i].update();
            }
        }




    }

    // Method to update player sprite based on direction

    public void render(){
        tileM.draw(gc);
        //add player
        entityList.add(player);

        //add npc entity TO the list.
        for(int i=0; i<npc[currentMap].length; i++){
            if(npc[currentMap][i]!=null){
                entityList.add(npc[currentMap][i]);
            }
        }


        //Sorting before draw to draw in perfect layer thinking z index so that it draws the accurate position not over drawing
        Collections.sort(entityList, new Comparator<Entity>() {
            @Override
            public int compare(Entity e1, Entity e2) {
                int result=Integer.compare(e1.worldY, e2.worldY);
                return result;
            }
        });
        //Finally we will draw the entity
        for(int i=0;i<entityList.size();i++){
            entityList.get(i).draw(gc);
        }

        //Empty the entity list because when this will render again the entity will added to the list again
        entityList.clear();

    }




    public static void main(String[] args) {
        launch(args);
    }
}

