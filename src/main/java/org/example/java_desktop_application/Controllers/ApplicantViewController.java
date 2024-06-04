package org.example.java_desktop_application.Controllers;
import javafx.fxml.FXML;
import org.example.java_desktop_application.Models.*;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;


import java.util.ArrayList;

public class ApplicantViewController extends Pane {

    private Applicant model;
    private ListView<String> skills;

    @FXML
    private TextField applicantName;

    @FXML
    private TextField applicantPhone;

    @FXML
    private TextField applicantEmail;

    @FXML
    private Label applicantNameLabel;

    @FXML
    public void updateApplicant() {

        String name = applicantName.getText();
        String phone = applicantPhone.getText();
        String email = applicantEmail.getText();

        updateName(name);

    }

    public void initialize() { applicantNameLabel.setText("Applicant Name"); }
    public void updateName(String str) { applicantNameLabel.setText(str); }
    public void clearInputs() {

        applicantName.setText("");
        applicantPhone.setText("");
        applicantEmail.setText("");

    }

}
