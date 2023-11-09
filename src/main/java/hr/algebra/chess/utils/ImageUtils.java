package hr.algebra.chess.utils;

import hr.algebra.chess.MainApplication;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImageUtils {
    private ImageUtils() {
    }

    public static List<ImageView> prepareImages() {
        Image bishopBlackImg = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/pieces/BishopBlack.png")));
        Image bishopWhiteImg = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/pieces/BishopWhite.png")));
        Image kingBlackImg = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/pieces/KingBlack.png")));
        Image kingWhiteImg = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/pieces/KingWhite.png")));
        Image knightBlackImg = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/pieces/KnightBlack.png")));
        Image knightWhiteImg = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/pieces/KnightWhite.png")));
        Image pawnBlackImg = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/pieces/PawnBlack.png")));
        Image pawnWhiteImg = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/pieces/PawnWhite.png")));
        Image queenBlackImg = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/pieces/QueenBlack.png")));
        Image queenWhiteImg = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/pieces/QueenWhite.png")));
        Image rookBlackImg = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/pieces/RookBlack.png")));
        Image rookWhiteImg = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/pieces/RookWhite.png")));

        return new ArrayList<>(List.of(
                new ImageView(bishopWhiteImg),
                new ImageView(bishopWhiteImg),
                new ImageView(bishopBlackImg),
                new ImageView(bishopBlackImg),
                new ImageView(kingWhiteImg),
                new ImageView(kingBlackImg),
                new ImageView(knightWhiteImg),
                new ImageView(knightWhiteImg),
                new ImageView(knightBlackImg),
                new ImageView(knightBlackImg),
                new ImageView(pawnWhiteImg),
                new ImageView(pawnWhiteImg),
                new ImageView(pawnWhiteImg),
                new ImageView(pawnWhiteImg),
                new ImageView(pawnWhiteImg),
                new ImageView(pawnWhiteImg),
                new ImageView(pawnWhiteImg),
                new ImageView(pawnWhiteImg),
                new ImageView(pawnBlackImg),
                new ImageView(pawnBlackImg),
                new ImageView(pawnBlackImg),
                new ImageView(pawnBlackImg),
                new ImageView(pawnBlackImg),
                new ImageView(pawnBlackImg),
                new ImageView(pawnBlackImg),
                new ImageView(pawnBlackImg),
                new ImageView(queenWhiteImg),
                new ImageView(queenBlackImg),
                new ImageView(rookWhiteImg),
                new ImageView(rookWhiteImg),
                new ImageView(rookBlackImg),
                new ImageView(rookBlackImg)
        ));
    }
}
