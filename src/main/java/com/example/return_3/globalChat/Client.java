package com.example.return_3.globalChat;

import com.example.return_3.Controllers.GlobalChatController;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static ClientWriterThread clientWriterThread;

    public static void startClient(FXMLLoader loader) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        System.out.println("Connected");
        GlobalChatController globalChatController = loader.getController();
        clientWriterThread = new ClientWriterThread(socket, globalChatController);
        new ClientReaderThread(socket, globalChatController);
    }

//    public static void sendMessage(String message) {
//        clientWriterThread.sendMessage(message);
//    }
}
