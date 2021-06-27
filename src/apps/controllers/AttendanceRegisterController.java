package apps.controllers;
import animatefx.animation.FadeIn;
import apps.DbManuplation.DbManipulate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AttendanceRegisterController implements Serializable {

    @FXML
    public TableView Table;
    @FXML
    private TableColumn date;
    @FXML
    private TableColumn service;
    @FXML
    private TableColumn no_of_male;
    @FXML
    private TableColumn no_of_female;
    @FXML
    private TableColumn no_of_children;
    @FXML
    private TableColumn total;
    String TABLE_NAME = "dbo.ATTENDANCE_TABLE";
    private ResultSet resultSet;


    private String Date;
    private String Service;
    private int num_of_male;
    private int num_of_female;
    private int num_of_children;
    private int Total;
    DbManipulate dbManipulate;

    public void setTable(TableView table) {
        Table = table;
    }

    public ObservableList data = FXCollections.observableArrayList();

    public void readData(){
        String Query = "SELECT * FROM " + TABLE_NAME;
        resultSet = dbManipulate.retrieveData(Query);

        try{
            if (resultSet != null){
                while (resultSet.next()){
                  Date=  resultSet.getString(0);
                  Service = resultSet.getString(1);
                  num_of_male = resultSet.getInt(2);
                  num_of_female = resultSet.getInt(3);
                  num_of_children = resultSet.getInt(4);
                  Total = resultSet.getInt(5);
                  System.out.println("yaay");


                data.addAll(Date, Service, num_of_male, num_of_female, num_of_children);

                Table.setItems(data);


                }
            }
        }catch (SQLException e){

        }

        readData();



    }

}
