package apps.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

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
    private void membership_tab_onclick() {
    }

    @FXML
    private void tithe_dropdown_onclick() {
        tithe_dropdown_icon.setRotate(tithe_dropdown_icon.getRotate() == 0 ? -90 : 0);
        tithe_dropdown_list.setVisible(!tithe_dropdown_list.isVisible());
        tithe_dropdown_list.setItems(tithe_list_elements);
    }

    @FXML
    private void tithe_tab_onclick() {
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
}
