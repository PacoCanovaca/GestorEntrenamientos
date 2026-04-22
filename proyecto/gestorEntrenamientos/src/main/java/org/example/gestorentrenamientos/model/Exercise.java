package org.example.gestorentrenamientos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Exercise {

    private int id, movementType;
    private String name, url, description;

    public Exercise(String name, String url, String description, int movementType) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.movementType = movementType;
    }
}
