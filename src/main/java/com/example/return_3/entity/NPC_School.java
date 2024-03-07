package com.example.return_3.entity;

import com.example.return_3.main.Game;
import com.example.return_3.object.OBJ_ChatBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;

public class NPC_School extends NPC{
    Game game;
    public NPC_School(Game game) {
        super(game);
        this.game=game;
        type= type_npc;
        getNPCImage();
    }
    public void getNPCImage(){
        up1=loadImage( "/npc/oldman_up_1.png",game.tileSize,game.tileSize);
        up2= loadImage("/npc/oldman_up_2.png",game.tileSize,game.tileSize);
        down1= loadImage("/npc/oldman_down_1.png",game.tileSize,game.tileSize);
        down2= loadImage("/npc/oldman_down_2.png",game.tileSize,game.tileSize);
        left1=loadImage ("/npc/oldman_left_1.png",game.tileSize,game.tileSize);
        left2= loadImage("/npc/oldman_left_2.png",game.tileSize,game.tileSize);
        right1= loadImage("/npc/oldman_right_1.png",game.tileSize,game.tileSize);
        right2= loadImage("/npc/oldman_right_2.png",game.tileSize,game.tileSize);
    }

    public void speak(){
        super.speak();
        onPath=true;
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
