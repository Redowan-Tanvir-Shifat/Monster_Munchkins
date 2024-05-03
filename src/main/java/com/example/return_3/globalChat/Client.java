package com.example.return_3.globalChat;

import com.example.return_3.Controllers.GlobalChatController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.Socket;
public class Client {
    public static Thread clientWriterThread;
    static GlobalChatController globalChatController;
    public static void startCLient(FXMLLoader loader) throws IOException {
        Socket socket = new Socket("localhost",8080);
        System.out.println("Connected");
        globalChatController=loader.getController();
        clientWriterThread= new Thread(new ClientWriterThread(socket,globalChatController));
        new ClientReaderThread(socket,globalChatController);
    }


}