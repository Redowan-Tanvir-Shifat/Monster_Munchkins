package com.example.return_3.npc;

import com.example.return_3.entity.NPC;
import com.example.return_3.main.Game;
import com.example.return_3.object.OBJ_ChatBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;



public class NPC_Universal extends NPC {
    Game game;
    String name;
    public NPC_Universal(Game game, String name) {
        super(game);
        this.game=game;
        this.name=name;
//        collisionOn=false;

        //type= type_npc;
        speed=(int) (65*game.targetFrameTime);        getNPCImage();
        setDialogue();
    }
    public void getNPCImage(){
        int size=game.tileSize-8;
        up1=loadImage( "/npc/npc_"+name+"_up_1.png",size,size);
        up2= loadImage("/npc/npc_"+name+"_up_2.png",size,size);
        down1= loadImage("/npc/npc_"+name+"_down_1.png",size,size);
        down2= loadImage("/npc/npc_"+name+"_down_2.png",size,size);
        left1=loadImage ("/npc/npc_"+name+"_left_1.png",size,size);
        left2= loadImage("/npc/npc_"+name+"_left_2.png",size,size);
        right1= loadImage("/npc/npc_"+name+"_right_1.png",size,size);
        right2= loadImage("/npc/npc_"+name+"_right_2.png",size,size);
    }
    //set dialogue
    public void speak(){
//        super.speak();
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

