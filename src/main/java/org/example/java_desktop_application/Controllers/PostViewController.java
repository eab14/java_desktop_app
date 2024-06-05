package org.example.java_desktop_application.Controllers;

import org.example.java_desktop_application.Models.*;

import java.util.ArrayList;

public class PostViewController {

    ArrayList<JobPosting> jobs = new ArrayList<>();

    public void setJobPostings(ArrayList<JobPosting> j) {
        this.jobs = j;
    }

}
