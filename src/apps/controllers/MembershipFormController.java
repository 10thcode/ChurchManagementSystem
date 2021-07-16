package apps.controllers;

import apps.DbManuplation.DbManipulate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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


    DbManipulate dbManipulate = new DbManipulate();


    ObservableList<String> gender_dropdown_elements = FXCollections.observableArrayList("Male", "Female");
    ObservableList<String> marital_status_dropdown_elements = FXCollections.observableArrayList("Single",
            "Married", "Divorced");
    ObservableList<String> children_number_dropdown_elements = FXCollections.observableArrayList("1", "2",
            "3", "4", "5", "6", "7", "8", "9", "10", "None");
    ObservableList<String> occupation_dropdown_elements = FXCollections.observableArrayList("Teacher",
            "Trader", "Lawyer", "Accountant", "Mechanic");
    ObservableList<String> region_dropdown_elements = FXCollections.observableArrayList("Volta", "Eastern",
            "Central", "Ashanti", "Northern", "Greater Accra", "Western", "Upper West", "Upper East", "Brong Ahafo",
            "Ahafo", "North East", "Oti", "Western North", "Bono", "Cape Coast", "Savannah");
    ObservableList<String> nationality_dropdown_elements = FXCollections.observableArrayList("Ghanaian", "Nigerian",
            "Togolese");
    ObservableList<String> education_level_dropdown_elements = FXCollections.observableArrayList("Primary",
            "Junior High", "Senior High", "Tertiary");
    ObservableList<String> relationship_dropdown_elements = FXCollections.observableArrayList("Father", "Mother",
            "Uncle", "Aunt", "Son", "Daughter");

    List<TextField> required_fields = new ArrayList<>();
    List<ChoiceBox<String>> required_dropdowns = new ArrayList<>();

    public  void  initialize(){
        marital_status_choicebox.getItems().addAll(marital_status_dropdown_elements);
        children_num_choicebox.getItems().addAll(children_number_dropdown_elements);
        cr_occupation_choicebox.getItems().addAll(occupation_dropdown_elements);
        occupation_choicebox.getItems().addAll(occupation_dropdown_elements);
        cr_relationship_choicebox.getItems().addAll(relationship_dropdown_elements);
        education_level_choicebox.getItems().addAll(education_level_dropdown_elements);
        nationality_choicebox.getItems().addAll(nationality_dropdown_elements);
        region_choicebox.getItems().addAll(region_dropdown_elements);
        gender_choicebox.getItems().addAll(gender_dropdown_elements);

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
    }

    @FXML private void clear_button_onclick() {
        setMembershipId(null);
        setFirstName(null);
        setLastName(null);
        setOtherNames(null);
        setGender(null);
        setDob(null);   //To be modified.
        setContact1(null);
        setContact2(null);
        setEmail(null);
        setAddress(null);
        setMaritalStatus(null);
        setChildrenNum(null);
        setOccupation(null);
        setResidence(null);
        setPassportImage("resources/images/default_passport_icon.png");
        setOccupation(null);
        setResidence(null);
        setHometown(null);
        setRegion(null);
        setNationality(null);
        setEducationLevel(null);
        setCrName(null);
        setCrRelationship(null);
        setCrContact(null);
        setCrOccupation(null);
        setCrAddress(null);
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

////    public String getSearch() {
//        return search_field.getText();
//    }

//    public String getMembershipId() {
//        return membership_id_field.getText();
//    }

    public void setMembershipId(String membership_id) {
        this.membership_id_field.setText(membership_id);
    }

//    public String getFirstName() {
//        return first_name_field.getText();
//    }

    public void setFirstName(String firstName) {
        this.first_name_field.setText(firstName);
    }

//    public String getLastName() {
//        return last_name_field.getText();
//    }

    public void setLastName(String last_name) {
        this.last_name_field.setText(last_name);
    }

//    public String getOtherNames() {
//        return other_names_field.getText();
//    }

    public void setOtherNames(String otherNames) {
        this.other_names_field.setText(otherNames);
    }

////    public String getGender() {
//        return gender_choicebox.getValue();
//    }

    public void setGender(String gender) {
        this.gender_choicebox.setValue(gender);
    }

//    public String getDob() {
//        return dob_field.getText();
//    }

    public void setDob(String dob) {
        this.dob_field.setText(dob);
    }

//    public String getContact1() {
//        return contact1_field.getText();
//    }

    public void setContact1(String contact1) {
        this.contact1_field.setText(contact1);
    }

//    public String getContact2() {
//        return contact2_field.getText();
//    }

    public void setContact2(String contact2) {
        this.contact2_field.setText(contact2);
    }

//    public String getEmail() {
//        return email.getText();
//    }

    public void setEmail(String email) {
        this.email.setText(email);
    }

//    public String getAddress() {
//        return address_field.getText();
//    }

    public void setAddress(String address) {
        this.address_field.setText(address);
    }

//    public String getMaritalStatus() {
//        return marital_status_choicebox.getValue();
//    }

    public void setMaritalStatus(String marital_status) {
        this.marital_status_choicebox.setValue(marital_status);
    }

//    public String getChildrenNum() {
//        return children_num_choicebox.getValue();
//    }

    public void setChildrenNum(String children_num) {
        this.children_num_choicebox.setValue(children_num);
    }

//    public String getPassportImageview() {
//        return passport_imageview.getImage().getUrl();
//    }

    public void setPassportImage(String passport_image_url) {
        this.passport_imageview.setImage(new Image(passport_image_url));
    }

//    public String getOccupation() {
//        return occupation_choicebox.getValue();
//    }

    public void setOccupation(String occupation) {
        this.occupation_choicebox.setValue(occupation);
    }

//    public String getResidence() {
//        return residence_field.getText();
//    }

    public void setResidence(String residence) {
        this.residence_field.setText(residence);
    }

//    public String getHometown() {
//        return hometown_field.getText();
//    }

    public void setHometown(String hometown) {
        this.hometown_field.setText(hometown);
    }

//    public String getRegion() {
//        return region_choicebox.getValue();
//    }

    public void setRegion(String region) {
        this.region_choicebox.setValue(region);
    }

//    public String getNationality() {
//        return nationality_choicebox.getValue();
//    }

    public void setNationality(String nationality) {
        this.nationality_choicebox.setValue(nationality);
    }

//    public String getEducationLevel() {
//        return education_level_choicebox.getValue();
//    }

    public void setEducationLevel(String education_level) {
        this.education_level_choicebox.setValue(education_level);
    }

//    public String getCrName() {
//        return cr_name_field.getText();
//    }

    public void setCrName(String cr_name) {
        this.cr_name_field.setText(cr_name);
    }

//    public String getCrContact() {
//        return cr_contact_field.getText();
//    }

    public void setCrContact(String cr_contact) {
        this.cr_contact_field.setText(cr_contact);
    }

//    public String getCrAddress() {
//        return cr_address_field.getText();
//    }

    public void setCrAddress(String cr_address) {
        this.cr_address_field.setText(cr_address);
    }

//    public String getCrRelationship() {
//        return cr_relationship_choicebox.getValue();
//    }

    public void setCrRelationship(String cr_relationship) {
        this.cr_relationship_choicebox.setValue(cr_relationship);
    }

//    public String getCrOccupation() {
//        return cr_occupation_choicebox.getValue();
//    }

    public void setCrOccupation(String cr_occupation) {
        this.cr_occupation_choicebox.setValue(cr_occupation);
    }

    public void contact_field1_onclick(KeyEvent keyEvent) {
        String text = contact1_field.getText().toLowerCase();
        char c = text.charAt(text.length()-1);
        if (c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f'|| c == 'g' || c == 'h' || c == 'i' || c == 'j' || c == 'k' || c == 'l' || c == 'm' || c == 'n' || c == 'o' || c == 'p' || c == 'q' || c == 'r' || c == 's' || c == 't' || c == 'u' || c == 'v' || c == 'w' || c == 'x' || c == 'y' || c == 'z'){
            setContact1(null);
        }

    }

    public void contact_field2_onclick(KeyEvent keyEvent) {
        String text = contact2_field.getText().toLowerCase();
        char c = text.charAt(text.length()-1);
        if (c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f'|| c == 'g' || c == 'h' || c == 'i' || c == 'j' || c == 'k' || c == 'l' || c == 'm' || c == 'n' || c == 'o' || c == 'p' || c == 'q' || c == 'r' || c == 's' || c == 't' || c == 'u' || c == 'v' || c == 'w' || c == 'x' || c == 'y' || c == 'z'){
            setContact2(null);
        }


    }

    public void cr_contact_on_Key_released(KeyEvent keyEvent) {

        String text = cr_contact_field.getText().toLowerCase();
        char c = text.charAt(text.length()-1);
        if (c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f'|| c == 'g' || c == 'h' || c == 'i' || c == 'j' || c == 'k' || c == 'l' || c == 'm' || c == 'n' || c == 'o' || c == 'p' || c == 'q' || c == 'r' || c == 's' || c == 't' || c == 'u' || c == 'v' || c == 'w' || c == 'x' || c == 'y' || c == 'z'){
            setCrContact(null);
        }

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
}
