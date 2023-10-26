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
        return new ArrayList<Piece>(List.of(
                new Bishop(2, 7, images.get(0), Team.White),
                new Bishop(5, 7, images.get(1), Team.White),
                new Bishop(2, 0, images.get(2), Team.Black),
                new Bishop(5, 0, images.get(3), Team.Black),
                new King(4, 7, images.get(4), Team.White),
                new King(4, 0, images.get(5), Team.Black),
                new Knight(1, 7, images.get(6), Team.White),
                new Knight(6, 7, images.get(7), Team.White),
                new Knight(1, 0, images.get(8), Team.Black),
                new Knight(6, 0, images.get(9), Team.Black),
                new Pawn(0, 6, images.get(10), Team.White),
                new Pawn(1, 6, images.get(11), Team.White),
                new Pawn(2, 6, images.get(12), Team.White),
                new Pawn(3, 6, images.get(13), Team.White),
                new Pawn(4, 6, images.get(14), Team.White),
                new Pawn(5, 6, images.get(15), Team.White),
                new Pawn(6, 6, images.get(16), Team.White),
                new Pawn(7, 6, images.get(17), Team.White),
                new Pawn(0, 1, images.get(18), Team.Black),
                new Pawn(1, 1, images.get(19), Team.Black),
                new Pawn(2, 1, images.get(20), Team.Black),
                new Pawn(3, 1, images.get(21), Team.Black),
                new Pawn(4, 1, images.get(22), Team.Black),
                new Pawn(5, 1, images.get(23), Team.Black),
                new Pawn(6, 1, images.get(24), Team.Black),
                new Pawn(7, 1, images.get(25), Team.Black),
                new Queen(3, 7, images.get(26), Team.White),
                new Queen(3, 0, images.get(27), Team.Black),
                new Rook(0, 7, images.get(28), Team.White),
                new Rook(7, 7, images.get(29), Team.White),
                new Rook(0, 0, images.get(30), Team.Black),
                new Rook(7, 0, images.get(31), Team.Black)
        ));
    }
}
