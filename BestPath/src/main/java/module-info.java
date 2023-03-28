module com.example.bestpath {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    opens com.example.bestpath to javafx.fxml;
    exports com.example.bestpath;
    exports com.example.bestpath.Graph;
    opens com.example.bestpath.Graph to javafx.fxml;
    exports com.example.bestpath.Printers;
    opens com.example.bestpath.Printers to javafx.fxml;
}