package apps.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class AttendanceFormController {
    ObservableList<String> service_dropdown_elements = FXCollections.observableArrayList("1st Service",
            "2nd Service", "3rd Service", "4th Service");

    @FXML private ChoiceBox<String> service_choicebox;

    public void initialize(){
        service_choicebox.getItems().addAll(service_dropdown_elements);
    }
}
