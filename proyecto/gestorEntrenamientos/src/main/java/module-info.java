module org.example.gestorentrenamientos {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    requires jakarta.xml.bind;

    opens org.example.gestorentrenamientos to javafx.fxml;
    exports org.example.gestorentrenamientos;

    opens org.example.gestorentrenamientos.model to javafx.fxml;
    exports org.example.gestorentrenamientos.model;

    opens org.example.gestorentrenamientos.controller to javafx.fxml;
    exports org.example.gestorentrenamientos.controller;

    opens org.example.gestorentrenamientos.dto to jakarta.xml.bind;
}