package com.example.return_3.globalChat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    public static ConcurrentHashMap<Socket, ObjectOutputStream> clientOutputStreams = new ConcurrentHashMap<>();
    public static String prevConversation = "";

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server is started on port: " + serverSocket.getLocalPort());
        int clientNumber = 0;

        while (true) {
            Socket socket = serverSocket.accept();
            String clientName = "Client-" + clientNumber++;
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            clientOutputStreams.put(socket, oos);
            System.out.println(clientName + " joined");
            oos.writeObject("You are " + clientName);
            oos.writeObject(prevConversation);
            new Thread(new ServerReaderThread(socket, clientName)).start();
        }
    }
}