package org.example.gestorentrenamientos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Data;
import org.example.gestorentrenamientos.Main;
import org.example.gestorentrenamientos.data.DataSet;
import org.example.gestorentrenamientos.model.Exercise;
import org.example.gestorentrenamientos.model.ExerciseTable;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ExercisesManagerController implements Initializable {

    @FXML
    private Button addExerciseBtn;

    @FXML
    private Button refreshBtn;

    @FXML
    private TableColumn<?, ?> descriptionCol;

    @FXML
    private TableView<ExerciseTable> exercisesTable;

    private ObservableList<ExerciseTable> exercisesTableList;

    private ObservableList<ExerciseTable> filteredExercisesTableList;

    @FXML
    private Button filterBtn;

    @FXML
    private TextField filterNameText;

    @FXML
    private ComboBox<String> filterTypeCombo;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private Button removeExerciseBtn;

    @FXML
    private Button seeExerciseBtn;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private TableColumn<?, ?> urlCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instances();
        actions();
    }

    private void instances() {
        generateExercisesList();
        setTableItems(exercisesTableList);
        filterTypeCombo.setItems(DataSet.getMovementTypes());
    }

    private void actions() {
        filterBtn.setOnAction(event -> {
            String name = filterNameText.getText();
            String movementType = filterTypeCombo.getSelectionModel().getSelectedItem();
            filterExercises(name, movementType);
        });
        refreshBtn.setOnAction(event -> {
            generateExercisesList();
            setTableItems(exercisesTableList);
        });
        addExerciseBtn.setOnAction(event -> {
            Stage stage = new Stage();
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("exercise-creator-view.fxml"));
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.setTitle("Añadir ejercicio");
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        seeExerciseBtn.setOnAction(event -> {
            ExerciseTable exerciseSelected = exercisesTable.getSelectionModel().getSelectedItem();
            Stage stage = new Stage();
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("exercise-info-view.fxml"));
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.setTitle("Ver ejercicio");
                ExerciseInfoController controller = loader.getController();
                // TODO falta asignar el ejercicio seleccionado a la nueva ventana
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        removeExerciseBtn.setOnAction(event -> {
            Alert alert;
            ExerciseTable exerciseSelected = exercisesTable.getSelectionModel().getSelectedItem();
            if (exerciseSelected == null) {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ejercicio no seleccionado");
                alert.setContentText("No se ha seleccionado ningún ejercicio.");
                alert.show();
                return;
            }
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar ejercicio");
            alert.setContentText(String.format("¿Quieres eliminar el ejercicio '%s'?", exerciseSelected.getName()));
            Optional<ButtonType> options = alert.showAndWait();
            if (options.get() == ButtonType.OK) {
                for (Exercise exercise : DataSet.getExercises()) {
                    if (exerciseSelected.getId() == exercise.getId()) {
                        DataSet.getExercises().remove(exercise);
                        generateExercisesList();
                        setTableItems(exercisesTableList);
                        return;
                    }
                }
            }
        });
    }

    private void setTableItems(ObservableList<ExerciseTable> exercises) {
        exercisesTable.setItems(exercises);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("movementType"));
        urlCol.setCellValueFactory(new PropertyValueFactory<>("url"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    private void generateExercisesList() {
        exercisesTableList = FXCollections.observableArrayList();
        for (Exercise exercise : DataSet.getExercises()) {
            exercisesTableList.add(new ExerciseTable(exercise.getId(), exercise.getName(), exercise.getMovementType(), exercise.getUrl(), exercise.getDescription()));
        }
    }

    private void filterExercisesByName(String name) {
        filteredExercisesTableList = FXCollections.observableArrayList(exercisesTableList.stream().filter(exercise -> exercise.getName().toLowerCase().contains(name.toLowerCase())).toList());
        setTableItems(filteredExercisesTableList);
    }

    private void filterExercisesByType(String movementType) {
        filteredExercisesTableList = FXCollections.observableArrayList(exercisesTableList.stream().filter(exercise -> exercise.getMovementType().equalsIgnoreCase(movementType)).toList());
        setTableItems(filteredExercisesTableList);
    }

    private void filterExercises(String name, String movementType) {
        if (name != null && movementType == null) {
            filterExercisesByName(name);
        } else if (movementType != null && name == null) {
            filterExercisesByType(movementType);
        } else if (movementType != null && name != null) {
            filteredExercisesTableList = FXCollections.observableArrayList(exercisesTableList.stream().filter(exercise -> exercise.getName().toLowerCase().contains(name.toLowerCase()) && exercise.getMovementType().equalsIgnoreCase(movementType)).toList());
            setTableItems(filteredExercisesTableList);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Filtro no introducido");
            alert.setContentText("No has introducido ningún filtro");
            alert.show();
        }
    }

}
