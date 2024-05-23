package com.example.return_3.globalChat;

import com.example.return_3.Controllers.GlobalChatController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientReaderThread implements Runnable {
    private ObjectInputStream ois;
    private GlobalChatController globalChatController;

    public ClientReaderThread(Socket socket, GlobalChatController globalChatController) throws IOException {
        this.ois = new ObjectInputStream(socket.getInputStream());
        this.globalChatController = globalChatController;
        new Thread(this).start();
    }


    @Override
    public void run() {
        try {
            String initialMessage = (String) ois.readObject();
            System.out.println(initialMessage);
            globalChatController.updateChat(initialMessage);

            String prevMessages = (String) ois.readObject();
            if (prevMessages != null && !prevMessages.isEmpty()) {
                String[] prevConversation = prevMessages.split("\\$");
                for (int i = Math.max(0, prevConversation.length - 11); i < prevConversation.length; i++) {
                    globalChatController.updateChat(prevConversation[i]);
                }
            }

            while (true) {
                String msg = (String) ois.readObject();
                globalChatController.updateChat(msg);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
