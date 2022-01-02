package com.college.oop_project.model;

import java.util.ArrayList;

public class Absences {
    private Student student;
    private Subject subject;
    private String date;
    public static ArrayList<Absences> allAbsences = new ArrayList<>();

    public Absences(Student student, Subject subject, String date) {
        this.student = student;
        this.subject = subject;
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }
}
