package ClinetServer;


import com.mygdx.game.Chessboard.Chessboard2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public interface ClientServer {
    default void send(Socket socket, Chessboard2 chessboard) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(chessboard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    default Chessboard2 receive(Socket socket) {
        Chessboard2 chessboard = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            chessboard = ((Chessboard2) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return chessboard;
    }
}
