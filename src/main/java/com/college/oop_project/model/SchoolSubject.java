package com.college.oop_project.model;

import java.util.ArrayList;

public class SchoolSubject {
    private Subject subject;
    private School school;
    private Professor professor;
    public ArrayList<SchoolSubject> schoolSubjects = new ArrayList<>();

    public SchoolSubject(Subject subject, School school, Professor professor) {
        this.subject = subject;
        this.school = school;
        this.professor = professor;
    }

    public Subject getSubject() {
        return subject;
    }

    public School getSchool() {
        return school;
    }

    public Professor getProfessor() {
        return professor;
    }
}
