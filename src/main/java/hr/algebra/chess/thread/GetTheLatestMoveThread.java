package hr.algebra.chess.thread;


import hr.algebra.chess.model.GameMove;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class GetTheLatestMoveThread extends GameMoveThread implements Runnable {
    private Label theLastMoveLabel;

    public GetTheLatestMoveThread(Label theLastMoveLabel) {
        this.theLastMoveLabel = theLastMoveLabel;
    }

    @Override
    public void run() {

        while(true) {
            Platform.runLater(() -> {
                GameMove theLastGameMove = getTheLastMove();
                if(theLastGameMove != null)
                {
                    theLastMoveLabel.setText("The last move: "
                            + theLastGameMove.getPiece().toString() + " to "
                            + theLastGameMove.getLocation() + " - "
                            + theLastGameMove.getDateTime().toString());
                }
            });

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}