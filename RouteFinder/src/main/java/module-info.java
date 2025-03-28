module com.example.routefinder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.routefinder to javafx.fxml;
    exports com.example.routefinder;
}