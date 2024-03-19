package com.example.return_3.npc;

import com.example.return_3.entity.NPC;
import com.example.return_3.main.Game;
import com.example.return_3.object.OBJ_ChatBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class NPC_Pig extends NPC {
    Game game;
    public NPC_Pig(Game game) {
        super(game);
        this.game=game;
        //type= type_npc;
        npc_area=area_village;
        speed=(int) (65*game.targetFrameTime);
        getNPCImage();
        setDialogue();
    }
    public void getNPCImage(){
        int size=game.tileSize-8;
        up1=loadImage( "/npc/npc_pig_up_1.png",size,size);
        up2= loadImage("/npc/npc_pig_up_2.png",size,size);
        down1= loadImage("/npc/npc_pig_down_1.png",size,size);
        down2= loadImage("/npc/npc_pig_down_2.png",size,size);
        left1=loadImage ("/npc/npc_pig_left_1.png",size,size);
        left2= loadImage("/npc/npc_pig_left_2.png",size,size);
        right1= loadImage("/npc/npc_pig_right_1.png",size,size);
        right2= loadImage("/npc/npc_pig_right_2.png",size,size);
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
