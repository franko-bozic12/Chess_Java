module hr.algebra.chess {
    requires javafx.controls;
    requires javafx.fxml;


    opens hr.algebra.chess to javafx.fxml;
    exports hr.algebra.chess;
    exports hr.algebra.chess.controllers;
    opens hr.algebra.chess.controllers to javafx.fxml;
}