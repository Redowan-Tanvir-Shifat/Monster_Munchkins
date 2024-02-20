//package com.example.return_3.main;
//
//import com.example.return_3.entity.Entity;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.image.Image;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//
//
//import java.util.ArrayList;
//
//public class UI {
//    Game game;
//     GraphicsContext gc;
//    Font arial_40, arial_80B;
//    Image heartFull,heartHalf,heartBlank,crystalFull,crystalBlank;
//    public boolean messageOn=false;
//    //    public String message="";
////    int messageCounter=0; //to set timer so that the message will be disappear after some moment
//    ArrayList<String> message = new ArrayList<>();
//    ArrayList<Integer> messageCounter = new ArrayList<>();
//
//    public boolean gameFinished=false; // if game is finished then the message will be shown
//    public String currentDialogue=""; //for setting the dialogue
//    public int commandNum=0; // this is for showing our menu specific commands
//
//    //INVENTORY
//    public int playerSlotCol=0;
//    public int playerSlotRow=0;
//    public int npcSlotCol=0;
//    public int npcSlotRow=0;
//    public int subState=0;
//
//    int counter=0;
//
//    public Entity npc;
//    //
//
//    //CONSTRUCTOR START
//
//    public UI(Game game){
//        this.game = game;
//        arial_40 = new Font("Arial",40);
//        arial_80B = new Font("Arial",80);
//        //       OBJ_Key key = new OBJ_Key(game);
//        //     keyImage =key.image;
//
//        //Create Heart Object
////        Entity heart= new OBJ_Heart(game);
////        heartFull=heart.image;
////        heartHalf=heart.image2;
////        heartBlank=heart.image3;
////        Entity crystal=new OBJ_ManaCrystal(game);
////        crystalFull=crystal.image;
////        crystalBlank=crystal.image2;
//    }
//    public void addMessage(String text){
////        message = text;
////        messageOn = true;
//        message.add(text);
//        messageCounter.add(0);
//    }
//    public void draw(GraphicsContext gc){
//        //we did this because we need to use this gc in other methods also
//        this.gc=gc;
//
//        gc.setFont(arial_40);
//        gc.setFill(Color.WHITE);
//        //TITLE STATE
//        if(game.gameState==game.titleState){
//            drawTitleScreen();
//        }
//        //PLAY STATE
//        if(game.gameState == game.playState){
//            //Do PlayState stuff
//            //Drawing Heart for player life
//            drawPlayerLife();
//            drawMana();
//            drawMessage();
//        }
//        //PAUSE STATE
//        if(game.gameState == game.pauseState){
//            //Do pauseState stuff
//            drawPauseScreen();
//            drawPlayerLife();
//            drawMana();
//        }
//        //DIALOGUE STATE
//        if(game.gameState == game.dialogueState){
//            drawPlayerLife();
//            drawMana();
//            drawDialogueScreen();
//        }
//        //CHARACTER STATE
//        if(game.gameState == game.characterState){
//            drawCharacterScreen();
//            drawInventory(game.player,true);
//        }
//        //OPTION STATE
//        if(game.gameState == game.optionState){
//            drawOptionsScreen();
//
//        }
//        //GAME OVER STATE
//        if(game.gameState == game.gameOverState){
//            drawGameOverScreen();
//
//        }
//        //Transition STATE
//        if(game.gameState == game.transitionState){
//            drawTransition();
//        }
//        //Transition STATE
//        if(game.gameState == game.tradeState){
//            drawTradeScreen();
//        }
//
//    }
//    public void drawPlayerLife(){
//        //game.player.life=4;
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
//
//    }
//    public void drawMana(){
//        int x=(game.tileSize/2)-5;
//        int y=(int)(game.tileSize*1.5);
//        int i=0;
//        while(i<game.player.maxMana){
//            gc.drawImage(crystalBlank,x,y);
//            i++;
//            x+=35;
//        }
//        //Draw Mana;
//        x=(game.tileSize/2)-5;
//        y=(int)(game.tileSize*1.5);
//        i=0;
//        while(i<game.player.mana){
//            gc.drawImage(crystalFull,x,y);
//            i++;
//            x+=35;
//        }
//    }
//    public void drawMessage(){
//        int messageX=game.tileSize;
//        int messageY=game.tileSize*4;
//        gc.setFont(gc.getFont().deriveFont(Font.BOLD,32F));
//        for(int i=0;i<message.size();i++){
//            if(message.get(i)!=null){
//
//                gc.setColor(Color.black);
//                gc.drawString(message.get(i),messageX+2,messageY+2);
//
//                gc.setColor(Color.white);
//                gc.drawString(message.get(i),messageX,messageY);
//                int counter= messageCounter.get(i)+1;
//                messageCounter.set(i,counter); //set the counter to the array;
//                messageY+=50;
//                if(messageCounter.get(i)>180){
//                    message.remove(i);
//                    messageCounter.remove(i);
//                }
//            }
//        }
//    }
//    public void drawTitleScreen(){
//        //to color Background [THough default is black but by chance if we want to change the background color]
//        gc.setColor(new Color(0,0,0));
//        gc.fillRect(0,0,game.screenWidth,game.screenHeight);
//
//        //TITLE NAME
//        gc.setFont(gc.getFont().deriveFont(Font.BOLD, 70F));
//        String text= "Blue Boy Adventure";
//        int x = getXforCenterText(text);
//        int y = game.tileSize*3;
//
//        //SHADOW
//        gc.setColor(Color.gray);
//        gc.drawString(text, x+5, y+5);
//        //MAIN COLOR
//        gc.setColor(Color.white);
//        gc.drawString(text, x, y);
//
//        //BLUE BOY IMAGE
//        x=game.screenWidth/2-(game.tileSize);
//        y+=game.tileSize*2;
//
//        gc.drawImage(game.player.down1, x, y,game.tileSize*2, game.tileSize*2,null);
//
//        //MENU
//        //changing font for menu
//        gc.setFont(gc.getFont().deriveFont(Font.BOLD, 40F));
//        text="New Game";
//        x= getXforCenterText(text);
//        y+=game.tileSize*3.5;
//        gc.drawString(text,x,y);
//        if(commandNum==0){
//            gc.drawString(">",x-game.tileSize,y);
//        }
//        text="Load Game";
//        x= getXforCenterText(text);
//        y+=game.tileSize;
//        gc.drawString(text,x,y);
//        if(commandNum==1){
//            gc.drawString(">",x-game.tileSize,y);
//        }
//        text="Quit Game";
//        x= getXforCenterText(text);
//        y+=game.tileSize;
//        gc.drawString(text,x,y);
//        if(commandNum==2){
//            gc.drawString(">",x-game.tileSize,y);
//        }
//
//    }
//    public void drawPauseScreen(){
//        gc.setFont(gc.getFont().deriveFont(Font.PLAIN,80F));
//        String text = "PAUSED";
//        int x=getXforCenterText(text);
//        int y =game.screenHeight/2 ;
//        //now drawing
//        gc.drawString(text, x, y);
//    }
//
//    public void drawDialogueScreen(){
//        //  CREATING A DIALOGUE WINDOW
//        //set parameter for window
//        int x=game.tileSize*3;
//        int y=game.tileSize/2;
//        int width=game.screenWidth-(game.tileSize*6);
//        int height=game.tileSize*4;
//        drawSubWindow(x, y, width, height);
//        gc.setFont(gc.getFont().deriveFont(Font.PLAIN,28F));
//        x+= game.tileSize;
//        y+= game.tileSize;
//
//        //to create new line
//        for(String line: currentDialogue.split("\n")){
//            gc.drawString(line,x,y);
//            y+=40;
//        }
//
//    }
//
//    public void drawCharacterScreen(){
//        //CREATE A FRAME
//        final int frameX=game.tileSize;
//        final int frameY=game.tileSize;
//        final int frameWidth=game.tileSize*5;
//        final int frameHeight=(game.tileSize*10)+20;
//        drawSubWindow(frameX,frameY,frameWidth,frameHeight);
//
//        //text
//        gc.setColor(Color.WHITE);
//        gc.setFont(gc.getFont().deriveFont(Font.PLAIN,30F));
//
//        int textX= frameX+20;
//        int textY= frameY+ game.tileSize;
//        final int lineHeight=36;
//
//        //NAMES
//        gc.drawString("Level",textX,textY);
//        textY+=lineHeight;
//        gc.drawString("Life",textX,textY);
//        textY+=lineHeight;
//
//        gc.drawString("Mana",textX,textY);
//        textY+=lineHeight;
//
//        gc.drawString("Strength",textX,textY);
//        textY+=lineHeight;
//        gc.drawString("Dexterity",textX,textY);
//        textY+=lineHeight;
//        gc.drawString("Attack",textX,textY);
//        textY+=lineHeight;
//        gc.drawString("Defense",textX,textY);
//        textY+=lineHeight;
//        gc.drawString("Exp",textX,textY);
//        textY+=lineHeight;
//        gc.drawString("Next Level",textX,textY);
//        textY+=lineHeight;
//        gc.drawString("Coin",textX,textY);
//        textY+=lineHeight+10;
//        gc.drawString("Weapon",textX,textY);
//        textY+=lineHeight+15;
//        gc.drawString("Sheild",textX,textY);
//
//        int tailX=(frameX+ frameWidth)-30;
//        //Reset TextY
//        textY=frameY+game.tileSize;
//
//        String value;
//        //drawing start
//        value= String.valueOf(game.player.level);
//        textX=getXforAlignRightText(value,tailX);
//        gc.drawString(value,textX,textY);
//        textY+=lineHeight;
//
//        value= String.valueOf(game.player.life +"/"+game.player.maxLife);
//        textX=getXforAlignRightText(value,tailX);
//        gc.drawString(value,textX,textY);
//        textY+=lineHeight;
//
//        value= String.valueOf(game.player.mana +"/"+game.player.maxMana);
//        textX=getXforAlignRightText(value,tailX);
//        gc.drawString(value,textX,textY);
//        textY+=lineHeight;
//
//        value= String.valueOf(game.player.strength);
//        textX=getXforAlignRightText(value,tailX);
//        gc.drawString(value,textX,textY);
//        textY+=lineHeight;
//        value= String.valueOf(game.player.dexterity);
//        textX=getXforAlignRightText(value,tailX);
//        gc.drawString(value,textX,textY);
//        textY+=lineHeight;
//        value= String.valueOf(game.player.attack);
//        textX=getXforAlignRightText(value,tailX);
//        gc.drawString(value,textX,textY);
//        textY+=lineHeight;
//        value= String.valueOf(game.player.defense);
//        textX=getXforAlignRightText(value,tailX);
//        gc.drawString(value,textX,textY);
//        textY+=lineHeight;
//        value= String.valueOf(game.player.exp);
//        textX=getXforAlignRightText(value,tailX);
//        gc.drawString(value,textX,textY);
//        textY+=lineHeight;
//        value= String.valueOf(game.player.nextLevelExp);
//        textX=getXforAlignRightText(value,tailX);
//        gc.drawString(value,textX,textY);
//        textY+=lineHeight;
//        value= String.valueOf(game.player.coin);
//        textX=getXforAlignRightText(value,tailX);
//        gc.drawString(value,textX,textY);
//        textY+=lineHeight;
//        //Drawing Weapon Image
//        gc.drawImage(game.player.currentWeapon.down1,tailX-game.tileSize,textY-24,null);
//        textY+=game.tileSize;
//
//        gc.drawImage(game.player.currentShield.down1,tailX-game.tileSize,textY-24,null);
//
//        textY+=lineHeight;
//
//    }
//
//    public void drawInventory(Entity entity, boolean cursor){
//        //Initial value
//        int frameX=0;
//        int frameY=0;
//        int frameWidth=0;
//        int frameHeight=0;
//        int slotCol=0;
//        int slotRow=0;
//
//        if(entity==game.player){
//            //FRAME
//            frameX=game.tileSize*9;
//            frameY=game.tileSize;
//            frameWidth=game.tileSize*6;
//            frameHeight=game.tileSize*5;
//            slotCol=playerSlotCol;
//            slotRow=playerSlotRow;
//        }else{
//            //FRAME
//            frameX=game.tileSize*2;
//            frameY=game.tileSize;
//            frameWidth=game.tileSize*6;
//            frameHeight=game.tileSize*5;
//            slotCol=npcSlotCol;
//            slotRow=npcSlotRow;
//        }
//
//        //Draw the frame
//        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
//
//        //SLOT
//        final int slotXStart=frameX+20;
//        final int slotYStart=frameY+20;
//        int slotX=slotXStart;
//        int slotY=slotYStart;
//        int slotSize=game.tileSize+3;
//
//        //Draw Players new Items
//        for(int i=0; i<entity.inventory.size(); i++){
//            //EQUIP CURSOR
//            if(entity.inventory.get(i)==entity.currentWeapon||entity.inventory.get(i)==entity.currentShield){
//                gc.setColor(new Color(240,190,90));
//                gc.fillRoundRect(slotX,slotY,game.tileSize,game.tileSize,10,10);
//            }
//
//            gc.drawImage(entity.inventory.get(i).down1, slotX, slotY,null);
//            slotX+=slotSize;
//            if(i==4 ||i==9||i==14){
//                slotY+=slotSize;
//                //reset SlotX
//                slotX=slotXStart;
//            }
//        }
//
//        //CURSOR
//        if(cursor==true){
//            int cursorX=slotXStart+(slotSize*slotCol);
//            int cursorY=slotYStart+(slotSize*slotRow);
//            int cursorWidth=game.tileSize;
//            int cursorHeight=game.tileSize;
//            //Draw cursor
//            gc.setColor(Color.white);
//            gc.setStroke(new BasicStroke(3));
//            gc.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight,10,10);
//
//            //Another subwindow to show the description
//            //DESCRIPTION FRAME
//            int dFrameX= frameX;
//            int dFrameY= frameY+ frameHeight;
//            int dFrameWidth= frameWidth;
//            int dFrameHeight= game.tileSize*3;
//            //DRAW DESCRIPTION TEXT
//            int textX=dFrameX+20;
//            int textY=dFrameY+game.tileSize;
//
//            gc.setFont(gc.getFont().deriveFont(28F));
//            int itemIndex=getItemIndexOnSlot(slotCol,slotRow);
//            if(itemIndex<entity.inventory.size()) {
//                drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);
//                for(String line : entity.inventory.get(itemIndex).description.split("\n")) {
//
//                    gc.drawString(line, textX,textY);
//                    textY+=32;
//                }
//            }
//        }
//
//    }
//    public void drawOptionsScreen(){
//        gc.setColor(Color.white);
//        gc.setFont(gc.getFont().deriveFont(28F));
//
//        //SUB WINDOW
//        int frameX= game.tileSize*4;
//        int frameY= game.tileSize;
//        int frameWidth= game.tileSize*8;
//        int frameHeight= game.tileSize*10;
//        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
//
//        switch (subState){
//            case 0:optionTop(frameX,frameY); break;
//            case 1:options_control(frameX,frameY); break;
//            case 2:options_endGameConfirmation(frameX,frameY); break;
//        }
//        game.keyHandler.enterPressed=false;
//    }
//    public void optionTop(int frameX,int frameY){
//        int textX;
//        int textY;
//
//        //TITLE
//        String text="Option";
//        textX =getXforCenterText(text);
//        textY=frameY+game.tileSize;
//        gc.drawString(text,textX,textY);
//
//        //FullScreen
//        textX = frameX+game.tileSize;
//        textY += game.tileSize*2;
//        gc.drawString("Full Screen",textX,textY);
//        if(commandNum==0){
//            gc.drawString(">",textX-25,textY);
//            if(game.keyHandler.enterPressed==true){
//                if(game.fullScreenOn==false){
//                    game.fullScreenOn=true;
//                }else if(game.fullScreenOn==true){
//                    game.fullScreenOn=false;
//                }
//            }
//        }
//
//        //MUSIC
//        textY+=game.tileSize;
//        gc.drawString("Music",textX,textY);
//        if(commandNum==1){
//            gc.drawString(">",textX-25,textY);
//        }
//        //SE
//        textY+=game.tileSize;
//        gc.drawString("SE",textX,textY);
//        if(commandNum==2){
//            gc.drawString(">",textX-25,textY);
//        }
//        //CONTROLS
//        textY+=game.tileSize;
//        gc.drawString("Control",textX,textY);
//        if(commandNum==3){
//            gc.drawString(">",textX-25,textY);
//            if(game.keyHandler.enterPressed==true){
//                subState=1;
//                commandNum=0;
//            }
//        }
//        //END GAME
//        textY+=game.tileSize;
//        gc.drawString("End Game",textX,textY);
//        if(commandNum==4){
//            gc.drawString(">",textX-25,textY);
//            if(game.keyHandler.enterPressed==true){
//                subState=2;
//                commandNum=0;
//            }
//        }
//        //Back to Window
//        textY+=game.tileSize*2;
//        gc.drawString("Back",textX,textY);
//        if(commandNum==5){
//            gc.drawString(">",textX-25,textY);
//            if(game.keyHandler.enterPressed==true){
//                game.gameState=game.playState;
//                commandNum=0;
//            }
//        }
//
//        //Full Screen CheckBox
//        textX=frameX+(int)(game.tileSize*4.5);
//        textY=frameY+game.tileSize*2+24;
//        gc.setStroke(new BasicStroke(3));
//        gc.drawRect(textX,textY,24,24);
//        if(game.fullScreenOn==true){
//            gc.fillRect(textX,textY,24,24);
//        }
//
//        //MUSIC VOLUME
//        textY += game.tileSize;
//        gc.drawRect(textX,textY,120,24); //120/5=24
//        int volumeWidth = 24* game.music.volumeScale;
//        gc.fillRect(textX,textY,volumeWidth,24);
//
//        //SE VOLUME
//        textY += game.tileSize;
//        gc.drawRect(textX,textY,120,24);//120/5=24
//        volumeWidth = 24* game.se.volumeScale;
//        gc.fillRect(textX,textY,volumeWidth,24);
//
//        //Save the settings
//        game.config.saveConfig();
//    }
//
//
//    public void options_control(int frameX,int frameY){
//        int textX;
//        int textY;
//
//        //Title
//        String text= "Controls";
//        textX=getXforCenterText(text);
//        textY=frameY+game.tileSize;
//        gc.drawString(text,textX,textY);
//
//        textX=frameX+game.tileSize;
//        textY+=game.tileSize;
//
//        gc.drawString("Move",textX,textY); textY+=game.tileSize;
//        gc.drawString("Confirm/Attack",textX,textY); textY+=game.tileSize;
//        gc.drawString("Shoot/Cast",textX,textY); textY+=game.tileSize;
//        gc.drawString("Character Screen",textX,textY); textY+=game.tileSize;
//        gc.drawString("Pause",textX,textY); textY+=game.tileSize;
//        gc.drawString("Options",textX,textY); textY+=game.tileSize;
//
//        textX=frameX+game.tileSize*6;
//        textY=frameY+game.tileSize*2;
//        gc.drawString("WASD",textX,textY); textY+=game.tileSize;
//        gc.drawString("ENTER",textX,textY); textY+=game.tileSize;
//        gc.drawString("F",textX,textY); textY+=game.tileSize;
//        gc.drawString("C",textX,textY); textY+=game.tileSize;
//        gc.drawString("P",textX,textY); textY+=game.tileSize;
//        gc.drawString("ESC",textX,textY); textY+=game.tileSize;
//
//        //BACK
//        textX=frameX+game.tileSize;
//        textY=frameY+game.tileSize*9;
//        gc.drawString("Back",textX,textY);
//        if(commandNum==0){
//            gc.drawString(">",textX-25,textY);
//            if(game.keyHandler.enterPressed==true){
//                subState=0;
//                commandNum=3;
//            }
//        }
//    }
//    public void options_endGameConfirmation(int frameX,int frameY){
//        int textX = frameX+ game.tileSize;
//        int textY = frameY+ game.tileSize*3;
//        currentDialogue= "Quit the game and \nreturn to the title Screen?";
//        for(String line:currentDialogue.split("\n")){
//            gc.drawString(line,textX,textY);
//            textY+=40;
//        }
//
//        //YES
//        String text = "Yes";
//        textX=getXforCenterText(text);
//        textY+=game.tileSize*3;
//        gc.drawString(text,textX,textY);
//        if(commandNum==0){
//            gc.drawString(">",textX-25,textY);
//            if(game.keyHandler.enterPressed==true){
//                subState=0;
//                game.gameState=game.titleState;
//            }
//        }
//
//        //NO
//        text = "No";
//        textX=getXforCenterText(text);
//        textY+=game.tileSize;
//        gc.drawString(text,textX,textY);
//        if(commandNum==1){
//            gc.drawString(">",textX-25,textY);
//            if(game.keyHandler.enterPressed==true){
//                subState=0;
//                commandNum=4;
//
//            }
//        }
//
//    }
//
//    public void drawGameOverScreen(){
//        gc.setColor(new Color(0,0,0,150));
//        gc.fillRect(0, 0, game.screenWidth, game.screenHeight);
//        int x;
//        int y;
//        String text;
//        gc.setFont(gc.getFont().deriveFont(Font.BOLD,110f));
//
//        text="Game Over";
//        //SHADOW
//        gc.setColor(Color.BLACK);
//        x=getXforCenterText(text);
//        y=game.tileSize*4;
//        gc.drawString(text,x,y);
//        //MAIN
//        gc.setColor(Color.WHITE);
//        gc.drawString(text,x-4,y-4);
//        //RETRY
//        gc.setFont(gc.getFont().deriveFont(Font.BOLD,50f));
//        text="Retry";
//        x=getXforCenterText(text);
//        y+= game.tileSize*4;
//        gc.drawString(text,x,y);
//        if(commandNum==0){
//            gc.drawString(">",x-40,y);
//        }
//        //Back to the Title Screen
//        text="Quit";
//        x=getXforCenterText(text);
//        y+= 55;
//        gc.drawString(text,x,y);
//        if(commandNum==1){
//            gc.drawString(">",x-40,y);
//        }
//
//    }
//
//    public void drawTransition(){
//        counter++;
//        gc.setColor(new Color(0,0,0,counter*5));
//        gc.fillRect(0,0,game.screenWidth,game.screenHeight);
//        if(counter==50){
//            counter=0;
//            game.gameState=game.playState;
//            game.currentMap= game.eHandler.tempMap;
//            game.player.worldX=game.tileSize*game.eHandler.tempCol;
//            game.player.worldY=game.tileSize*game.eHandler.tempRow;
//            game.eHandler.previousEventX=game.player.worldX;
//            game.eHandler.previousEventY=game.player.worldY;
//        }
//    }
//
//    public void drawTradeScreen(){
//        switch (subState){
//            case 0:trade_select();break;
//            case 1:trade_buy();break;
//            case 2:trade_sell();break;
//        }
//        game.keyHandler.enterPressed=false;
//    }
//    public void trade_select(){
//        drawDialogueScreen();
//        //Draw Window
//        int x=game.tileSize*15;
//        int y=game.tileSize*4;
//        int width=game.tileSize*3;
//        int height=(int)(game.tileSize*3.5);
//        drawSubWindow(x, y, width, height);
//
//        //DRAW TEXT
//        x+= game.tileSize;
//        y+= game.tileSize;
//        gc.drawString("Buy",x,y);
//        if(commandNum==0){
//            gc.drawString(">",x-24,y);
//            if(game.keyHandler.enterPressed==true){
//                subState=1;
//            }
//        }
//        y+= game.tileSize;
//        gc.drawString("Sell",x,y);
//        if(commandNum==1){
//            gc.drawString(">",x-24,y);
//            if(game.keyHandler.enterPressed==true){
//                subState=2;
//            }
//        }
//
//        y+= game.tileSize;
//        gc.drawString("Leave",x,y);
//        if(commandNum==2){
//            gc.drawString(">",x-24,y);
//            if(game.keyHandler.enterPressed==true){
//                commandNum=0;
//                game.gameState=game.dialogueState;
//                currentDialogue="Come again bitch!";
//            }
//        }
//
//
//
//
//    }
//    public void trade_buy(){
//        //Draw player Inventory
//        drawInventory(game.player,false);
//        //Draw NPC inventory
//        drawInventory(npc,true);
//
//        //DRAW HINT WINDOWS
//        int x= game.tileSize*2;
//        int y=game.tileSize*9;
//        int width=game.tileSize*6;
//        int height=game.tileSize*2;
//        drawSubWindow(x,y,width,height);
//        //drawing text
//        gc.drawString("[ESC] back",x+24,y+60);
//
//
//        //DRAW PLAYER COIN WINDOWs
//        x= game.tileSize*12;
//        y=game.tileSize*9;
//        width=game.tileSize*6;
//        height=game.tileSize*2;
//        drawSubWindow(x,y,width,height);
//        gc.drawString("Your Coin: "+game.player.coin,x+24,y+60);
//
//
//        //DRAW PRICE WINDOWS
//        int itemIndex = getItemIndexOnSlot(npcSlotCol,npcSlotRow);
//        if(itemIndex<npc.inventory.size()){
//            x=(int)(game.tileSize*5.5);
//            y=(int)(game.tileSize*5.5);
//            width=(int)(game.tileSize*2.5);
//            height=game.tileSize;
//            drawSubWindow(x,y,width,height);
//        }
//    }
//    public void trade_sell(){}
//
//    public int getItemIndexOnSlot(int slotCol,int slotRow){
//        int itemIndex=slotCol+(slotRow*5);
//        return itemIndex;
//    }
//
//    public void drawSubWindow(int x, int y, int width, int height){
//        Color c= new Color(0,0,0,210);
//        gc.setColor(c);
//        gc.fillRoundRect(x,y,width,height,35,35);
//
//        c=new Color(255,255,255);
//        gc.setColor(c);
//        gc.setStroke(new BasicStroke(5));
//        gc.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
//    }
//
//
//    //creating a method to get the middle position of x co ordinate as dependign the text
//    public int getXforCenterText(String text){
//        int length= (int)gc.getFontMetrics().getStringBounds(text,gc).getWidth();
//        return game.screenWidth/2-length/2;
//    }
//    public int getXforAlignRightText(String text,int tailX){
//        int length= (int)gc.getFontMetrics().getStringBounds(text,gc).getWidth();
//        int x=tailX-length;
//        return x;
//    }
//}
