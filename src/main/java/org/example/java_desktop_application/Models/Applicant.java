package org.example.java_desktop_application.Models;

import java.util.ArrayList;

public class Applicant {

    private String name;
    private String phone;
    private String email;

    private ArrayList<String> skills;

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public ArrayList<String> getSkills() { return skills; }

}
