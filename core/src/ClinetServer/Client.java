package ClinetServer;

import java.io.IOException;
import java.net.Socket;

public class Client implements ClientServer {
    private final String HOST = "localhost";
    private final int PORT = 8090;
    Socket socket;
    private static Client instance = null;

    private Client(){

    }
    public Socket getSocket() {
        return socket;
    }
    public static Client getInstance() {
        if(instance == null){
            instance = new Client();
        }
        return instance;
    }
    public boolean connect(){
        try {
            socket = new Socket(HOST,PORT);
        } catch (IOException e) {
            return false;
        }
        System.out.println("CLIENT");
        return true;
    }
}
