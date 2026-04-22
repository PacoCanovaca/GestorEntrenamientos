package org.example.gestorentrenamientos.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.gestorentrenamientos.database.DBConnection;
import org.example.gestorentrenamientos.database.DBExerciseScheme;
import org.example.gestorentrenamientos.model.Exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void insertExercise(Exercise exercise) throws SQLException {
        connection = DBConnection.getConnection();
        String query = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?,?,?,?)", DBExerciseScheme.TAB_NAME, DBExerciseScheme.COL_ID_MOVEMENT, DBExerciseScheme.COL_NAME, DBExerciseScheme.COL_URL, DBExerciseScheme.COL_DESCRIPTION);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, exercise.getMovementType());
        preparedStatement.setString(2, exercise.getName());
        preparedStatement.setString(3, exercise.getUrl());
        preparedStatement.setString(4, exercise.getDescription());
        preparedStatement.executeUpdate();
    }

    public void deleteExercise(int id) throws SQLException {
        connection = DBConnection.getConnection();
        String query = String.format("DELETE FROM %s WHERE %s = ?", DBExerciseScheme.TAB_NAME, DBExerciseScheme.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public void updateExercise(Exercise exercise) throws SQLException{
        connection = DBConnection.getConnection();
        String query = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?", DBExerciseScheme.TAB_NAME, DBExerciseScheme.COL_ID_MOVEMENT, DBExerciseScheme.COL_NAME, DBExerciseScheme.COL_URL, DBExerciseScheme.COL_DESCRIPTION);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, exercise.getMovementType());
        preparedStatement.setString(2, exercise.getName());
        preparedStatement.setString(3, exercise.getUrl());
        preparedStatement.setString(4, exercise.getDescription());
        preparedStatement.executeUpdate();
    }

    public ObservableList<Exercise> readExercises() throws SQLException {
        connection = DBConnection.getConnection();
        ObservableList<Exercise> exercises = FXCollections.observableArrayList();
        String query = String.format("SELECT * FROM %s", DBExerciseScheme.TAB_NAME);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(DBExerciseScheme.COL_ID);
            int movementType = resultSet.getInt(DBExerciseScheme.COL_ID_MOVEMENT);
            String name = resultSet.getString(DBExerciseScheme.COL_NAME);
            String url = resultSet.getString(DBExerciseScheme.COL_URL);
            String description = resultSet.getString(DBExerciseScheme.COL_DESCRIPTION);
            exercises.add(new Exercise(id, movementType, name, url, description));
        }
        return exercises;
    }

}
