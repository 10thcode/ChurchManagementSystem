package apps.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AttendanceFormController {
    public Button btnSave;
    public TextField txtNumber_of_children;
    public TextField number_of_female;
    public TextField txtNumber_of_male;
    public TextField txtTotal;




    ObservableList<String> service_dropdown_elements = FXCollections.observableArrayList("1st Service",
            "2nd Service", "3rd Service", "4th Service");

    @FXML private ChoiceBox<String> service_choicebox;

    public void initialize(){
        service_choicebox.getItems().addAll(service_dropdown_elements);
    }

    public void SaveAttendance(ActionEvent actionEvent) {
       int num_of_children = Integer.parseInt(txtNumber_of_children.getText());
       int num_of_female = Integer.parseInt(number_of_female.getText());
       int num_of_male = Integer.parseInt(txtNumber_of_male.getText());




       int Total = num_of_children + num_of_female + num_of_male;

       txtTotal.setText(String.valueOf(Total));


    }


    public void Clear(ActionEvent actionEvent) {

        txtNumber_of_male.clear();
        txtNumber_of_children.clear();
        number_of_female.clear();


    }
}
