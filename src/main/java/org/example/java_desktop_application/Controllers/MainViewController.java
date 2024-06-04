package org.example.java_desktop_application.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainViewController {

    @FXML
    private Label applicantNameLabel;

    @FXML
    protected void onExitClick() { System.exit(0); }

    public void updateName(String str) { applicantNameLabel.setText(str); }

}