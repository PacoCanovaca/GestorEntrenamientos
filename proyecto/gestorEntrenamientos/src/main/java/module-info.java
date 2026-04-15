module org.example.gestorentrenamientos {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens org.example.gestorentrenamientos to javafx.fxml;
    exports org.example.gestorentrenamientos;
}