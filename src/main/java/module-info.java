module example.myapp1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens example.myapp1 to javafx.fxml;
    exports example.myapp1;
}