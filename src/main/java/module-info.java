module com.example.return_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.return_3.main to javafx.fxml;
    exports com.example.return_3.main;
}