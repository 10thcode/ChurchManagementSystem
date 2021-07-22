package apps.controllers;

import apps.DbManuplation.DbManipulate;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;

public class TitheRegisterController {

    public ChoiceBox officer_choice;
    public TableView<Tithe_Register_Table_Model> Tithe_Register_Table;
    public TableColumn<Tithe_Register_Table_Model, String> date;
    public TableColumn<Tithe_Register_Table_Model, String> member_name;
    public TableColumn<Tithe_Register_Table_Model, String> member_id;
    public TableColumn<Tithe_Register_Table_Model, String> number;
    public TableColumn<Tithe_Register_Table_Model, String> amount;
    public TableColumn<Tithe_Register_Table_Model, String> payment_mode;
    public TableColumn<Tithe_Register_Table_Model, String> officer;
    public TableColumn<Tithe_Register_Table_Model, String> time;

    DbManipulate dbManipulate = new DbManipulate();
    ResultSet resultSet;
    ResultSet altSet;

    ObservableList<String> officers = FXCollections.observableArrayList("All");
    public ObservableList<Tithe_Register_Table_Model> data = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        officer_choice.getItems().addAll(officers);
        TitheRegisterController();

        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        member_name.setCellValueFactory(new PropertyValueFactory<>("Member_name"));
        member_id.setCellValueFactory(new PropertyValueFactory<>("Membership_id"));
        number.setCellValueFactory(new PropertyValueFactory<>("Phone_number"));
        amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        payment_mode.setCellValueFactory(new PropertyValueFactory<>("Payment_mode"));
        officer.setCellValueFactory(new PropertyValueFactory<>("Officer"));
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));


        Tithe_Register_Table.setItems(data);



    }

    public void TitheRegisterController() {

        String Query = "SELECT * FROM TITHE_TABLE";
        try {
            resultSet = dbManipulate.retrieveData(Query);
            if (resultSet != null){

                while(resultSet.next()){

                    String id = resultSet.getString("MEMBERSHIP_ID");
                    String firstname = "";
                    String surname = "";
                    String num = "";
                    String date = resultSet.getString("DATE");
                    String mode = resultSet.getString("PAYMENT_MODE");
                    int amount = resultSet.getInt("AMOUNT");
                    String officer = resultSet.getString("OFFICER");
                    String time = resultSet.getString("TIME");



                    String Other_Query = "SELECT FIRST_NAME, SURNAME, CONTACT_NUMBER_1 FROM MEMBERSHIP_RECORDS_TABLE WHERE MEMBERSHIP_ID=" + "'" + id + "'";
                    try {
                        altSet = dbManipulate.retrieveData(Other_Query);
                        if (altSet != null){

                            while (altSet.next()){

                                firstname = altSet.getString("FIRST_NAME");
                                surname = altSet.getString("SURNAME");
                                num = altSet.getString("CONTACT_NUMBER_1");

                            }

                        }

                    }catch (Exception e){

                    }

                    String fullname = firstname + " " + surname;

                data.addAll(new Tithe_Register_Table_Model(date, fullname, id, num, amount, mode, officer, time));


                }

            }


        }catch (Exception e){

        }



    }

    public void search_button(MouseEvent mouseEvent) {
    }

    public class Tithe_Register_Table_Model{

        private SimpleStringProperty Date;
        private SimpleStringProperty Member_name;
        private SimpleStringProperty Membership_id;
        private SimpleStringProperty Phone_number;
        private SimpleIntegerProperty Amount;
        private SimpleStringProperty Payment_mode;
        private SimpleStringProperty Officer;
        private SimpleStringProperty Time;

        public Tithe_Register_Table_Model(String date, String name, String id, String number, int amount, String mode_of_payment, String officer, String time) {

            Date = new SimpleStringProperty(date);
            Member_name = new SimpleStringProperty(name);
            Membership_id = new SimpleStringProperty(id);
            Phone_number = new SimpleStringProperty(number);
            Amount = new SimpleIntegerProperty(amount);
            Payment_mode = new SimpleStringProperty(mode_of_payment);
            Officer = new SimpleStringProperty(officer);
            Time = new SimpleStringProperty(time);

        }

        public String getDate() {
            return Date.get();
        }

        public SimpleStringProperty dateProperty() {
            return Date;
        }

        public void setDate(String date) {
            this.Date.set(date);
        }

        public String getMember_name() {
            return Member_name.get();
        }

        public SimpleStringProperty member_nameProperty() {
            return Member_name;
        }

        public void setMember_name(String member_name) {
            this.Member_name.set(member_name);
        }

        public String getMembership_id() {
            return Membership_id.get();
        }

        public SimpleStringProperty membership_idProperty() {
            return Membership_id;
        }

        public void setMembership_id(String membership_id) {
            this.Membership_id.set(membership_id);
        }

        public String getPhone_number() {
            return Phone_number.get();
        }

        public SimpleStringProperty phone_numberProperty() {
            return Phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.Phone_number.set(phone_number);
        }

        public int getAmount() {
            return Amount.get();
        }

        public SimpleIntegerProperty amountProperty() {
            return Amount;
        }

        public void setAmount(int amount) {
            this.Amount.set(amount);
        }

        public String getPayment_mode() {
            return Payment_mode.get();
        }

        public SimpleStringProperty payment_modeProperty() {
            return Payment_mode;
        }

        public void setPayment_mode(String payment_mode) {
            this.Payment_mode.set(payment_mode);
        }

        public String getOfficer() {
            return Officer.get();
        }

        public SimpleStringProperty officerProperty() {
            return Officer;
        }

        public void setOfficer(String officer) {
            this.Officer.set(officer);
        }

        public String getTime() {
            return Time.get();
        }

        public SimpleStringProperty timeProperty() {
            return Time;
        }

        public void setTime(String time) {
            this.Time.set(time);
        }
    }


}
