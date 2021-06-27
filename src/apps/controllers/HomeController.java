package apps.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomeController {
    ObservableList<String> membership_list_elements = FXCollections.observableArrayList("Attendance",
            "Membership Register", "Attendance Register");
    ObservableList<String> tithe_list_elements = FXCollections.observableArrayList("Offering",
            "Offering Register", "Tithe Register");
    ObservableList<String> fundraising_list_elements = FXCollections.observableArrayList("Pledge",
            "Pledge Register", "Fund-raising Register", "Special Project", "Special Project Register",
            "Special Project Pledge", "Special Project Pledge Register");
    ObservableList<String> welfare_list_elements = FXCollections.observableArrayList("Welfare Register");

    @FXML ListView<String> membership_dropdown_list;
    @FXML ListView<String> tithe_dropdown_list;
    @FXML ListView<String> fundraising_dropdown_list;
    @FXML ListView<String> welfare_dropdown_list;

    @FXML ImageView membership_dropdown_icon;
    @FXML ImageView tithe_dropdown_icon;
    @FXML ImageView fundraising_dropdown_icon;
    @FXML ImageView welfare_dropdown_icon;

    @FXML
    private void membership_dropdown_onclick(){
        membership_dropdown_icon.setRotate(membership_dropdown_icon.getRotate() == 0 ? -90 : 0);
        membership_dropdown_list.setVisible(!membership_dropdown_list.isVisible());
        membership_dropdown_list.setItems(membership_list_elements);
    }

    @FXML
    private void membership_tab_onclick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("../../resources/views/membership_form.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Church Management - Membership Form");
        stage.show();
    }

    @FXML
    private void tithe_dropdown_onclick() {
        tithe_dropdown_icon.setRotate(tithe_dropdown_icon.getRotate() == 0 ? -90 : 0);
        tithe_dropdown_list.setVisible(!tithe_dropdown_list.isVisible());
        tithe_dropdown_list.setItems(tithe_list_elements);
    }

    @FXML
    private void tithe_tab_onclick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
                .getResource("../../resources/views/tithe_form.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Church Management - Tithe Form");
        stage.show();
    }

    @FXML
    private void fundraising_dropdown_onclick() {
        fundraising_dropdown_icon.setRotate(fundraising_dropdown_icon.getRotate() == 0 ? -90 : 0);
        fundraising_dropdown_list.setVisible(!fundraising_dropdown_list.isVisible());
        fundraising_dropdown_list.setItems(fundraising_list_elements);
    }

    @FXML
    private void fundraising_tab_onclick() {
    }

    @FXML
    private void welfare_dropdown_onclick() {
        welfare_dropdown_icon.setRotate(welfare_dropdown_icon.getRotate() == 0 ? -90 : 0);
        welfare_dropdown_list.setVisible(!welfare_dropdown_list.isVisible());
        welfare_dropdown_list.setItems(welfare_list_elements);
    }

    @FXML
    private void welfare_tab_onclick() {
    }

    public void membership_list_onselect() throws IOException {
        String selected = membership_dropdown_list.getSelectionModel().getSelectedItems().toString();
        Parent root;
        Stage stage = new Stage();
        switch (selected) {
            case "[Membership Register]":
                root = FXMLLoader.load(Objects.requireNonNull(getClass().
                        getResource("../../resources/views/membership_register.fxml")));
                stage.setTitle("Church Management - Membership Record");
                stage.setScene(new Scene(root));
                stage.show();
                break;
            case "[Attendance]":
                root = FXMLLoader.load(Objects.requireNonNull(getClass().
                        getResource("../../resources/views/attendance_form.fxml")));
                stage.setTitle("Church Management - Attendance Form");
                stage.setScene(new Scene(root));
                stage.show();
                break;
            case "[Attendance Register]":
                root = FXMLLoader.load(Objects.requireNonNull(getClass().
                        getResource("../../resources/views/attendance_register.fxml")));
                stage.setTitle("Church Management - Attendance Record");
                stage.setScene(new Scene(root));
                stage.show();
                break;
            default:
                //Do nothing;
        }
    }

    public void tithe_list_onselect() throws IOException {
        String selected = tithe_dropdown_list.getSelectionModel().getSelectedItems().toString();
        Parent root;
        Stage stage = new Stage();
        switch (selected) {
            case "[Offering]":
                root = FXMLLoader.load(Objects.requireNonNull(getClass().
                        getResource("../../resources/views/offering_form.fxml")));
                stage.setTitle("Church Management - Offering Form");
                stage.setScene(new Scene(root));
                stage.show();
                break;
            case "[Offering Register]":
                root = FXMLLoader.load(Objects.requireNonNull(getClass().
                        getResource("../../resources/views/offering_register.fxml")));
                stage.setTitle("Church Management - Attendance Form");
                stage.setScene(new Scene(root));
                stage.show();
                break;
            case "[Tithe Register]":
                root = FXMLLoader.load(Objects.requireNonNull(getClass().
                        getResource("../../resources/views/tithe_register.fxml")));
                stage.setTitle("Church Management - Tithe Record");
                stage.setScene(new Scene(root));
                stage.show();
                break;
            default:
                //Do nothing;
        }
    }
}
