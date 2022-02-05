package com.college.oop_project.model;

import java.util.ArrayList;

public class Grade {
    private Student student;
    private SchoolSubject schoolSubject;
    private int grade;
    private String date;
    public static ArrayList<Grade> grades = new ArrayList<>();

    public Grade(int studentID, int schoolSubjectID, int grade, String date) throws Exception {
        if (grade < 1 || grade > 5) {
            throw new Exception("Nedozvoljen unos, ocjena treba da bude u opsegu od 1 do 5.");
        }

        this.student = Student.getStudentWithID(studentID);
        this.schoolSubject = SchoolSubject.getSchoolSubjectWithID(schoolSubjectID);
        this.grade = grade;
        this.date = date;

        this.student.setSchool(schoolSubject.getSchool());
        this.student.setSchoolGrade(schoolSubject.getSubject().getSchoolGrade());
        this.student.listOfGrades.add(this);

        grades.add(this);
    }

    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return schoolSubject.getSubject();
    }

    public String getSubjectName() {
        return schoolSubject.getSubject().getName();
    }

    public Professor getProfessor() {
        return schoolSubject.getProfessor();
    }

    public int getGrade() {
        return grade;
    }
    public int getSubjectGrade() {
        return grade;
    }

    public String getDate() {
        return date;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Info about grade:").append("\n");
        sb.append("Student name: ").append(this.student.getFirstName()).append("\n");
        sb.append("Student lastname: ").append(this.student.getLastName()).append("\n");
        sb.append("Subject: ").append(this.getSubject().getName()).append("\n");
        sb.append("Professor: ").append(this.getProfessor().getFirstName() + this.getProfessor().getLastName()).append("\n");
        sb.append("Grade: ").append(this.grade).append("\n");

        return sb.toString();
    }
}
