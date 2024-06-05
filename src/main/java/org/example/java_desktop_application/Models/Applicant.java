package org.example.java_desktop_application.Models;

import java.util.ArrayList;

public class Applicant {

    private String name;
    private String phone;
    private String email;

    private ArrayList<String> skills;
    private ArrayList<JobPosting> appliedJobs;

    public Applicant() {
        this.name = null;
        this.skills = new ArrayList<>();
        this.appliedJobs = new ArrayList<JobPosting>();
    }

    public String getName() {

        if (this.name == null) return "Applicant Name";
        else return name;

    }

    public void setName(String inputName) { this.name = inputName; }

    public String getPhone() { return phone; }
    public void setPhone(String inputPhone) { this.phone = inputPhone; }

    public String getEmail() { return email; }
    public void setEmail(String inputEmail) { this.email = inputEmail; }

    public ArrayList<String> getSkills() { return skills; }
    public void addSkill(String str) { skills.add(str); }

}
