package org.example.gestorentrenamientos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Exercise {

    private int id;
    private String name, url, description, movementType;

}
