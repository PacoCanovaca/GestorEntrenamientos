package org.example.gestorentrenamientos.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;

public class ExerciseTable {

    SimpleStringProperty  name, url, description;
    SimpleIntegerProperty movementType;
    @Getter
    int id;

    public ExerciseTable(int id, String name, int movementType, String url, String description) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.movementType = new SimpleIntegerProperty(movementType);
        this.url = new SimpleStringProperty(url);
        this.description = new SimpleStringProperty(description);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public int getMovementType() {
        return movementType.get();
    }

    public SimpleIntegerProperty movementTypeProperty() {
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
