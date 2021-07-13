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

public class TitheFormController {
    public ChoiceBox receipt_choiceBox;
    public TextField display_date;
    public ChoiceBox payment_choice;
    public TextField membership_id;
    public TextField Surname;
    public TextField other_name;
    public TextField number;
    public TextField email;
    public TextField Receipt_date;
    public TextField amount;
    public TextField details;
    public TableView<TitheModel> tithe_table;
    public TableColumn<TitheModel, String> DATE;
    public TableColumn<TitheModel, String> PAYMENT_DETAILS;
    public TableColumn<TitheModel, Integer> AMOUNT_PAID;
    public TableColumn<TitheModel, String> OFFICER;

    ResultSet resultSet;
    DbManipulate dbManipulate = new DbManipulate();

    ObservableList<String> payment_mode_elements = FXCollections.observableArrayList("All");
    ObservableList<String> receipt_printing_elements = FXCollections.observableArrayList("Transactional", "Cumulative");
    public ObservableList<TitheModel> data = FXCollections.observableArrayList();

    @FXML
    public void initialize(){

        receipt_choiceBox.getItems().addAll(receipt_printing_elements);
        payment_choice.getItems().addAll(payment_mode_elements);

        DATE.setCellValueFactory( new PropertyValueFactory<>("date"));
        PAYMENT_DETAILS.setCellValueFactory(new PropertyValueFactory<>("details"));
        AMOUNT_PAID.setCellValueFactory(new PropertyValueFactory<>("amount"));
        OFFICER.setCellValueFactory(new PropertyValueFactory<>("officer"));


        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate r = localDateTime.toLocalDate();
        String dateValue = r.toString();

        display_date.setText(dateValue);

        tithe_table.setItems(data);

    }

    @FXML
    private void receive_cash_onclick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("../../resources/views/tithe_popup.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void visitors_registration_onclick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("../../resources/views/visitors_registration.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    

    public void onEnter(ActionEvent event) {


        String Query = "SELECT SURNAME, OTHER_NAMES, CONTACT_NUMBER_1, EMAIL FROM MEMBERSHIP_RECORDS_TABLE WHERE MEMBERSHIP_ID = " + "'" + membership_id.getText() + "';";
        try{

            resultSet = dbManipulate.retrieveData(Query);
            if (resultSet != null){

                while (resultSet.next()){
                    String surname = resultSet.getString("SURNAME");
                    String other_names = resultSet.getString("OTHER_NAMES");
                    String phone_number = resultSet.getString("CONTACT_NUMBER_1");
                    String Email = resultSet.getString("EMAIL");

                    Surname.setText(surname);
                    other_name.setText(other_names);
                    number.setText(phone_number);
                    email.setText(Email);
                }

            }else {

            }


        }catch (Exception e){

        }

    }




    public void Clear(MouseEvent mouseEvent) {

        Surname.clear();
        other_name.clear();
        number.clear();
        email.clear();
        membership_id.clear();

    }

    public void Save(MouseEvent mouseEvent) {


    }

    public void Apply(MouseEvent mouseEvent) {

            String rDate = Receipt_date.getText();
            String paymentDetails = details.getText();
            int payment_amount = Integer.parseInt(amount.getText());
            String Officer = "all";
            String mode_of_payment = payment_choice.getValue().toString();
            String mem_id = membership_id.getText();

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

            data.addAll(new TitheModel(payment_date,paymentDetails, payment_amount, Officer));

        try {
            String Query = "INSERT INTO TITHE_TABLE VALUES(" + "'" + mem_id + "'" + ", " + "'" + rDate + "'" + ", " + "'" + payment_date + "'" + ", " + "'" + paymentDetails + "'" + ", " + payment_amount  + ", " + "'" + mode_of_payment + "'"+ ", " + "'" + Officer + "'"+ ", " + "'" + timeValue + "'" + ");";
            dbManipulate.InsertIntoDb(Query);
        }catch (Exception e){

        }




    }


    public static class TitheModel{

        private SimpleStringProperty date;
        private SimpleStringProperty details;
        private SimpleIntegerProperty amount;
        private SimpleStringProperty officer;

        public TitheModel(String DATE, String DETAILS, int AMOUNT, String OFFICER) {

            date = new SimpleStringProperty(DATE);
            details = new SimpleStringProperty(DETAILS);
            amount = new SimpleIntegerProperty(AMOUNT);
            officer = new SimpleStringProperty(OFFICER);

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

        public String getDetails() {
            return details.get();
        }

        public SimpleStringProperty detailsProperty() {
            return details;
        }

        public void setDetails(String details) {
            this.details.set(details);
        }

        public int getAmount() {
            return amount.get();
        }

        public SimpleIntegerProperty amountProperty() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount.set(amount);
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
