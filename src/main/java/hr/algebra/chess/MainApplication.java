package hr.algebra.chess;

import hr.algebra.chess.chat.RemoteChatService;
import hr.algebra.chess.chat.RemoteChatServiceImpl;
import hr.algebra.chess.controllers.GameController;
import hr.algebra.chess.model.GameState;
import hr.algebra.chess.model.NetworkConfiguration;
import hr.algebra.chess.model.PlayerType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class MainApplication extends Application {

    private static Stage mainStage;

    public static PlayerType playerLoggedIn;

    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Chess");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/icon.png"))));
        stage.setScene(scene);
        stage.show();*/

        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 750);
        mainStage.setTitle(playerLoggedIn.name());
        mainStage.setResizable(false);
        mainStage.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/icon.png"))));
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        String playerName = args[0];

        if(PlayerType.SERVER.name().equals(playerName)) {
            playerLoggedIn = PlayerType.SERVER;
            new Thread(MainApplication::startServer).start();
        }
        else if(PlayerType.CLIENT.name().equals(playerName)){
            playerLoggedIn = PlayerType.CLIENT;
            new Thread(MainApplication::startClient).start();
        }
        else if(PlayerType.SINGLE_PLAYER.name().equals(playerName)) {
            playerLoggedIn = PlayerType.SINGLE_PLAYER;
        }

        System.out.println("The player name is " + playerLoggedIn);

        launch();
    }

    public static void startServer() {
        startRmiServer();
        acceptRequestsOnPort(NetworkConfiguration.SERVER_PORT);
    }

    public static void startClient() {
        //acceptServerRequests();
        acceptRequestsOnPort(NetworkConfiguration.CLIENT_PORT);
    }

    public static void startRmiServer() {
        try {
            Registry registry = LocateRegistry.createRegistry(NetworkConfiguration.RMI_PORT);
            RemoteChatService remoteChatService = new RemoteChatServiceImpl();
            RemoteChatService skeleton = (RemoteChatService)
                    UnicastRemoteObject.exportObject(remoteChatService,
                            NetworkConfiguration.RANDOM_PORT_HINT);
            registry.rebind(RemoteChatService.REMOTE_CHAT_OBJECT_NAME, skeleton);
            System.err.println("Object registered in RMI registry");
        }
        catch(RemoteException ex) {
            ex.printStackTrace();
        }
    }

    private static void acceptRequestsOnPort(Integer port) {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.err.println("Server listening on port: " + serverSocket.getLocalPort());
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.err.println("Client connected from port: " + clientSocket.getPort());
                new Thread(() ->  processSerializableClient(clientSocket)).start();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processSerializableClient(Socket clientSocket) {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());){
            GameState gameBoard = (GameState) ois.readObject();
            Platform.runLater(() -> GameController.restoreGameBoard(gameBoard));
            System.out.println("Game board received from the client!");
            oos.writeObject("Confirmed that the game board has been received!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}