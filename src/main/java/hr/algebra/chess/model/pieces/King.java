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

public class King extends Piece {
    public King(int locationX, int locationY, ImageView img, String imgString, Team teamColor) {
        super(locationX, locationY, img, imgString, teamColor);
    }

    @Override
    public List<Tile> getMovableTiles(Button selectedFigure) {
        Pair<Integer, Integer> tileLocation = getTileLocation(findTile(selectedFigure));
        List<Tile> movableTiles = new ArrayList<>();
        Team teamColor = Objects.requireNonNull(findTile(selectedFigure)).getPiece().getTeamColor();

        checkKingMovement(movableTiles, Objects.requireNonNull(tileLocation), teamColor);

        return movableTiles;
    }

    private void checkKingMovement(List<Tile> movableTiles, Pair<Integer, Integer> tileLocation, Team teamColor) {
        int x = tileLocation.getValue();
        int y = tileLocation.getKey();

        int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };
        int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };

        for (int i = 0; i < 8; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newX >= 0 && newX <= 7 && newY >= 0 && newY <= 7) {
                Tile targetTile = GameBoard.gameBoard[newY][newX];
                if (targetTile.isEmpty() || targetTile.getPiece().getTeamColor() != teamColor) {
                    movableTiles.add(targetTile);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "King";
    }
}
