package apps.controllers;

import apps.DbManuplation.DbManipulate;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WelfareFormController {
    public TableView<WelfareFormModel> welfare_table;

    public TextField date;
    public TextField membership_id;
    public TextField Surname;
    public TextField firstname;
    public TextField number;
    public TextField email;
    public TextField receipt_date;
    public TextField details;
    public TextField amount;
    public TableColumn<WelfareFormModel, String> date_column;
    public TableColumn<WelfareFormModel, String> payment_details;
    public TableColumn<WelfareFormModel, Integer> amount_paid;
    public TableColumn<WelfareFormModel, String> officer;
    @FXML
    public ChoiceBox<String> Payment_choice;
    public ChoiceBox Receipt_choice;

    DbManipulate dbManipulate = new DbManipulate();
    ResultSet resultSet;

    ObservableList<String> payment_mode_elements = FXCollections.observableArrayList("All");
    ObservableList<String> receipt_printing_elements = FXCollections.observableArrayList("Transactional", "Cumulative");
    public ObservableList<WelfareFormModel> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        date_column.setCellValueFactory(new PropertyValueFactory<>("date"));
        payment_details.setCellValueFactory(new PropertyValueFactory<>("payment_details"));
        amount_paid.setCellValueFactory( new PropertyValueFactory<>("amount_paid"));
        officer.setCellValueFactory(new PropertyValueFactory<>("officer"));

        Payment_choice.getItems().addAll(payment_mode_elements);
        Receipt_choice.getItems().addAll(receipt_printing_elements);
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate r = localDateTime.toLocalDate();
        String dateValue = r.toString();

        date.setText(dateValue);

        welfare_table.setItems(data);


    }

    @FXML
    public void onEnter(ActionEvent event) {

        String Query = "SELECT FIRST_NAME, SURNAME, CONTACT_NUMBER_1, EMAIL FROM MEMBERSHIP_RECORDS_TABLE WHERE MEMBERSHIP_ID = " + "'" + membership_id.getText() + "';";

        try{

            resultSet = dbManipulate.retrieveData(Query);
            if (resultSet != null){

                while (resultSet.next()){
                    String firstName = resultSet.getString("FIRST_NAME");
                    String surname = resultSet.getString("SURNAME");
                    String phone_number = resultSet.getString("CONTACT_NUMBER_1");
                    String Email = resultSet.getString("EMAIL");

                    firstname.setText(firstName);
                    Surname.setText(surname);
                    number.setText(phone_number);
                    email.setText(Email);
                }

            }else {

            }


        }catch (Exception e){

        }

    }





    public void receive_cash_onclick() {
    }

    public void clear(MouseEvent mouseEvent) {

        membership_id.clear();
        firstname.clear();
        number.clear();
        Surname.clear();
        email.clear();
        receipt_date.clear();
        details.clear();
        amount.clear();
        Payment_choice.setValue(null);
        data.clear();


    }

    @FXML
    public void Apply(MouseEvent mouseEvent) {

            String rDate = receipt_date.getText();
            String paymentDetails = details.getText();
            int paymentAmount = Integer.parseInt(amount.getText());
            String paymentChoiceOption = Payment_choice.getValue();
            String Officer = "All";
            String mem_id = membership_id.getText();

            LocalDateTime l = LocalDateTime.now();
            String localDate = l.getMonth().name();
            String month = localDate;

            data.addAll( new WelfareFormModel(rDate, paymentDetails, paymentAmount, Officer));


            System.out.println(month);

            try {
                String Query = "INSERT INTO WELFARE_TABLE VALUES(" + "'" + mem_id + "'" + ", " + "'" + rDate + "'" + ", " + "'" + paymentDetails + "'" + ", " + paymentAmount  + ", " + "'" + month + "'" + ");";
                dbManipulate.InsertIntoDb(Query);
            }catch (Exception e){

            }



    }


    public class WelfareFormModel{

        private SimpleStringProperty date;
        private SimpleStringProperty payment_details;
        private SimpleIntegerProperty amount_paid;
        private SimpleStringProperty officer;

        public WelfareFormModel(String Date, String Payment_details, int Amount_paid, String Officer) {

            date = new SimpleStringProperty(Date);
            payment_details = new SimpleStringProperty(Payment_details);
            amount_paid = new SimpleIntegerProperty(Amount_paid);
            officer = new SimpleStringProperty(Officer);


        }

        public String getDate() {
            return date.get();
        }

        public SimpleStringProperty dateProperty() {
            return date;
        }

        public void setDate(String date) {
            this.date.set(date);
        }

        public String getPayment_details() {
            return payment_details.get();
        }

        public SimpleStringProperty payment_detailsProperty() {
            return payment_details;
        }

        public void setPayment_details(String payment_details) {
            this.payment_details.set(payment_details);
        }

        public int getAmount_paid() {
            return amount_paid.get();
        }

        public SimpleIntegerProperty amount_paidProperty() {
            return amount_paid;
        }

        public void setAmount_paid(int amount_paid) {
            this.amount_paid.set(amount_paid);
        }

        public String getOfficer() {
            return officer.get();
        }

        public SimpleStringProperty officerProperty() {
            return officer;
        }

        public void setOfficer(String officer) {
            this.officer.set(officer);
        }
    }



}
