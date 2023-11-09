package hr.algebra.chess.model;

import hr.algebra.chess.MainApplication;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class Piece implements Serializable {
    private final int startLocationX;
    private final int startLocationY;
    private transient ImageView img;
    private final String imgString;
    private final Team teamColor;

    protected Piece(int locationX, int locationY, ImageView img, String imgString, Team teamColor) {
        this.startLocationX = locationX;
        this.startLocationY = locationY;
        this.img = img;
        this.imgString = imgString;
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

    public String getImgString() {
        return imgString;
    }

    public void setImage() {
       img = new ImageView(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream(imgString))));
    }
}
