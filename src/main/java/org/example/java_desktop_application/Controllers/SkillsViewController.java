package org.example.java_desktop_application.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
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
            showSkillDialog(selectedSkill);
        }
    }

    @FXML
    private void addSkill() {
        showSkillDialog(null);
    }

    private void showSkillDialog(String initialValue) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(initialValue == null ? "Add Skill" : "Edit Skill");
        dialog.setHeaderText(null);
        dialog.initModality(Modality.APPLICATION_MODAL);

        ButtonType submitButtonType = new ButtonType("Submit", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(submitButtonType, cancelButtonType);

        TextField skillField = new TextField(initialValue);
        skillField.setPromptText("Enter skill...");
        skillField.setPrefWidth(200);

        Label skillLabel = new Label("Skill:");
        skillLabel.setStyle("-fx-font-weight: bold");

        HBox inputBox = new HBox(10);
        inputBox.getChildren().addAll(skillLabel, skillField);
        inputBox.setAlignment(Pos.CENTER_LEFT);

        VBox content = new VBox(10);
        content.getChildren().addAll(inputBox);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);

        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == submitButtonType) {
                return skillField.getText();
            }
            return null;
        });

        Button submitButton = (Button) dialog.getDialogPane().lookupButton(submitButtonType);
        submitButton.setStyle("-fx-font-weight: bold");
        Button cancelButton = (Button) dialog.getDialogPane().lookupButton(cancelButtonType);
        cancelButton.setStyle("-fx-font-weight: bold");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(skill -> {
            if (!skill.isEmpty()) {
                if (initialValue == null) {
                    applicant.addSkill(skill);
                } else {
                    applicant.removeSkill(initialValue);
                    applicant.addSkill(skill);
                }
                ObservableList<String> updatedSkillsList = FXCollections.observableArrayList(applicant.getSkills());
                skillsListView.setItems(updatedSkillsList);
            }
        });

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
