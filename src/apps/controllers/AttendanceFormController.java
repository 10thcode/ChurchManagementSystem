package apps.controllers;

import animatefx.animation.FadeIn;
import apps.DbConnection.DbConnection;
import apps.DbManuplation.DbManipulate;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import java.sql.*;

import javax.management.Query;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class AttendanceFormController {
    public Button btnSave;
    public TextField txtNumber_of_children;
    public TextField number_of_female;
    public TextField txtNumber_of_male;
    public TextField txtTotal;
    public VBox form;
    public Label error;

    DbConnection dbConnection = new DbConnection();
    DbManipulate dbManipulate = new DbManipulate();
    String TABLE_NAME = "dbo.ATTENDANCE_TABLE";





    ObservableList<String> service_dropdown_elements = FXCollections.observableArrayList("First Service",
            "Second Service", "Third Service", "Fourth Service");

    @FXML private ChoiceBox<String> service_choicebox;

    public void initialize(){
        service_choicebox.getItems().addAll(service_dropdown_elements);
    }

    //Save Button

    public void SaveAttendance(ActionEvent actionEvent) throws SQLServerException {
       int num_of_children = Integer.parseInt(txtNumber_of_children.getText());
       int num_of_female = Integer.parseInt(number_of_female.getText());
       int num_of_male = Integer.parseInt(txtNumber_of_male.getText());
        LocalDateTime time = LocalDateTime.now();
        LocalDate r = time.toLocalDate();
        System.out.println(r);
        String choice = service_choicebox.getValue();



       int Total = num_of_children + num_of_female + num_of_male;

       txtTotal.setText(String.valueOf(Total));


        try {

            String Query = "INSERT INTO " + TABLE_NAME + " VALUES(" + "'" + r + "'" + ", " + "'" + choice + "'" +   ", " + num_of_male + ", " + num_of_female + ", " + num_of_children + ", " + Total + ");";

//            Connection con = DbConnection.getConnection();
//            Statement statement = con.createStatement();
//            statement.execute(Query);

            dbManipulate.InsertIntoDb(Query);

        }
        catch (Exception e){
                e.printStackTrace();
        }


       if (txtNumber_of_male.getText().isBlank()){
           error.setText("NO. OF MALE CANNOT BE BLANK");
            error.setVisible(true);
           FadeIn fadeIn = new FadeIn();
           fadeIn.setNode(error);
           fadeIn.play();
       }else if (number_of_female.getText().isBlank()){
           error.setText("NO. OF FEMALE CANNOT BE BLANK");
           error.setVisible(true);
           FadeIn fadeIn = new FadeIn();
           fadeIn.setNode(error);
           fadeIn.play();
       }else if(txtNumber_of_children.getText().isBlank()){
           error.setText("NO. OF MALE CANNOT BE BLANK");
           error.setVisible(true);
           FadeIn fadeIn = new FadeIn();
           fadeIn.setNode(error);
           fadeIn.play();
       }





    }


    //Clear Button


    public void Clear(ActionEvent actionEvent) {

        txtNumber_of_male.clear();
        txtNumber_of_children.clear();
        number_of_female.clear();
        txtTotal.clear();

    }
}
