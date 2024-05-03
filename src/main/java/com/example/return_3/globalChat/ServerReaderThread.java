package com.example.return_3.globalChat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class ServerReaderThread implements Runnable{
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Socket socket;
    String  name;
    String msg;
    public ServerReaderThread(Socket socket, String  name){
        this.name=name;
        this.socket=socket;
        Thread thread= new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(true){
            try {
                ois = new ObjectInputStream(socket.getInputStream());
                msg=(String)ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println( name+ " disconnected !");
                Server.clientInfo.remove(socket);
                for(Socket socket0 :Server.clientInfo.keySet()){
                    System.out.println(Server.clientInfo.get(socket0));
                    try {
                        oos=new ObjectOutputStream(socket0.getOutputStream());
                        oos.writeObject(name+": left!");
                    } catch (IOException e2) {
                        throw new RuntimeException(e2);
                    }
                }
                e.printStackTrace();
                break;
            }
            for(Socket socket0 :Server.clientInfo.keySet()){
                if(socket0!=socket){
                    try {
                        oos=new ObjectOutputStream(socket0.getOutputStream());
                        oos.writeObject(name+": "+msg);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            String msgToSend=name+" says: "+msg;
            System.out.println(msgToSend);
            Server.prevConversation+="$"+msgToSend;
        }
    }
}