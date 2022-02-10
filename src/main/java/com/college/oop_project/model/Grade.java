package com.college.oop_project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Grade implements Comparable<Grade> {
    private Student student;
    private SchoolSubject schoolSubject;
    private int grade;
    private LocalDate date;
    public static ArrayList<Grade> grades = new ArrayList<>();

    public Grade(int studentID, int schoolSubjectID, int grade, String date) {
        this.student = Student.getStudentWithID(studentID);
        this.schoolSubject = SchoolSubject.getSchoolSubjectWithID(schoolSubjectID);
        this.grade = grade;
        this.date = LocalDate.parse(date);

        this.student.setSchool(schoolSubject.getSchool());
        this.student.setSchoolGrade(schoolSubject.getSubject().getSchoolGrade());
        this.student.listOfGrades.add(this);
        this.student.listOfProfessors.add(this.schoolSubject.getProfessor());
        this.student.listOfSubjects.add(schoolSubject);

        grades.add(this);
    }

    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return schoolSubject.getSubject();
    }

    public String getSubjectName() {
        return schoolSubject.getSubject().getName() + " " + schoolSubject.getSubject().getSchoolGrade();
    }

    public Professor getProfessor() {
        return schoolSubject.getProfessor();
    }

    public int getSubjectGrade() {
        return grade;
    }

    public LocalDate getDate() {
        return date;
    }

    public String toString() {
        return "" + grade;
    }

    public static ArrayList<Grade> getAllGradesForStudent(Student s, Subject subject, School school) {
        ArrayList<Grade> grades = new ArrayList<>();

        for (Grade g: grades) {
            if (g.student.equals(s) && g.schoolSubject.getSubject().equals(subject) && g.schoolSubject.getSchool().equals(school)) {
                grades.add(g);
            }
        }

        return grades;
    }

    @Override
    public int compareTo(Grade o) {
        if (this.date.getYear() == o.date.getYear()) {
            if (this.date.getMonthValue() == o.date.getMonthValue()) {
                return Integer.compare(this.date.getDayOfMonth(), this.date.getDayOfMonth());
            } else {
                return Integer.compare(this.date.getMonthValue(), o.date.getMonthValue());
            }
        } else {
            return Integer.compare(this.date.getYear(), o.date.getYear());
        }
    }
}
