package com.college.oop_project.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Professor {
    private String firstName;
    private String lastName;
    private String sex;
    private AccessData accessData;
    public static ArrayList<Professor> allProfessors = new ArrayList<>();

    public Professor(String firstName, String lastName, String sex, AccessData accessData) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.accessData = accessData;
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
}
