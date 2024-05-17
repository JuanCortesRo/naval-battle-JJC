module com.example.navalbattlejjc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.navalbattlejjc to javafx.fxml;
    exports com.example.navalbattlejjc;
}