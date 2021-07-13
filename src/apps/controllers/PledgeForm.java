package apps.controllers;

import apps.DbManuplation.DbManipulate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class PledgeForm {
    public TextField date;
    public TextField Membership_id;
    public TextField lastname;
    public TextField othernames;
    public TextField amount;
    public TextField number;
    public TextField email;

    DbManipulate dbManipulate = new DbManipulate();
    ResultSet resultSet;




    @FXML
    public void initialize(){

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate r = localDateTime.toLocalDate();
        String dateValue = r.toString();

        date.setText(dateValue);

    }






    public void visitors_registration_onclick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("../../resources/views/visitors_registration.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void OnMemberEnter(ActionEvent event) {

        String Query = "SELECT SURNAME, OTHER_NAMES, CONTACT_NUMBER_1, EMAIL FROM MEMBERSHIP_RECORDS_TABLE WHERE MEMBERSHIP_ID = " + "'" + Membership_id.getText() + "';";
        try{

            resultSet = dbManipulate.retrieveData(Query);
            if (resultSet != null){

                while (resultSet.next()){

                    String Surname = resultSet.getString("SURNAME");
                    String other_names = resultSet.getString("OTHER_NAMES");
                    String phone_number = resultSet.getString("CONTACT_NUMBER_1");
                    String Email = resultSet.getString("EMAIL");

                    lastname.setText(Surname);
                    othernames.setText(other_names);
                    number.setText(phone_number);
                    email.setText(Email);


                }

            }


        }catch (Exception e){

        }

    }

    public void clear(MouseEvent mouseEvent) {

        Membership_id.clear();
        lastname.clear();
        othernames.clear();
        number.clear();
        email.clear();
        amount.clear();

    }

    public void save(MouseEvent mouseEvent){

        String id = Membership_id.getText();
        int Amount = Integer.parseInt(amount.getText());
        String Date = date.getText();

        String Query = "INSERT INTO PLEDGE_TABLE VALUES(" + "'" + id + "'" + ", " + "'" + Date + "'" + ", " + "'" + Amount + "'" + ");";

        try{

            dbManipulate.InsertIntoDb(Query);

        }catch (Exception e){

        }


        Stage stage = (Stage) Membership_id.getScene().getWindow();
        stage.close();


    }

    public void amount_validate(KeyEvent keyEvent) {

        String text = amount.getText().toLowerCase();
        char c = text.charAt(text.length()-1);
        if (c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f'|| c == 'g' || c == 'h' || c == 'i' || c == 'j' || c == 'k' || c == 'l' || c == 'm' || c == 'n' || c == 'o' || c == 'p' || c == 'q' || c == 'r' || c == 's' || c == 't' || c == 'u' || c == 'v' || c == 'w' || c == 'x' || c == 'y' || c == 'z'){
            amount.clear();
        }

    }
}
