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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MembershipRegisterController {

    @FXML
    public TableView<MembershipTableData> MemberTable;
    public TableColumn<MembershipTableData, String> member_no;
    public TableColumn<MembershipTableData, String> full_name;
    public TableColumn<MembershipTableData, String> dob;
    public TableColumn<MembershipTableData, String> gender;
    public TableColumn<MembershipTableData, String> contact1;
    public TableColumn<MembershipTableData, String> contact2;
    public TableColumn<MembershipTableData, Integer> age;
    public TableColumn<MembershipTableData, String> residence;
    public TextField total;
    public ChoiceBox search_by_options_choice;
    public TextField search_text;

    private String TableName = "MEMBERSHIP_RECORDS_TABLE";
    DbManipulate dbManipulate = new DbManipulate();
    private ResultSet resultSet;

    ObservableList<String> search_by_options_items = FXCollections.observableArrayList("Membership ID", "Full Name", "Phone Number", "None");
    public ObservableList<MembershipTableData> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        search_by_options_choice.getItems().addAll(search_by_options_items);

        MembershipRegisterController();


        member_no.setCellValueFactory(new PropertyValueFactory<>("membership_number"));
        full_name.setCellValueFactory(new PropertyValueFactory<>("membership_full_name"));
        dob.setCellValueFactory(new PropertyValueFactory<>("date_of_birth"));
        gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        contact1.setCellValueFactory(new PropertyValueFactory<>("Contact1"));
        contact2.setCellValueFactory(new PropertyValueFactory<>("Contact2"));
        age.setCellValueFactory(new PropertyValueFactory<>("Age"));
        residence.setCellValueFactory(new PropertyValueFactory<>("Residence"));

        MemberTable.setItems(data);

    }



    public void MembershipRegisterController() {


        String Query = "SELECT MEMBERSHIP_ID, FIRST_NAME, SURNAME, DATE_OF_BIRTH, AGE, GENDER, CONTACT_NUMBER_1, CONTACT_NUMBER_2, PLACE_OF_RESIDENCE FROM MEMBERSHIP_RECORDS_TABLE";


        try {

            resultSet = dbManipulate.retrieveData(Query);

            if (resultSet != null) {

                while (resultSet.next()) {

                    String membership_id = resultSet.getString("MEMBERSHIP_ID");
                    String firstName = resultSet.getString("FIRST_NAME");
                    String surname = resultSet.getString("SURNAME");
                    String fullName = firstName + " " + surname;
                    String date_of_birth = resultSet.getString("DATE_OF_BIRTH");
                    int age = resultSet.getInt("AGE");
                    String gender = resultSet.getString("GENDER");
                    String contact_number1 = resultSet.getString("CONTACT_NUMBER_1");
                    String contact_number2 = resultSet.getString("CONTACT_NUMBER_2");
                    String residence = resultSet.getString("PLACE_OF_RESIDENCE");


//                    System.out.println(totalAge);
//                    int birthYear = Integer.parseInt(totalAge);





//                    System.out.println(age);



                    //modified variables


                    data.addAll(new MembershipTableData(membership_id, fullName, date_of_birth, gender, contact_number1, contact_number2, age, residence));


                }
                //  MemberTable.refresh();
                int sizeOfData = data.size();
                total.setText(String.valueOf(sizeOfData));

            }

        } catch (Exception e) {


        }


    }

    public void search_button(MouseEvent mouseEvent) {

        String value = search_by_options_choice.getValue().toString();
        String UserSearch = search_text.getText();
        String QueryForMembershipId = "SELECT * FROM MEMBERSHIP_RECORDS_TABLE WHERE MEMBERSHIP_ID=" + "'" + UserSearch + "'";
        String QueryForFirstName = "SELECT * FROM MEMBERSHIP_RECORDS_TABLE WHERE FIRST_NAME=" + "'" + UserSearch + "'";
        String QueryForSurname = "SELECT * FROM MEMBERSHIP_RECORDS_TABLE WHERE SURNAME=" + "'" + UserSearch + "'";
        String QueryForPhoneNumber = "SELECT * FROM MEMBERSHIP_RECORDS_TABLE WHERE CONTACT_NUMBER_1=" + "'" + UserSearch + "'";


        if (search_by_options_choice.getValue().toString().equalsIgnoreCase("Membership ID")) {

            ResultSet memberShipIdSet = dbManipulate.retrieveData(QueryForMembershipId);
            try {
                if (memberShipIdSet != null) {

                    while (memberShipIdSet.next()) {

                        String membership_id = memberShipIdSet.getString("MEMBERSHIP_ID");
                        String firstName = memberShipIdSet.getString("FIRST_NAME");
                        String surname = memberShipIdSet.getString("SURNAME");
                        String date_of_birth = memberShipIdSet.getString("DATE_OF_BIRTH");
                        String gender = memberShipIdSet.getString("GENDER");
                        String contact_number1 = memberShipIdSet.getString("CONTACT_NUMBER_1");
                        String contact_number2 = memberShipIdSet.getString("CONTACT_NUMBER_2");

                        char[] ageArray = date_of_birth.toCharArray();
                        String firstnum = String.valueOf(ageArray[6]);
                        String secondnum = String.valueOf(ageArray[7]);
                        String thirdnum = String.valueOf(ageArray[8]);
                        String fourthnum = String.valueOf(ageArray[9]);
                        String totalAge = firstnum + secondnum + thirdnum + fourthnum;
                        int birthYear = Integer.parseInt(totalAge);
                        LocalDate localDateTime = LocalDate.now();
                        int currentYear = localDateTime.getYear();
                        System.out.println(birthYear);
                        System.out.println(currentYear);

                        int age = currentYear - birthYear;
                        System.out.println(age);

                        String residence = memberShipIdSet.getString("PLACE_OF_RESIDENCE");

                        //modified variables
                        String fullName = firstName + " " + surname;

                        data.clear();

                        data.addAll(new MembershipTableData(membership_id, fullName, date_of_birth, gender, contact_number1, contact_number2, age, residence));

                        int sizeOfData = data.size();
                        total.setText(String.valueOf(sizeOfData));


                    }

                }
            } catch (Exception e) {

            }


        } else if (search_by_options_choice.getValue().toString().equalsIgnoreCase("None")) {

            data.clear();

            try {
                String Query = "SELECT MEMBERSHIP_ID, FIRST_NAME, SURNAME, DATE_OF_BIRTH, GENDER, CONTACT_NUMBER_1, CONTACT_NUMBER_2, PLACE_OF_RESIDENCE FROM MEMBERSHIP_RECORDS_TABLE";
                resultSet = dbManipulate.retrieveData(Query);

                if (resultSet != null) {

                    while (resultSet.next()) {

                        String membership_id = resultSet.getString("MEMBERSHIP_ID");
                        String firstName = resultSet.getString("FIRST_NAME");
                        String surname = resultSet.getString("SURNAME");
                        String date_of_birth = resultSet.getString("DATE_OF_BIRTH");
                        String gender = resultSet.getString("GENDER");
                        String contact_number1 = resultSet.getString("CONTACT_NUMBER_1");
                        String contact_number2 = resultSet.getString("CONTACT_NUMBER_2");

                        char[] ageArray = date_of_birth.toCharArray();
                        String firstnum = String.valueOf(ageArray[6]);
                        String secondnum = String.valueOf(ageArray[7]);
                        String thirdnum = String.valueOf(ageArray[8]);
                        String fourthnum = String.valueOf(ageArray[9]);
                        String totalAge = firstnum + secondnum + thirdnum + fourthnum;
                        int birthYear = Integer.parseInt(totalAge);
                        LocalDate localDateTime = LocalDate.now();
                        int currentYear = localDateTime.getYear();
                        System.out.println(birthYear);
                        System.out.println(currentYear);

                        int age = currentYear - birthYear;
                        System.out.println(age);

                        String residence = resultSet.getString("PLACE_OF_RESIDENCE");

                        //modified variables
                        String fullName = firstName + " " + surname;


                        data.addAll(new MembershipTableData(membership_id, fullName, date_of_birth, gender, contact_number1, contact_number2, age, residence));


                    }


                    //  MemberTable.refresh();
                    int sizeOfData = data.size();
                    total.setText(String.valueOf(sizeOfData));
                }
            } catch (Exception exception) {

            }


        } else if (search_by_options_choice.getValue().toString().equalsIgnoreCase("Phone Number")) {

            data.clear();

            try {
                String Query = "SELECT MEMBERSHIP_ID, FIRST_NAME, SURNAME, DATE_OF_BIRTH, GENDER, CONTACT_NUMBER_1, CONTACT_NUMBER_2, PLACE_OF_RESIDENCE FROM MEMBERSHIP_RECORDS_TABLE WHERE CONTACT_NUMBER_1 =" + "'" + search_text.getText() + "'";
                resultSet = dbManipulate.retrieveData(Query);


                if (resultSet != null) {

                    while (resultSet.next()) {

                        String membership_id = resultSet.getString("MEMBERSHIP_ID");
                        String firstName = resultSet.getString("FIRST_NAME");
                        String surname = resultSet.getString("SURNAME");
                        String date_of_birth = resultSet.getString("DATE_OF_BIRTH");
                        String gender = resultSet.getString("GENDER");
                        String contact_number1 = resultSet.getString("CONTACT_NUMBER_1");
                        String contact_number2 = resultSet.getString("CONTACT_NUMBER_2");

                        char[] ageArray = date_of_birth.toCharArray();
                        String firstnum = String.valueOf(ageArray[6]);
                        String secondnum = String.valueOf(ageArray[7]);
                        String thirdnum = String.valueOf(ageArray[8]);
                        String fourthnum = String.valueOf(ageArray[9]);
                        String totalAge = firstnum + secondnum + thirdnum + fourthnum;
                        int birthYear = Integer.parseInt(totalAge);
                        LocalDate localDateTime = LocalDate.now();
                        int currentYear = localDateTime.getYear();
                        System.out.println(birthYear);
                        System.out.println(currentYear);

                        int age = currentYear - birthYear;
                        System.out.println(age);

                        String residence = resultSet.getString("PLACE_OF_RESIDENCE");

                        //modified variables
                        String fullName = firstName + " " + surname;


                        data.addAll(new MembershipTableData(membership_id, fullName, date_of_birth, gender, contact_number1, contact_number2, age, residence));

                    }

                    int sizeOfData = data.size();
                    total.setText(String.valueOf(sizeOfData));
                }


            } catch (Exception e) {

            }
        }

    }

        public class MembershipTableData {
            private SimpleStringProperty membership_number;
            private SimpleStringProperty membership_full_name;
            private SimpleStringProperty date_of_birth;
            private SimpleStringProperty Gender;
            private SimpleStringProperty Contact1;
            private SimpleStringProperty Contact2;
            private SimpleIntegerProperty Age;
            private SimpleStringProperty Residence;

            public MembershipTableData(String member_num, String fullname, String dob, String gender, String contact1, String contact2, int age, String residence) {

                membership_number = new SimpleStringProperty(member_num);
                membership_full_name = new SimpleStringProperty(fullname);
                date_of_birth = new SimpleStringProperty(dob);
                Gender = new SimpleStringProperty(gender);
                Contact1 = new SimpleStringProperty(contact1);
                Contact2 = new SimpleStringProperty(contact2);
                Age = new SimpleIntegerProperty(age);
                Residence = new SimpleStringProperty(residence);


            }


            public String getMembership_number() {
                return membership_number.get();
            }

            public SimpleStringProperty membership_numberProperty() {
                return membership_number;
            }

            public void setMembership_number(String membership_number) {
                this.membership_number.set(membership_number);
            }

            public String getMembership_full_name() {
                return membership_full_name.get();
            }

            public SimpleStringProperty membership_full_nameProperty() {
                return membership_full_name;
            }

            public void setMembership_full_name(String membership_full_name) {
                this.membership_full_name.set(membership_full_name);
            }

            public String getDate_of_birth() {
                return date_of_birth.get();
            }

            public SimpleStringProperty date_of_birthProperty() {
                return date_of_birth;
            }

            public void setDate_of_birth(String date_of_birth) {
                this.date_of_birth.set(date_of_birth);
            }

            public String getGender() {
                return Gender.get();
            }

            public SimpleStringProperty genderProperty() {
                return Gender;
            }

            public void setGender(String gender) {
                this.Gender.set(gender);
            }

            public String getContact1() {
                return Contact1.get();
            }

            public SimpleStringProperty contact1Property() {
                return Contact1;
            }

            public void setContact1(String contact1) {
                this.Contact1.set(contact1);
            }

            public String getContact2() {
                return Contact2.get();
            }

            public SimpleStringProperty contact2Property() {
                return Contact2;
            }

            public void setContact2(String contact2) {
                this.Contact2.set(contact2);
            }

            public int getAge() {
                return Age.get();
            }

            public SimpleIntegerProperty ageProperty() {
                return Age;
            }

            public void setAge(int age) {
                this.Age.set(age);
            }

            public String getResidence() {
                return Residence.get();
            }

            public SimpleStringProperty residenceProperty() {
                return Residence;
            }

            public void setResidence(String residence) {
                this.Residence.set(residence);
            }
        }


    }

