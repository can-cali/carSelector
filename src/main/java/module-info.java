module com.example.carselector {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.carselector to javafx.fxml;
    exports com.example.carselector;
}
