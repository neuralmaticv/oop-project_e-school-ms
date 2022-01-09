package com.college.oop_project.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Professor {
    private final String firstName;
    private final String lastName;
    private final String sex;
    private final AccessData accessData;
    public static ArrayList<Professor> allProfessors = new ArrayList<>();

    public Professor(String firstName, String lastName, String sex, int dataID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.accessData = AccessData.getUser(dataID);

        allProfessors.add(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSex() {
        return sex;
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

    public void addNewSubject() {
        String name;
        int grade;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter subject name:");
        name = scanner.next();
        System.out.println("Enter school grade:");
        grade = scanner.nextInt();

        // TODO:
    }

    public void changePassword() {
        if (accessData.setNewPassword()) {
            System.out.println("...");
        }
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
