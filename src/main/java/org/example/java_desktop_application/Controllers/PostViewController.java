package org.example.java_desktop_application.Controllers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.example.java_desktop_application.Models.*;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import java.util.ArrayList;

public class PostViewController {

    @FXML
    private VBox postContainer;

    ArrayList<JobPosting> jobs = new ArrayList<>();

    public void setJobPostings(ArrayList<JobPosting> array) {

        for (JobPosting job : array) {
            HBox jobBox = createJobBox(job);
            postContainer.getChildren().add(jobBox);
            jobs.add(job);
        }

    }

    private HBox createJobBox(JobPosting job) {

        HBox jobBox = new HBox();
        Label companyLabel = new Label(job.getCompany().getName() + " - " + "$" +job.getSalary().toString());
        Label roleLabel = new Label(job.getRole().getTitle() + " - " + job.getLevel());

        companyLabel.setStyle("-fx-font-weight: bold;");

        Button applyButton = new Button();
        applyButton.setText("Apply");
        applyButton.setStyle("-fx-font-weight: bold;");

        VBox leftCol = new VBox();
        leftCol.setPadding(new Insets(0, 0, 0, 10));
        leftCol.getChildren().addAll(companyLabel, roleLabel);

        VBox rightCol = new VBox();
        rightCol.setPadding(new Insets(0, 10, 0, 0));
        rightCol.getChildren().addAll(applyButton);

        jobBox.setAlignment(Pos.CENTER_LEFT);

        HBox.setHgrow(leftCol, Priority.ALWAYS);
        HBox.setHgrow(rightCol, Priority.ALWAYS);

        leftCol.setAlignment(Pos.CENTER_LEFT);
        rightCol.setAlignment(Pos.CENTER_RIGHT);

        jobBox.setStyle("-fx-padding: 5; -fx-border-color: #d4d4d4; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-min-height: 48px;");

        jobBox.getChildren().addAll(leftCol, rightCol);

        HBox.setMargin(companyLabel, new Insets(5));
        HBox.setMargin(roleLabel, new Insets(5));
        return jobBox;

    }

}
