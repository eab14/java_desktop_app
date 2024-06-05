package org.example.java_desktop_application.Models;

import java.util.ArrayList;

public class Utils {

    public static ArrayList<JobPosting> initialize() {

        ArrayList<JobPosting> jobs = new ArrayList<>();

        ArrayList<Company> companyList = new ArrayList<>();
        companyList.add(new Company("Test Insurance Company"));
        companyList.add(new Company("Test Tech Company"));

        ArrayList<JobRole> rolesList = new ArrayList<>();
        rolesList.add(new JobRole("Web Developer"));
        rolesList.add(new JobRole("Software Developer"));

        jobs.add(new JobPosting(companyList.get(0), rolesList.get(0),120000, "Senior"));
        jobs.add(new JobPosting(companyList.get(1), rolesList.get(0),65000, "Junior"));

        jobs.add(new JobPosting(companyList.get(1), rolesList.get(1),45000, "Intern"));
        jobs.add(new JobPosting(companyList.get(1), rolesList.get(0),45000, "Intern"));

        return jobs;

    }

}
