package apps.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PledgeForm {
    public void visitors_registration_onclick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("../../resources/views/visitors_registration.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
