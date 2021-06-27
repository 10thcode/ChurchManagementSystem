package apps.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class TitheFormController {
    @FXML
    private void receive_cash_onclick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("../../resources/views/tithe_popup.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void visitors_registration_onclick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("../../resources/views/visitors_registration.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
