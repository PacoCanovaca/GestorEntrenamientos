package org.example.gestorentrenamientos.controller;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
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
        typeComboBox.setItems(DataSet.getMovementTypes());
    }

    private void actions() {
        saveBtn.setOnAction(event -> {
            Alert alert;
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
                ObservableList<Exercise> exercises = DataSet.getExercises();
                for (Exercise exercise : exercises) {
                    if (exercise.getId() == exerciseId) {
                        exercise.setName(nameTextField.getText());
                        exercise.setUrl(urlTextField.getText());
                        exercise.setDescription(descriptionTextArea.getText());
                        exercise.setMovementType(Integer.parseInt(typeComboBox.getSelectionModel().getSelectedItem()));
                    }
                }
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ejercicio editado");
                alert.setContentText("El ejercicio ha sido modificado en la base de datos");
                alert.show();
                Stage stage = (Stage) saveBtn.getScene().getWindow();
                stage.close();
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
            if (exercise.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

}