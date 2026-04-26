package org.example.gestorentrenamientos.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import org.example.gestorentrenamientos.dao.ExerciseDao;
import org.example.gestorentrenamientos.data.DataSet;
import org.example.gestorentrenamientos.model.Exercise;
import org.example.gestorentrenamientos.model.ExerciseTable;

public class ExerciseEditorController implements Initializable {

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private TextField urlTextField;

    private int exerciseId;

    private ExerciseTable exercise;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instances();
        actions();
    }

    private void instances() {
        typeComboBox.setItems(DataSet.getMovementTypesNames());
    }

    private void actions() {
        saveBtn.setOnAction(event -> {
            Alert alert;
            ExerciseDao exerciseDao = new ExerciseDao();
            try {
                exerciseDao.updateExercise(new Exercise(exerciseId, DataSet.getMovementTypeIdByName(typeComboBox.getSelectionModel().getSelectedItem()), nameTextField.getText(), urlTextField.getText(), descriptionTextArea.getText()));
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ejercicio editado");
                alert.setContentText("El ejercicio ha sido modificado en la base de datos");
                alert.show();
                Stage stage = (Stage) saveBtn.getScene().getWindow();
                stage.close();
            } catch (SQLException e) {
                if (nameTextField.getText().equalsIgnoreCase("")) {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Nombre no introducido");
                    alert.setContentText("No has introducido el nombre del ejercicio.");
                    alert.show();
                } else if (existsName(nameTextField.getText())) {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Nombre ya existente");
                    alert.setContentText("El nombre que has introducido ya existe.");
                    alert.show();
                } else if (typeComboBox.getSelectionModel().getSelectedItem() == null) {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Tipo no seleccionado");
                    alert.setContentText("Debes seleccionar un tipo de movimiento para el ejercicio.");
                    alert.show();
                } else {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error desconocido");
                    alert.setContentText("Se ha producido un error sin identificar a la hora de editar el ejercicio.");
                    alert.show();
                }
            }
        });
    }

    public void setExercise(ExerciseTable exercise) {
        this.exercise = exercise;
        exerciseId = exercise.getId();
        nameTextField.setText(exercise.getName());
        urlTextField.setText(exercise.getUrl());
        descriptionTextArea.setText(exercise.getDescription());
    }

    private boolean existsName(String name) {
        for (Exercise exercise : DataSet.getExercises()) {
            if (exerciseId == exercise.getId()) {
                continue;
            }
            if (exercise.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

}