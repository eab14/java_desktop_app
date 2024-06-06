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

        Applicant applicant = new Applicant();
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

        HBox applicantView = new HBox();
        applicantView.setSpacing(20);
        HBox.setMargin(applicantSpacer, new Insets(0, 0, 0, 20));
        applicantView.getChildren().addAll(applicantSpacer, skills);
        ApplicantViewController applicantController = applicantLoader.getController();
        applicantController.setApplicant(applicant);

        FXMLLoader postLoader = new FXMLLoader(getClass().getResource("Views/postings.fxml"));
        VBox posts = postLoader.load();
        VBox.setMargin(posts, new Insets(10, 20, 10, 20));
        PostViewController postController = postLoader.getController();
        postController.setJobPostings(jobs);

        FXMLLoader exitLoader = new FXMLLoader(getClass().getResource("Views/exit.fxml"));
        VBox exit = exitLoader.load();

        applicantController.setMainViewController(mainViewController);

        VBox view = new VBox();
        view.setStyle("-fx-background-color: white;");
        view.getChildren().addAll(header, applicantView, posts, exit);

        Scene scene = new Scene(view, 820, 620);

        stage.setResizable(false);
        stage.setMaximized(false);

        stage.setTitle("APD 545 - Job Application Program");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) { launch(); }

}