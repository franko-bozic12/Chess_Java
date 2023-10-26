package hr.algebra.chess.utils;

import hr.algebra.chess.model.GameBoard;
import hr.algebra.chess.model.Team;
import hr.algebra.chess.model.Tile;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.util.Pair;

import java.util.List;
import java.util.Objects;

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
}
