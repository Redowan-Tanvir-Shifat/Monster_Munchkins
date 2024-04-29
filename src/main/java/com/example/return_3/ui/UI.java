package com.example.return_3.ui;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.main.UtilityTool;
import com.example.return_3.object.OBJ_Heart;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


import java.awt.*;
import java.util.ArrayList;

public class UI {
    Game game;
    //public UI_MainGame uiMainGame = new UI_MainGame(Game.gameInstance);
    public UI_MainGame uiMainGame;
    //Entity entity;
    GraphicsContext gc;
//    Font arial_40, arial_80B;
    //Image heartFull, starImage, energyImage, coinImage;
    //public boolean messageOn=false;

    //ArrayList<String> message = new ArrayList<>();
    //ArrayList<Integer> messageCounter = new ArrayList<>();

    //public boolean gameFinished=false; // if game is finished then the message will be shown
    //public String currentDialogue=""; //for setting the dialogue
    //public int commandNum = 0; // this is for showing our menu specific commands


//    public Entity heart;
//    int counter=0;

//    public Entity npc;
    //

    //CONSTRUCTOR START

    public UI(Game game){
        this.game = game;
        this.uiMainGame = new UI_MainGame(game);
//        arial_40 = new Font("Arial",40);
//        arial_80B = new Font("Arial",80);
//        UtilityTool uTool= new UtilityTool();
//        heart = new OBJ_Heart(game);
//        heartFull = heart.image1;
//        starImage = uTool.loadImage("/objects/star1.png",game.tileSize+10,game.tileSize+10);
//        energyImage = uTool.loadImage("/objects/energy.png",game.tileSize-7,game.tileSize-7);
//        coinImage = uTool.loadImage("/objects/coin3.png",game.tileSize-7,game.tileSize-7);

    }

    public void draw(GraphicsContext gc) {
        //we did this because we need to use this gc in other methods also
        this.gc = gc;

//        gc.setFont(arial_40);
//        gc.setFill(Color.WHITE);


            // <------PLAY STATE------>
            if (game.gameState == game.playState) {
                uiMainGame.drawPlayerLife();
                uiMainGame.drawPlayerLevel();
                uiMainGame.drawMessage();
                uiMainGame.drawEnergy();
                uiMainGame.drawCoin();

            }

            // <-------Hospital State------->
            if (game.gameState == game.hospitalState) {
                uiMainGame.hospitalScreen();
            }


            // <-------MenuBar State------>
            if (game.gameState == game.menuBarState) {
                uiMainGame.menuBarScreen();
            }


            // <-------DIALOGUE STATE------->
            if (game.gameState == game.dialogueState) {
                uiMainGame.drawDialogueScreen();
            }// <-------Message STATE------->
            if (game.gameState == game.messageState) {
                uiMainGame.drawDialogueScreen();
            }


            // <--------WizConversationState STATE-------->
            if (game.gameState == game.wizConversationState) {
                uiMainGame.drawWizConversationScreen();
            }


            // <-------Character State------->
            if (game.gameState == game.characterState) {
                uiMainGame.drawCharacterScreen();
                uiMainGame.drawInventory(game.player, true,18);
            }
            if (game.gameState == game.tradeState) {
                uiMainGame.drawTradeScreen();
            }

            // <----------Settings State----------->
            if (game.gameState == game.settingsState) {
                uiMainGame.drawSettingsScreen();
            }

    }
}