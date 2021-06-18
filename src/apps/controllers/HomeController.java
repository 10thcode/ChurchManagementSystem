package apps.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class HomeController {
    ObservableList<String> membership_list_elements = FXCollections.observableArrayList("Attendance",
            "Membership Register", "Attendance Register");
    ObservableList<String> tithe_list_elements = FXCollections.observableArrayList("Offering",
            "Offering Register", "Tithe Register");
    ObservableList<String> fundraising_list_elements = FXCollections.observableArrayList("Pledge",
            "Pledge Register", "Fund-raising Register", "Special Project", "Special Project Register");
    ObservableList<String> welfare_list_elements = FXCollections.observableArrayList("Welfare Register");

    @FXML
     ListView<String> membership_dropdown_list = new ListView<>();
    @FXML
    ListView<String> tithe_dropdown_list = new ListView<>();
    @FXML
    ListView<String> fundraising_dropdown_list = new ListView<>();
    @FXML
    ListView<String> welfare_dropdown_list = new ListView<>();

    @FXML
    public void membership_dropdown_onclick(){
        membership_dropdown_list.setVisible(!membership_dropdown_list.isVisible());
        membership_dropdown_list.setItems(membership_list_elements);
    }

    public void membership_tab_onclick() {
    }

    public void tithe_dropdown_onclick() {
        tithe_dropdown_list.setVisible(!tithe_dropdown_list.isVisible());
        tithe_dropdown_list.setItems(tithe_list_elements);
    }

    public void tithe_tab_onclick() {
    }

    public void fundraising_dropdown_onclick() {
        fundraising_dropdown_list.setVisible(!fundraising_dropdown_list.isVisible());
        fundraising_dropdown_list.setItems(fundraising_list_elements);
    }

    public void fundraising_tab_onclick() {
    }

    public void welfare_dropdown_onclick() {
        welfare_dropdown_list.setVisible(!welfare_dropdown_list.isVisible());
        welfare_dropdown_list.setItems(welfare_list_elements);
    }

    public void welfare_tab_onclick() {
    }
}
