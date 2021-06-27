package apps.controllers;
import animatefx.animation.FadeIn;
import apps.DbManuplation.DbManipulate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AttendanceRegisterController {


    public TableView Table;
    @FXML
    private TableColumn date;

    private String Date;
    private String Service;
    private int num_of_male;
    private int num_of_female;
    private int num_of_children;
    private int Total;







}
