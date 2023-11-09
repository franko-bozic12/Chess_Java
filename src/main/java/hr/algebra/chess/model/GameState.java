package hr.algebra.chess.model;

import java.io.Serializable;

public record GameState(Piece[][] gameBoard, Team playerTurn) implements Serializable {}
