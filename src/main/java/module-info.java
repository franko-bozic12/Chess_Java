module hr.algebra.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.xml;


    opens hr.algebra.chess to javafx.fxml;
    exports hr.algebra.chess;
    exports hr.algebra.chess.controllers;
    exports hr.algebra.chess.chat to java.rmi;
    opens hr.algebra.chess.controllers to javafx.fxml;
}