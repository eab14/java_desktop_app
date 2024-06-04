package org.example.java_desktop_application.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.example.java_desktop_application.Models.Applicant;

import java.util.Objects;

public class ApplicantViewController extends Pane {

    Applicant applicant = new Applicant();

    @FXML
    private TextField applicantName;

    @FXML
    private TextField applicantPhone;

    @FXML
    private TextField applicantEmail;

    private MainViewController mainViewController;

    public void setMainViewController(MainViewController mainViewController) { this.mainViewController = mainViewController; }

    @FXML
    public void updateApplicant() {

        if (!Objects.equals(applicantName.getText(), "")) {

            applicant.setName(applicantName.getText());
            mainViewController.updateName(applicant.getName());

        }

        if (!Objects.equals(applicantPhone.getText(), "")) applicant.setPhone(applicantPhone.getText());
        if (!Objects.equals(applicantEmail.getText(), "")) applicant.setEmail(applicantEmail.getText());


    }

    public void clearInputs() {

        applicant = new Applicant();

        applicantName.setText("");
        applicantPhone.setText("");
        applicantEmail.setText("");

        mainViewController.updateName(applicant.getName());

        updateApplicant();

    }



}