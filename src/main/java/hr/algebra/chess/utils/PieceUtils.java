package hr.algebra.chess.utils;

import hr.algebra.chess.model.*;
import hr.algebra.chess.model.pieces.*;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class PieceUtils {
    private PieceUtils() {
    }

    public static List<Piece> generatePieces(List<ImageView> images) {
        return new ArrayList<>(List.of(
                new Bishop(2, 7, images.get(0), "images/pieces/BishopWhite.png", Team.White),
                new Bishop(5, 7, images.get(1), "images/pieces/BishopWhite.png", Team.White),
                new Bishop(2, 0, images.get(2), "images/pieces/BishopBlack.png", Team.Black),
                new Bishop(5, 0, images.get(3), "images/pieces/BishopBlack.png", Team.Black),
                new King(4, 7, images.get(4), "images/pieces/KingWhite.png", Team.White),
                new King(4, 0, images.get(5), "images/pieces/KingBlack.png", Team.Black),
                new Knight(1, 7, images.get(6), "images/pieces/KnightWhite.png", Team.White),
                new Knight(6, 7, images.get(7), "images/pieces/KnightWhite.png", Team.White),
                new Knight(1, 0, images.get(8), "images/pieces/KnightBlack.png", Team.Black),
                new Knight(6, 0, images.get(9), "images/pieces/KnightBlack.png", Team.Black),
                new Pawn(0, 6, images.get(10), "images/pieces/PawnWhite.png", Team.White),
                new Pawn(1, 6, images.get(11), "images/pieces/PawnWhite.png", Team.White),
                new Pawn(2, 6, images.get(12), "images/pieces/PawnWhite.png", Team.White),
                new Pawn(3, 6, images.get(13), "images/pieces/PawnWhite.png", Team.White),
                new Pawn(4, 6, images.get(14), "images/pieces/PawnWhite.png", Team.White),
                new Pawn(5, 6, images.get(15), "images/pieces/PawnWhite.png", Team.White),
                new Pawn(6, 6, images.get(16), "images/pieces/PawnWhite.png", Team.White),
                new Pawn(7, 6, images.get(17), "images/pieces/PawnWhite.png", Team.White),
                new Pawn(0, 1, images.get(18), "images/pieces/PawnBlack.png", Team.Black),
                new Pawn(1, 1, images.get(19), "images/pieces/PawnBlack.png", Team.Black),
                new Pawn(2, 1, images.get(20), "images/pieces/PawnBlack.png", Team.Black),
                new Pawn(3, 1, images.get(21), "images/pieces/PawnBlack.png", Team.Black),
                new Pawn(4, 1, images.get(22), "images/pieces/PawnBlack.png", Team.Black),
                new Pawn(5, 1, images.get(23), "images/pieces/PawnBlack.png", Team.Black),
                new Pawn(6, 1, images.get(24), "images/pieces/PawnBlack.png", Team.Black),
                new Pawn(7, 1, images.get(25), "images/pieces/PawnBlack.png", Team.Black),
                new Queen(3, 7, images.get(26), "images/pieces/QueenWhite.png", Team.White),
                new Queen(3, 0, images.get(27), "images/pieces/QueenBlack.png", Team.Black),
                new Rook(0, 7, images.get(28), "images/pieces/RookWhite.png", Team.White),
                new Rook(7, 7, images.get(29), "images/pieces/RookWhite.png", Team.White),
                new Rook(0, 0, images.get(30), "images/pieces/RookBlack.png", Team.Black),
                new Rook(7, 0, images.get(31), "images/pieces/RookBlack.png", Team.Black)
        ));
    }
}
