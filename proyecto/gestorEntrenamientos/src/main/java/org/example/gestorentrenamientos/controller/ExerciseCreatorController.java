package org.example.gestorentrenamientos.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.gestorentrenamientos.dao.ExerciseDao;
import org.example.gestorentrenamientos.data.DataSet;
import org.example.gestorentrenamientos.model.Exercise;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ExerciseCreatorController implements Initializable {

    @FXML
    private Button addBtn;

    @FXML
    private TextArea descriptionText;

    @FXML
    private TextField nameText;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private TextField urlText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instances();
        actions();
    }

    private void instances() {
        typeComboBox.setItems(DataSet.getMovementTypesNames());
    }

    private void actions() {
        addBtn.setOnAction(event -> {
            Alert alert;
            ExerciseDao exerciseDao = new ExerciseDao();
            try {
                exerciseDao.insertExercise(new Exercise(DataSet.getMovementTypeIdByName(typeComboBox.getSelectionModel().getSelectedItem()), nameText.getText(), urlText.getText(), descriptionText.getText()));
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ejercicio añadido");
                alert.setContentText("El ejercicio ha sido añadido a la base de datos");
                alert.show();
                Stage stage = (Stage) addBtn.getScene().getWindow();
                stage.close();
            } catch (SQLException e) {
                if (nameText.getText().equalsIgnoreCase("")) {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Nombre no introducido");
                    alert.setContentText("No has introducido el nombre del ejercicio.");
                    alert.show();
                } else if (existsName(nameText.getText())) {
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
                    alert.setContentText("Se ha producido un error sin identificar a la hora de crear el nuevo ejercicio.");
                    alert.show();
                }
            }
        });
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
