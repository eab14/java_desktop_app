package org.example.java_desktop_application.Models;

import java.util.ArrayList;

public class JobPosting {

    private Company company;
    private JobRole role;
    private Float salary;
    private String level;
    private ArrayList<String> requiredSkills;

    public Company getCompany() { return this.company; }
    public JobRole getRole() { return this.role; }

    public JobPosting(Company c, JobRole j, double s, String l, ArrayList<String> rs) {

        this.company = c;
        this.role = j;
        this.salary = (float) s;
        this.level = l;
        this.requiredSkills = rs;

    }

    public Float getSalary() { return this.salary; }
    public String getLevel() { return this.level; }
    public ArrayList<String> getRequiredSkills() { return this.requiredSkills; }

}
