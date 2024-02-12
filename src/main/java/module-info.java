module com.example.return_3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.return_3 to javafx.fxml;
    exports com.example.return_3.main;
}