package ClinetServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements ClientServer {
    ServerSocket serverSocket;
    Socket socket;
    private final int PORT = 8090;
    private static Server instance = null;
    public String dupa = "dupa";

    public Socket getSocket() {
        return socket;
    }

    public static Server getInstance() {
        if(instance == null){
            instance = new Server();
        }
        return instance;
    }

    private Server(){
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("SERVER");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void accept(){
        try {
           socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
