package com.college.oop_project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Professor {
    private final int professorID;
    private final String firstName;
    private final String lastName;
    private final Sex sex;
    private final AccessData accessData;
    public Map<School, Set<Subject>> schoolAndSubjects = new HashMap<>();
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
        for (Professor p: allProfessors) {
            if (p.professorID == id) {
                return p;
            }
        }

        return null;
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

    public int getProfessorID() {
        return professorID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Professor professor = (Professor) o;

        return this.firstName.equals(professor.firstName) && this.lastName.equals(professor.lastName) &&
                this.sex.equals(professor.sex);
    }
}
