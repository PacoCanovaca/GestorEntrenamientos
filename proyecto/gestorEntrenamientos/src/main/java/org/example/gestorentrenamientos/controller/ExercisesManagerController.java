package org.example.gestorentrenamientos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.gestorentrenamientos.data.DataSet;
import org.example.gestorentrenamientos.model.Exercise;
import org.example.gestorentrenamientos.model.ExerciseTable;

import java.net.URL;
import java.util.ResourceBundle;

public class ExercisesManagerController implements Initializable {

    @FXML
    private Button addExerciseBtn;

    @FXML
    private TableColumn<?, ?> descriptionCol;

    @FXML
    private TableView<ExerciseTable> exercisesTable;

    private ObservableList<ExerciseTable> exercisesTableList;

    @FXML
    private Button filterBtn;

    @FXML
    private TextField filterNameText;

    @FXML
    private ComboBox<?> filterTypeCombo;

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
    }

    public void instances() {
        exercisesTableList = FXCollections.observableArrayList();
        generateExercisesList();
        exercisesTable.setItems(exercisesTableList);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("movementType"));
        urlCol.setCellValueFactory(new PropertyValueFactory<>("url"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    public void generateExercisesList() {
        for (Exercise exercise : DataSet.getExercises()) {
            exercisesTableList.add(new ExerciseTable(exercise.getName(), exercise.getMovementType(), exercise.getUrl(), exercise.getDescription()));
        }
    }

}
