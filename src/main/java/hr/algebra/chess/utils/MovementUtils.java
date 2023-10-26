package hr.algebra.chess.utils;

import hr.algebra.chess.model.GameBoard;
import hr.algebra.chess.model.Team;
import hr.algebra.chess.model.Tile;
import javafx.util.Pair;

import java.util.List;

public class MovementUtils {
    private MovementUtils() {
    }

    public static void checkVerticalMovement(List<Tile> movableTiles, Pair<Integer, Integer> tileLocation, Team teamColor) {
        //vertical movement down
        for(int i = tileLocation.getValue() + 1; i <= 7; i++)
        {
            if(!GameBoard.gameBoard[tileLocation.getKey()][i].isEmpty())
            {
                if(GameBoard.gameBoard[tileLocation.getKey()][i].getPiece().getTeamColor() != teamColor) {
                    movableTiles.add(GameBoard.gameBoard[tileLocation.getKey()][i]);
                }
                break;
            }
            movableTiles.add(GameBoard.gameBoard[tileLocation.getKey()][i]);
        }
        //horizontal movement up
        for(int i = tileLocation.getValue() - 1; i >= 0; i--)
        {
            if(!GameBoard.gameBoard[tileLocation.getKey()][i].isEmpty())
            {
                if(GameBoard.gameBoard[tileLocation.getKey()][i].getPiece().getTeamColor() != teamColor) {
                    movableTiles.add(GameBoard.gameBoard[tileLocation.getKey()][i]);
                }
                break;
            }
            movableTiles.add(GameBoard.gameBoard[tileLocation.getKey()][i]);
        }
    }

    public static void checkHorizontalMovement(List<Tile> movableTiles, Pair<Integer, Integer> tileLocation, Team teamColor) {
        //horizontal movement to the right
        for(int i = tileLocation.getKey() + 1; i <= 7; i++)
        {
            if(!GameBoard.gameBoard[i][tileLocation.getValue()].isEmpty())
            {
                if(GameBoard.gameBoard[i][tileLocation.getValue()].getPiece().getTeamColor() != teamColor) {
                    movableTiles.add(GameBoard.gameBoard[i][tileLocation.getValue()]);
                }
                break;
            }
            movableTiles.add(GameBoard.gameBoard[i][tileLocation.getValue()]);
        }
        //horizontal movement to the left
        for(int i = tileLocation.getKey() - 1; i >= 0; i--)
        {
            if(!GameBoard.gameBoard[i][tileLocation.getValue()].isEmpty())
            {
                if(GameBoard.gameBoard[i][tileLocation.getValue()].getPiece().getTeamColor() != teamColor) {
                    movableTiles.add(GameBoard.gameBoard[i][tileLocation.getValue()]);
                }
                break;
            }
            movableTiles.add(GameBoard.gameBoard[i][tileLocation.getValue()]);
        }
    }

    public static void checkSecondaryDiagonalMovement(List<Tile> movableTiles, Pair<Integer, Integer> tileLocation, Team teamColor) {
        int x = tileLocation.getValue();
        int y = tileLocation.getKey();
        //left down movement
        while(x - 1 >= 0 && y + 1 <= 7)
        {
            x--;
            y++;
            if(!GameBoard.gameBoard[y][x].isEmpty())
            {
                if(GameBoard.gameBoard[y][x].getPiece().getTeamColor() != teamColor) {
                    movableTiles.add(GameBoard.gameBoard[y][x]);
                }
                break;
            }
            movableTiles.add(GameBoard.gameBoard[y][x]);
        }

        x = tileLocation.getValue();
        y = tileLocation.getKey();
        //right up movement
        while(x + 1 <= 7 && y - 1 >= 0)
        {
            x++;
            y--;
            if(!GameBoard.gameBoard[y][x].isEmpty())
            {
                if(GameBoard.gameBoard[y][x].getPiece().getTeamColor() != teamColor) {
                    movableTiles.add(GameBoard.gameBoard[y][x]);
                }
                break;
            }
            movableTiles.add(GameBoard.gameBoard[y][x]);
        }
    }

    public static void checkMainDiagonalMovement(List<Tile> movableTiles, Pair<Integer, Integer> tileLocation, Team teamColor) {
        int x = tileLocation.getValue();
        int y = tileLocation.getKey();
        //left up movement
        while(x - 1 >= 0 && y - 1 >= 0)
        {
            x--;
            y--;
            if(!GameBoard.gameBoard[y][x].isEmpty())
            {
                if(GameBoard.gameBoard[y][x].getPiece().getTeamColor() != teamColor) {
                    movableTiles.add(GameBoard.gameBoard[y][x]);
                }
                break;
            }
            movableTiles.add(GameBoard.gameBoard[y][x]);
        }

        x = tileLocation.getValue();
        y = tileLocation.getKey();
        //right down movement
        while(x + 1 <= 7 && y + 1 <= 7)
        {
            x++;
            y++;
            if(!GameBoard.gameBoard[y][x].isEmpty())
            {
                if(GameBoard.gameBoard[y][x].getPiece().getTeamColor() != teamColor) {
                    movableTiles.add(GameBoard.gameBoard[y][x]);
                }
                break;
            }
            movableTiles.add(GameBoard.gameBoard[y][x]);
        }
    }
}
