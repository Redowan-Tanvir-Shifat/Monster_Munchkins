package com.example.return_3.globalChat;

import com.example.return_3.Controllers.GlobalChatController;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientReaderThread implements Runnable {
    ObjectInputStream ois;
    Socket socket;
    GlobalChatController globalChatController;
    public ClientReaderThread(Socket socket,GlobalChatController globalChatController) {
        this.socket=socket;
        Thread thread= new Thread(this);
        thread.start();
       this. globalChatController=globalChatController;
    }
    @Override
    public void run() {

        try {
            ois = new ObjectInputStream(socket.getInputStream());
            String msg=(String)ois.readObject();
            System.out.println(msg);
            String prevMsg=(String)ois.readObject();
            if(prevMsg!=null){
                String []prevConversation=prevMsg.split("\\$");
                for(int i=prevConversation.length-10;i<prevConversation.length;i++){
                    System.out.println(prevConversation[i]);
                }
            }
            while(true){
                ois = new ObjectInputStream(socket.getInputStream());
                String msg2=(String)ois.readObject();
                System.out.println(msg2);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}