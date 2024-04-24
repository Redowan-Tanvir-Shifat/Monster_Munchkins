package com.example.return_3.main;

import com.example.return_3.Controllers.*;
import com.example.return_3.Controllers.HistoryController;
import com.example.return_3.ai.PathFinder;
import com.example.return_3.db.MyJDBC;
import com.example.return_3.db.User;
import com.example.return_3.entity.Entity;
import com.example.return_3.entity.Player;
import com.example.return_3.gameCenter.snakey.Snakey;
import com.example.return_3.gameCenter.spaceInvaders.GameSpaceInvaders;
import com.example.return_3.gameCenter.ticTacToe.TicTacToe;
import com.example.return_3.interactiveTile.InteractiveTile;
import com.example.return_3.object.*;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Game extends Application {


    // GAME SETTINGS

    //STATIC VARIABLES
    public static Game gameInstance;
    public static Stage primaryStage;
    public static Scene menuScene;
    public static Scene loginScene;
    public static Scene gameScene;
    public static Scene mainGameScene;


    // $$$$$$$$$  GAME STATE $$$$$$$$$
    public int gameState;
    public final int menuBarState = 0;
    public final int playState = 1;
    public final int pauseState=2;
    public final int dialogueState = 3;
    public final int wizConversationState = 4;
    public final int characterState = 5;


//    public final int optionState=5;
    public final int mapState=6;
//    public final int transitionState=7;
    public final int tradeState=8;
    public final int hospitalState = 9;
    public final int titleState = 10;
    public final int messageState=11;

    // $$$$$$$$$  GAME STATUS $$$$$$$$$
    public int gameStatus;
    public final int gameFXMLStatus=0;
    public final int gameMainStatus=1;
    public final int gameSpaceInvadersStatus=2;
    public final int gameSnakeyStatus=3;
    public final int gameTicTacToeStatus=4;

    //TYPE

    public int objectType;
    public final int type_interactiveTIle=1;
    public final int type_object=2;
    public final int type_monster=2;





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
//    public Map map= new Map(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public EventHandler eventHandler= new EventHandler(this);
    public AssetSetter assetSetter= new AssetSetter(this);
    public UI ui= new UI(this);
    public static GameAnimationTimer gameTimer;

    public Entity obj[][]= new Entity[maxMap][20];
    public Entity npc[][]= new Entity[maxMap][100]; //set the number of 10 NPC Number
    public Entity[][] monster = new Entity[maxMap][100];
    public Entity[][] slimeMonster = new Entity[maxMap][20];
    public InteractiveTile iTile[][]= new InteractiveTile[maxMap][50];
    ArrayList<Entity> entityList = new ArrayList<>();
    // public  ArrayList<Entity> projectileList = new ArrayList<>();
    public Entity[][] projectile = new Entity[maxMap][20];
    public ArrayList<Entity> particleList = new ArrayList<>();

    //For Sound
    private Sound music = new Sound();
    private Sound soundEffect = new Sound();


    //For inventory
    HashMap<Integer, Entity> inventoryMap;
    public Entity axe = new OBJ_Axe(this);
    public Entity shieldBlue = new OBJ_Shield_Blue(this);
    public Entity sword = new OBJ_Sword_Normal(this);
    Entity shieldWood = new OBJ_Shield_Wood(this);
    public Entity key= new OBJ_Key(this);
    public Entity redPotion = new OBJ_Potion_Red(this);





    public Player player ;
    public User user;
    public static KeyHandler keyHandler;
    //Static instance of gameSpaceInvaders;
    public static GameSpaceInvaders gameSpaceInvaders;
    public TicTacToe ticTacToeGame;
    public static Snakey gameSnakey;

    public PathFinder pFinder= new PathFinder(this);




    int spriteCounter=0;
    int spriteNum=1;
    String direction="down1";


    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //              <----------------- IN this start method our Application is running ------------------------>
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    @Override
    public void start(Stage stage) throws Exception {

        primaryStage = stage; //as we have a static Stage variable . we initialize the value as game stage of start method
        gameInstance= this;  // we create a
        // Check login status
        int loginStatus = readLoginStatusFromFile();
        if (loginStatus < 0) {
            // Negative number indicates logout, so display login page
            loginPage();
        } else {
            // Positive number indicates login, load user data and start the game
            User user = loadUserData(loginStatus);
            if (user != null) {
                Game.gameInstance.user = user;
                showGameScene();
            } else {
                // Error loading user data, revert to login page
                loginPage();
            }
        }
        primaryStage.setTitle("Powered By return_3;"); //set the title of the stage
        primaryStage.initStyle(StageStyle.UNDECORATED); //create un decorated style
//        primaryStage.setScene(menuScene);
        primaryStage.show(); //by this show method we are now showing the stage
        //primaryStage.setOnCloseRequest(windowEvent -> exit(primaryStage));
    }

    private int readLoginStatusFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("loginStatus.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return -1; // Default to negative number if file reading fails
    }

    private User loadUserData(int userId) {
        // Load user data from database or file based on userId
        // Example code to load user data
        return MyJDBC.getUserData(userId);
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



    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //              <----------------- IN this `ShowGameScene` method our Application will direct you to the game screen ------------------------>
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    public static void showGameScene()  {
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

            // Clear canvas and redraw main game content
            //gameInstance.clearCanvas(gameInstance.gc); // Implement this method to clear the canvas
            gameInstance.gameStatus = gameInstance.gameMainStatus;
            gameInstance.gameState = gameInstance.playState;
            primaryStage.setScene(gameScene);
            gameTimer.start();
        }
    }
//    public void clearCanvas(GraphicsContext gc){
//        gc.clearRect(0,0,screenWidth, screenHeight);
//    }


    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //<----------------- IN this `startGame` method our Application will direct you to the main game Screen  ------------------------>
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    public void startGame() throws Exception {
        gameStatus=gameMainStatus;

        inventoryMap = new HashMap<>();
        inventoryMapAddItem(axe.itemCode, axe);
        inventoryMapAddItem(sword.itemCode, sword);
        inventoryMapAddItem(redPotion.itemCode, redPotion);
        inventoryMapAddItem(shieldBlue.itemCode, shieldBlue);
        inventoryMapAddItem(shieldWood.itemCode, shieldWood);
        inventoryMapAddItem(key.itemCode, key);
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
        playMusic(0);
        mainGameScene=gameScene;
        primaryStage.setScene(gameScene);
        //When the game is starting then gameState will be PlayState
        gameState=playState;

    }


    public void inventoryMapAddItem(int itemCode, Entity item) {
        inventoryMap.put(itemCode, item);
    }

    public Entity inventoryMapgetItem(int itemCode) {
        return inventoryMap.get(itemCode);
    }

    public void inventoryMapremoveItem(int itemCode) {
        inventoryMap.remove(itemCode);
    }



    //this restart game method is not needed
    public void reStartGame() throws Exception {
        //gameInstance = this;
        gameStatus=gameMainStatus;
        gameState=playState;
        Pane mainGameroot = new Pane();
        mainGameroot.setOnMouseEntered(event -> {
            mainGameroot.setCursor(Cursor.HAND);
        });
        gameScene = new Scene(mainGameroot, screenWidth, screenHeight); // Set the scene before creating KeyHandler
        //gameScene=scene;
        keyHandler= new KeyHandler(this);
        player = new Player(this); // KeyHandler depends on game.scene
        mainGameroot.getChildren().add(mainGameCanvas);
//        assetSetter.setObject();
//        assetSetter.setNPC();
//        assetSetter.setMonster();
//        assetSetter.setInteractiveTile();
//        lastNanoTime = System.nanoTime();
//        gameTimer = new GameAnimationTimer(this);
        gameTimer.start();
        primaryStage.setScene(gameScene);
        //When the game is starting then gameState will be PlayState
//        gameState=playState;

//        primaryStage.setX(300);
//        primaryStage.setY(120);


    }


    public static void exitGame() {
        Platform.exit();
    }
    public static void exitWithConfirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");

        alert.setHeaderText("You are about to exit!");
        alert.setContentText("Are you sure you want to exit?");

        // Add "Yes" and "No" buttons to the confirmation dialog
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // Custom CSS for the dialog
        alert.getDialogPane().getStylesheets().add(
                Game.class.getResource("/com/example/return_3/alert.css").toExternalForm());
        alert.initStyle(StageStyle.TRANSPARENT); // Transparent style for a sleek look

        // Handle button actions
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeYes) {
                Platform.exit(); // Exit the application
            }
        });
    }

    public void logout(){
        try {
            // Write negative number to indicate logout
            BufferedWriter writer = new BufferedWriter(new FileWriter("loginStatus.txt"));
            writer.write("-1");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Optionally, you can reset user data
        Game.gameInstance.user = null;
        // Redirect to login page
        try {
            loginPage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //To reset the player properties
        obj= new Entity[maxMap][20];
        npc= new Entity[maxMap][50]; //set the number of 10 NPC Number
        monster = new Entity[maxMap][100];
        iTile= new InteractiveTile[maxMap][50];
        entityList = new ArrayList<>();
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
                for (int i = 0; i < monster[currentMap].length; i++) {
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
                // Slime Monster
                for (int i = 0; i < slimeMonster[currentMap].length; i++) {
                    if (slimeMonster[currentMap][i] != null) {
                        if (slimeMonster[currentMap][i].alive == true) {
                            slimeMonster[currentMap][i].update();
                        }
                        if (slimeMonster[currentMap][i].alive == false) {
                            slimeMonster[currentMap][i].checkDrop();
                            slimeMonster[currentMap][i] = null;
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
            }else if(gameStatus==gameSnakeyStatus){
                gameSnakey.update();
            }else if(gameStatus==gameTicTacToeStatus){
//                ticTacToeGame.update();
            }


        }





    }

    // Method to update player sprite based on direction

    public void render(){
       // ui.draw(gc);
        if(gameStatus == gameMainStatus) {

                tileM.draw(gc);
//                if(gameState== mapState){
//                    map.drawFullMapScreen(gc);
//                }
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
                //ADD Slime MONSTER TO THE LIST
                for (int i = 0; i < slimeMonster[currentMap].length; i++) {
                    if (slimeMonster[currentMap][i] != null) {
                        entityList.add(slimeMonster[currentMap][i]);
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
            gameSpaceInvaders.draw();
//            ui.draw(gc);
            gameSpaceInvaders.uiGameSpaceInvaders.draw();
        } else if (gameStatus == gameSnakeyStatus) {
            gameSnakey.draw(gc);
            //ui.draw(gc);
        }else if (gameStatus == gameTicTacToeStatus){
//            ticTacToeGame.draw(gc);
        }

    }

    public void hoverButton(ArrayList <Button> arrayList){
        for(Button button : arrayList){
            button.setOnMouseEntered(e ->{

                button.setScaleX(1.05);
                button.setScaleY(1.05);
            });
            button.setOnMouseExited(e ->{
                button.setScaleX(1);
                button.setScaleY(1);
            });
            button.setOnMousePressed(e -> {
                button.setScaleX(0.95);
                button.setScaleY(0.95);
            });
            button.setOnMouseReleased(e -> {
                button.setScaleX(1);
                button.setScaleY(1);
            });
        }
    }

    // <-----------------FXML SCENE--------------------->
    public void loginPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/return_3/login.fxml"));
        loginScene = new Scene(fxmlLoader.load());

        ArrayList <Button> buttons= new ArrayList<>();
        LoginController loginController = fxmlLoader.getController();
        buttons.add(loginController.loginBtn);
        buttons.add(loginController.signUpBtn);
        buttons.add(loginController.closeBtn);

        hoverButton(buttons);

        primaryStage.setScene(loginScene);
    }
    public void signUpPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/signUp.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);

        ArrayList <Button> buttons = new ArrayList<>();
        SignUpController signUpController = loader.getController();
        buttons.add(signUpController.signUpBtn);
        buttons.add(signUpController.closeBtn);
        buttons.add(signUpController.loginBtn);

        hoverButton(buttons);

        primaryStage.setScene(scene);
    }

    public void showGameCenter() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/gameCenter.fxml"));
        Parent root = loader.load();
        Scene scene= new Scene(root);
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("school.css")).toExternalForm());
        gameTimer.stop();
        GameController gameController = loader.getController();
        gameController.setCoin(player.coin); // Pass the time to the controller

        ArrayList <Button> buttons= new ArrayList<>();
        buttons.add(gameController.backButton);
        buttons.add(gameController.spaceInvaders);
        buttons.add(gameController.snakeyBtn);
        buttons.add(gameController.puzzle);
        buttons.add(gameController.tictactoe);

        hoverButton(buttons);

        Game.primaryStage.setScene(scene);
    }

    public void showSchoolScene() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/school.fxml"));
        Parent root = loader.load();
        Scene scene= new Scene(root);
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("school.css")).toExternalForm());
        gameTimer.stop();

        ArrayList <Button> buttons= new ArrayList<>();
        SchoolController schoolController = loader.getController();
        buttons.add(schoolController.mathButton);
        buttons.add(schoolController.englishButton);
        buttons.add(schoolController.histryButton);
        buttons.add(schoolController.geographybutton);
        buttons.add(schoolController.backButton);

        hoverButton(buttons);

        Game.primaryStage.setScene(scene);
    }

    public void showMathScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/math.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);

        ArrayList <Button> buttons= new ArrayList<>();
        MathController mathController = loader.getController();
        buttons.add(mathController.triginometryBtn);
        buttons.add(mathController.algebraBtn);
        buttons.add(mathController.conversionBtn);
        buttons.add(mathController.geometryBtn);
        buttons.add(mathController.backButton);

        hoverButton(buttons);

        primaryStage.setScene(scene);
    }
    public void showHistoryScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/history.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);

        ArrayList <Button> buttons= new ArrayList<>();
        HistoryController historyController = loader.getController();
        buttons.add(historyController.backButton);

        hoverButton(buttons);

        primaryStage.setScene(scene);
    }
    public void showEnglishScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/english.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);

        ArrayList <Button> buttons= new ArrayList<>();
        EnglishController englishController = loader.getController();
        buttons.add(englishController.backButton);

        hoverButton(buttons);

        primaryStage.setScene(scene);
    }
    public void showGeographyScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/geography.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);

        ArrayList <Button> buttons= new ArrayList<>();
        GeographyController geographyController = loader.getController();
        buttons.add(geographyController.backButton);

        hoverButton(buttons);

        primaryStage.setScene(scene);
    }

    public void showAlgebraPage1() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/algebraP_1.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);

//        ArrayList <Button> buttons= new ArrayList<>();
//        GeographyController geographyController = loader.getController();
//        buttons.add(geographyController.backButton);
//
//        hoverButton(buttons);

        primaryStage.setScene(scene);
    }

    public void showAlgebraPage2() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/algebraP_2.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);

//        ArrayList <Button> buttons= new ArrayList<>();
//        GeographyController geographyController = loader.getController();
//        buttons.add(geographyController.backButton);
//
//        hoverButton(buttons);

        primaryStage.setScene(scene);
    }

    public void showAlgebraPage3() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/algebraP_3.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);

//        ArrayList <Button> buttons= new ArrayList<>();
//        GeographyController geographyController = loader.getController();
//        buttons.add(geographyController.backButton);
//
//        hoverButton(buttons);

        primaryStage.setScene(scene);
    }

    public void showAlgebraPage4() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/algebraP_4.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);

//        ArrayList <Button> buttons= new ArrayList<>();
//        GeographyController geographyController = loader.getController();
//        buttons.add(geographyController.backButton);
//
//        hoverButton(buttons);

        primaryStage.setScene(scene);
    }

    public void showAlgebraPage5() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/algebraP_5.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);

//        ArrayList <Button> buttons= new ArrayList<>();
//        GeographyController geographyController = loader.getController();
//        buttons.add(geographyController.backButton);
//
//        hoverButton(buttons);

        primaryStage.setScene(scene);
    }

    public void showAlgebraPage6() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/algebraP_6.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);

//        ArrayList <Button> buttons= new ArrayList<>();
//        GeographyController geographyController = loader.getController();
//        buttons.add(geographyController.backButton);
//
//        hoverButton(buttons);

        primaryStage.setScene(scene);
    }

    public void showAlgebraPage7() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/algebraP_7.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);

//        ArrayList <Button> buttons= new ArrayList<>();
//        GeographyController geographyController = loader.getController();
//        buttons.add(geographyController.backButton);
//
//        hoverButton(buttons);

        primaryStage.setScene(scene);
    }

    public void showAlgebraTestPage1() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/algebraTestP1.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);

//        ArrayList <Button> buttons= new ArrayList<>();
//        GeographyController geographyController = loader.getController();
//        buttons.add(geographyController.backButton); 
//
//        hoverButton(buttons);

        primaryStage.setScene(scene);
    }

    public void showAlgebraTestPage2() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/algebraTestP2.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);

//        ArrayList <Button> buttons= new ArrayList<>();
//        GeographyController geographyController = loader.getController();
//        buttons.add(geographyController.backButton);
//
//        hoverButton(buttons);

        primaryStage.setScene(scene);
    }

    public void showAlgebraTestPage3() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/algebraTestP3.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);

//        ArrayList <Button> buttons= new ArrayList<>();
//        GeographyController geographyController = loader.getController();
//        buttons.add(geographyController.backButton);
//
//        hoverButton(buttons);

        primaryStage.setScene(scene);
    }

    public void showAlgebraTestPage4() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/algebraTestP4.fxml"));
        //loader.setController(new MenuController());
        Parent root = loader.load();
        Scene scene = new Scene(root);

//        ArrayList <Button> buttons= new ArrayList<>();
//        GeographyController geographyController = loader.getController();
//        buttons.add(geographyController.backButton);
//
//        hoverButton(buttons);

        primaryStage.setScene(scene);
    }


//CODE FOR SOUND SYSTEM
public void playMusic(int i) {
    music.setFile(i);
    music.play();
    music.loop();
}

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }



    public static void main(String[] args) {
        launch(args);
    }
}

