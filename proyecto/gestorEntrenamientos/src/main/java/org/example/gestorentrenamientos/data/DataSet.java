package org.example.gestorentrenamientos.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.gestorentrenamientos.dao.ExerciseDao;
import org.example.gestorentrenamientos.model.Exercise;
import org.example.gestorentrenamientos.model.MovementTypes;

import java.sql.SQLException;
import java.util.Arrays;

public class DataSet {

    private static ObservableList<Exercise> exercises = FXCollections.observableArrayList();

    private static MovementTypes[] movementTypes = MovementTypes.values();

    private static ObservableList<String> movementTypesNames = FXCollections.observableArrayList(Arrays.stream(movementTypes).map(MovementTypes::getName).toList());

    private DataSet() {}

    public static void importDataSet() throws SQLException {
        ExerciseDao exerciseDao = new ExerciseDao();
        exercises = exerciseDao.readExercises();
    }

    public static ObservableList<Exercise> getExercises() {
        return exercises;
    }

    public static ObservableList<String> getMovementTypesNames() {
        return movementTypesNames;
    }

    public static int getMovementTypeIdByName(String name) {
        int id = 0;
        for (MovementTypes movementType : movementTypes) {
            if (name.equalsIgnoreCase(movementType.getName())){
                id = movementType.getId();
                break;
            }
        }
        return id;
    }

    public static String getMovementTypeNameById(int id) {
        String name = null;
        for (MovementTypes movementType : movementTypes) {
            if (id == movementType.getId()){
                name = movementType.getName();
                break;
            }
        }
        return name;
    }
}
