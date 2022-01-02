package com.college.oop_project.model;

public class SubjectRank {
    private final Student student;
    private final SchoolSubject schoolSubject;
    private final Question question;
    private final int grade;

    public SubjectRank(Student student, SchoolSubject schoolSubject, Question question, int grade) {
        this.student = student;
        this.schoolSubject = schoolSubject;
        this.question = question;
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public SchoolSubject getSchoolSubject() {
        return schoolSubject;
    }

    public Question getQuestion() {
        return question;
    }

    public int getGrade() {
        return grade;
    }
}
