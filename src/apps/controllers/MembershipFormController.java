package apps.controllers;

import animatefx.animation.FadeIn;
import apps.DbManuplation.DbManipulate;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MembershipFormController {
    public TextField membership_id_field;
    public TextField first_name_field;
    public TextField last_name_field;
    public TextField other_names_field;
    public ChoiceBox<String> gender_choicebox;
    public TextField dob_field;
    public TextField contact1_field;
    public TextField contact2_field;
    public TextField email;
    public TextField address_field;
    public ChoiceBox<String> marital_status_choicebox;
    public ChoiceBox<String> children_num_choicebox;
    public ImageView passport_imageview;
    public ChoiceBox<String> occupation_choicebox;
    public TextField residence_field;
    public TextField hometown_field;
    public ChoiceBox<String> region_choicebox;
    public ChoiceBox<String> nationality_choicebox;
    public ChoiceBox<String> education_level_choicebox;
    public TextField cr_contact_field;
    public TextField cr_address_field;
    public ChoiceBox<String> cr_relationship_choicebox;
    public ChoiceBox<String> cr_occupation_choicebox;
    public TextField search_field;
    public TextField cr_name_field;
    public TextField add_textfield;
    public Button add_button;
    public Button add_cancel_button;

    private ChoiceBox<String> source;

    public ResultSet resultSet;
    DbManipulate dbManipulate = new DbManipulate();

    ObservableList<String> gender_dropdown_elements = FXCollections.observableArrayList("Male", "Female");
    ObservableList<String> marital_status_dropdown_elements = FXCollections.observableArrayList("Single",
            "Married", "Divorced", "Separated");
    ObservableList<String> children_number_dropdown_elements = FXCollections.observableArrayList( "None", "1", "2",
            "3", "4", "5", "6", "7", "8", "9", "10", "Add New");
    ObservableList<String> occupation_dropdown_elements = FXCollections.observableArrayList("Teacher",
            "Trader", "Lawyer", "Accountant", "Mechanic", "Add New");
    ObservableList<String> region_dropdown_elements = FXCollections.observableArrayList("Volta", "Eastern",
            "Central", "Ashanti", "Northern", "Greater Accra", "Western", "Upper West", "Upper East", "Brong Ahafo",
            "Ahafo", "North East", "Oti", "Western North", "Bono", "Cape Coast", "Savannah");
    ObservableList<String> nationality_dropdown_elements = FXCollections.observableArrayList("Ghanaian",
            "Nigerian", "Togolese", "Add New");
    ObservableList<String> education_level_dropdown_elements = FXCollections.observableArrayList("Primary",
            "Junior High", "Senior High", "Tertiary");
    ObservableList<String> relationship_dropdown_elements = FXCollections.observableArrayList("Father", "Mother",
            "Uncle", "Aunt", "Son", "Daughter");

    List<TextField> required_fields = new ArrayList<>();
    List<ChoiceBox<String>> required_dropdowns = new ArrayList<>();

    public  void  initialize() throws IOException {
        marital_status_choicebox.getItems().addAll(marital_status_dropdown_elements);
        children_num_choicebox.getItems().addAll(children_number_dropdown_elements);
        cr_occupation_choicebox.getItems().addAll(occupation_dropdown_elements);
        occupation_choicebox.getItems().addAll(occupation_dropdown_elements);
        cr_relationship_choicebox.getItems().addAll(relationship_dropdown_elements);
        education_level_choicebox.getItems().addAll(education_level_dropdown_elements);
        nationality_choicebox.getItems().addAll(nationality_dropdown_elements);
        region_choicebox.getItems().addAll(region_dropdown_elements);
        gender_choicebox.getItems().addAll(gender_dropdown_elements);

        try {
            File file = new File("children_num_choicebox.txt");
            Scanner scn = new Scanner(file);
            while (scn.hasNextLine()) {
                String data = scn.nextLine();
                children_num_choicebox.getItems().add(children_num_choicebox.getItems().size() - 1, data);
            }
            scn.close();
        } catch (FileNotFoundException e) {
        }

        try {
            File file = new File("occupation_choicebox.txt");
            Scanner scn = new Scanner(file);
            while (scn.hasNextLine()) {
                String data = scn.nextLine();
                occupation_choicebox.getItems().add(occupation_choicebox.getItems().size() -1, data);
            }
            scn.close();
        } catch (FileNotFoundException e) {
        }

        try {
            File file = new File("nationality_choicebox.txt");
            Scanner scn = new Scanner(file);
            while (scn.hasNextLine()) {
                String data = scn.nextLine();
                nationality_choicebox.getItems().add(nationality_choicebox.getItems().size() -1, data);
            }
            scn.close();
        } catch (FileNotFoundException e) {
        }

        add_button.setManaged(false);
        add_textfield.setManaged(false);
        add_cancel_button.setManaged(false);

        required_fields.add(membership_id_field);
        required_fields.add(dob_field);
        required_fields.add(contact1_field);
        required_fields.add(hometown_field);
        required_fields.add(residence_field);
        required_fields.add(first_name_field);
        required_fields.add(last_name_field);
        required_fields.add(address_field);
        required_dropdowns.add(gender_choicebox);
        required_dropdowns.add(marital_status_choicebox);
        required_dropdowns.add(children_num_choicebox);
        required_dropdowns.add(education_level_choicebox);
        required_dropdowns.add(occupation_choicebox);
        required_dropdowns.add(region_choicebox);
        required_dropdowns.add(nationality_choicebox);

        occupation_choicebox.getSelectionModel().selectedIndexProperty()
                .addListener((ObservableValue<? extends Number> ov, Number old, Number new_val) ->{
            if (occupation_choicebox.getSelectionModel().isSelected(occupation_choicebox.getItems().size() -1)){
                source = occupation_choicebox;
                FadeIn fadeIn = new FadeIn();
                fadeIn.setNode(add_textfield);
                fadeIn.play();
                FadeIn add_button_fadeIn = new FadeIn();
                add_button_fadeIn.setNode(add_button);
                add_button_fadeIn.play();
                add_textfield.setVisible(true);
                add_textfield.setManaged(true);
                add_button.setVisible(true);
                add_button.setManaged(true);
                add_cancel_button.setVisible(true);
                add_cancel_button.setManaged(true);
            }
        });

        children_num_choicebox.getSelectionModel().selectedIndexProperty()
                .addListener((ObservableValue<? extends Number> ov, Number old, Number new_val) ->{
            if (children_num_choicebox.getSelectionModel().isSelected(children_num_choicebox.getItems().size() -1)){
                source = children_num_choicebox;
                FadeIn fadeIn = new FadeIn();
                fadeIn.setNode(add_textfield);
                fadeIn.play();
                FadeIn add_button_fadeIn = new FadeIn();
                add_button_fadeIn.setNode(add_button);
                add_button_fadeIn.play();
                add_textfield.setVisible(true);
                add_textfield.setManaged(true);
                add_button.setVisible(true);
                add_button.setManaged(true);
                add_cancel_button.setVisible(true);
                add_cancel_button.setManaged(true);
            }
        });

        nationality_choicebox.getSelectionModel().selectedIndexProperty()
                .addListener((ObservableValue<? extends Number> ov, Number old, Number new_val) ->{
            if (nationality_choicebox.getSelectionModel().isSelected(nationality_choicebox.getItems().size() -1)){
                source = nationality_choicebox;
                FadeIn fadeIn = new FadeIn();
                fadeIn.setNode(add_textfield);
                fadeIn.play();
                FadeIn add_button_fadeIn = new FadeIn();
                add_button_fadeIn.setNode(add_button);
                add_button_fadeIn.play();
                add_textfield.setVisible(true);
                add_textfield.setManaged(true);
                add_button.setVisible(true);
                add_button.setManaged(true);
                add_cancel_button.setVisible(true);
                add_cancel_button.setManaged(true);
            }
        });
    }

    @FXML private void capture_button_onclick() {
    }

    @FXML private void upload_button_onclick() {
        Stage stage = (Stage) membership_id_field.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        try {
            String url = selectedFile.getPath();
            setPassportImage("file:" + url);
        }catch (NullPointerException e) {
            System.out.println("No Image Selected");
        }

    }

    @FXML private void search_button_onclick() {
        String search = search_field.getText();

        //RETRIEVE THE DATA FROM THE DATABASE

        try{
            String Query = "SELECT * FROM MEMBERSHIP_RECORDS_TABLE WHERE MEMBERSHIP_ID=" + "'" +  search + "'";
            resultSet = dbManipulate.retrieveData(Query);

            if (resultSet != null){

                while(resultSet.next()){

                    String Membership_id = resultSet.getString("MEMBERSHIP_ID");
                    String firstName = resultSet.getString("FIRST_NAME");
                    String lastName= resultSet.getString("SURNAME");
                    String otherNames = resultSet.getString("OTHER_NAMES");
                    String genderChoiceValue = resultSet.getString("GENDER");
                    String DateOfBirth = resultSet.getString("DATE_OF_BIRTH");
                    String contactField1 = resultSet.getString("CONTACT_NUMBER_1");
                    String contactField2 = resultSet.getString("CONTACT_NUMBER_2");
                    String emailValue = resultSet.getString("EMAIL");
                    String addressField = resultSet.getString("DIGITAL_ADDRESS");
                    String maritalStatusChoiceValue = resultSet.getString("MARITAL_STATUS");
                    int numberOfChildren = resultSet.getInt("NUMBER_OF_CHILDREN");

                    //Will come back to the image later

                    String occupation = resultSet.getString("OCCUPATION");
                    String residence = resultSet.getString("PLACE_OF_RESIDENCE");
                    String hometown = resultSet.getString("HOME_TOWN");
                    String region = resultSet.getString("REGION");
                    String nationality = resultSet.getString("NATIONALITY");
                    String educationLevel = resultSet.getString("LEVEL_OF_EDUCATION");
                    String closestRelationName = resultSet.getString("CR_NAME");
                    String closestRelationContact = resultSet.getString("CR_CONTACT");
                    String closestRelationAddress = resultSet.getString("CR_ADDRESS");
                    String closestRelationRelationship = resultSet.getString("CR_RELATIONSHIP");
                    String closestRelationOccupation = resultSet.getString("CR_OCCUPATION");


                    //FILL THE FIELDS

                    membership_id_field.setText(Membership_id);
                    first_name_field.setText(firstName);
                    last_name_field.setText(lastName);
                    other_names_field.setText(otherNames);
                    gender_choicebox.setValue(genderChoiceValue);
                    dob_field.setText(DateOfBirth);
                    contact1_field.setText(contactField1);
                    contact2_field.setText(contactField2);
                    email.setText(emailValue);
                    address_field.setText(addressField);
                    marital_status_choicebox.setValue(maritalStatusChoiceValue);
                    children_num_choicebox.setValue(String.valueOf(numberOfChildren));
                    occupation_choicebox.setValue(occupation);
                    residence_field.setText(residence);
                    hometown_field.setText(hometown);
                    region_choicebox.setValue(region);
                    nationality_choicebox.setValue(nationality);
                    education_level_choicebox.setValue(educationLevel);
                    cr_name_field.setText(closestRelationName);
                    cr_relationship_choicebox.setValue(closestRelationRelationship);
                    cr_contact_field.setText(closestRelationContact);
                    cr_occupation_choicebox.setValue(closestRelationOccupation);
                    cr_address_field.setText(closestRelationAddress);


                }

            }

        }catch (Exception e){

        }


    }

    @FXML private void clear_button_onclick() {
        setMembershipId("");
        setFirstName("");
        setLastName("");
        setOtherNames("");
        setGender(null);
        setDob("");   //To be modified.
        setContact1("");
        setContact2("");
        setEmail("");
        setAddress("");
        setMaritalStatus(null);
        setChildrenNum(null);
        setResidence("");
        setPassportImage("resources/images/default_passport_icon.png");
        setOccupation(null);
        setHometown("");
        setRegion(null);
        setNationality(null);
        setEducationLevel(null);
        setCrName("");
        setCrRelationship(null);
        setCrContact("");
        setCrOccupation(null);
        setCrAddress("");
    }

    @FXML private void save_button_onclick() {
        String Membership_id = membership_id_field.getText();
        String firstName = first_name_field.getText();
        String lastName= last_name_field.getText();
        String otherNames = other_names_field.getText();
        String genderChoiceValue = gender_choicebox.getValue();
        String DateOfBirth = dob_field.getText();
        String contactField1 = contact1_field.getText();
        String contactField2 = contact2_field.getText();
        String emailValue = email.getText();
        String addressField = address_field.getText();
        String maritalStatusChoiceValue= marital_status_choicebox.getValue();
        int numberOfChildren = Integer.parseInt(children_num_choicebox.getValue());

        //Will come back to the image later

        String occupation = occupation_choicebox.getValue();
        String residence = residence_field.getText();
        String hometown = hometown_field.getText();
        String region = region_choicebox.getValue();
        String nationality = nationality_choicebox.getValue();
        String educationLevel = education_level_choicebox.getValue();
        String closestRelationName = cr_name_field.getText();
        String closestRelationContact = cr_contact_field.getText();
        String closestRelationAddress = cr_address_field.getText();
        String closestRelationRelationship = cr_relationship_choicebox.getValue();
        String closestRelationOccupation = cr_occupation_choicebox.getValue();

        if (is_valid()){
            try {
                String Query = "INSERT INTO MEMBERSHIP_RECORDS_TABLE VALUES(" + "'" + Membership_id + "'" + ", " + "'" + firstName + "'" + ", " + "'" + lastName + "'" + ", " + "'" + otherNames + "'" + ", " + "'" + genderChoiceValue + "'" + ", " + "'" + DateOfBirth + "'" + ", " + "'" + contactField1 + "'" + ", " + "'" + contactField2 + "'" + ", " + "'" + emailValue + "'" + ", " + "'" + addressField + "'" + ", " + "'" + maritalStatusChoiceValue + "'" + ", "  + numberOfChildren + ", " + "'" + occupation + "'" + ", " + "'" + residence + "'" + ", " + "'" + hometown + "'" + ", " + "'" + region + "'" + ", " + "'" + nationality + "'" + ", " + "'" + educationLevel + "'" + ", " + "'" + closestRelationName + "'" + ", " + "'" + closestRelationContact + "'" + ", " + "'" + closestRelationAddress + "'" + ", " + "'" + closestRelationRelationship + "'" + ", " + "'" + closestRelationOccupation + "'" + ");";

                dbManipulate.InsertIntoDb(Query);
            }catch (Exception exception){

            }

            Stage stage = (Stage) membership_id_field.getScene().getWindow();
            stage.close();
        }
    }

    public void setMembershipId(String membership_id) {
        this.membership_id_field.setText(membership_id);
    }

    public void setFirstName(String firstName) {
        this.first_name_field.setText(firstName);
    }

    public void setLastName(String last_name) {
        this.last_name_field.setText(last_name);
    }

    public void setOtherNames(String otherNames) {
        this.other_names_field.setText(otherNames);
    }

    public void setGender(String gender) {
        this.gender_choicebox.setValue(gender);
    }

    public void setDob(String dob) {
        this.dob_field.setText(dob);
    }

    public void setContact1(String contact1) {
        this.contact1_field.setText(contact1);
    }

    public void setContact2(String contact2) {
        this.contact2_field.setText(contact2);
    }

    public void setEmail(String email) {
        this.email.setText(email);
    }

    public void setAddress(String address) {
        this.address_field.setText(address);
    }

    public void setMaritalStatus(String marital_status) {
        this.marital_status_choicebox.setValue(marital_status);
    }

    public void setChildrenNum(String children_num) {
        this.children_num_choicebox.setValue(children_num);
    }

    public void setPassportImage(String passport_image_url) {
        this.passport_imageview.setImage(new Image(passport_image_url));
    }

    public void setOccupation(String occupation) {
        this.occupation_choicebox.setValue(occupation);
    }

    public void setResidence(String residence) {
        this.residence_field.setText(residence);
    }

    public void setHometown(String hometown) {
        this.hometown_field.setText(hometown);
    }

    public void setRegion(String region) {
        this.region_choicebox.setValue(region);
    }

    public void setNationality(String nationality) {
        this.nationality_choicebox.setValue(nationality);
    }

    public void setEducationLevel(String education_level) {
        this.education_level_choicebox.setValue(education_level);
    }

    public void setCrName(String cr_name) {
        this.cr_name_field.setText(cr_name);
    }

    public void setCrContact(String cr_contact) {
        this.cr_contact_field.setText(cr_contact);
    }

    public void setCrAddress(String cr_address) {
        this.cr_address_field.setText(cr_address);
    }

    public void setCrRelationship(String cr_relationship) {
        this.cr_relationship_choicebox.setValue(cr_relationship);
    }

    public void setCrOccupation(String cr_occupation) {
        this.cr_occupation_choicebox.setValue(cr_occupation);
    }

    public boolean is_valid(){
        boolean valid_state = true;
        for (TextField field : required_fields){
            if(!field.getText().isBlank()){
                field.setStyle("-fx-background-color: white;");
            }else {
                field.setStyle("-fx-background-color: #ff8a80;");
                valid_state = false;
            }
        }

        for (ChoiceBox<String> dropdown : required_dropdowns) {
            if(dropdown.getValue() != null){
                dropdown.setStyle("-fx-background-color: white;");
            }else {
                dropdown.setStyle("-fx-background-color: #ff8a80;");
                valid_state = false;
            }
        }
        return valid_state;
    }

    public void add_button_onclick() {
        if (!add_textfield.getText().isBlank()){
            source.getItems().add(source.getItems().size() - 1,
                    add_textfield.getText());
            source.setValue(add_textfield.getText());
            try(FileWriter fw = new FileWriter(source.getId() + ".txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                out.println(add_textfield.getText());
            } catch (IOException ignored) {
            }
            add_button.setManaged(false);
            add_button.setVisible(false);
            add_textfield.setText("");
            add_textfield.setManaged(false);
            add_textfield.setVisible(false);
            add_cancel_button.setVisible(false);
            add_cancel_button.setManaged(false);
        }else {
            add_textfield.setStyle("-fx-background-color: #ff8a80");
        }
    }

    public void add_cancel_button_onclick() {
        add_button.setManaged(false);
        add_button.setVisible(false);
        add_textfield.setText("");
        add_textfield.setManaged(false);
        add_textfield.setVisible(false);
        add_cancel_button.setVisible(false);
        add_cancel_button.setManaged(false);
        add_textfield.setStyle("-fx-background-color: white");
        source.setValue(null);
    }
}
