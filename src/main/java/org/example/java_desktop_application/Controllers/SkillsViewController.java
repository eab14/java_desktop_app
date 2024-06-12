package org.example.java_desktop_application.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.util.Pair;
import javafx.geometry.Pos;
import org.example.java_desktop_application.Models.Applicant;

import java.util.Optional;

public class SkillsViewController {

    private Applicant applicant;

    @FXML
    private ListView<String> skillsListView;

    public void setApplicant(Applicant applicant) {

        this.applicant = applicant;
        ObservableList<String> skillsObservableList = FXCollections.observableArrayList(applicant.getSkills());
        skillsListView.setItems(skillsObservableList);
        skillsListView.setCellFactory(skillListView -> new LabelCell());
        skillsListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                editSelectedSkill();
            }
        });

    }

    private void editSelectedSkill() {
        String selectedSkill = skillsListView.getSelectionModel().getSelectedItem();
        if (selectedSkill != null) {
            TextInputDialog dialog = new TextInputDialog(selectedSkill);
            dialog.setTitle("Edit Skill");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter new skill:");

            Optional<String> result = dialog.showAndWait();

            result.ifPresent(newSkill -> {
                if (!newSkill.isEmpty() && !newSkill.equals(selectedSkill)) {
                    applicant.removeSkill(selectedSkill);
                    applicant.addSkill(newSkill);
                    ObservableList<String> updatedSkillsList = FXCollections.observableArrayList(applicant.getSkills());
                    skillsListView.setItems(updatedSkillsList);
                }
            });
        }
    }


    @FXML
    private TextField applicantSkill;

    @FXML
    private void addSkill() {
        Dialog<Pair<String, Boolean>> dialog = new Dialog<>();
        dialog.setTitle("Add Skill");
        dialog.setHeaderText(null);
        dialog.initModality(Modality.APPLICATION_MODAL);

        ButtonType submitButtonType = new ButtonType("Submit");
        ButtonType cancelButtonType = new ButtonType("Cancel");

        dialog.getDialogPane().getButtonTypes().addAll(submitButtonType, cancelButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField skillField = new TextField();
        skillField.setPromptText("Enter skill...");

        Label skillLabel = new Label("Skill:");
        skillLabel.setStyle("-fx-font-weight: bold");

        grid.add(skillLabel, 0, 0);
        grid.add(skillField, 1, 0);

        dialog.getDialogPane().setContent(grid);

        Button submitButton = (Button) dialog.getDialogPane().lookupButton(submitButtonType);
        submitButton.setStyle("-fx-font-weight: bold");
        submitButton.setOnAction(event -> {
            String skill = skillField.getText();
            if (!skill.isEmpty()) {
                applicant.addSkill(skill);
                ObservableList<String> updatedSkillsList = FXCollections.observableArrayList(applicant.getSkills());
                skillsListView.setItems(updatedSkillsList);
            }
            dialog.setResult(new Pair<>(skill, true));
        });

        Button cancelButton = (Button) dialog.getDialogPane().lookupButton(cancelButtonType);
        cancelButton.setStyle("-fx-font-weight: bold");
        cancelButton.setOnAction(event -> dialog.setResult(new Pair<>(null, false)));

        dialog.showAndWait();
    }


    private class LabelCell extends ListCell<String> {

        @Override
        protected void updateItem(String skill, boolean empty) {
            super.updateItem(skill, empty);

            if (empty || skill == null) {
                setText(null);
                setGraphic(null);
            } else {
                Label label = new Label(skill);
                label.setStyle("-fx-font-weight: bold");

                Button deleteButton = new Button("Delete");
                deleteButton.setStyle("-fx-font-weight: bold");

                deleteButton.setOnAction(event -> {
                    // Remove the skill from the applicant and update the list view
                    applicant.removeSkill(skill);
                    ObservableList<String> updatedSkillsList = FXCollections.observableArrayList(applicant.getSkills());
                    skillsListView.setItems(updatedSkillsList);
                });

                HBox hbox = new HBox(label);
                hbox.setAlignment(Pos.CENTER_LEFT);
                HBox.setHgrow(label, Priority.ALWAYS);

                StackPane stackPane = new StackPane(hbox, deleteButton);
                StackPane.setAlignment(deleteButton, Pos.CENTER_RIGHT);

                setGraphic(stackPane);
            }
        }

    }

    public void clearSkills() {

        applicant.clearSkills();
        skillsListView.getItems().clear();

    }

}