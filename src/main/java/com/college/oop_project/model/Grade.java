package com.college.oop_project.model;

public class Grade {
    private Student student;
    private Subject subject;
    private int grade;
    private String date;

    public Grade(Student student, Subject subject, int grade, String date) {
        if (grade < 1 || grade > 5) {
            System.out.println("Nedozvoljen unos, ocjena treba da bude u opsegu od 1 do 5.");
            // TODO:
            // Create exception
            return;
        }

        this.student = student;
        this.subject = subject;
        this.grade = grade;
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return subject;
    }

    public int getGrade() {
        return grade;
    }

    public String getDate() {
        return date;
    }
}
