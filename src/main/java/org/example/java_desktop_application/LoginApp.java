/********************************************************************************
 * APD545 – Workshop #5
 * I declare that this assignment is my own work in accordance with Seneca Academic Policy.
 * No part of this assignment has been copied manually or electronically from any other source
 * (including web sites) or distributed to other students.
 *
 * Name:         Evan Boileau
 * Student ID:   048655096
 * Date:         10/7/2024
 *
 *
 ********************************************************************************/

package org.example.java_desktop_application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginApp {

    private Map<String, String> users = new HashMap<>();
    private boolean isLoggedIn = false;
    private Stage mainStage;

    public void start(Stage primaryStage, String type) {
        users.put("admin", "root");

        if (type.equals("login")) {
            showLoginForm(primaryStage);
        } else if (type.equals("register")) {
            showRegisterForm(primaryStage);
        }
    }

    private void showLoginForm(Stage primaryStage) {
        Label userLabel = new Label("Username:");
        Label passLabel = new Label("Password:");
        TextField userTextField = new TextField();
        PasswordField passTextField = new PasswordField();
        Button loginButton = new Button("Login");
        Button registerToggle = new Button("Register");

        loginButton.setStyle("-fx-font-weight: bold;");
        registerToggle.setStyle("-fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(userLabel, 0, 0);
        gridPane.add(userTextField, 1, 0);
        gridPane.add(passLabel, 0, 1);
        gridPane.add(passTextField, 1, 1);
        gridPane.add(loginButton, 1, 2);

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(loginButton, registerToggle);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setStyle("-fx-alignment: center;");

        VBox root = new VBox(20);
        root.getChildren().addAll(gridPane, buttonBox);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 300, 200);

        loginButton.setOnAction(event -> {
            String username = userTextField.getText();
            String password = passTextField.getText();

            if (authenticate(username, password)) {
                isLoggedIn = true;
                primaryStage.close();
                showMainApp(primaryStage, username);
            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        });

        registerToggle.setOnAction(event -> {
            primaryStage.close();
            showRegisterForm(primaryStage);
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    private void showRegisterForm(Stage primaryStage) {
        Label userLabel = new Label("Username:");
        Label passLabel = new Label("Password:");
        Label confirmPassLabel = new Label("Confirm:");
        TextField userTextField = new TextField();
        PasswordField passTextField = new PasswordField();
        PasswordField confirmPassTextField = new PasswordField();
        Button registerButton = new Button("Submit");
        Button backToLogin = new Button("Back");

        registerButton.setStyle("-fx-font-weight: bold;");
        backToLogin.setStyle("-fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(userLabel, 0, 0);
        gridPane.add(userTextField, 1, 0);
        gridPane.add(passLabel, 0, 1);
        gridPane.add(passTextField, 1, 1);
        gridPane.add(confirmPassLabel, 0, 2);
        gridPane.add(confirmPassTextField, 1, 2);
        gridPane.add(registerButton, 1, 3);

        VBox root = new VBox();
        root.getChildren().addAll(gridPane, backToLogin);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 300, 230);

        registerButton.setOnAction(event -> {
            String username = userTextField.getText();
            String password = passTextField.getText();
            String confirmPassword = confirmPassTextField.getText();

            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match. Please try again.");
                return;
            }

            users.put(username, password);
            System.out.println("User registered successfully.");

            userTextField.clear();
            passTextField.clear();
            confirmPassTextField.clear();

            primaryStage.close();
            showLoginForm(primaryStage);
        });

        backToLogin.setOnAction(event -> {
            primaryStage.close();
            showLoginForm(primaryStage);
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Register");
        primaryStage.show();

    }

    private boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    private void showMainApp(Stage primaryStage, String username) {
        MainApp mainApp = new MainApp();
        try { mainApp.start(primaryStage, username); }
        catch (Exception e) { e.printStackTrace(); }
    }
}
