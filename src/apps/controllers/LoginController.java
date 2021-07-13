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

    private DbManipulate dbManipulate;

    private ResultSet resultSet;

    @FXML
    public void change_password_link_onclick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
                .getResource("../../resources/views/change_password.fxml")));
        Stage stage = (Stage) change_password_link.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Church Management - Change Password");
    }

    public void login_button_onclick() throws IOException, SQLException {
        String username = login_username.getText();
        String password = login_password.getText();
        login_error_message.setVisible(username.isBlank() || password.isBlank());

        // create instance of DbManipulate
        dbManipulate = new DbManipulate();

        String query = String.format("Select Username , Password from USER_TABLE\n" +
                "  where Username = '%s' and Password = '%s'",username , password);

        resultSet = dbManipulate.retrieveData(query);

        if (resultSet != null /*username.equalsIgnoreCase("admin") &&
                password.equalsIgnoreCase("admin")*/){
            if (resultSet.next()) {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
                        .getResource("../../resources/views/home.fxml")));
                Stage stage = (Stage) login_button.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Church Management System - Home");
            }
            else {
                FadeIn fadeIn = new FadeIn();
                fadeIn.setNode(login_error_message);
                fadeIn.play();
            }
        }
    }
}
