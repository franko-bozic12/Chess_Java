package hr.algebra.chess.model;


import java.io.Serializable;
import java.time.LocalDateTime;

public class GameMove implements Serializable {

    private Piece piece;
    private String location;
    private LocalDateTime dateTime;

    public GameMove(Piece piece, String location, LocalDateTime dateTime) {
        this.piece = piece;
        this.location = location;
        this.dateTime = dateTime;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}