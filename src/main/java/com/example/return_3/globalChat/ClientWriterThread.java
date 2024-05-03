package com.example.return_3.globalChat;

import com.example.return_3.Controllers.GlobalChatController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientWriterThread implements Runnable {
    ObjectOutputStream oos;
    Socket socket;
    GlobalChatController globalChatController;
    public ClientWriterThread(Socket socket, GlobalChatController globalChatController) {
        this.socket=socket;
//        Thread thread= new Thread(this);
//        thread.start();
        this. globalChatController=globalChatController;

    }
    @Override
    public void run() {
//        while(true){
//            Scanner scanner= new Scanner(System.in);
//            String msg=scanner.nextLine();
        String msg = globalChatController.textField.getText();
            try {
                oos= new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//        }
    }
}