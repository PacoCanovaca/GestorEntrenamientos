package org.example.gestorentrenamientos.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;
import org.example.gestorentrenamientos.Main;
import org.example.gestorentrenamientos.data.DataSet;
import org.example.gestorentrenamientos.model.Exercise;

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

    @Setter
    private int exerciseId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actions();
    }

    private void actions() {
        editBtn.setOnAction(event -> {
            Stage stage = new Stage();
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("exercise-editor-view.fxml"));
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.setTitle("Editar ejercicio");
                ExerciseEditorController controller = loader.getController();
                controller.setExerciseId(exerciseId);
                controller.getNameTextField().setText(nameText.getText());
                controller.getUrlTextField().setText(urlText.getText());
                controller.getDescriptionTextArea().setText(descriptionText.getText());
                stage.show();
                ((Stage) editBtn.getScene().getWindow()).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Text getDescriptionText() {
        return descriptionText;
    }

    public Text getMovementTypeText() {
        return movementTypeText;
    }

    public Text getNameText() {
        return nameText;
    }

    public Text getUrlText() {
        return urlText;
    }
}