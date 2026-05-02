package org.example.gestorentrenamientos.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Setter;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "exercises")
@Setter
public class ExercisesWrapperXml {

    @XmlElement(name = "movementType")
    private List<MovementTypeXml> movementTypes;

}
