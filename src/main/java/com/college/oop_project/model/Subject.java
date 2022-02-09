package com.college.oop_project.model;

import java.util.ArrayList;

public class Subject {
    private final int subjectID;
    private final String name;
    private SchoolGrade schoolGrade;
    public static ArrayList<Subject> subjects = new ArrayList<>();

    public Subject(int subjectID, String name, int schoolGrade) throws Exception {
        this.subjectID = subjectID;
        this.name = name;
        this.schoolGrade = SchoolGrade.fromInt(schoolGrade);

        if (!subjectExist(this)) {
            subjects.add(this);
        } else {
            throw new Exception("Predmet sa zadatim informacijama vec postoji u sistemu!");
        }
    }

    public static Subject getSubjectWithID(int id) {
        return subjects.get(id - 1);
    }

    private boolean subjectExist(Subject subject) {
        for (Subject s : subjects) {
            if (s.name.equals(subject.name) && s.schoolGrade == subject.schoolGrade) {
                return true;
            }
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public SchoolGrade getSchoolGrade() {
        return schoolGrade;
    }

    public int getSubjectID() {
        return subjectID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append(" za ").append(this.schoolGrade).append(" razred");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        return this.name.equals(subject.name) && this.schoolGrade == subject.schoolGrade;
    }

}
