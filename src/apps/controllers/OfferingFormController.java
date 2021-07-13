package apps.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.DataFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OfferingFormController {

    public Label date;
    ObservableList<String> service_dropdown_elements = FXCollections.observableArrayList("First Service",
            "Second Service", "Third Service", "Fourth Service");

    @FXML private ChoiceBox<String> choiceBox;

   // public ObservableList<AttendanceRegisterController.TableModel> data = FXCollections.observableArrayList();

    public void initialize(){
        choiceBox.getItems().addAll(service_dropdown_elements);
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate r = localDateTime.toLocalDate();
        String dateValue = r.toString();
        date.setText(dateValue);

    }


}
