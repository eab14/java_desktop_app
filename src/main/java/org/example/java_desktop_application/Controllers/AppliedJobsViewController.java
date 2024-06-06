package org.example.java_desktop_application.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import org.example.java_desktop_application.Models.Applicant;
import org.example.java_desktop_application.Models.JobPosting;

public class AppliedJobsViewController {

    private Applicant applicant;

    @FXML
    private ListView<JobPosting> appliedJobsListView;

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
        displayAppliedJobs();
    }

    private void displayAppliedJobs() {
        appliedJobsListView.getItems().setAll(applicant.getAppliedJobs());
        appliedJobsListView.setCellFactory(jobListView -> new LabelCell());
    }
    public void updateAppliedJobs() { displayAppliedJobs(); }

    private static class LabelCell extends ListCell<JobPosting> {

        @Override
        protected void updateItem(JobPosting job, boolean empty) {

            super.updateItem(job, empty);

            if (empty || job == null) setText(null);

            else {
                setText(job.getCompany().getName() + " - " + job.getRole().getTitle() + " (" + job.getLevel() + ")");
                setStyle("-fx-font-weight: bold");
            }

        }

    }
}
