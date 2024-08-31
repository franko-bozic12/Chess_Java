package hr.algebra.chess.utils;

import hr.algebra.chess.model.GameState;
import hr.algebra.chess.model.NetworkConfiguration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkingUtils {

    public static void sendGameBoardToServer(GameState gameBoard) {
        try (Socket clientSocket = new Socket(NetworkConfiguration.HOST_NAME,
                NetworkConfiguration.SERVER_PORT))
        {
            System.err.println("Client is connecting to " + clientSocket.getInetAddress() + ":" +clientSocket.getPort());
            sendSerializableRequest(clientSocket, gameBoard);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void sendGameBoardToClient(GameState gameBoard) {
        try (Socket clientSocket = new Socket(NetworkConfiguration.HOST_NAME,
                NetworkConfiguration.CLIENT_PORT))
        {
            System.err.println("Server is connecting to " + clientSocket.getInetAddress() + ":" +clientSocket.getPort());
            sendSerializableRequest(clientSocket, gameBoard);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static void sendSerializableRequest(Socket client, GameState gameBoard) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
        oos.writeObject(gameBoard);
        System.out.println("Game board sent to the server!");
    }

}
