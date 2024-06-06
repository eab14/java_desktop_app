package org.example.java_desktop_application.Controllers;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.java_desktop_application.Models.*;
import javafx.fxml.FXML;
import java.util.ArrayList;

public class PostViewController {

    private Applicant applicant;
    private AppliedJobsViewController appliedJobsController;

    @FXML
    private VBox postContainer;

    ArrayList<JobPosting> jobs = new ArrayList<>();

    public void setApplicant(Applicant applicant) { this.applicant = applicant; }

    public void setJobPostings(ArrayList<JobPosting> array) {

        for (JobPosting job : array) {
            HBox jobBox = createJobBox(job);
            postContainer.getChildren().add(jobBox);
            jobs.add(job);
        }

    }

    public void setAppliedJobsController(AppliedJobsViewController appliedJobsController) { this.appliedJobsController = appliedJobsController; }

    private HBox createJobBox(JobPosting job) {

        HBox jobBox = new HBox();
        Label companyLabel = new Label(job.getCompany().getName() + " - $" + String.format("%.2f", job.getSalary()));
        Label roleLabel = new Label(job.getRole().getTitle() + " - " + job.getLevel());

        companyLabel.setStyle("-fx-font-weight: bold;");

        HBox buttonsContainer = new HBox();

        Button applyButton = new Button();
        applyButton.setText("Apply");
        applyButton.setStyle("-fx-font-weight: bold;");

        applyButton.setOnAction(event -> {

            if (applicant.verifyAppilcant()) {

                applicant.addAppliedJob(job);
                jobs.remove(job);
                appliedJobsController.updateAppliedJobs();
                updateJobPostings();

            }

            else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error: Applicant verification failed!");
                alert.showAndWait();

            }

        });

        Button detailsButton = new Button();
        detailsButton.setText("Details");
        detailsButton.setStyle("-fx-font-weight: bold;");

        detailsButton.setOnAction(event -> {
            showJobDetailsWindow(job);
        });

        buttonsContainer.setSpacing(10);
        buttonsContainer.getChildren().addAll(detailsButton, applyButton);

        VBox leftCol = new VBox();
        leftCol.setPadding(new Insets(0, 0, 0, 10));
        leftCol.getChildren().addAll(companyLabel, roleLabel);

        VBox rightCol = new VBox();
        rightCol.getChildren().addAll(buttonsContainer);

        buttonsContainer.setAlignment(Pos.CENTER_RIGHT);

        HBox.setHgrow(leftCol, Priority.ALWAYS);
        HBox.setHgrow(rightCol, Priority.ALWAYS);
        HBox.setHgrow(buttonsContainer, Priority.ALWAYS);

        leftCol.setAlignment(Pos.CENTER_LEFT);
        rightCol.setAlignment(Pos.CENTER_RIGHT);

        jobBox.setStyle("-fx-padding: 5; -fx-border-color: #d4d4d4; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-min-height: 48px;");

        jobBox.getChildren().addAll(leftCol, rightCol);

        HBox.setMargin(companyLabel, new Insets(5));
        HBox.setMargin(roleLabel, new Insets(5));
        HBox.setMargin(rightCol, new Insets(0, 10, 0, 0));

        return jobBox;

    }

    private void showJobDetailsWindow(JobPosting job) {

        Stage stage = new Stage();
        stage.setTitle("Job Details");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.setStyle("-fx-background-color: white;");

        layout.getChildren().addAll(
                createLabelWithContent("Company:", job.getCompany().getName()),
                createLabelWithContent("Role:", job.getRole().getTitle()),
                createLabelWithContent("Salary:", String.format("$%.2f", job.getSalary())),
                createLabelWithContent("Level:", job.getLevel()),
                createLabelWithContent("Required Skills:", String.join(", ", job.getRequiredSkills())),
                createLabelWithContent("Description:", job.getRole().getDescription())
        );

        Button closeButton = createCloseButton(stage);

        HBox closeButtonContainer = new HBox();
        closeButtonContainer.getChildren().add(closeButton);
        closeButtonContainer.setAlignment(Pos.CENTER_RIGHT);
        closeButtonContainer.setPadding(new Insets(10));

        layout.getChildren().add(closeButtonContainer);

        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        Scene scene = new Scene(scrollPane, 500, 360);

        stage.setResizable(false);
        stage.setMaximized(false);

        stage.setScene(scene);
        stage.show();

        Platform.runLater(() -> scrollPane.setVvalue(0));

    }

    private VBox createLabelWithContent(String labelText, String content) {

        Label label = new Label(labelText);
        label.setStyle("-fx-font-weight: bold;");

        Label contentLabel = new Label(content);
        contentLabel.setWrapText(true);

        VBox container = new VBox(5);
        container.getChildren().addAll(label, contentLabel);
        container.setStyle("-fx-padding: 5; -fx-border-color: #d4d4d4; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-min-height: 48px;");
        VBox.setMargin(container, new Insets(5, 10, 0, 10));

        return container;
    }

    private Button createCloseButton(Stage stage) {

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());
        return closeButton;

    }

    private void updateJobPostings() {

        postContainer.getChildren().clear();

        for (JobPosting job : jobs) {
            HBox jobBox = createJobBox(job);
            postContainer.getChildren().add(jobBox);
        }

    }

    public void clearJobPostings() {

        jobs = new ArrayList<>();
        postContainer.getChildren().clear();

    }

}
