package org.example.java_desktop_application.Models;

public class JobRole {

    private String title;
    private String description;

    public String getTitle() { return this.title; }
    public String getDescription() { return this.description; }

    public JobRole(String t, String d) { this.title = t; this.description = d; }

}
