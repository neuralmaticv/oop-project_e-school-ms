package com.college.oop_project.model;

import java.util.ArrayList;

public class SchoolSubject {
    private final Subject subject;
    private final School school;
    private final Professor professor;
    public static ArrayList<SchoolSubject> schoolSubjects = new ArrayList<>();

    public SchoolSubject(int subjectID, int schoolID, int professorID) {
        this.subject = Subject.getSubjectWithID(subjectID);
        this.school = School.getSchoolWithID(schoolID);
        this.professor = Professor.getProfessorWithID(professorID);
        this.professor.schools.add(this.school);
        this.professor.subjects.add(this.subject);
        schoolSubjects.add(this);
    }

    public static SchoolSubject getSchoolSubjectWithID(int id) {
        return schoolSubjects.get(id - 1);
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
