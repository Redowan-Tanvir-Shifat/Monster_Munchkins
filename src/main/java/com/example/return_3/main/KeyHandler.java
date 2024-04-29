package com.example.return_3.main;


import com.example.return_3.db.MyJDBC;
import com.example.return_3.entity.Entity;
import com.example.return_3.entity.NPC_Trade;
import com.example.return_3.shop.StuffShop;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.beans.XMLEncoder;

public class KeyHandler {
    Game game;
    Stage stage;
    private boolean moveUp, moveDown, moveLeft, moveRight,enterPressed, spacePressed, vKeyPressed, escapePressed,FKeyPressed;

    public KeyHandler(Game game) {
        this.game = game;
        Game.gameScene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        Game.gameScene.setOnKeyReleased(event -> handleKeyRelease(event.getCode()));
    }

    private void handleKeyPress(KeyCode code) {

//        if (code == KeyCode.Q) {
//            try {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Exit");
//                alert.setHeaderText("You are about to exit");
//                alert.setContentText("Do you want to exit?");
//                if (alert.showAndWait().get() == ButtonType.OK) {
//                    Game.exitGame();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


        //This is for PlayState
        if(game.gameState == game.playState){
            playState(code);
            if(code==KeyCode.L){
                new StuffShop(game).use();
            }
            if(code==KeyCode.M){
                game.gameState=game.mapState;
            }if(code==KeyCode.K){
                System.out.println("Pressing K in KeyHandler");

            }
        }
        
        //DIALOGUE state
        else if(game.gameState == game.dialogueState) {
            dialogueState(code);
        }//MESSAGE state
        else if(game.gameState == game.messageState) {
            messageState(code);
        }
        //WizConversation state
        else if(game.gameState == game.wizConversationState){
            wizConversationState(code);
        }
        //characterState state
        else if (game.gameState == game.characterState) {
//            game.ui.uiMainGame.playerSlotCol=0;
//            game.ui.uiMainGame.playerSlotRow=0;
            characterState(code);
        }
        //TRADE state
        else if (game.gameState == game.tradeState) {
            tradeState(code);
        }
        //MENU BAR state
        else if (game.gameState == game.menuBarState) {
            if (game.gameStatus == game.gameMainStatus) {
                menuBarState(code);
            }
//            if (game.gameStatus == game.gameSpaceInvadersStatus) {
//                menuBarState(code);
//            }
        }
        else if (game.gameState == game.hospitalState) {
            hospitalState(code);
        }else if (game.gameState == game.mapState) {
            mapState(code);
        }


        else if (game.gameState == game.settingsState) {
            settingsState(code);
        }
    }

    private void settingsState(KeyCode code) {
        if(code== KeyCode.W || code== KeyCode.UP){
            game.ui.uiMainGame.commandNum--;
            game.playSoundEffect(9);
            if(game.ui.uiMainGame.commandNum < 0){
                game.ui.uiMainGame.commandNum = 2;
            }
        }
        if(code== KeyCode.S || code== KeyCode.DOWN){
            game.ui.uiMainGame.commandNum++;
            game.playSoundEffect(9);
            if(game.ui.uiMainGame.commandNum > 2){
                game.ui.uiMainGame.commandNum=0;
            }
        }
        if (code == KeyCode.ENTER) {
            if (game.ui.uiMainGame.commandNum == 0) {

            }
            if (game.ui.uiMainGame.commandNum == 1) {

            }
            if (game.ui.uiMainGame.commandNum == 2) {
                game.gameState = game.menuBarState;
            }
        }
        if (code == KeyCode.A || code == KeyCode.LEFT) {
            if (game.ui.uiMainGame.commandNum == 0 && game.music.volumeScale > 0) {
                game.music.volumeScale--;
                game.music.checkVolume();
                game.playSoundEffect(9);
            }
            if (game.ui.uiMainGame.commandNum == 1 && game.soundEffect.volumeScale > 0) {
                game.soundEffect.volumeScale--;
                game.playSoundEffect(9);
            }
        }
        if (code == KeyCode.D || code == KeyCode.RIGHT) {
            if (game.ui.uiMainGame.commandNum == 0 && game.music.volumeScale < 5) {
                game.music.volumeScale++;
                game.music.checkVolume();
                game.playSoundEffect(9);
            }
            if (game.ui.uiMainGame.commandNum == 1 && game.soundEffect.volumeScale < 5) {
                game.soundEffect.volumeScale++;
                game.playSoundEffect(9);
            }
        }
    }

    private void hospitalState(KeyCode code) {
        if(code== KeyCode.W || code== KeyCode.UP){
            game.ui.uiMainGame.commandNum--;
            game.playSoundEffect(9);
            if(game.ui.uiMainGame.commandNum < 0){
                game.ui.uiMainGame.commandNum = 3;
            }
        }
        if(code== KeyCode.S || code== KeyCode.DOWN){
            game.ui.uiMainGame.commandNum++;
            game.playSoundEffect(9);
            if(game.ui.uiMainGame.commandNum > 3){
                game.ui.uiMainGame.commandNum=0;
            }
        }
        if (code == KeyCode.ENTER) {
            if (game.ui.uiMainGame.commandNum == 0) {
                int calCoin = (game.player.maxLife - game.player.life) * 20;
                if (game.player.coin >= calCoin && game.player.life < game.player.maxLife) {
                    game.player.life = game.player.maxLife;
                    game.player.coin -= calCoin;
                    game.gameState = game.playState;
                    game.ui.uiMainGame.addMessage("Healing SuccessFull!");
                }
                else {
                    System.out.println("Don't have coin ");

                    if (game.player.coin < calCoin) {
                        game.gameState = game.playState;
                        game.ui.uiMainGame.addMessage("You don't have much coin!");
                    }
                    else if (game.player.life >= game.player.maxLife) {
                        game.gameState = game.playState;
                        game.ui.uiMainGame.addMessage("You already have max life!");
                    }

                }

            }
            if (game.ui.uiMainGame.commandNum == 1) {
                if (game.player.coin >= 2000) {
                    game.player.maxLife += 10;
                    game.player.coin -= 2000;
                    game.gameState = game.playState;
                    game.ui.uiMainGame.addMessage("Increasing Max life successful!");
                }
                else {
                    System.out.println("Don't have coin ");
                    game.gameState = game.playState;
                    game.ui.uiMainGame.addMessage("You don't have much coin!");
                }
            }

            if (game.ui.uiMainGame.commandNum == 2) {
                if (game.player.coin >= 1000) {
                    game.player.energy += 10;
                    game.player.coin -= 500;
                    game.gameState = game.playState;
                    game.ui.uiMainGame.addMessage("Increasing energy successful!");
                }
                else {
                    System.out.println("Don't have coin ");
                    game.gameState = game.playState;
                    game.ui.uiMainGame.addMessage("You don't have much coin!");
                }
            }

            if (game.ui.uiMainGame.commandNum == 3) {
                game.gameState = game.playState;
            }
        }
    }

    private void characterState(KeyCode code) {
        if (code == KeyCode.C) {
            game.gameState = game.playState;
        }
        if(code==KeyCode.ENTER){
            game.player.selectItem();
        }
        playerInventory(code);
    }
    public void playerInventory(KeyCode code){
        if(code== KeyCode.W || code== KeyCode.UP){ //VK_W means if user press W then
            if(game.ui.uiMainGame.playerSlotRow!=0){
                game.ui.uiMainGame.playerSlotRow--;
                game.playSoundEffect(9);
            }
        }
        if(code== KeyCode.S || code== KeyCode.DOWN){ //VK_S means if user press S then
            if(game.ui.uiMainGame.playerSlotRow!=3) {
                game.ui.uiMainGame.playerSlotRow++;
                game.playSoundEffect(9);
            }
        }
        if(code==KeyCode.A || code== KeyCode.LEFT){ //VK_A means if user press A then
            if(game.ui.uiMainGame.playerSlotCol!=0) {
                game.ui.uiMainGame.playerSlotCol--;
                game.playSoundEffect(9);
            }
        }
        if(code==KeyCode.D || code== KeyCode.RIGHT){ //VK_D means if user press D then
            if(game.ui.uiMainGame.playerSlotCol!=4) {
                game.ui.uiMainGame.playerSlotCol++;
                game.playSoundEffect(9);
            }
        }
    }
    public void npcInventory(KeyCode code){
        if(code== KeyCode.W || code== KeyCode.UP){ //VK_W means if user press W then
            if(game.ui.uiMainGame.shopSlotRow!=0){
                game.ui.uiMainGame.shopSlotRow--;
                game.playSoundEffect(9);
            }
        }
        if(code== KeyCode.S || code== KeyCode.DOWN){ //VK_S means if user press S then
            if(game.ui.uiMainGame.shopSlotRow!=3) {
                game.ui.uiMainGame.shopSlotRow++;
                game.playSoundEffect(9);
            }
        }
        if(code==KeyCode.A || code== KeyCode.LEFT){ //VK_A means if user press A then
            if(game.ui.uiMainGame.shopSlotCol!=0) {
                game.ui.uiMainGame.shopSlotCol--;
                game.playSoundEffect(9);
            }
        }
        if(code==KeyCode.D || code== KeyCode.RIGHT){ //VK_D means if user press D then
            if(game.ui.uiMainGame.shopSlotCol!=4) {
                game.ui.uiMainGame.shopSlotCol++;
                game.playSoundEffect(9);
            }
        }
    };

    private void menuBarState(KeyCode code) {
        if (code == KeyCode.ESCAPE) {
            escapePressed = true;
            game.gameState=game.playState;
        }
        if(code== KeyCode.W || code== KeyCode.UP){
            game.ui.uiMainGame.commandNum--;
            game.playSoundEffect(9);
            if(game.ui.uiMainGame.commandNum < 0){
                game.ui.uiMainGame.commandNum = 3;
            }
        }
        if(code== KeyCode.S || code== KeyCode.DOWN){
            game.ui.uiMainGame.commandNum++;
            game.playSoundEffect(9);
            if(game.ui.uiMainGame.commandNum > 3){
                game.ui.uiMainGame.commandNum=0;
            }
        }
        if (code == KeyCode.ENTER) {
            if (game.ui.uiMainGame.commandNum == 0) {

            }
            if (game.ui.uiMainGame.commandNum == 1) {
                game.gameState = game.settingsState;
            }
            if (game.ui.uiMainGame.commandNum == 2) {
                MyJDBC.updateUser(game.player);
                for ( Entity item: game.player.inventory) {
                    int itemCode=item.itemCode;
                    int count=item.itemCount;
                MyJDBC.updateInventory(game.user.getUserId(),itemCode,count);
                    System.out.println("after updated:");
                    System.out.println("itemName: "+item.name+"|| itemCount: "+count);
                }
                game.stopMusic();
                Game.exitGame();
            }
            if (game.ui.uiMainGame.commandNum == 3) {
                MyJDBC.updateUser(game.player);
                for ( Entity item: game.player.inventory) {
                    int itemCode = item.itemCode;
                    int count = item.itemCount;
                    MyJDBC.updateInventory(game.user.getUserId(), itemCode, count);
                    System.out.println("after updated:");
                    System.out.println("itemName: " + item.name + "|| itemCount: " + count);
                }
                game.stopMusic();
                game.logout();
            }
        }
    }

    public void playState(KeyCode code){

        switch (code) {
            case UP: moveUp = true; break;
            case DOWN: moveDown = true; break;
            case LEFT: moveLeft = true; break;
            case RIGHT: moveRight = true; break;
            case W: moveUp = true; break;
            case S: moveDown = true; break;
            case A: moveLeft = true; break;
            case D: moveRight = true; break;
            case F: FKeyPressed=true;break;
            case ENTER: enterPressed = true; break;
            case SPACE: spacePressed = true; break;
            case V: vKeyPressed = true; break;
            case C: game.gameState = game.characterState; break;
            case ESCAPE: game.gameState = game.menuBarState; break;

        }
    }


    public void dialogueState(KeyCode code){
        if(code== KeyCode.ENTER ){ //VK_ENTER means if user press ENTER then


           // game.ui.uiMainGame.npc.dialogueIndex++;
            if(game.ui.uiMainGame.npc.dialogue[game.ui.uiMainGame.npc.dialogueIndex]==null){
//                game.isDialogueToGameState=true;
                game.gameState=game.playState;
                game.ui.uiMainGame.npc.dialogueIndex=0;
            }else{
                game.ui.uiMainGame.npc.speak();
            }


//            if( game.isDialogueToGameState==true){
//                game.gameState=game.playState;
//                //game.ui.npc.onPath=true;
//                game.isDialogueToGameState=false;
//            }else {
//                game.ui.uiMainGame.npc.speak();
//
//            }
        }
    }

    public void messageState(KeyCode code){
        if(code== KeyCode.ENTER ){ //VK_ENTER means if user press ENTER then
//            game.ui.uiMainGame.currentDialogue=dialogue[dialogueIndex];
            game.gameState=game.playState;
        }
    }

    public void wizConversationState(KeyCode code){
        if(code== KeyCode.ENTER ){
            enterPressed=true;
        }
        if(code== KeyCode.W || code== KeyCode.UP){
            game.ui.uiMainGame.commandNum--;
            if(game.ui.uiMainGame.commandNum<0){
                game.ui.uiMainGame.commandNum=3;
            }
        }
        if(code== KeyCode.S || code== KeyCode.DOWN){
            game.ui.uiMainGame.commandNum++;
            if(game.ui.uiMainGame.commandNum>3){
                game.ui.uiMainGame.commandNum=0;
            }
        }
    }

    public void tradeState(KeyCode code){
        if(code== KeyCode.ENTER ){
            enterPressed=true;
        }
        if(game.ui.uiMainGame.subState==0){

            if(code== KeyCode.W || code== KeyCode.UP){
                game.ui.uiMainGame.commandNum--;
                if(game.ui.uiMainGame.commandNum<0){
                    game.ui.uiMainGame.commandNum=2;
                }
            }
            if(code== KeyCode.S || code== KeyCode.DOWN){
                game.ui.uiMainGame.commandNum++;
                if(game.ui.uiMainGame.commandNum>2){
                    game.ui.uiMainGame.commandNum=0;
                }
            }
        }
        if(game.ui.uiMainGame.subState==1){
            npcInventory(code);
            if(code==KeyCode.ESCAPE){
                game.ui.uiMainGame.subState=0;
            }
        }if(game.ui.uiMainGame.subState==2){
            playerInventory(code);
            if(code==KeyCode.ESCAPE){
                game.ui.uiMainGame.subState=0;
            }
        }

    }
    public void mapState(KeyCode code) {
        if(code==KeyCode.M){
            game.gameState=game.playState;
        }
    }

    private void handleKeyRelease(KeyCode code) {
        switch (code) {
            case UP: moveUp = false; break;
            case DOWN: moveDown = false; break;
            case LEFT: moveLeft = false; break;
            case RIGHT: moveRight = false; break;
            case W: moveUp = false; break;
            case S: moveDown = false; break;
            case A: moveLeft = false; break;
            case D: moveRight = false; break;
            case F: FKeyPressed=false;break;
            case ENTER: enterPressed = false; break;
            case SPACE:spacePressed = false; break;
            case V: vKeyPressed = false; break;
            case ESCAPE:escapePressed = false; break;
        }

    }

    // Getters for the movement flags
    public boolean isMoveUp() {
        return moveUp;
    }

    public boolean isMoveDown() {

        return moveDown;
    }

    public boolean isMoveLeft() {

        return moveLeft;
    }

    public boolean isMoveRight() {
        return moveRight;
    }
    public boolean isEnterPressed(){
        return enterPressed;
    }
    public boolean isSpacePressed() {
        return spacePressed;
    }
    public boolean isVKeyPressed() {
        return vKeyPressed;
    }
    public boolean isEscapePressed() {
        return escapePressed;
    }
    public  boolean isFKeyPressed(){
        return FKeyPressed;
    }

    public void setEnterPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
    }
    public void setSpacePressed(boolean spacePressed) {
        this.spacePressed = spacePressed;
    }
    public void setVKeyPressed(boolean vKeyPressed) {
        this.vKeyPressed = vKeyPressed;
    }
    public void setEscapePressed(boolean escapePressed) {
        this.escapePressed = escapePressed;
    }


    public void setBooleanAll(boolean value){
        this.moveUp = value;
        this.moveDown = value;
        this.moveLeft = value;
        this.moveRight = value;
        this.enterPressed = value;
        this.spacePressed = value;
        this.escapePressed = value;
        this.vKeyPressed = value;
    }

}