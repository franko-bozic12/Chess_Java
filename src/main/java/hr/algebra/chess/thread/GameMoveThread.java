package hr.algebra.chess.thread;

import hr.algebra.chess.model.GameMove;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class GameMoveThread {

    private static Boolean fileAccessInProgress = false;

    private static final String MOVES_FILE = "files/moves.dat";

    public synchronized void saveMove(GameMove newGameMove) {

        System.out.println("Save move in progress...");

        while(fileAccessInProgress) {
            try {
                System.out.println("Waiting because someone else reads the file...");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Continue with saving the last move...");

        fileAccessInProgress = true;

        List<GameMove> gameMoveList = getAllMoves();
        gameMoveList.add(newGameMove);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MOVES_FILE))) {
            oos.writeObject(gameMoveList);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        fileAccessInProgress = false;

        System.out.println("Saving the last move done...");

        notifyAll();
    }

    private List<GameMove> getAllMoves() {

        System.out.println("Getting all the moves from the file...");

        System.out.println("Getting all the moves started...");

        List<GameMove> gameMoveList = new ArrayList<>();

        if(Files.exists(Path.of(MOVES_FILE))) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MOVES_FILE))) {
                gameMoveList.addAll((List<GameMove>) ois.readObject());
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("Getting the last move finished...");

        return gameMoveList;
    }

    public synchronized GameMove getTheLastMove() {

        while(fileAccessInProgress) {
            try {
                System.out.println("Waiting because a saving of a new move is in progress...");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        fileAccessInProgress = true;

        List<GameMove> gameMoveList = getAllMoves();

        fileAccessInProgress = false;

        notifyAll();

        if(gameMoveList.isEmpty()) {
            return null;
        }

        return gameMoveList.get(gameMoveList.size() - 1);
    }

}
