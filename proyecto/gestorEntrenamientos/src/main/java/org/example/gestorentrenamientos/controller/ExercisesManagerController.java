package org.example.gestorentrenamientos.controller;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.gestorentrenamientos.Main;
import org.example.gestorentrenamientos.dao.ExerciseDao;
import org.example.gestorentrenamientos.data.DataSet;
import org.example.gestorentrenamientos.dto.ExerciseXml;
import org.example.gestorentrenamientos.dto.ExercisesWrapperXml;
import org.example.gestorentrenamientos.dto.MovementTypeXml;
import org.example.gestorentrenamientos.model.Exercise;
import org.example.gestorentrenamientos.model.ExerciseTable;
import org.example.gestorentrenamientos.model.MovementTypes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    private FilteredList<ExerciseTable> filteredExercisesTableList;

    @FXML
    private Button exportBtn;

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
        initGUI();
        actions();
    }

    private void initGUI() {
        removeExerciseBtn.setDisable(true);
        seeExerciseBtn.setDisable(true);
        exercisesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                removeExerciseBtn.setDisable(false);
                seeExerciseBtn.setDisable(false);
            } else {
                removeExerciseBtn.setDisable(true);
                seeExerciseBtn.setDisable(true);
            }
        });
    }

    private void instances() {
        generateExercisesList();
        setTableItems();
        filterTypeCombo.setItems(DataSet.getMovementTypesNames());
    }

    private void actions() {
        filterBtn.setOnAction(event -> {
            String name = filterNameText.getText();
            String movementType = filterTypeCombo.getSelectionModel().getSelectedItem();
            filterExercises(name, movementType);
        });
        refreshBtn.setOnAction(event -> {
            generateExercisesList();
            setTableItems();
            filterNameText.setText(null);
            filterTypeCombo.setValue(null);
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
                Parent root = loader.load();
                ExerciseInfoController controller = loader.getController();
                controller.setExercise(exerciseSelected);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Ver ejercicio");
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
                ExerciseDao exerciseDao = new ExerciseDao();
                try {
                    exerciseDao.deleteExercise(exerciseSelected.getId());
                    generateExercisesList();
                    setTableItems();
                } catch (SQLException e) {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error de borrado");
                    alert.setContentText("No ha sido posible eliminar el ejercicio de la base de datos");
                    alert.show();
                }
            }
        });
        exportBtn.setOnAction(event -> exportExercisesXml());
    }

    public void setTableItems() {
        exercisesTable.setItems(filteredExercisesTableList);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("movementType"));
        urlCol.setCellValueFactory(new PropertyValueFactory<>("url"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    private void generateExercisesList() {
        exercisesTableList = FXCollections.observableArrayList();
        try {
            DataSet.importDataSet();
            for (Exercise exercise : DataSet.getExercises()) {
                exercisesTableList.add(new ExerciseTable(exercise.getId(), exercise.getName(), DataSet.getMovementTypeNameById(exercise.getMovementType()), exercise.getUrl(), exercise.getDescription()));
            }
            filteredExercisesTableList = new FilteredList<>(exercisesTableList);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error en la importación.");
            alert.setContentText("No se ha podido importar los ejercicios de la base de datos.");
            alert.show();
        }
    }

    private void filterExercisesByName(String name) {
        filteredExercisesTableList.setPredicate(exerciseTable -> exerciseTable.getName().toLowerCase().contains(name));
    }

    private void filterExercisesByType(String movementType) {
        filteredExercisesTableList.setPredicate(exerciseTable -> exerciseTable.getMovementType().equalsIgnoreCase(movementType));
    }

    private void filterExercises(String name, String movementType) {
        if (name != null && movementType == null) {
            filterExercisesByName(name);
        } else if (movementType != null && name == null) {
            filterExercisesByType(movementType);
        } else if (movementType != null && name != null) {
            filteredExercisesTableList.setPredicate(exerciseTable -> exerciseTable.getMovementType().equalsIgnoreCase(movementType) && exerciseTable.getName().toLowerCase().contains(name));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Filtro no introducido");
            alert.setContentText("No has introducido ningún filtro");
            alert.show();
        }
    }

    private void exportExercisesXml() {
        List<MovementTypeXml> movementTypes = new ArrayList<>();
        Alert alert;
        for (MovementTypes type : MovementTypes.values()) {
            if (type == MovementTypes.NULL) continue;
            List<Exercise> exercisesFiltered = DataSet.getExercises().stream().filter(exercise -> exercise.getMovementType() == type.getId()).toList();
            List<ExerciseXml> exercisesMapped = exercisesFiltered.stream().map(exercise -> new ExerciseXml(exercise.getId(), exercise.getName(), exercise.getUrl(), exercise.getDescription())).toList();
            if (!exercisesMapped.isEmpty()) {
                movementTypes.add(new MovementTypeXml(type.getId(), type.getName(), type.getDescription(), exercisesMapped));
            }
        }
        ExercisesWrapperXml wrapperXml = new ExercisesWrapperXml();
        wrapperXml.setMovementTypes(movementTypes);
        try {
            JAXBContext context = JAXBContext.newInstance(ExercisesWrapperXml.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "scheme.xsd");
            marshaller.marshal(wrapperXml, new File("src/main/resources/xml/exercises.xml"));
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exportación exitosa");
            alert.setContentText("Los datos se han exportado correctamente al archivo XML");
            alert.show();
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error en la exportación");
            alert.setContentText("No se han podido exportar los datos al archivo XML");
            alert.show();
        }
    }

}
