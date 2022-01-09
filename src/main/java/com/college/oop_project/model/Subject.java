package com.college.oop_project.model;

import java.util.ArrayList;

public class Subject {
    private final String name;
    private final int schoolGrade;
    public static ArrayList<Subject> subjects = new ArrayList<>();

    public Subject(String name, int schoolGrade) {
        this.name = name;
        this.schoolGrade = schoolGrade;

        if (!subjectExist(this)) {
            subjects.add(this);
        } else {
            // TODO:
            // Create exception
            System.out.println("Predmet vec postoji");
        }
    }

    private boolean subjectExist(Subject subject) {
        for (Subject s : subjects) {
            if (s.name.equals(subject.name) && s.schoolGrade == subject.schoolGrade) {
                return true;
            }
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public int getSchoolGrade() {
        return schoolGrade;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Predmet: ").append(this.name);
        sb.append(" za ").append(this.schoolGrade).append(" razred");

        return sb.toString();
    }
}
