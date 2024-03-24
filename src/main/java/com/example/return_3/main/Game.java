package com.example.return_3.main;


import com.example.return_3.ai.PathFinder;
import com.example.return_3.db.User;
import com.example.return_3.entity.Entity;
import com.example.return_3.entity.Player;
import com.example.return_3.gameCenter.spaceInvaders.GameSpaceInvaders;
import com.example.return_3.interactiveTile.InteractiveTile;
import com.example.return_3.tile.TileManager;
import com.example.return_3.ui.UI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
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

    //STATIC VARIABLES
    public static Game gameInstance;
    public static Stage primaryStage;
    public static Scene menuScene;
    public static Scene loginScene;
    public static Scene gameScene;


    // $$$$$$$$$  GAME STATE $$$$$$$$$
    public int gameState;
    public final int menuBarState = 0;
    public final int playState = 1;
    public final int pauseState=2;
    public final int dialogueState = 3;
    public final int wizConversationState = 4;
    public final int characterState = 5;

//    public final int optionState=5;
//    public final int gameOverState=6;
//    public final int transitionState=7;
    public final int tradeState=8;
    public final int hospitalState = 9;
public final int messageState=10;

    // $$$$$$$$$  GAME STATUS $$$$$$$$$
    public int gameStatus;
    public int gameMainStatus=0;
    public int gameSpaceInvadersStatus=1;



    //SCREEN SETTINGS
    public final int tileSize = 32;   //SO every tile will be 32 pixels
    public final int maxScreenCol = 30;   //here will be 30 column of titles  => 960 pixel width
    public final int maxScreenRow = 18;   //here will be 18 row of titles => 576 pixel height
    public final int screenWidth= tileSize * maxScreenCol; //1024 pixel width
    public final int screenHeight= tileSize*maxScreenRow; //800 pixel height


    // $$$$$$$$$  WORLD MAP SETTINGS $$$$$$$$$
    public  int maxWorldRow;
    public  int maxWorldCol;
    public final int maxMap=10;
    public int currentMap = 0;

    // $$$$$$$$$  GAME LOOP SETTINGS $$$$$$$$$

    public static final double targetFrameRate=60.0;
    public static final double targetFrameTime=1/targetFrameRate; //0.01666666666 it means per second e frame 60. so 0.01666666666 second e ekbar frame change
    public double accumulatedTime=0.0; // accumulated time
    public long lastNanoTime;

    public boolean isDialogueToGameState = false;

    // $$$$$$$$$ INSTANTIATE $$$$$$$$$
    //instantiates new instances
    public Canvas mainGameCanvas = new Canvas(screenWidth, screenHeight);
    public GraphicsContext gc = mainGameCanvas.getGraphicsContext2D();
    public TileManager tileM= new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public EventHandler eventHandler= new EventHandler(this);
    public AssetSetter assetSetter= new AssetSetter(this);
    public UI ui= new UI(this);
    public static GameAnimationTimer gameTimer;

    public Entity obj[][]= new Entity[maxMap][20];
    public Entity npc[][]= new Entity[maxMap][20]; //set the number of 10 NPC Number
    public Entity[][] monster = new Entity[maxMap][20];

    public InteractiveTile iTile[][]= new InteractiveTile[maxMap][50];
    ArrayList<Entity> entityList = new ArrayList<>();
    // public  ArrayList<Entity> projectileList = new ArrayList<>();
    public Entity[][] projectile = new Entity[maxMap][20];
    public ArrayList<Entity> particleList = new ArrayList<>();



    public Player player ;
    public User user;
    public KeyHandler keyHandler;
    //Static instance of gameSpaceInvaders;
    public static GameSpaceInvaders gameSpaceInvaders;

    public PathFinder pFinder= new PathFinder(this);




    int spriteCounter=0;
    int spriteNum=1;
    String direction="down1";



    //-------------------------- --- IN this start method our Application is running ----------------------------------------------------------------
    @Override
    public void start(Stage stage) throws Exception {

        primaryStage = stage; //as we have a static Stage variable . we initialize the value as game stage of start method
        gameInstance= this; // we create a
        //showGameScene(); // now we are redirecting the showGameScene method
        loginPage();
//        loadMenuScene();
        primaryStage.setTitle("Powered By return_3;"); //set the title of the stage
        primaryStage.initStyle(StageStyle.UNDECORATED); //create un decorated style
//        primaryStage.setScene(menuScene);
        primaryStage.show(); //by this show method we are now showing the stage
        //primaryStage.setOnCloseRequest(windowEvent -> exit(primaryStage));
    }

    //----------------------------- IN this Exit method our Application will close ----------------------------------------------------------------

    void exit(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit");
        alert.setContentText("Do you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Game.exitGame();
        }

    }

    //-------------------------- --- IN this `loadMenuScene` method our Application will show menu setting  ----------------------------------------------------------------

    public void loadMenuScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/menu.fxml"));
        //loader.setController(new MenuController());
        Parent menuRoot = loader.load();
        menuScene = new Scene(menuRoot, screenWidth, screenHeight);
    }

    //-------------------------- --- IN this `loginPage` method our Application will direct you to the login system  ----------------------------------------------------------------

    public void loginPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/return_3/login.fxml"));
        loginScene = new Scene(fxmlLoader.load());
        primaryStage.setScene(loginScene);
    }


    //----------------------------- IN this `showGameScene` method our Application will direct you to the main game  ----------------------------------------------------------------
    public static void showGameScene() {
        if (gameScene == null) {   // if there is no Scene initialize then
//            System.out.println("GameScene is null");
            try {
//                Game game = new Game();
                gameInstance.startGame();
//                gameSpaceInvaders =new GameSpaceInvaders(gameInstance); // initialize gameSpaceInvaders instance
//                gameSpaceInvaders.startGameSpaceInvaders();  //called this start method for start the game

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            primaryStage.setScene(gameScene);
            gameTimer.start();
        }
    }



    //----------------------------- IN this `startGame` method our Application will direct you to the main game Screen  ----------------------------------------------------------------
    public void startGame() throws Exception {
        //gameInstance = this;
        gameStatus=gameMainStatus;
        Pane mainGameroot = new Pane();
        mainGameroot.setOnMouseEntered(event -> {
            mainGameroot.setCursor(Cursor.HAND);
        });
        gameScene = new Scene(mainGameroot, screenWidth, screenHeight); // Set the scene before creating KeyHandler
        //gameScene=scene;
         keyHandler= new KeyHandler(this);
        player = new Player(this); // KeyHandler depends on game.scene
        mainGameroot.getChildren().add(mainGameCanvas);
        assetSetter.setObject();
        assetSetter.setNPC();
        assetSetter.setMonster();
        assetSetter.setInteractiveTile();
        lastNanoTime = System.nanoTime();
        gameTimer = new GameAnimationTimer(this);
        gameTimer.start();
        primaryStage.setScene(gameScene);
        //When the game is starting then gameState will be PlayState
        gameState=playState;

//        primaryStage.setX(300);
//        primaryStage.setY(120);


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

    public void showGameCenter() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/gameCenter.fxml"));
        Parent root = loader.load();
        Scene scene= new Scene(root);
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("school.css")).toExternalForm());
        gameTimer.stop();
        Game.primaryStage.setScene(scene);
    }




    public static void exitGame() {
        Platform.exit();
    }

    public void update() {

        //Player UPDATE
        if(gameState==playState){
            if(gameStatus == gameMainStatus){
                player.update();

                //NPC UPDATE
                for(int i=0; i<npc[currentMap].length; i++){
                    if(npc[currentMap][i] !=null){
                        npc[currentMap][i].update();
                    }
                }

                //Monster
                for (int i = 0; i < monster.length; i++) {
                    if (monster[currentMap][i] != null) {
                        if (monster[currentMap][i].alive == true) {
                            monster[currentMap][i].update();
                        }
                        if (monster[currentMap][i].alive == false) {
                            monster[currentMap][i].checkDrop();
                            monster[currentMap][i] = null;
                        }
                    }
                }

                //PROJECTILE
                for (int i = 0; i < projectile[currentMap].length; i++) {
                    if (projectile[currentMap][i] != null) {
                        if (projectile[currentMap][i].alive == true) {
                            projectile[currentMap][i].update();
                        }
                        if (projectile[currentMap][i].alive == false) {
                            projectile[currentMap][i] = null;
                        }
                    }
                }
                //PARTICLE
                for (int i = 0; i < particleList.size(); i++) {
                    if (particleList.get(i)!= null) {
                        if (particleList.get(i).alive == true) {
                            particleList.get(i).update();
                        }
                        if (particleList.get(i).alive == false) {
                            particleList.remove(i) ;
                        }
                    }
                }
                //Interactive TILEs  UPDATE
                for(int i = 0;i<iTile[currentMap].length;i++) {
                    if(iTile[currentMap][i] != null){
                        iTile[currentMap][i].update();
                    }
                }

                player.keyHandler.setEnterPressed(false);
                player.keyHandler.setSpacePressed(false);
            } else if (gameStatus==gameSpaceInvadersStatus) {
                gameSpaceInvaders.update();
            }


        }





    }

    // Method to update player sprite based on direction

    public void render(){
       // ui.draw(gc);
        if(gameStatus == gameMainStatus) {

                tileM.draw(gc);
                //draw Interactive tile
                for(int i=0;i< iTile[currentMap].length;i++){
                    if(iTile[currentMap][i]!=null){
                        iTile[currentMap][i].draw(gc);
                    }
                }

                //add player
                entityList.add(player);

                //add npc entity TO the list.
                for (int i = 0; i < npc[currentMap].length; i++) {
                    if (npc[currentMap][i] != null) {
                        entityList.add(npc[currentMap][i]);
                    }
                }
                //ADD MONSTER TO THE LIST
                for (int i = 0; i < monster[currentMap].length; i++) {
                    if (monster[currentMap][i] != null) {
                        entityList.add(monster[currentMap][i]);
                    }
                }
                //add object to list
                for(int i=0; i<obj[currentMap].length; i++){
                    if(obj[currentMap][i]!=null){
                        entityList.add(obj[currentMap][i]);
                    }
                }
                //ADD projectile TO THE LIST
                for (int i = 0; i < projectile[currentMap].length; i++) {
                    if (projectile[currentMap][i] != null) {
                        entityList.add(projectile[currentMap][i]);
                    }
                }

                //ADD PARTICLE TO THE LIST
                for (int i = 0; i < particleList.size(); i++) {
                    if (particleList.get(i) != null) {
                        entityList.add(particleList.get(i));
                    }
                }


                //Sorting before draw to draw in perfect layer thinking z index so that it draws the accurate position not over drawing
                Collections.sort(entityList, new Comparator<Entity>() {
                    @Override
                    public int compare(Entity e1, Entity e2) {
                        int result = Integer.compare(e1.worldY, e2.worldY);
                        return result;
                    }
                });
                //Finally we will draw the entity
                for (int i = 0; i < entityList.size(); i++) {
                    entityList.get(i).draw(gc);
                }

                //Empty the entity list because when this will render again the entity will added to the list again
                entityList.clear();
                ui.draw(gc);
                gameInstance.keyHandler.setEnterPressed(false);


        } else if (gameStatus == gameSpaceInvadersStatus) {
            gameSpaceInvaders.draw(gc);
            ui.draw(gc);
        }
    }




    public static void main(String[] args) {
        launch(args);
    }
}

