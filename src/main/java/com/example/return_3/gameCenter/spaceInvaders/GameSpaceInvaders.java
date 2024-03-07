package com.example.return_3.gameCenter.spaceInvaders;

import com.example.return_3.main.Game;
import com.example.return_3.main.GameAnimationTimer;
import com.example.return_3.main.KeyHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import java.util.Objects;

public class GameSpaceInvaders {

    Game game;
//    Image bcPic;
    //SCREEN SETTINGS
    public final int tileSize = 32; //SO every tile will be 32 pixels
    public final int maxScreenCol = 28; //here will be 20 column of titles  =>1024 pixel width
    public final int maxScreenRow = 18; //here will be 25 row of titles => 800 pixel height
    public final int screenWidth= tileSize*maxScreenCol; //1024 pixel width
    public final int screenHeight= tileSize*maxScreenRow; //800 pixel height


    // $$$$$$$$$ System $$$$$$$$$
    //instantiates new instances
    Canvas canvas = new Canvas(screenWidth, screenHeight);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    SpaceShip spaceShip ;
    Image bcPic;


    public GameSpaceInvaders( Game game){
        this.game = game;
    }



    public void startGameSpaceInvaders() throws Exception {
        game.gameStatus=game.gameSpaceInvadersStatus; //change the GameStatus  as our new gameStatus so that update and render method will work in that such crieteria
        game.gameState=game.playState; //change the GameState as play state
        Pane gameSpaceInvadersRoot = new Pane();
        Game.gameScene = new Scene(gameSpaceInvadersRoot, screenWidth, screenHeight); // Set the scene before creating KeyHandler
        //game.keyHandler= new KeyHandler(game);
        spaceShip=new SpaceShip(game,new KeyHandler(game));
        bcPic=  loadImage("/gameCenter/spaceInvaders/background_1.jpg", Game.gameSpaceInvaders.screenWidth, Game.gameSpaceInvaders.screenHeight);

        gameSpaceInvadersRoot.getChildren().add(canvas); //Now we added the root created in gameSpaceInvaders
        //game.lastNanoTime = System.nanoTime();
       // Game.gameTimer = new GameAnimationTimer(game);
        Game.gameTimer.start();
        Game.primaryStage.setScene(Game.gameScene);
    }



    public void update(){
        //System.out.println("update working");
        spaceShip.update();
    }

    public void draw( GraphicsContext gc){
        //System.out.println("GameSpaceInvaders Class: `drawMethod`  draw working");
        //Now drawing the background

        Image backgroundImage =null;
         backgroundImage =bcPic;
        gc.drawImage(backgroundImage,0,0);
        //new CheckedThread(bcPic,gc).start();


        //Drawing spaceShip
       // spaceShip.draw(this.gc);
    }

    public Image loadImage(String imagePath, int width, int height) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)), width, height, true, true);
    }

}
