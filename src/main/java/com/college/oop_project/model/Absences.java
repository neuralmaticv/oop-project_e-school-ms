package com.college.oop_project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Absences {
    private final Student student;
    private final SchoolSubject schoolSubject;
    private final LocalDate date;
    public static ArrayList<Absences> allAbsences = new ArrayList<>();

    public Absences(int studentID, int schoolSubjectID, String date) {
        this.student = Student.getStudentWithID(studentID);
        this.schoolSubject = SchoolSubject.getSchoolSubjectWithID(schoolSubjectID);
        this.date = LocalDate.parse(date);

        this.student.listOfAbsences.add(this);
        this.student.listOfProfessors.add(this.schoolSubject.getProfessor());
        this.student.listOfSubjects.add(schoolSubject);

        allAbsences.add(this);
    }

    public Student getStudent() {
        return student;
    }

    public SchoolSubject getSchoolSubject() {
        return schoolSubject;
    }

    public String getSubject() {
        return schoolSubject.getSubject().getName() + " " + schoolSubject.getSubject().getSchoolGrade();
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return this.date.toString();
    }
}
