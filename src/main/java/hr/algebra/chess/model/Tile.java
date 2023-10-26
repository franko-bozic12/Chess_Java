package hr.algebra.chess.model;

import javafx.scene.control.Button;

public class Tile {
    private Piece piece;
    private final Button button;

    public Tile(Button button) {
        this.button = button;
        this.piece = null;
    }

    public Button getButton() {
        return button;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isEmpty() {
        return piece == null;
    }
}
