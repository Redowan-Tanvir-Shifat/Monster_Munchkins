package com.example.return_3.ui;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.main.UtilityTool;
import com.example.return_3.object.OBJ_Coin;
import com.example.return_3.object.OBJ_Heart;
import com.example.return_3.object.OBJ_Ladi;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;

public class UI_MainGame {
    Game game;
    Entity entity;
    GraphicsContext gc;
    Font arial_40, arial_80B;
    Image heartFull, starImage, energyImage, coinImage;
    public boolean messageOn = false;

    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();

    public boolean gameFinished=false; // if game is finished then the message will be shown
    public String currentDialogue = ""; //for setting the dialogue
    public int commandNum = 0; // this is for showing our menu specific commands

    public int subState=0;

    public Entity heart;
    public int playerSlotCol=0;
    public int playerSlotRow=0;
    public int npcSlotCol=0;
    public int npcSlotRow=0;
    int counter=0;

    public Entity npc;
    public UI_MainGame(Game game) {
        this.game = game;
        this.gc = game.gc;
        arial_40 = new Font("Arial",40);
        arial_80B = new Font("Arial",80);
        UtilityTool uTool= new UtilityTool();
        heart = new OBJ_Heart(game);
        heartFull = heart.image1;
        starImage = uTool.loadImage("/objects/star1.png",game.tileSize+10,game.tileSize+10);
        energyImage = uTool.loadImage("/objects/energy.png",game.tileSize-7,game.tileSize-7);
        Entity coin= new OBJ_Coin(game);
        coinImage = coin.down1;
    }





    public void drawPlayerLife() {
        int x = (game.screenWidth / 2) - game.tileSize * 3;
        double y = game.tileSize / 1.5;
        int tempY = 25;
        // Fill yellow bar
        gc.setFill(Color.rgb(255, 209, 184));
        //gc.fillRect(x, y, lifeWidth, game.tileSize);
        gc.fillRoundRect(x, tempY, game.tileSize * 6, game.tileSize / 2, 10, 10);

        // Dark yellow outline
        gc.setStroke(Color.rgb(26, 3, 5)); // Dark yellow color
        gc.setLineWidth(2); // Width of the outline

        gc.strokeRoundRect(x, tempY, game.tileSize * 6, game.tileSize / 2, 10, 10); // Outline of the bar

        // Calculate width of yellow bar based on energy and maxEnergy
        double lifeWidth = ((double) game.player.life / game.player.maxLife) * game.tileSize * 6;

        // Fill yellow bar
        gc.setFill(Color.rgb(238, 26, 49));
        //gc.fillRect(x, y, lifeWidth, game.tileSize);
        gc.fillRoundRect(x, tempY, lifeWidth, game.tileSize / 2, 10, 10);

        // Set font for text
        gc.setFont(Font.getDefault());

        gc.drawImage(heartFull, x - (game.tileSize - 8), y - 5);
    }


    public void drawEnergy(){
        int x = game.screenWidth - game.tileSize * 3;
        double y = game.tileSize / 1.5;
        int tempY=25;
        // Dark yellow outline
        gc.setStroke(Color.rgb(129, 61, 57)); // Dark yellow color
        gc.setLineWidth(2); // Width of the outline
        gc.strokeRoundRect(x, tempY, game.tileSize * 2.5, game.tileSize / 2,10,10); // Outline of the bar
        // Fill yellow bar
        gc.setFill(Color.rgb(255,209,184));
        //gc.fillRect(x, y, lifeWidth, game.tileSize);
        gc.fillRoundRect(x, tempY, game.tileSize * 2.5, game.tileSize/2,10,10);


        // Calculate width of yellow bar based on energy and maxEnergy
        double energyWidth = ((double) game.player.energy / game.player.maxEnergy) * game.tileSize * 2;

        // Fill yellow bar
        gc.setFill(Color.rgb(252, 190, 69));
        gc.fillRoundRect(x, tempY, energyWidth, game.tileSize / 2,10,10);

        // Set font for text
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        // Draw "Energy" text
        gc.setFill(Color.WHITE);
        String text = "" + game.player.energy + "/" + game.player.maxEnergy;

        // Create a temporary Text node to measure the width
        Text textNode = new Text(text);
        textNode.setFont(gc.getFont());
        double textWidth = textNode.getBoundsInLocal().getWidth();

        // Draw text at adjusted x position
        gc.fillText(text, x+12, tempY+12);
        gc.drawImage(energyImage,x-15,y);
    }


    public void drawCoin(){

        int x = game.screenWidth - game.tileSize * 3;
        double y = 20+game.tileSize;
        double tempY=25+game.tileSize;

//        x+=game.tileSize-10;
        // Dark yellow outline
        gc.setStroke(Color.rgb(129, 61, 57)); // Dark yellow color
        gc.setLineWidth(2); // Width of the outline
        gc.strokeRoundRect(x, tempY, game.tileSize * 2.5, game.tileSize / 2,10,10); // Outline of the bar

        // Fill yellow bar
        gc.setFill(Color.rgb(255,209,184));
//        gc.setFill(Color.rgb(252, 190, 69));
        gc.fillRoundRect(x, tempY, game.tileSize * 2.5, game.tileSize / 2,10,10);

        // Set font for text
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        // Draw "Energy" text
        gc.setFill(Color.rgb(125,89,9));
        String text = "" + game.player.coin;

        // Draw text at adjusted x position
        gc.fillText(text, x +15, y+17);
        gc.drawImage(coinImage,x-15,y);

    }


    public void drawPlayerLevel() {
        int x =game.tileSize/2;
        int y = 10;
        int tempX=x+30;
        int tempY=25;

        gc.setStroke(Color.rgb(129, 61, 57)); // Dark yellow color
        gc.setLineWidth(2); // Width of the outline
        gc.strokeRoundRect(tempX, tempY, game.tileSize * 3, game.tileSize / 2,10,10); // Outline of the bar

        // Fill yellow bar
        gc.setFill(Color.rgb(255,209,184));
        //gc.fillRect(x, y, lifeWidth, game.tileSize);
        gc.fillRoundRect(tempX, tempY, game.tileSize * 3, game.tileSize/2,10,10);
        // Dark yellow outline


        // Calculate width of yellow bar based on energy and maxEnergy
        double expWidth = ((double) game.player.exp / game.player.nextLevelExp) * game.tileSize * 3;

        // Fill yellow bar
        gc.setFill(Color.rgb(252, 190, 69));

        gc.fillRoundRect(tempX, tempY, expWidth, game.tileSize / 2,10,10);

        gc.drawImage(starImage,x,y);

        // Draw level number
        int level = game.player.level;
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 15)); // Customize font as needed
        gc.fillText("50", 13 + x, tempY+12); // Adjust position as needed

        //Draw Player EXP
        String expText= game.player.exp + "/" + game.player.nextLevelExp;
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Customize font as needed

        // Draw exp text at adjusted x position
        // gc.fillText(expText, tempX+10, tempY+5 );
        gc.fillText(expText, x+(game.tileSize+10), tempY+12);

    }


    public void hospitalScreen() {
        // Create a Frame...
        final int frameX = game.tileSize * 5;
        final int frameY = game.tileSize * 2;
        final int frameWidth = game.tileSize * 20;
        final int frameHeight = game.tileSize * 14;

        Color c = Color.rgb(255, 209, 184);
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        String text = "Welcome To Hospital";
        int textX = getXForCenteredText(text);
        int textY = frameY + game.tileSize + 16;

        gc.setFill(Color.rgb(255, 255, 255));
        gc.setFont(Font.font("Arial", 30));
        gc.fillText(text, textX, textY);

        // Menu...
        gc.setFill(Color.rgb(255, 255, 255));
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        text = "You need 300 coin to heal yourself.";
        textX = game.tileSize * 8;
        textY = game.tileSize * 6;
        gc.fillText(text, textX, textY);
        if (commandNum == 0) {
            gc.fillText(">", textX-game.tileSize, textY);
        }

        text = "You need 1000 coin to increase 20% of life.";
        textX = game.tileSize * 8;
        textY = game.tileSize * 7;
        gc.fillText(text, textX, textY);
        if (commandNum == 1) {
            gc.fillText(">", textX-game.tileSize, textY);
        }

        text = "Back";
        textX = game.tileSize * 8;
        textY = game.tileSize * 9;
        gc.fillText(text, textX, textY);
        if (commandNum == 2) {
            gc.fillText(">", textX-game.tileSize, textY);
        }
    }


    public void menuBarScreen() {
        // Create a Frame...
        final int frameX = game.tileSize * 5;
        final int frameY = game.tileSize * 2;
        final int frameWidth = game.tileSize * 20;
        final int frameHeight = game.tileSize * 14;

        Color c = Color.rgb(255, 209, 184);
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        String text = "Game Name";
        int textX = getXForCenteredText(text);
        int textY = frameY + game.tileSize + 16;

        gc.setFill(Color.rgb(255, 255, 255));
        gc.setFont(Font.font("Arial", 40));
        gc.fillText(text, textX, textY);

        // Menu...
        gc.setFill(Color.rgb(255, 255, 255));
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        text = "Back To Town hall";
        textX = getXForCenteredText(text);
        textY = game.tileSize * 6;
        gc.fillText(text, textX, textY);
        if (commandNum == 0) {
            gc.fillText(">", textX-game.tileSize, textY);
        }

        text = "Exit Game";
        textX = getXForCenteredText(text);
        textY = game.tileSize * 7;
        gc.fillText(text, textX, textY);
        if (commandNum == 1) {
            gc.fillText(">", textX-game.tileSize, textY);
        }
    }

    public void drawCharacterScreen() {
        // Create a Frame...
        final int frameX = game.tileSize * 2;
        final int frameY = game.tileSize * 3;
        final int frameWidth = game.tileSize * 6;
        final int frameHeight = game.tileSize * 12;

        Color c = Color.rgb(255, 209, 184);
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // Text....
        gc.setFill(Color.rgb(255, 255, 255));
        gc.setFont(Font.font("Arial", 16));

        int textX = frameX + game.tileSize/2;
        int textY = frameY + game.tileSize+8;
        final int lineGap = 32;

        // NAMES....
        gc.fillText("Level: ", textX, textY);
        textY += lineGap;
        gc.fillText("Life: ", textX, textY);
        textY += lineGap;
        gc.fillText("Strength: ", textX, textY);
        textY += lineGap;
        gc.fillText("Dexterity: ", textX, textY);
        textY += lineGap;
        gc.fillText("Attack: ", textX, textY);
        textY += lineGap;
        gc.fillText("EXP: ", textX, textY);
        textY += lineGap;
        gc.fillText("Next Level EXP: ", textX, textY);
        textY += lineGap;
        gc.fillText("Coin: ", textX, textY);
        textY += lineGap + 8;
        gc.fillText("Weapon: ", textX, textY);
        textY += lineGap + 8;
        gc.fillText("Shield: ", textX, textY);

        // VALUES....
        int tailX = (frameX + frameWidth) - 18;
        // RESET textY...
        textY = frameY + game.tileSize+8;
        String value;

        value = String.valueOf(game.player.life + "/" + game.player.maxLife);
        textX = getXForAlignToRightText(value, tailX);
        gc.fillText(value, textX, textY);
        textY += lineGap;

        value = String.valueOf(game.player.strength);
        textX = getXForAlignToRightText(value, tailX);
        gc.fillText(value, textX, textY);
        textY += lineGap;

        value = String.valueOf(game.player.dexterity);
        textX = getXForAlignToRightText(value, tailX);
        gc.fillText(value, textX, textY);
        textY += lineGap;

        value = String.valueOf(game.player.attack);
        textX = getXForAlignToRightText(value, tailX);
        gc.fillText(value, textX, textY);
        textY += lineGap;

        value = String.valueOf(game.player.defense);
        textX = getXForAlignToRightText(value, tailX);
        gc.fillText(value, textX, textY);
        textY += lineGap;

        value = String.valueOf(game.player.exp);
        textX = getXForAlignToRightText(value, tailX);
        gc.fillText(value, textX, textY);
        textY += lineGap;

        value = String.valueOf(game.player.nextLevelExp);
        textX = getXForAlignToRightText(value, tailX);
        gc.fillText(value, textX, textY);
        textY += lineGap;

        value = String.valueOf(game.player.coin);
        textX = getXForAlignToRightText(value, tailX);
        gc.fillText(value, textX, textY);
        textY += lineGap - 16;

        gc.drawImage(game.player.currentWeapon.down1, tailX-game.tileSize, textY);
        textY += lineGap + 8;

        gc.drawImage(game.player.currentShield.down1, tailX-game.tileSize, textY);
    }

    public void drawInventory(Entity entity, boolean cursor){
        //Initial value
        int frameX=0;
        int frameY=0;
        int frameWidth=0;
        int frameHeight=0;
        int slotCol=0;
        int slotRow=0;

        if(entity==game.player){
            //FRAME
            frameX=game.tileSize*9;
            frameY=game.tileSize;
            frameWidth=game.tileSize*6;
            frameHeight=game.tileSize*5;
            slotCol=playerSlotCol;
            slotRow=playerSlotRow;
        }else{
            //FRAME
            frameX=game.tileSize*2;
            frameY=game.tileSize;
            frameWidth=game.tileSize*6;
            frameHeight=game.tileSize*5;
            slotCol=npcSlotCol;
            slotRow=npcSlotRow;
        }

        //Draw the frame
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);


        //SLOT
        final int slotXStart=frameX+20;
        final int slotYStart=frameY+20;
        int slotX=slotXStart;
        int slotY=slotYStart;
        int slotSize=game.tileSize+3;

        //Draw Players new Items
        for(int i=0; i<entity.inventory.size(); i++){

            // Text....

            //EQUIP CURSOR
            if(entity.inventory.get(i)==entity.currentWeapon||entity.inventory.get(i)==entity.currentShield){
                gc.setFill(Color.rgb(240, 190, 90));
                gc.fillRoundRect(slotX,slotY,game.tileSize,game.tileSize,10,10);
            }

            gc.drawImage(entity.inventory.get(i).down1, slotX, slotY);
            slotX+=slotSize;
            if(i==4 ||i==9||i==14){
                slotY+=slotSize;
                //reset SlotX
                slotX=slotXStart;
            }
        }

        //CURSOR
        if(cursor==true){
            int cursorX=slotXStart+(slotSize*slotCol);
            int cursorY=slotYStart+(slotSize*slotRow);
            int cursorWidth=game.tileSize;
            int cursorHeight=game.tileSize;
            //Draw cursor
            gc.setStroke(Color.rgb(255, 255, 255));
            //gc.setFont(Font.font("Arial", 16));
           // gc.setStroke(new BasicStroke(3));
            //gc.setLineWidth(3);
            //gc.setStroke(Color.rgb(26, 3, 5)); // Dark yellow color
            gc.setLineWidth(3); // Width of the outline
            gc.strokeRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10); // Stroke the round rectangle

            //Another subwindow to show the description
            //DESCRIPTION FRAME
            int dFrameX= frameX;
            int dFrameY= frameY+ frameHeight;
            int dFrameWidth= frameWidth;
            int dFrameHeight= game.tileSize*3;
            //DRAW DESCRIPTION TEXT
            int textX=dFrameX+20;
            int textY=dFrameY+game.tileSize;

           // gc.setFill(Color.rgb(255, 255, 255));
            //gc.setFont(Font.font("Arial", 16));
            //this is for setting description TEXT
            int itemIndex=getItemIndexOnSlot(slotCol,slotRow);
            if(itemIndex<entity.inventory.size()) {
                drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);
                for(String line : entity.inventory.get(itemIndex).description.split("\n")) {

                    gc.fillText(line, textX,textY);
                    textY+=32;
                }
            }
        }

    }




    public void drawTradeScreen(){
        switch (subState){
            case 0: select();break;
            case 1: buy();break;
            case 2: sell();break;
        }
        game.keyHandler.setEnterPressed(false);
    }

    public void select(){
        drawDialogueScreen();

        //Draw Window
        int x=game.tileSize*15;
        int y=game.tileSize*4;
        int width=game.tileSize*3;
        int height=(int)(game.tileSize*3.5);
        drawSubWindow(x, y, width, height);
        // Text....
        gc.setFill(Color.rgb(255, 255, 255));
        gc.setFont(Font.font("Arial", 16));
        //DRAW TEXT
        x+= game.tileSize;
        y+= game.tileSize;
        gc.fillText("Buy",x,y);
        if(commandNum==0){
            gc.fillText(">",x-24,y);
            if(game.keyHandler.isEnterPressed()==true){
                subState=1;
            }
        }
        y+= game.tileSize;
        gc.fillText("Sell",x,y);
        if(commandNum==1){
            gc.fillText(">",x-24,y);
            if(game.keyHandler.isEnterPressed()==true){
                subState=2;
            }
        }

        y+= game.tileSize;
        gc.fillText("Leave",x,y);
        if(commandNum==2){
            gc.fillText(">",x-24,y);
            if(game.keyHandler.isEnterPressed()==true){
                commandNum=0;
                game.gameState=game.dialogueState;
                currentDialogue="Come again bitch!";
            }
        }
    }
    public void buy(){
//        //Draw player Inventory
        drawInventory(game.player,false);
//        //Draw NPC inventory
        drawInventory(npc,true);

        //DRAW HINT WINDOWS
        int x= game.tileSize*2;
        int y=game.tileSize*9;
        int width=game.tileSize*6;
        int height=game.tileSize*2;
        drawSubWindow(x,y,width,height);
        //drawing text
        gc.fillText("[ESC] back",x+24,y+60);


        //DRAW PLAYER COIN WINDOWs
        x= game.tileSize*12;
        y=game.tileSize*9;
        width=game.tileSize*6;
        height=game.tileSize*2;
        drawSubWindow(x, y, width, height);
        gc.setFont(Font.font("Arial", 16));
        gc.fillText("Your Coin: "+game.player.coin,x+24,y+60);


        //DRAW PRICE WINDOWS
        int itemIndex = getItemIndexOnSlot(npcSlotCol,npcSlotRow);
        if(itemIndex<npc.inventory.size()){
            x=(int)(game.tileSize*5.5);
            y=(int)(game.tileSize*5.5);
            width=(int)(game.tileSize*2.5);
            height=game.tileSize;
            drawSubWindow(x,y,width,height);
            //draw
            gc.drawImage(coinImage,x+10,y+10,25,25);
            int price= npc.inventory.get(itemIndex).price;

            String text=""+price;
            x=getXForAlignToRightText(text,game.tileSize*8);
            gc.fillText(text,x,y+20);

            //IF BUY an ITEM
            if(game.keyHandler.isEnterPressed()==true){
                if(npc.inventory.get(itemIndex).price>game.player.coin){
                    subState=0;
                    game.gameState=game.dialogueState;
                    currentDialogue="You need more coin to buy that";
                    drawDialogueScreen();
                } else if (game.player.inventory.size()==game.player.maxInventorySize) {
                    subState=0;
                    game.gameState=game.dialogueState;
                    currentDialogue="You can not carry any more items";
                }else{
                    game.player.coin-=npc.inventory.get(itemIndex).price;
                    game.player.inventory.add(npc.inventory.get(itemIndex));
                }
            }
        }
    }
    public void sell(){
//        //Draw player Inventory
        drawInventory(game.player,true);
//        //Draw NPC inventory
      //  drawInventory(npc,true);

        //DRAW HINT WINDOWS
        int x= game.tileSize*2;
        int y=game.tileSize*9;
        int width=game.tileSize*6;
        int height=game.tileSize*2;
        drawSubWindow(x,y,width,height);
        //drawing text
        gc.fillText("[ESC] back",x+24,y+60);


        //DRAW PLAYER COIN WINDOWs
        x= game.tileSize*12;
        y=game.tileSize*9;
        width=game.tileSize*6;
        height=game.tileSize*2;
        drawSubWindow(x, y, width, height);
        gc.setFont(Font.font("Arial", 16));
        gc.fillText("Your Coin: "+game.player.coin,x+24,y+60);


        //DRAW PRICE WINDOWS
        int itemIndex = getItemIndexOnSlot(playerSlotCol,playerSlotRow);
        if(itemIndex<game.player.inventory.size()){
            x=(int)(game.tileSize*15.5);
            y=(int)(game.tileSize*5.5);
            width=(int)(game.tileSize*2.5);
            height=game.tileSize;
            drawSubWindow(x,y,width,height);
            //draw
            gc.drawImage(coinImage,x+10,y+10,25,25);

            int price= game.player.inventory.get(itemIndex).price;

            String text=""+price;
            x=getXForAlignToRightText(text,game.tileSize*8-20);
            gc.fillText(text,x,y+20);

            //IF Sell an ITEM
            if(game.keyHandler.isEnterPressed()==true){
                if(game.player.inventory.get(itemIndex)==game.player.currentWeapon||game.player.inventory.get(itemIndex)==game.player.currentShield){
                    commandNum=0;
                    subState=0;
                    game.gameState=game.dialogueState;
                    currentDialogue="you can not sell an equiped items";
                }else{
                    game.player.inventory.remove(itemIndex);
                    game.player.coin+=price;
                }


            }
        }
    }


    public int getXForAlignToRightText(String text, int tailX) {
        Text textNode = new Text(text);
        textNode.setFont(gc.getFont());
        int length = (int)textNode.getBoundsInLocal().getWidth();
        int x = tailX - length;
        return x;
    }

    public int getXForCenteredText(String text) {
        Text textNode = new Text(text);
        textNode.setFont(gc.getFont());
        int length = (int)textNode.getBoundsInLocal().getWidth();
        int x = game.screenWidth/2 - length/2;
        return x;
    }


    public void addMessage(String text){
//        message = text;
//        messageOn = true;
        message.add(text);
        messageCounter.add(0);
    }

    public void drawMessage(){
        int messageX = game.tileSize;
        int messageY = game.tileSize * 5;

        gc.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gc.setFill(Color.WHITE);
        for(int i = 0; i < message.size(); i++){

            if(message.get(i) != null){

                gc.fillText(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);     //set the counter to the array;
                messageY += 50;

                if(messageCounter.get(i) > 180){
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

        int x = game.tileSize * 3;
        int y = game.tileSize / 2;
        int width = game.screenWidth-(game.tileSize*6);
        int height = game.tileSize * 4;
        drawSubWindow(x, y, width, height);
        gc.setFill(Color.rgb(255,255,255));
        //gc.setFont(gc.getFont().deriveFont(Font.PLAIN,28F));
        x += game.tileSize;
        y += game.tileSize;
        //System.out.println(currentDialogue);
        //to create new line
        for(String line: currentDialogue.split("\n")){
            gc.fillText(line,x,y);
            y+=40;

        }

    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = Color.rgb(0, 0, 0, .20);
        gc.setFill(c);
        gc.fillRoundRect(x,y,width,height,35,35);

        //c=Color.WHITE;
        // gc.setFill(c);
        //gc.setStroke(Color.WHITE);


        gc.setLineWidth(5); // Setting stroke width
        gc.setLineDashes(0); // Setting line dashes to 0 (solid line)
        gc.setLineCap(StrokeLineCap.ROUND); // Setting line cap to round
        gc.strokeRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
        gc.setFill(Color.rgb(255, 255, 255));
        gc.setFont(Font.font("Arial", 16));
    }

    public void drawSubWindow(int x, int y, int width, int height, Color c){
        gc.setFill(c);
        gc.fillRoundRect(x,y,width,height,35,35);

        c = Color.BLACK;
        //gc.setFill(c);
        gc.setStroke(c);


        gc.setLineWidth(5); // Setting stroke width
        gc.setLineDashes(0); // Setting line dashes to 0 (solid line)
        gc.setLineCap(StrokeLineCap.ROUND); // Setting line cap to round
        gc.strokeRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }


    public void drawWizConversationScreen(){
        System.out.println("Command num: "+commandNum);
        //System.out.println("drawWizConversationScreen draw working");

        drawDialogueScreen();
        int x=game.tileSize*15;
        int y=game.tileSize*4;
        int width=game.tileSize*3;
        int height=game.tileSize*4;
        drawSubWindow(x, y, width, height);
        //Draw Text
        gc.setFill(Color.rgb(255,255,255));
        x+=game.tileSize;
        y+=game.tileSize;
        gc.fillText("School",x,y);
        if(commandNum==0){
            System.out.println("command num 2 ");
            gc.fillText(">",x-24,y);
            if(game.keyHandler.isEnterPressed()){
                showPath(84,115);
            }
        }

        y+=game.tileSize;
        gc.fillText("Game Center",x,y);
        if(commandNum==1){
            System.out.println("command num 1 ");
            gc.fillText(">",x-24,y);
            if(game.keyHandler.isEnterPressed()){
                showPath(176,166);
            }
        }

        y+=game.tileSize;
        gc.fillText("Shop",x,y);
        if(commandNum==2){
            System.out.println("command num 2 ");
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


    public int getItemIndexOnSlot(int slotCol,int slotRow){
        int itemIndex=slotCol+(slotRow*5);
        return itemIndex;
    }
    public double getWidthOfText(String text){
        // Create a temporary Text node to measure the width
        Text textNode = new Text(text);
        textNode.setFont(gc.getFont());
        double textWidth = textNode.getBoundsInLocal().getWidth();
        return textWidth;
    }
    public double getXforAlignRightText(String text, int tailX){
        double width=getWidthOfText(text);
        double x=tailX-width;
        return x;
    }

    public void showPath(int goalCol,int goalRow) {
        npc.onPath = true;
        npc.goalCol = goalCol;
        npc.goalRow = goalRow;
        game.gameState = game.playState;
    }
}
