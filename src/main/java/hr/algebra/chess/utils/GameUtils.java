package hr.algebra.chess.utils;

import hr.algebra.chess.MainApplication;
import hr.algebra.chess.model.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static hr.algebra.chess.controllers.GameController.NUM_COLS;
import static hr.algebra.chess.controllers.GameController.NUM_ROWS;

public class GameUtils {
    private GameUtils() {
    }

    public static Tile findTile(Button clickedButton) {
        for(int i = 0; i < NUM_ROWS; i++)
        {
            for(int j = 0; j < NUM_COLS; j++)
            {
                if(GameBoard.gameBoard[i][j].getButton() == clickedButton)
                {
                    return GameBoard.gameBoard[i][j];
                }
            }
        }
        return null;
    }

    public static Pair<Integer, Integer> getTileLocation(Tile tile) {
        for(int i = 0; i < NUM_ROWS; i++)
        {
            for(int j = 0; j < NUM_COLS; j++)
            {
                if(GameBoard.gameBoard[i][j] == tile)
                {
                    return new Pair<>(i, j);
                }
            }
        }
        return null;
    }

    public static void replayTheLastGame() {
        GameBoard.clearBoard();
        GameBoard.setBoard();
        GameBoard.playerTurn = Team.White;

        List<GameMove> gameMoveList = XmlUtils.readGameMovesFromXmlFile();

        AtomicInteger counter = new AtomicInteger(0);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            GameMove newGameMove = gameMoveList.get(counter.get());

            Piece piece = newGameMove.getPiece();
            String location = newGameMove.getLocation();
            String oldLocation = newGameMove.getOldLocation();

            Button button = null;
            Tile selectedTile = null;

            for(int i = 0; i < NUM_ROWS; i++)
            {
                for(int j = 0; j < NUM_COLS; j++)
                {
                    if(GameBoard.gameBoard[i][j].getButton().getId().equals(location))
                    {
                        button = GameBoard.gameBoard[i][j].getButton();
                        selectedTile = findTile(button);
                    }
                }
            }

            Button buttonOld = null;
            Tile selectedTileOld = null;

            for(int i = 0; i < NUM_ROWS; i++)
            {
                for(int j = 0; j < NUM_COLS; j++)
                {
                    if(GameBoard.gameBoard[i][j].getButton().getId().equals(oldLocation))
                    {
                        buttonOld = GameBoard.gameBoard[i][j].getButton();
                        selectedTileOld = findTile(buttonOld);
                    }
                }
            }

            selectedTile.setPiece(piece);
            selectedTile.getButton().setGraphic(piece.getImg());

            selectedTileOld.setPiece(null);
            selectedTileOld.getButton().setGraphic(null);

            counter.set(counter.get() + 1);
        }));
        timeline.setCycleCount(gameMoveList.size());
        timeline.playFromStart();
    }
}
