package org.example.java_desktop_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader applicantLoader = new FXMLLoader(getClass().getResource("Views/applicant.fxml"));
        VBox applicant = applicantLoader.load();

        FXMLLoader exitLoader = new FXMLLoader(getClass().getResource("Views/exit.fxml"));
        VBox exit = exitLoader.load();

        VBox view = new VBox();
        view.getChildren().addAll(applicant, exit);

        Scene scene = new Scene(view, 820, 540);
        stage.setTitle("APD 545 - Job Application Program");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}