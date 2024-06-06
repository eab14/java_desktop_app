package org.example.java_desktop_application.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.java_desktop_application.Models.Applicant;

public class SkillsViewController {

    private Applicant applicant;

    @FXML
    private ListView<String> skillsListView;

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
        ObservableList<String> skillsObservableList = FXCollections.observableArrayList(applicant.getSkills());
        skillsListView.setItems(skillsObservableList);
        skillsListView.setCellFactory(skillListView -> new LabelCell());
    }

    @FXML
    private TextField applicantSkill;

    @FXML
    private void addSkill() {
        String newSkill = applicantSkill.getText();
        if (!newSkill.isEmpty()) {
            applicant.addSkill(newSkill);
            ObservableList<String> updatedSkillsList = FXCollections.observableArrayList(applicant.getSkills());
            skillsListView.setItems(updatedSkillsList);
            applicantSkill.clear();
        }
    }

    private static class LabelCell extends ListCell<String> {

        @Override
        protected void updateItem(String skill, boolean empty) {
            super.updateItem(skill, empty);

            if (empty || skill == null) {
                setText(null);
            } else {
                setText(skill);
                setStyle("-fx-font-weight: bold");
            }
        }
    }
}