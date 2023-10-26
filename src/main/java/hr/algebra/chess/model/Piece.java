package hr.algebra.chess.model;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.List;

public abstract class Piece {
    private final int startLocationX;
    private final int startLocationY;
    private final ImageView img;
    private final Team teamColor;

    protected Piece(int locationX, int locationY, ImageView img, Team teamColor) {
        this.startLocationX = locationX;
        this.startLocationY = locationY;
        this.img = img;
        this.teamColor = teamColor;
    }

    public abstract List<Tile> getMovableTiles(Button clickedButton);

    public int getStartLocationX() {
        return startLocationX;
    }

    public int getStartLocationY() {
        return startLocationY;
    }

    public ImageView getImg() {
        return img;
    }

    public Team getTeamColor() {
        return teamColor;
    }
}
