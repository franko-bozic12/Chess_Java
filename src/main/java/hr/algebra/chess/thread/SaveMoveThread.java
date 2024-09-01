package hr.algebra.chess.thread;

import hr.algebra.chess.model.GameMove;

public class SaveMoveThread extends GameMoveThread implements Runnable {

    private GameMove gameMove;

    public SaveMoveThread(GameMove gameMove) {
        this.gameMove = gameMove;
    }

    @Override
    public void run() {
        saveMove(gameMove);
    }
}
