/********************************************************************************
 * APD545 – Workshop #2
 * I declare that this assignment is my own work in accordance with Seneca Academic Policy.
 * No part of this assignment has been copied manually or electronically from any other source
 * (including web sites) or distributed to other students.
 *
 * Name:         Evan Boileau
 * Student ID:   048655096
 * Date:         6/6/2024
 *
 *
 ********************************************************************************/

package org.example.java_desktop_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.java_desktop_application.Controllers.*;
import org.example.java_desktop_application.Models.*;

import java.io.IOException;
import java.util.ArrayList;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Applicant applicant = Applicant.getInstance();
        ArrayList<JobPosting> jobs = Utils.initialize();

        FXMLLoader headerLoader = new FXMLLoader(getClass().getResource("Views/header.fxml"));
        VBox header = headerLoader.load();
        VBox.setMargin(header, new Insets(10, 20, 10, 20));
        MainViewController mainViewController = headerLoader.getController();

        FXMLLoader applicantLoader = new FXMLLoader(getClass().getResource("Views/applicant.fxml"));
        VBox applicantSpacer = applicantLoader.load();

        FXMLLoader skillLoader = new FXMLLoader(getClass().getResource("Views/skills.fxml"));
        VBox skills = skillLoader.load();
        SkillsViewController skillsController = skillLoader.getController();
        skillsController.setApplicant(applicant);

        FXMLLoader appliedLoader = new FXMLLoader(getClass().getResource("Views/appliedJobs.fxml"));
        VBox appliedJobs = appliedLoader.load();
        AppliedJobsViewController appliedJobsController = appliedLoader.getController();
        appliedJobsController.setApplicant(applicant);

        HBox applicantView = new HBox();
        applicantView.setSpacing(10);
        HBox.setMargin(applicantSpacer, new Insets(0, 0, 0, 20));
        applicantView.getChildren().addAll(applicantSpacer, skills, appliedJobs);
        ApplicantViewController applicantController = applicantLoader.getController();
        applicantController.setApplicant(applicant);

        FXMLLoader postLoader = new FXMLLoader(getClass().getResource("Views/postings.fxml"));
        VBox posts = postLoader.load();
        VBox.setMargin(posts, new Insets(10, 20, 10, 20));
        PostViewController postController = postLoader.getController();
        postController.setJobPostings(jobs);
        postController.setApplicant(applicant);
        postController.setAppliedJobsController(appliedJobsController);

        FXMLLoader exitLoader = new FXMLLoader(getClass().getResource("Views/exit.fxml"));
        VBox exit = exitLoader.load();

        applicantController.setMainViewController(mainViewController);
        applicantController.setPostViewController(postController);
        applicantController.setSkillsViewController(skillsController);
        applicantController.setAppliedJobsViewController(appliedJobsController);

        VBox view = new VBox();
        view.setStyle("-fx-background-color: white;");
        view.getChildren().addAll(header, applicantView, posts, exit);

        Scene scene = new Scene(view, 860, 620);

        stage.setResizable(false);
        stage.setMaximized(false);

        stage.setTitle("APD 545 - Job Application Program");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) { launch(); }

}