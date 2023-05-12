module csc180final {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires java.sql;

    opens csc180final to javafx.fxml;

    exports csc180final;
}
