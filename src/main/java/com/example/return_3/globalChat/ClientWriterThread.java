package com.example.return_3.globalChat;

import com.example.return_3.Controllers.GlobalChatController;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientWriterThread implements Runnable {
    ObjectOutputStream oos;
    Socket socket;
    GlobalChatController globalChatController;

    public ClientWriterThread(Socket socket, GlobalChatController globalChatController) {
        this.socket = socket;
        this.globalChatController = globalChatController;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String msg = globalChatController.getMessage();
                if (msg != null && !msg.trim().isEmpty()) {
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(msg);
                }
                Thread.sleep(100); // Small delay to reduce CPU usage
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
