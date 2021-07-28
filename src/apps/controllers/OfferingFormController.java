package apps.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DataFormat;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OfferingFormController {

    public Label date;
    public TextField quantity200_tf;
    public TextField quantity100_tf;
    public TextField quantity50_tf;
    public TextField quantity20_tf;
    public TextField quantity5_tf;
    public TextField quantity2_tf;
    public TextField quantity10_tf;
    public TextField quantity1_tf;
    public TextField quantity2_coin_tf;
    public TextField quantity1_coin_tf;
    public TextField quantity20_coin_tf;
    public TextField quantity10_coin_tf;
    public TextField quantity50_coin_tf;
    public TextField amount200_tf;
    public TextField amount100_tf;
    public TextField amount50_tf;
    public TextField amount10_tf;
    public TextField amount2_coin_tf;
    public TextField amount1_coin_tf;
    public TextField amount20_tf;
    public TextField amount2_tf;
    public TextField amount5_tf;
    public TextField amount50_coin_tf;
    public TextField amount20_coin_tf;
    public TextField amount1_tf;
    public TextField total_tf;
    public TextField amount10_coin_tf;
    ObservableList<String> service_dropdown_elements = FXCollections.observableArrayList("First Service",
            "Second Service", "Third Service", "Fourth Service");

    @FXML private ChoiceBox<String> choiceBox;

   // public ObservableList<AttendanceRegisterController.TableModel> data = FXCollections.observableArrayList();

   // Alert alert  = new Alert(Alert.AlertType.WARNING);



    public void initialize(){
        choiceBox.getItems().addAll(service_dropdown_elements);
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate r = localDateTime.toLocalDate();
        String dateValue = r.toString();
        date.setText(dateValue);

        //just alert code
//        alert.setTitle("Hello there");
//        alert.setHeaderText(null);
//        alert.setContentText("Hello there");
//        alert.showAndWait();

    }


}
