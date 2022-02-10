package com.college.oop_project.model;

import java.util.ArrayList;

public class SubjectRank {
    private final Student student;
    private final SchoolSubject schoolSubject;
    private final Question question;
    private final int grade;
    public static ArrayList<SubjectRank> subjectRanks = new ArrayList<>();

    public SubjectRank(int schoolSubjectID, int studentID, int questionID, int rank) {
        this.student = Student.getStudentWithID(studentID);
        this.schoolSubject = SchoolSubject.getSchoolSubjectWithID(schoolSubjectID);
        this.question = Question.getQuestionWithID(questionID);
        this.grade = rank;

        subjectRanks.add(this);
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

    public static ArrayList<SubjectRank> getSubjectRank(SchoolSubject schoolSubject, Student student) {
        ArrayList<SubjectRank> list = new ArrayList<>();
        for (SubjectRank sr : subjectRanks) {
            if (sr.getSchoolSubject().equals(schoolSubject) && sr.student.equals(student)) {
                list.add(sr);
            }
        }

        return list;
    }
}
