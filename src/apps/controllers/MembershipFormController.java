package apps.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MembershipFormController {
    ObservableList<String> gender_dropdown_elements = FXCollections.observableArrayList("Male", "Female");
    ObservableList<String> marital_status_dropdown_elements = FXCollections.observableArrayList("Single",
            "Married", "Divorced");
    ObservableList<String> children_number_dropdown_elements = FXCollections.observableArrayList("1", "2",
            "3", "4", "5", "6", "7", "8", "9", "10");
    ObservableList<String> occupation_dropdown_elements = FXCollections.observableArrayList("Teacher",
            "Trader", "Lawyer", "Accountant", "Mechanic");
    ObservableList<String> region_dropdown_elements = FXCollections.observableArrayList("Volta", "Eastern",
            "Central", "Ashanti", "Northern", "Greater Accra", "Western", "Upper West", "Upper East", "Brong Ahafo",
            "Ahafo", "North East", "Oti", "Western North", "Bono", "Cape Coast", "Savannah");
    ObservableList<String> nationality_dropdown_elements = FXCollections.observableArrayList("Ghanian", "Nigerian",
            "Togolese");
    ObservableList<String> education_level_dropdown_elements = FXCollections.observableArrayList("Primary",
            "Junior High", "Senior High", "Polytechnic", "University");
    ObservableList<String> relationship_dropdown_elements = FXCollections.observableArrayList("Father", "Mohter",
            "Uncle", "Aunt", "Son", "Daughter");
    @FXML private TextArea membership_id_field;
    @FXML private TextArea first_name_field;
    @FXML private TextArea last_name_field;
    @FXML private TextArea other_names_field;
    @FXML private ChoiceBox<String> gender_choicebox;
    @FXML private TextArea dob_field;
    @FXML private TextArea contact1_field;
    @FXML private TextArea contact2_field;
    @FXML private TextArea email;
    @FXML private TextArea address_field;
    @FXML private ChoiceBox<String> marital_status_choicebox;
    @FXML private ChoiceBox<String> children_num_choicebox;
    @FXML private ImageView passport_imageview;
    @FXML private ChoiceBox<String> occupation_choicebox;
    @FXML private TextArea residence_field;
    @FXML private TextArea hometown_field;
    @FXML private ChoiceBox<String> region_choicebox;
    @FXML private ChoiceBox<String> nationality_choicebox;
    @FXML private ChoiceBox<String> education_level_choicebox;
    @FXML private TextArea cr_name_field;
    @FXML private TextArea cr_contact_field;
    @FXML private TextArea cr_address_field;
    @FXML private ChoiceBox<String> cr_relationship_choicebox;
    @FXML private ChoiceBox<String> cr_occupation_choicebox;
    @FXML private TextField search_field;

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
        // TODO: 6/21/21 Get field values and store in database.

        //closing window after details have successfully been saved in the database.
        Stage stage = (Stage) membership_id_field.getScene().getWindow();
        stage.close();
    }

    public String getSearch() {
        return search_field.getText();
    }

    public String getMembershipId() {
        return membership_id_field.getText();
    }

    public void setMembershipId(String membership_id) {
        this.membership_id_field.setText(membership_id);
    }

    public String getFirstName() {
        return first_name_field.getText();
    }

    public void setFirstName(String firstName) {
        this.first_name_field.setText(firstName);
    }

    public String getLastName() {
        return last_name_field.getText();
    }

    public void setLastName(String last_name) {
        this.last_name_field.setText(last_name);
    }

    public String getOtherNames() {
        return other_names_field.getText();
    }

    public void setOtherNames(String otherNames) {
        this.other_names_field.setText(otherNames);
    }

    public String getGender() {
        return gender_choicebox.getValue().toString();
    }

    public void setGender(String gender) {
        this.gender_choicebox.setValue(gender);
    }

    public String getDob() {
        return dob_field.getText();
    }

    public void setDob(String dob) {
        this.dob_field.setText(dob);
    }

    public String getContact1() {
        return contact1_field.getText();
    }

    public void setContact1(String contact1) {
        this.contact1_field.setText(contact1);
    }

    public String getContact2() {
        return contact2_field.getText();
    }

    public void setContact2(String contact2) {
        this.contact2_field.setText(contact2);
    }

    public String getEmail() {
        return email.getText();
    }

    public void setEmail(String email) {
        this.email.setText(email);
    }

    public String getAddress() {
        return address_field.getText();
    }

    public void setAddress(String address) {
        this.address_field.setText(address);
    }

    public String getMaritalStatus() {
        return marital_status_choicebox.getValue().toString();
    }

    public void setMaritalStatus(String marital_status) {
        this.marital_status_choicebox.setValue(marital_status);
    }

    public String getChildrenNum() {
        return children_num_choicebox.getValue().toString();
    }

    public void setChildrenNum(String children_num) {
        this.children_num_choicebox.setValue(children_num);
    }

    public String getPassportImageview() {
        return passport_imageview.getImage().getUrl();
    }

    public void setPassportImage(String passport_image_url) {
        this.passport_imageview.setImage(new Image(passport_image_url));
    }

    public String getOccupation() {
        return occupation_choicebox.getValue().toString();
    }

    public void setOccupation(String occupation) {
        this.occupation_choicebox.setValue(occupation);
    }

    public String getResidence() {
        return residence_field.getText();
    }

    public void setResidence(String residence) {
        this.residence_field.setText(residence);
    }

    public String getHometown() {
        return hometown_field.getText();
    }

    public void setHometown(String hometown) {
        this.hometown_field.setText(hometown);
    }

    public String getRegion() {
        return region_choicebox.getValue().toString();
    }

    public void setRegion(String region) {
        this.region_choicebox.setValue(region);
    }

    public String getNationality() {
        return nationality_choicebox.getValue().toString();
    }

    public void setNationality(String nationality) {
        this.nationality_choicebox.setValue(nationality);
    }

    public String getEducationLevel() {
        return education_level_choicebox.getValue().toString();
    }

    public void setEducationLevel(String education_level) {
        this.education_level_choicebox.setValue(education_level);
    }

    public String getCrName() {
        return cr_name_field.getText();
    }

    public void setCrName(String cr_name) {
        this.cr_name_field.setText(cr_name);
    }

    public String getCrContact() {
        return cr_contact_field.getText();
    }

    public void setCrContact(String cr_contact) {
        this.cr_contact_field.setText(cr_contact);
    }

    public String getCrAddress() {
        return cr_address_field.getText();
    }

    public void setCrAddress(String cr_address) {
        this.cr_address_field.setText(cr_address);
    }

    public String getCrRelationship() {
        return cr_relationship_choicebox.getValue().toString();
    }

    public void setCrRelationship(String cr_relationship) {
        this.cr_relationship_choicebox.setValue(cr_relationship);
    }

    public String getCrOccupation() {
        return cr_occupation_choicebox.getValue().toString();
    }

    public void setCrOccupation(String cr_occupation) {
        this.cr_occupation_choicebox.setValue(cr_occupation);
    }
}
