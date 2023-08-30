package net.niceeli.gravitarviewer.gravitarviewer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GravatarController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}