package org.example.java_desktop_application.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.example.java_desktop_application.Models.Applicant;
import org.example.java_desktop_application.Models.Utils;

import java.util.Objects;

public class ApplicantViewController extends Pane {

    private Applicant applicant;

    private MainViewController mainViewController;
    private PostViewController postController;
    private SkillsViewController skillsController;
    private AppliedJobsViewController appliedJobsController;

    public void setApplicant(Applicant applicant) { this.applicant = applicant; }

    public void setMainViewController(MainViewController mainViewController) { this.mainViewController = mainViewController; }
    public void setPostViewController(PostViewController postController) { this.postController = postController; }
    public void setSkillsViewController(SkillsViewController skillsController) { this.skillsController = skillsController; }
    public void setAppliedJobsViewController(AppliedJobsViewController appliedJobsController) { this.appliedJobsController = appliedJobsController; }

    @FXML
    private TextField applicantName;

    @FXML
    private TextField applicantPhone;

    @FXML
    private TextField applicantEmail;

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

        skillsController.clearSkills();

        postController.clearJobPostings();
        postController.setJobPostings(Utils.initialize());

        appliedJobsController.clearJobPostings();

        applicant.setName("");
        applicant.setPhone("");
        applicant.setEmail("");

        applicantName.setText("");
        applicantPhone.setText("");
        applicantEmail.setText("");

        mainViewController.updateName(applicant.getName());

        updateApplicant();

    }

}