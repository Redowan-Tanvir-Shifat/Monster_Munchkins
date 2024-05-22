package com.example.return_3.globalChat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    public static ConcurrentHashMap<Socket, String> clientInfo = new ConcurrentHashMap<>();
    static String prevConversation = "";

    public static void main(String[] args) throws IOException {
        InetAddress localhost = InetAddress.getByName("localhost");
        ServerSocket serverSocket = new ServerSocket(8080, 0, localhost);
        System.out.println("Server is started on ip: " + localhost.getHostAddress() + " and port: " + serverSocket.getLocalPort());
        int i = 0;
        while (true) {
            Socket socket = serverSocket.accept();
            String name = "Client-" + i;
            clientInfo.put(socket, name);
            System.out.println(name + " joined");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("You are " + name);
            oos.writeObject(prevConversation);
            new ServerReaderThread(socket, name);
            i++;
        }
    }
}
