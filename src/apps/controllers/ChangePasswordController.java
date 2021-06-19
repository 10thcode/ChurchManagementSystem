package apps.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ChangePasswordController {
    @FXML
    private Button cancel_button;
    @FXML
    public void cancel_button_onclick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../resources/views/login.fxml")));
        Stage stage = (Stage) cancel_button.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Church Management System - Login");
    }
}
