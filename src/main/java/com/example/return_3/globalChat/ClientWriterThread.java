package com.example.return_3.globalChat;

import com.example.return_3.Controllers.GlobalChatController;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientWriterThread implements Runnable {
    private ObjectOutputStream oos;
    private BlockingQueue<String> messageQueue;

    public ClientWriterThread(Socket socket, GlobalChatController globalChatController) throws IOException {
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.messageQueue = new LinkedBlockingQueue<>();
        new Thread(this).start();
    }

    public void sendMessage(String message) {
        try {
            messageQueue.put(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = messageQueue.take();
                oos.writeObject(message);
                oos.flush();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
