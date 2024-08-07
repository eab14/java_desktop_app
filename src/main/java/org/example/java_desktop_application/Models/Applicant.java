package org.example.java_desktop_application.Models;

import java.util.ArrayList;
import java.util.Objects;

public class Applicant {

    private static Applicant instance;

    private String name;
    private String phone;
    private String email;

    private ArrayList<String> skills;;
    private ArrayList<JobPosting> appliedJobs;

    public Applicant() {
        this.name = null;
        this.phone = null;
        this.email= null;
        this.skills = new ArrayList<>();
        this.appliedJobs = new ArrayList<JobPosting>();
    }

    public String getName() {

        if (this.name == null || this.name.isEmpty()) return "Enter Applicant Information...";
        else return name;

    }
    public static Applicant getInstance() {

        if (instance == null) {
            instance = new Applicant();
        }

        return instance;

    }


    public void setName(String inputName) { this.name = inputName; }

    public String getPhone() { return phone; }
    public void setPhone(String inputPhone) { this.phone = inputPhone; }

    public String getEmail() { return email; }
    public void setEmail(String inputEmail) { this.email = inputEmail; }

    public ArrayList<String> getSkills() { return skills; }
    public void addSkill(String newSkill) {
        boolean skillExists = false;
        for (int i = 0; i < skills.size(); i++) {
            if (Objects.equals(skills.get(i), newSkill)) {
                skills.set(i, newSkill);
                skillExists = true;
                break;
            }
        }
        if (!skillExists) {
            skills.add(newSkill);
        }
    }
    public void clearSkills() { this.skills = new ArrayList<>(); }
    public void removeSkill(String skill) { skills.remove(skill); }

    public ArrayList<JobPosting> getAppliedJobs() { return this.appliedJobs; }
    public void addAppliedJob(JobPosting post) { appliedJobs.add(post); }
    public void clearAppliedJobs() { this.appliedJobs = new ArrayList<>(); }

    public boolean verifyAppilcant() {

        return !Objects.equals(this.getName(), "") &&
                !Objects.equals(this.getPhone(), "") &&
                !Objects.equals(this.getEmail(), "") &&
                !this.getSkills().isEmpty();

    }

}
