package apps.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

;

public class TithePopupController {
    @FXML
    private Button cancel_button;

    public void cancel_button_onclick() {
        Stage stage = (Stage) cancel_button.getScene().getWindow();
        stage.close();
    }
}
