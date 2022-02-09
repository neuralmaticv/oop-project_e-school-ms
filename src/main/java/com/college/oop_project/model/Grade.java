package com.college.oop_project.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Grade implements Comparable<Grade> {
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
        this.student.listOfProfessors.put(this.schoolSubject.getProfessor(), new HashMap<>());

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

    @Override
    public int compareTo(Grade o) {
        List<Integer> dl = Arrays.stream(date.split("-")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> oDL = Arrays.stream(o.date.split("-")).map(Integer::parseInt).collect(Collectors.toList());

        if (dl.get(1) == oDL.get(1)) {
            return Integer.compare(dl.get(2), oDL.get(2));
        } else {
            return Integer.compare(dl.get(1), oDL.get(1));
        }
    }
}
