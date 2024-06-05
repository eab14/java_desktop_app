package org.example.java_desktop_application.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.example.java_desktop_application.Models.Applicant;

public class SkillsViewController extends Pane {

    private Applicant applicant;

    private ObservableList<String> skillsList = FXCollections.observableArrayList();

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    @FXML
    private TextField applicantSkill;

    @FXML
    private GridPane skillsGrid;

    @FXML
    private void addSkill() {

        String newSkill = applicantSkill.getText();

        if (!newSkill.isEmpty()) {

            int numRows = skillsGrid.getRowCount();
            Label skillLabel = new Label(newSkill);
            skillLabel.setStyle("-fx-padding: 3 10 3 10;");
            GridPane.setMargin(skillLabel, new Insets(0, 0, 5, 0));
            GridPane.setRowIndex(skillLabel, numRows);
            skillsGrid.getChildren().add(skillLabel);
            applicant.addSkill(newSkill);
            applicantSkill.clear();

        }
    }

}
