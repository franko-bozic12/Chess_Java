package hr.algebra.chess.model.pieces;

import hr.algebra.chess.model.GameBoard;
import hr.algebra.chess.model.Piece;
import hr.algebra.chess.model.Team;
import hr.algebra.chess.model.Tile;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static hr.algebra.chess.utils.GameUtils.findTile;
import static hr.algebra.chess.utils.GameUtils.getTileLocation;

public class Knight extends Piece {
    public Knight(int locationX, int locationY, ImageView img, String imgString, Team teamColor) {
        super(locationX, locationY, img, imgString, teamColor);
    }

    @Override
    public List<Tile> getMovableTiles(Button selectedFigure) {
        Pair<Integer, Integer> tileLocation = getTileLocation(findTile(selectedFigure));
        List<Tile> movableTiles = new ArrayList<>();
        Team teamColor = Objects.requireNonNull(findTile(selectedFigure)).getPiece().getTeamColor();

        int[][] knightMoves = {
                {-2, -1}, {-2, 1},
                {-1, -2}, {-1, 2},
                {1, -2}, {1, 2},
                {2, -1}, {2, 1}
        };

        for (int[] move : knightMoves) {
            int newRow = Objects.requireNonNull(tileLocation).getKey() + move[0];
            int newCol = tileLocation.getValue() + move[1];

            if (isValidPosition(newRow, newCol)) {
                Tile destinationTile = GameBoard.gameBoard[newRow][newCol];

                if (destinationTile.isEmpty() || isOpponentPiece(destinationTile, teamColor)) {
                    movableTiles.add(destinationTile);
                }
            }
        }

        return movableTiles;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row <= 7 && col >= 0 && col <= 7;
    }

    private boolean isOpponentPiece(Tile tile, Team teamColor) {
        return tile.getPiece().getTeamColor() != teamColor;
    }

    @Override
    public String toString() {
        return "Knight";
    }
}
