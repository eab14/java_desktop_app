package org.example.java_desktop_application.Controllers;
import org.example.java_desktop_application.Models.*;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;


import java.util.ArrayList;

public class CompanyViewController extends Pane {

    private ChoiceBox<String> companies;

    public CompanyViewController() {

        companies = new ChoiceBox<>();
        companies.relocate(100, 100);
        companies.setPrefSize(120, 25);

        getChildren().addAll(companies);
        update();

    }

    public void update() {}

}