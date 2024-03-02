package com.example.return_3.gameCenter.spaceInvaders;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class CheckedThread extends Thread{
    Image image;
    GraphicsContext gc;
 public CheckedThread (Image image, GraphicsContext gc){
     this.image=image;
     this.gc=gc;

 }
 public void run(){
     System.out.println("CheckedThread");
     Image backgroundImage =null;
     backgroundImage =image;
     gc.drawImage(backgroundImage,0,0);
 }
}
