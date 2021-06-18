package apps.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    @FXML
    private Button login_button;
    @FXML
    private Label login_error_message;
    @FXML
    private Hyperlink change_password_link;
    @FXML
    private TextField login_username;
    @FXML
    private PasswordField login_password;

    @FXML
    public void change_password_link_onclick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../resources/views/change_password.fxml")));
        Stage stage = (Stage) change_password_link.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void login_button_onclick() throws IOException {
        String username = String.valueOf(login_username.getCharacters());
        String password = String.valueOf(login_password.getCharacters());
        login_error_message.setVisible(username.isBlank() || password.isBlank());
        //This is just a test
        if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../resources/views/home.fxml")));
            Stage stage = (Stage) login_button.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Church Management System - Home");
        }
    }

    public String get_login_username() {
        return String.valueOf(login_username.getCharacters());
    }

    public String get_login_password(){
        return String.valueOf(login_password.getCharacters());
    }
}
