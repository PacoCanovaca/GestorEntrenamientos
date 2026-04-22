package org.example.gestorentrenamientos.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.gestorentrenamientos.Main;
import org.example.gestorentrenamientos.model.ExerciseTable;

public class ExerciseInfoController implements Initializable {

    @FXML
    private Text descriptionText;

    @FXML
    private Button editBtn;

    @FXML
    private Text movementTypeText;

    @FXML
    private Text nameText;

    @FXML
    private Text urlText;

    private int exerciseId;

    private ExerciseTable exercise;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actions();
    }

    private void actions() {
        editBtn.setOnAction(event -> {
            Stage stage = new Stage();
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("exercise-editor-view.fxml"));
                Parent root = loader.load();
                ExerciseEditorController controller = loader.getController();
                controller.setExercise(exercise);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Editar ejercicio");
                stage.show();
                ((Stage) editBtn.getScene().getWindow()).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void setExercise(ExerciseTable exercise) {
        this.exercise = exercise;
        exerciseId = exercise.getId();
        nameText.setText(exercise.getName());
        urlText.setText(exercise.getUrl());
        descriptionText.setText(exercise.getDescription());
        movementTypeText.setText(String.valueOf(exercise.getMovementType()));
    }
}