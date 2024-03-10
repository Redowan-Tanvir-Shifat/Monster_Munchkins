package com.example.return_3.main;

import com.example.return_3.entity.Entity;
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
    GraphicsContext gc;
    Font arial_40, arial_80B;
    Image heartFull,heartHalf,heartBlank,crystalFull,crystalBlank,starImage,energyImage;
    public boolean messageOn=false;
    //    public String message="";
//    int messageCounter=0; //to set timer so that the message will be disappear after some moment
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();

    public boolean gameFinished=false; // if game is finished then the message will be shown
    public String currentDialogue=""; //for setting the dialogue
    public int commandNum=0; // this is for showing our menu specific commands


    public Entity heart;
    int counter=0;

    public Entity npc;
    //

    //CONSTRUCTOR START

    public UI(Game game){
        this.game = game;
        arial_40 = new Font("Arial",40);
        arial_80B = new Font("Arial",80);
        UtilityTool uTool= new UtilityTool();
        heart= new OBJ_Heart(game);
        heartFull=heart.image1;
        heartHalf=heart.image2;
        heartBlank=heart.image3;
        starImage=uTool.loadImage("/objects/star.png",game.tileSize,game.tileSize);
        energyImage=uTool.loadImage("/objects/energy.png",game.tileSize-10,game.tileSize-10);

    }
    public void addMessage(String text){
//        message = text;
//        messageOn = true;
        message.add(text);
        messageCounter.add(0);
    }
    public void draw(GraphicsContext gc){
        //we did this because we need to use this gc in other methods also
        this.gc=gc;

        gc.setFont(arial_40);
        gc.setFill(Color.WHITE);


//        PLAY STATE
        if(game.gameState == game.playState){

            drawPlayerLife();
            drawPlayerLevel();
            drawMessage();
            drawEnergy();
            drawCoin();

        }

        //DIALOGUE STATE
        if(game.gameState == game.dialogueState){

            drawDialogueScreen();
        }
        //WizConversationState STATE
        if(game.gameState == game.wizConversationState){


            drawWizConversationScreen();
        }


    }
    public void drawPlayerLife(){
//        game.player.life=4;
//        int x= game.tileSize/2;
//        int y=game.tileSize/2;
//        int i=0;
//        //DRAW BLANK HEART
//        while(i<(game.player.maxLife/2)){
//            gc.drawImage(heartBlank,x,y);
//            i++;
//            x+=game.tileSize;
//        }
//        //RESET THE values
//        x= game.tileSize/2;
//        y=game.tileSize/2;
//        i=0;
//
//        //Draw Current LIFE
//        while(i<game.player.life){
//            gc.drawImage(heartHalf,x,y);
//            i++;
//            if(i<game.player.life){
//                gc.drawImage(heartFull,x,y);
//            }
//            i++;
//            x+=game.tileSize;
//        }
        int x = (game.screenWidth/2) - game.tileSize * 3;
        int y = game.tileSize / 2;

        // Dark yellow outline
        gc.setStroke(Color.rgb(26, 3, 5)); // Dark yellow color
        gc.setLineWidth(2); // Width of the outline
       // gc.strokeRect(x, y, game.tileSize * 6, game.tileSize); // Outline of the bar
        gc.strokeRoundRect(x, y, game.tileSize * 6, game.tileSize/2,10,10); // Outline of the bar

        // Calculate width of yellow bar based on energy and maxEnergy
        double lifeWidth = ((double) game.player.life / game.player.maxLife) * game.tileSize * 6;

        // Fill yellow bar
        gc.setFill(Color.rgb(238,26,49));
        //gc.fillRect(x, y, lifeWidth, game.tileSize);
        gc.fillRoundRect(x, y, lifeWidth, game.tileSize/2,10,10);

        // Set font for text
        gc.setFont(Font.getDefault());

        gc.drawImage(heartFull,x-(1.5*game.tileSize),y/2);



    }
    public void drawEnergy(){
        int x = game.screenWidth - game.tileSize * 3;
        int y = game.tileSize / 2;

        // Dark yellow outline
        gc.setStroke(Color.rgb(129, 61, 57)); // Dark yellow color
        gc.setLineWidth(2); // Width of the outline
        gc.strokeRoundRect(x, y, game.tileSize * 2, game.tileSize / 2,10,10); // Outline of the bar

        // Calculate width of yellow bar based on energy and maxEnergy
        double energyWidth = ((double) game.player.energy / game.player.maxEnergy) * game.tileSize * 2;

        // Fill yellow bar
        gc.setFill(Color.rgb(252, 190, 69));
        gc.fillRoundRect(x, y, energyWidth, game.tileSize / 2,10,10);

        // Set font for text
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // Draw "Energy" text
        gc.setFill(Color.WHITE);
        String text = "" + game.player.energy + "/" + game.player.maxEnergy;

        // Create a temporary Text node to measure the width
        Text textNode = new Text(text);
        textNode.setFont(gc.getFont());
        double textWidth = textNode.getBoundsInLocal().getWidth();

        // Draw text at adjusted x position
        gc.fillText(text, x - (textWidth+5), y+(game.tileSize/2.2));
        gc.drawImage(energyImage,x-(textWidth+game.tileSize+5),y);




    }

    public void drawCoin(){


        // Set font for text
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // Draw "Energy" text
        gc.setFill(Color.WHITE);
        String text = "" + game.player.coin;

        // Create a temporary Text node to measure the width
        Text textNode = new Text(text);
        textNode.setFont(gc.getFont());
        double textWidth = textNode.getBoundsInLocal().getWidth();
        double textHeight = textNode.getBoundsInLocal().getHeight();

        double x = game.screenWidth - (game.tileSize+textWidth);
        double y = game.tileSize *2;

        // Dark yellow outline
        gc.setStroke(Color.rgb(129, 61, 57)); // Dark yellow color
        gc.setLineWidth(2); // Width of the outline
        gc.strokeRoundRect(x, y, textWidth+10, textHeight+10,10,10); // Outline of the bar

        // Calculate width of yellow bar based on energy and maxEnergy

        // Fill yellow bar
        gc.setFill(Color.rgb(252, 190, 69));
        gc.fillRoundRect(x, y, textWidth+10, textHeight+10,10,10);



        // Draw text at adjusted x position
        gc.fillText(text, x - (textWidth+5), y+(game.tileSize/2.2));
        gc.drawImage(energyImage,x+10,y+5);




    }

    public void drawPlayerLevel() {
        int x =game.tileSize;
        int y = game.tileSize / 2;

       gc.drawImage(starImage,x,y);


        // Draw level number
        int level = game.player.level;
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 15)); // Customize font as needed
        gc.fillText(""+ level, 10 + x, 8 + (y*2)); // Adjust position as needed

        //Draw Player EXP
        String expText = "Exp: " + game.player.exp + "/" + game.player.nextLevelExp;
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Customize font as needed
        y=y*4;
        // Draw exp text at adjusted x position
        gc.fillText(expText, x/2, y );


    }


    public void drawMessage(){
        int messageX=game.tileSize;
        int messageY=game.tileSize*4;
        //gc.setFont(gc.getFont().deriveFont(Font.BOLD,32F));
        for(int i=0;i<message.size();i++){
            if(message.get(i)!=null){

                gc.setFill(Color.BLACK);
                gc.fillText(message.get(i),messageX+2,messageY+2);

                //gc.setColor(Color.white);
                gc.fillText(message.get(i),messageX,messageY);
                int counter= messageCounter.get(i)+1;
                messageCounter.set(i,counter); //set the counter to the array;
                messageY+=50;
                if(messageCounter.get(i)>180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }



    public void drawDialogueScreen(){
        //  CREATING A DIALOGUE WINDOW
        //set parameter for window
//        System.out.println("UI: drawDialogueScreen() method called");
        int x=game.tileSize*3;
        int y=game.tileSize/2;
        int width=game.screenWidth-(game.tileSize*6);
        int height=game.tileSize*4;
        drawSubWindow(x, y, width, height);
        //gc.setFont(gc.getFont().deriveFont(Font.PLAIN,28F));
        x+= game.tileSize;
        y+= game.tileSize;

        //to create new line
        for(String line: currentDialogue.split("\n")){
            gc.fillText(line,x,y);
            y+=40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c=Color.rgb(0, 0, 0, .20);
        gc.setFill(c);
        gc.fillRoundRect(x,y,width,height,35,35);

        c=Color.WHITE;
        gc.setFill(c);
        //gc.setStroke(Color.WHITE);


        gc.setLineWidth(5); // Setting stroke width
        gc.setLineDashes(0); // Setting line dashes to 0 (solid line)
        gc.setLineCap(StrokeLineCap.ROUND); // Setting line cap to round
        gc.strokeRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }


    public void drawWizConversationScreen(){
        System.out.println("drawWizConversationScreen draw working");

        drawDialogueScreen();
        int x=game.tileSize*15;
        int y=game.tileSize*4;
        int width=game.tileSize*3;
        int height=game.tileSize*4;
        drawSubWindow(x, y, width, height);
        //Draw Text
        x+=game.tileSize;
        y+=game.tileSize;
        gc.fillText("School",x,y);
        if(commandNum==0){
            gc.fillText(">",x-24,y);
            if(game.keyHandler.isEnterPressed()){
                showPath(84,115);
            }
        }

        y+=game.tileSize;
        gc.fillText("Game Center",x,y);
        if(commandNum==1){
            gc.fillText(">",x-24,y);
            if(game.keyHandler.isEnterPressed()){
                showPath(176,166);
            }
        }

        y+=game.tileSize;
        gc.fillText("Shop",x,y);
        if(commandNum==2){
            gc.fillText(">",x-24,y);
            if(game.keyHandler.isEnterPressed()){
                showPath(118,153);
            }
        }

        y+=game.tileSize;
        gc.fillText("leave",x,y);
        if(commandNum==3){
            gc.fillText(">",x-24,y);
            if(game.keyHandler.isEnterPressed()){
                System.out.println("enter press for leave working");
                commandNum=0;
                game.gameState=game.playState;
            }

        }
    }

    public void showPath(int goalCol,int goalRow){
        game.ui.npc.onPath=true;
        game.ui.npc.goalCol = goalCol;
        game.ui.npc.goalRow = goalRow;
        game.gameState=game.playState;
    }

}