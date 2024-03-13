package com.example.return_3.entity;

import com.example.return_3.main.Game;
import com.example.return_3.object.OBJ_Heart;

public class NPC_Trade extends NPC {
Game game;
    public NPC_Trade(Game game) {
        super(game);
        this.game=game;
        npc_area=area_village;
        getNPCImage();
        setDialogue();
        setItems();
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

    public void setDialogue(){
        dialogue[0]="He he, so you found me.\nI have some good stuff. Do you want to trade?";
    }

    public void setItems(){
        inventory.add(new OBJ_Heart(game));
        inventory.add(new OBJ_Heart(game));
        inventory.add(new OBJ_Heart(game));
        inventory.add(new OBJ_Heart(game));
        inventory.add(new OBJ_Heart(game));
    }
    public void speak(){
        game.gameState = game.tradeState;
        game.ui.uiMainGame.npc=this;
        game.ui.uiMainGame.currentDialogue=dialogue[dialogueIndex];
        // dialogueIndex++;
        switch (game.player.direction){
            case "up":
                direction="down";
                break;
            case "left":
                direction="right";
                break;
            case "right":
                direction="left";
                break;
            case "down":
                direction="up";
                break;
        }
    }
}
