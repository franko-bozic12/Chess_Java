package hr.algebra.chess.model;

import hr.algebra.chess.utils.ImageUtils;
import hr.algebra.chess.utils.PieceUtils;
import java.util.List;

public class GameBoard {
    public static Tile[][] gameBoard;
    public static Team playerTurn;

    public static void setBoard() {
        List<Piece> pieces = PieceUtils.generatePieces(ImageUtils.prepareImages());

        for (Piece piece : pieces) {
            gameBoard[piece.getStartLocationY()][piece.getStartLocationX()].getButton().setGraphic(piece.getImg());
            gameBoard[piece.getStartLocationY()][piece.getStartLocationX()].setPiece(piece);

        }
    }

    public static void clearBoard() {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                gameBoard[i][j].setPiece(null);
                gameBoard[i][j].getButton().setGraphic(null);
            }
        }
    }
}
