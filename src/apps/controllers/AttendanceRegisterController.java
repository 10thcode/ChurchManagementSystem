package apps.controllers;
import animatefx.animation.FadeIn;
import apps.DbManuplation.DbManipulate;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class AttendanceRegisterController {

    @FXML
    public TableView<TableModel> Table;
    @FXML
    private TableColumn<TableModel, String> date;
    @FXML
    private TableColumn<TableModel, String> service;
    @FXML
    private TableColumn<TableModel, Integer> no_of_male;
    @FXML
    private TableColumn<TableModel, Integer> no_of_female;
    @FXML
    private TableColumn<TableModel, Integer> no_of_children;
    @FXML
    private TableColumn<TableModel, Integer> total;
    String TABLE_NAME = "dbo.ATTENDANCE_TABLE";
    private ResultSet resultSet;

    public ObservableList<TableModel> data = FXCollections.observableArrayList();

    @FXML
    public void initialize(){


       AttendanceRegisterController();

        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        service.setCellValueFactory(new PropertyValueFactory<>("Service"));
        no_of_male.setCellValueFactory(new PropertyValueFactory<>("no_of_male"));
        no_of_female.setCellValueFactory(new PropertyValueFactory<>("no_of_female"));
        no_of_children.setCellValueFactory(new PropertyValueFactory<>("no_of_children"));
        total.setCellValueFactory(new PropertyValueFactory<>("Total"));

        Table.setItems(data);
       // AttendanceRegisterController();
    }


    public void AttendanceRegisterController() {

        DbManipulate dbManipulate = new DbManipulate();

        String Query = "SELECT * FROM ATTENDANCE_TABLE";
        try {
            resultSet = dbManipulate.retrieveData(Query);

            if (resultSet != null){

                while (resultSet.next())
                {
                    String date = resultSet.getString("DATE");
                    String service = resultSet.getString("SERVICE");
                    int no_of_male = Integer.parseInt(resultSet.getString("NO. OF MALE"));
                    int no_of_female = Integer.parseInt(resultSet.getString("NO. OF FEMALE"));
                    int no_of_children = Integer.parseInt(resultSet.getString("NO. OF CHILDREN"));
                    int total = Integer.parseInt(resultSet.getString("TOTAL"));

                    // adding to observableArrayList
                    data.addAll(new TableModel(date , service , no_of_male , no_of_female, no_of_children , total));
                }
               // Table.refresh();

            }

        }catch (Exception e){

        }

    }


    public class TableModel{

            private SimpleStringProperty Date;
            private SimpleStringProperty Service;
            private SimpleIntegerProperty no_of_male;
            private SimpleIntegerProperty no_of_female;
            private SimpleIntegerProperty no_of_children;
            private SimpleIntegerProperty Total;

        public TableModel(String date, String service, int no_of_male, int no_of_female, int no_of_children, int total) {
            Date = new SimpleStringProperty(date);
            Service =new SimpleStringProperty(service);
            this.no_of_male = new SimpleIntegerProperty(no_of_male);
            this.no_of_female = new SimpleIntegerProperty(no_of_female);
            this.no_of_children =new SimpleIntegerProperty(no_of_children);
            Total = new SimpleIntegerProperty(total);
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

        public String getService() {
            return Service.get();
        }

        public SimpleStringProperty serviceProperty() {
            return Service;
        }

        public void setService(String service) {
            this.Service.set(service);
        }

        public int getNo_of_male() {
            return no_of_male.get();
        }

        public SimpleIntegerProperty no_of_maleProperty() {
            return no_of_male;
        }

        public void setNo_of_male(int no_of_male) {
            this.no_of_male.set(no_of_male);
        }

        public int getNo_of_female() {
            return no_of_female.get();
        }

        public SimpleIntegerProperty no_of_femaleProperty() {
            return no_of_female;
        }

        public void setNo_of_female(int no_of_female) {
            this.no_of_female.set(no_of_female);
        }

        public int getNo_of_children() {
            return no_of_children.get();
        }

        public SimpleIntegerProperty no_of_childrenProperty() {
            return no_of_children;
        }

        public void setNo_of_children(int no_of_children) {
            this.no_of_children.set(no_of_children);
        }

        public int getTotal() {
            return Total.get();
        }

        public SimpleIntegerProperty totalProperty() {
            return Total;
        }

        public void setTotal(int total) {
            this.Total.set(total);
        }
    }


}


