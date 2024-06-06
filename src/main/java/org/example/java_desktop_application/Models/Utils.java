package org.example.java_desktop_application.Models;

import java.util.ArrayList;

public class Utils {

    public static ArrayList<JobPosting> initialize() {

        ArrayList<JobPosting> jobs = new ArrayList<>();

        ArrayList<Company> companyList = new ArrayList<>();
        companyList.add(new Company("Test Insurance Company"));
        companyList.add(new Company("Test Tech Company"));

        ArrayList<JobRole> rolesList = new ArrayList<>();
        rolesList.add(new JobRole("Web Developer", "Assisting the web development team creating and optimizing web applications."));
        rolesList.add(new JobRole("Software Developer", "Assisting the software development team with testing, and furthering functionality in existing programs and applications."));

        ArrayList<String> webDeveloperSkills = new ArrayList<>();
        webDeveloperSkills.add("HTML");
        webDeveloperSkills.add("CSS");
        webDeveloperSkills.add("JavaScript");
        webDeveloperSkills.add("React");

        ArrayList<String> softwareDeveloperSkills = new ArrayList<>();
        softwareDeveloperSkills.add("Java");
        softwareDeveloperSkills.add("Python");
        softwareDeveloperSkills.add("C++");

        jobs.add(new JobPosting(companyList.get(0), rolesList.get(0),120000, "Senior", webDeveloperSkills));
        jobs.add(new JobPosting(companyList.get(1), rolesList.get(0),65000, "Junior", webDeveloperSkills));

        jobs.add(new JobPosting(companyList.get(1), rolesList.get(1),45000, "Intern", softwareDeveloperSkills));
        jobs.add(new JobPosting(companyList.get(1), rolesList.get(0),45000, "Intern", webDeveloperSkills));

        return jobs;

    }

}
