package org.example.gestorentrenamientos.dto;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovementTypeXml {
    @XmlAttribute
    private int id;
    private String name, description;

    @XmlElementWrapper(name = "exercises")
    @XmlElement(name = "exercise")
    private List<ExerciseXml> exercises;

}
