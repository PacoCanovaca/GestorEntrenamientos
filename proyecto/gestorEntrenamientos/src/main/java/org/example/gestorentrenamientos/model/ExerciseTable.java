package org.example.gestorentrenamientos.model;

import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;

public class ExerciseTable {

    SimpleStringProperty  name, movementType, url, description;
    @Getter
    int id;

    public ExerciseTable(int id, String name, String movementType, String url, String description) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.movementType = new SimpleStringProperty(movementType);
        this.url = new SimpleStringProperty(url);
        this.description = new SimpleStringProperty(description);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getMovementType() {
        return movementType.get();
    }

    public SimpleStringProperty movementTypeProperty() {
        return movementType;
    }

    public String getUrl() {
        return url.get();
    }

    public SimpleStringProperty urlProperty() {
        return url;
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }
}
