package org.example.gestorentrenamientos.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.gestorentrenamientos.model.Exercise;

public class DataSet {

    private static ObservableList<Exercise> exercises = FXCollections.observableArrayList();;

    private DataSet() {}

    public static ObservableList<Exercise> getExercises() {
        return exercises;
    }

    public static void setExercises(ObservableList<Exercise> exercises) {
        DataSet.exercises = exercises;
    }
}
