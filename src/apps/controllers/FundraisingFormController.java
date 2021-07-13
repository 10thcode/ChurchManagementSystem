package apps.controllers;

import apps.DbManuplation.DbManipulate;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class FundraisingFormController {
    public TextField dateField;
    public ChoiceBox receipt_choice;
    public TextField membership_id_field;
    public TextField surnameField;
    public TextField otherNameField;
    public TextField phone_number_field;
    public TextField emailField;
    public TextField receipt_date_field;
    public TextField details_field;
    public TextField amount_field;
    public ChoiceBox payment_choice;
    public TableView<FundraisingFormModel> fundraising_table;
    public TableColumn<FundraisingFormModel, String> dateColumn;
    public TableColumn<FundraisingFormModel, String> paymentDetailsColumn;
    public TableColumn<FundraisingFormModel, Integer> amountColumn;
    public TableColumn<FundraisingFormModel, String> officerColumn;

    DbManipulate dbManipulate = new DbManipulate();
    ResultSet resultSet;


    ObservableList<String> receipt_choice_elements = FXCollections.observableArrayList("Transactional", "Cumulative");

    ObservableList<String> payment_mode_elements = FXCollections.observableArrayList("Cash", "Momo", "Cheque");

    ObservableList<FundraisingFormModel> data = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate r = localDateTime.toLocalDate();
        String dateValue = r.toString();
        receipt_choice.getItems().addAll(receipt_choice_elements);
        payment_choice.getItems().addAll(payment_mode_elements);

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        paymentDetailsColumn.setCellValueFactory(new PropertyValueFactory<>("payment_details"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount_paid"));
        officerColumn.setCellValueFactory(new PropertyValueFactory<>("officer"));



        dateField.setText(dateValue);

        fundraising_table.setItems(data);

    }





    public void visitors_registration_onclick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("../../resources/views/visitors_registration.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void recieve_cash_onclick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("../../resources/views/tithe_popup.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void onEnterMembershipId(ActionEvent event) {

        String Query = "SELECT SURNAME, OTHER_NAMES, CONTACT_NUMBER_1, EMAIL FROM MEMBERSHIP_RECORDS_TABLE WHERE MEMBERSHIP_ID = " + "'" + membership_id_field.getText() + "';";
        try{

            resultSet = dbManipulate.retrieveData(Query);
            if (resultSet != null){

                while (resultSet.next()){

                    String Surname = resultSet.getString("SURNAME");
                    String other_names = resultSet.getString("OTHER_NAMES");
                    String phone_number = resultSet.getString("CONTACT_NUMBER_1");
                    String Email = resultSet.getString("EMAIL");

                    surnameField.setText(Surname);
                    otherNameField.setText(other_names);
                    phone_number_field.setText(phone_number);
                    emailField.setText(Email);


                }

            }


        }catch (Exception e){

        }



    }



    public void Apply(MouseEvent mouseEvent) {

        String date = dateField.getText();
        String payment_mode = payment_choice.getValue().toString();
        String payment_details = details_field.getText();
        int amount = Integer.parseInt(amount_field.getText());
        String officer = "all";
        String id = membership_id_field.getText();

        //Date would be declared here
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate r = localDateTime.toLocalDate();
        LocalTime time = LocalTime.now();
        String dateValue = r.toString();
        int hour  = time.getHour();
        int minute = time.getMinute();
        String timeValue = String.valueOf(hour) + ":" + String.valueOf(minute);

        System.out.println(dateValue);
        System.out.println(timeValue);

        String payment_date = dateValue;

        data.addAll(new FundraisingFormModel(payment_date, payment_details, amount, officer));


        try{
            String Query = "INSERT INTO FUNDRAISING_TABLE VALUES(" + "'" + id + "'" + ", " + "'" + payment_date + "'" + ", " + "'" + payment_mode + "'" + ", "  + amount  + ", " + "'" + officer + "'"+ ", " + "'" + timeValue + "'" + ");";
            dbManipulate.InsertIntoDb(Query);

        }catch (Exception e){

        }


    }


    public class FundraisingFormModel{

        private SimpleStringProperty date;
        private SimpleStringProperty payment_details;
        private SimpleIntegerProperty amount_paid;
        private SimpleStringProperty officer;

        public FundraisingFormModel(String Date, String Payment_details, int Amount_paid, String Ofiicer) {

            date = new SimpleStringProperty(Date);
            payment_details = new SimpleStringProperty(Payment_details);
            amount_paid = new SimpleIntegerProperty(Amount_paid);
            officer = new SimpleStringProperty(Ofiicer);
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
