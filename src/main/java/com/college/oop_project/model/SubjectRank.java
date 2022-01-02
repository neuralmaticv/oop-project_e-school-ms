package com.college.oop_project.model;

public class SubjectRank {
    private Student student;
    private SchoolSubject schoolSubject;
    private Question question;
    private int grade;

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
