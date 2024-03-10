package com.example.return_3.main;


import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;

public class KeyHandler {
    Game game;
    Stage stage;
    private boolean moveUp, moveDown, moveLeft, moveRight,enterPressed, spacePressed, escapePressed;

    public KeyHandler(Game game) {
        this.game = game;
        Game.gameScene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        Game.gameScene.setOnKeyReleased(event -> handleKeyRelease(event.getCode()));
    }

    private void handleKeyPress(KeyCode code) {

        if (code == KeyCode.ESCAPE) {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Exit");
                alert.setHeaderText("You are about to exit");
                alert.setContentText("Do you want to exit?");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    Game.exitGame();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        //This is for PlayState
        if(game.gameState == game.playState){
            playState(code);

        }
        //PAUSE state
//        else if(game.gameState==game.pauseState) {
//            pauseState(code);
//        }
        //DIALOGUE state
        else if(game.gameState==game.dialogueState){
            dialogueState(code);
        }
        //WizConversation state
        else if(game.gameState==game.wizConversationState){
            wizConversationState(code);
        }
        else if (game.gameState == game.characterState) {
            characterState(code);
        }

    }

    private void characterState(KeyCode code) {
        if (code == KeyCode.C) {
            game.gameState = game.playState;
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
            case ENTER: enterPressed = true; break;
            case SPACE: spacePressed = true; break;
            case C: game.gameState = game.characterState; break;

        }
    }

    public void dialogueState(KeyCode code){
        if(code== KeyCode.ENTER ){ //VK_ENTER means if user press ENTER then
            game.ui.npc.dialogueIndex++;
            if(game.ui.npc.dialogue[game.ui.npc.dialogueIndex]==null){
                game.isDialogueToGameState=true;
                game.ui.npc.dialogueIndex=0;
            }
            if( game.isDialogueToGameState==true){
                game.gameState=game.playState;
                //game.ui.npc.onPath=true;
                game.isDialogueToGameState=false;
            }else {


                game.ui.npc.speak();
                System.out.println(game.ui.npc.dialogue[game.ui.npc.dialogueIndex]);

                System.out.println(game.ui.npc.dialogueIndex);
            }
        }
    }


    public void wizConversationState(KeyCode code){
        if(code== KeyCode.ENTER ){
            enterPressed=true;
        }
        if(code== KeyCode.W || code== KeyCode.UP){
            game.ui.commandNum--;
            if(game.ui.commandNum<0){
                game.ui.commandNum=3;
            }
        }
        if(code== KeyCode.S || code== KeyCode.DOWN){
            game.ui.commandNum++;
            if(game.ui.commandNum>3){
                game.ui.commandNum=0;
            }
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
            case ENTER: enterPressed=false; break;
            case SPACE:spacePressed = false; break;
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
    public boolean isEscapePressed() {
        return escapePressed;
    }

    public void setEnterPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
    }
    public void setSpacePressed(boolean spacePressed) {
        this.spacePressed = spacePressed;
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

    }

}