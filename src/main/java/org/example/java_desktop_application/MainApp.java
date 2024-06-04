package org.example.java_desktop_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("Views/index.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 820, 540);
        stage.setTitle("APD 545 - Job Application Program");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}