package com.college.oop_project.model;

import java.util.ArrayList;

public class Absences {
    private final Student student;
    private final SchoolSubject schoolSubject;
    private final String date;
    public static ArrayList<Absences> allAbsences = new ArrayList<>();

    public Absences(int studentID, int schoolSubjectID, String date) {
        this.student = Student.getStudentWithID(studentID);
        this.schoolSubject = SchoolSubject.getSchoolSubjectWithID(schoolSubjectID);
        this.date = date;

        this.student.listOfAbsences.add(this);
        allAbsences.add(this);
    }

    public Student getStudent() {
        return student;
    }

    public SchoolSubject getSchoolSubject() {
        return schoolSubject;
    }

    public String getSubjectName() {
        return schoolSubject.getSubject().getName();
    }

    public String getDate() {
        return date;
    }
}
