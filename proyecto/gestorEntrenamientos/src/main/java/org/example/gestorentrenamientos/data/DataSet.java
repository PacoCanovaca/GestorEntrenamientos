package org.example.gestorentrenamientos.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.gestorentrenamientos.dao.ExerciseDao;
import org.example.gestorentrenamientos.model.Exercise;
import org.example.gestorentrenamientos.model.MovementTypes;

import java.sql.SQLException;
import java.util.Arrays;

public class DataSet {

    // DataSet de prueba antes de conectar con la base de datos
    private static ObservableList<Exercise> exercises = FXCollections.observableArrayList(/*
            new Exercise(1, "Dominada", "https://www.youtube.com/watch?v=un6HKZo2Mhs", "Subiremos de manera explosiva y aguantaremos la fase de bajada. Al bajar es importante no perder la tensión muscular por completo ni descolgar los hombros.", "Tracción vertical"),
            new Exercise(2, "Jalon al pecho", "https://www.youtube.com/watch?v=ShqtJk37UPM", "Tracciona llevando la barra hacia zona del esternón. Los hombros irán hacia atras (simulando sacar pecho), evitaremos que los hombros roten hacia dentro al tracionar. En la fase excéntrica iremos lento y controlado, sin que perdamos tensión al final del rango.", "Tracción vertical"),
            new Exercise(3, "Remo en cuerda", "https://www.youtube.com/watch?v=vRsHJtFbyDY", "", "Tracción vertical"),
            new Exercise(4, "Face pull", "https://www.youtube.com/watch?v=0Po47vvj9g4&pp=ygUJZmFjZSBwdWxs", "Lleva las manos hacia la altura de la frente, llevando los codos hacia arriba y hacia atras.", "Tracción horizontal"),
            new Exercise(5, "Remo con alcance desde abajo", "https://www.youtube.com/watch?v=I5r_tzXW0S4", "", "Tracción horizontal"),
            new Exercise(6, "Renegade Row ", "https://www.youtube.com/watch?v=G1AcX8Y_byg", "Realizar una flexion estricta  (si no sale puedes apoyar las rodillas para este gesto) y despues tracionamos llevando la mano hacia la cadera manteniendo el torso estable, sin que rote.", "Tracción horizontal"),
            new Exercise(7, "Elevaciones laterales", "https://www.youtube.com/watch?v=n_r-ROwHkdA", "Eleva las mancuernas ligeramente en diagonal, lateralmente y hacia delante. Intentanta que los codos esten extendidos o ligeramente flexionados. Y manten las manos todo y hombro siempre en linea.", "Empuje vertical"),
            new Exercise(8, "Press militar barra", "https://www.youtube.com/watch?v=_aISMzimYEA", "", "Empuje vertical"),
            new Exercise(9, "Push press", "https://www.youtube.com/watch?v=d0d0TWaiukA", "", "Empuje vertical"),
            new Exercise(10, "Aperturas con mancuernas", "https://www.youtube.com/watch?v=z8juzhSsFKU", "", "Empuje horizontal"),
            new Exercise(11, "Flexiones", "https://www.youtube.com/watch?v=mm6_WcoCVTA&list=PLyqKj7LwU2RuyZwWCIiDHuFZGN11QW3Ff&index=26", "", "Empuje horizontal"),
            new Exercise(12, "Press banca con barra", "https://www.youtube.com/watch?v=SCVCLChPQFY", "", "Empuje horizontal")*/
    );

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

    public static void setExercises(ObservableList<Exercise> exercises) {
        DataSet.exercises = exercises;
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
