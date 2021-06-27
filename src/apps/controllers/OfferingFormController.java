package apps.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class OfferingFormController {

    ObservableList<String> service_dropdown_elements = FXCollections.observableArrayList("First Service",
            "Second Service", "Third Service", "Fourth Service");

    @FXML private ChoiceBox<String> choiceBox;

    public void initialize(){
        choiceBox.getItems().addAll(service_dropdown_elements);
    }


}
