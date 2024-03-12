package com.example.return_3.npc;

import com.example.return_3.entity.NPC;
import com.example.return_3.main.Game;
import com.example.return_3.object.OBJ_ChatBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class NPC_OldMan2 extends NPC{
    Game game;
    public NPC_OldMan2(Game game) {
        super(game);
        this.game=game;
        //type= type_npc;
        speed=(int) (65*game.targetFrameTime);        getNPCImage();
        setDialogue();
    }
    public void getNPCImage(){
        up1=loadImage( "/npc/npc_oldman_up_1.png",game.tileSize,game.tileSize);
        up2= loadImage("/npc/npc_oldman_up_2.png",game.tileSize,game.tileSize);
        down1= loadImage("/npc/npc_oldman_down_1.png",game.tileSize,game.tileSize);
        down2= loadImage("/npc/npc_oldman_down_2.png",game.tileSize,game.tileSize);
        left1=loadImage ("/npc/npc_oldman_left_1.png",game.tileSize,game.tileSize);
        left2= loadImage("/npc/npc_oldman_left_2.png",game.tileSize,game.tileSize);
        right1= loadImage("/npc/npc_oldman_right_1.png",game.tileSize,game.tileSize);
        right2= loadImage("/npc/npc_oldman_right_2.png",game.tileSize,game.tileSize);
    }
    //set dialogue
    public void setDialogue(){
        dialogue[0]="Hello Lad! \nWelcome to the Mysterious Island";
        dialogue[1]="In this Island you are now facing lots \nof exciting things";
        dialogue[2]="Let me help you to take you a tour\ni'm a bit too old for taking an adventure though.";
        dialogue[3]="Where you want to go?";
    }
    public void speak(){
        super.speak();
    }
    public void draw(GraphicsContext gc){
        super.draw(gc);

        if(chatOnStatus==true){
            int screenX= worldX-game.player.worldX + game.player.screenX;
            int screenY= worldY-game.player.worldY + game.player.screenY;
            Image image= null;
            //System.out.println("draw method working");
            if(chatNum==1){
                image= new OBJ_ChatBox(game).down1;
            } else if (chatNum==2) {
                image= new OBJ_ChatBox(game).up1;
            }else if(chatNum==3){
                image= new OBJ_ChatBox(game).left1;
            } else if (chatNum==4) {
                image= new OBJ_ChatBox(game).right1;
            }
            gc.drawImage(image,screenX,screenY-(game.tileSize/2));
        }
    }

}
