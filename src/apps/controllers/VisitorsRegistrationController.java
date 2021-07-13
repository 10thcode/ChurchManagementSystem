package apps.controllers;

import apps.DbManuplation.DbManipulate;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VisitorsRegistrationController {


    @FXML
    public TextField display_date;
    public TextField visitiors_id_field;
    public TextField surname;
    public TextField Other_Names;
    public TextField Age;
    public TextField Residence;
    public TextField number;
    public TextField gender;
    public TextField email;

    DbManipulate dbManipulate = new DbManipulate();

    @FXML
    public void initialize(){


        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate r = localDateTime.toLocalDate();
        String dateValue = r.toString();

        display_date.setText(dateValue);

    }


    public void Clear(MouseEvent mouseEvent) {

        surname.clear();
        Other_Names.clear();
        Age.clear();
        Residence.clear();
        number.clear();
        gender.clear();
        email.clear();

    }

    public void Save(MouseEvent mouseEvent) {
    }
}
