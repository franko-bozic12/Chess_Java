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

public class Pawn extends Piece {
    public Pawn(int locationX, int locationY, ImageView img, String imgString, Team teamColor) {
        super(locationX, locationY, img, imgString, teamColor);
    }

    @Override
    public List<Tile> getMovableTiles(Button selectedFigure) {
        Pair<Integer, Integer> tileLocation = getTileLocation(findTile(selectedFigure));
        List<Tile> movableTiles = new ArrayList<>();
        Team teamColor = Objects.requireNonNull(findTile(selectedFigure)).getPiece().getTeamColor();

        int colorIndex = 1;
        if(teamColor == Team.Black)
        {
            colorIndex = -1;
        }

        if(Objects.requireNonNull(tileLocation).getKey() == getStartLocationY()
                && Objects.requireNonNull(tileLocation).getValue() == getStartLocationX()) {
            checkNormalMovement(movableTiles, colorIndex, tileLocation, teamColor);
            checkStartMovement(movableTiles, colorIndex, tileLocation);
        }
        else {
            checkNormalMovement(movableTiles, colorIndex, tileLocation, teamColor);
        }

        return movableTiles;
    }

    private void checkStartMovement(List<Tile> movableTiles, int colorIndex, Pair<Integer, Integer> tileLocation) {
        Tile twoStepsAhead = GameBoard.gameBoard[tileLocation.getKey() - 2 * colorIndex][tileLocation.getValue()];
        Tile oneStepAhead = GameBoard.gameBoard[tileLocation.getKey() - colorIndex][tileLocation.getValue()];

        if (twoStepsAhead.getButton().getGraphic() == null && oneStepAhead.getButton().getGraphic() == null) {
            movableTiles.add(twoStepsAhead);
        }
    }

    private void checkNormalMovement(List<Tile> movableTiles, int colorIndex, Pair<Integer, Integer> tileLocation, Team teamColor) {
        Tile oneStepAhead = GameBoard.gameBoard[tileLocation.getKey() - colorIndex][tileLocation.getValue()];

        if (oneStepAhead.getButton().getGraphic() == null) {
            movableTiles.add(oneStepAhead);
        }

        int[] colOffsets = {1, -1};

        for (int colOffset : colOffsets) {
            int targetCol = tileLocation.getValue() + colOffset;

            if (targetCol >= 0 && targetCol <= 7) {
                Tile adjacentTile = GameBoard.gameBoard[tileLocation.getKey() - colorIndex][targetCol];

                if (adjacentTile.getButton().getGraphic() != null && adjacentTile.getPiece().getTeamColor() != teamColor) {
                    movableTiles.add(adjacentTile);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Pawn";
    }
}
