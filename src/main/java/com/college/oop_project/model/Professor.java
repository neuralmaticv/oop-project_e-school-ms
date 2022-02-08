package com.college.oop_project.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Professor {
    private final int professorID;
    private final String firstName;
    private final String lastName;
    private final Sex sex;
    private final AccessData accessData;
    public Set<School> schools = new HashSet<>();
    public Set<Subject> subjects = new HashSet<>();
    public static ArrayList<Professor> allProfessors = new ArrayList<>();

    public Professor(int professorID, String firstName, String lastName, int sexID, int dataID) {
        this.professorID = professorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = Sex.fromInt(sexID);
        this.accessData = AccessData.getUser(dataID);

        allProfessors.add(this);
    }

    public static Professor getProfessorWithID(int id) {
        return allProfessors.get(id - 1);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getSex() {
        return sex.toString();
    }

    public AccessData getAccessData() {
        return accessData;
    }

    public static Professor getProfessor(String username, String password) {
        for (Professor p : allProfessors) {
            if (p.accessData.getUserName().equals(username) && p.accessData.getUserPassword().equals(password)) {
                return p;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Info about professor:").append("\n");
        sb.append("First name: ").append(this.firstName).append("\n");
        sb.append("Last name: ").append(this.lastName).append("\n");
        sb.append("Sex: ").append(this.sex).append("\n");
        sb.append("Username: ").append(this.accessData.getUserName()).append("\n");
        sb.append("Email: ").append(this.accessData.getUserMail()).append("\n");
        sb.append("Password: ").append(this.accessData.getUserPassword()).append("\n");

        return sb.toString();
    }
}
