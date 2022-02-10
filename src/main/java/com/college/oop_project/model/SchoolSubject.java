package com.college.oop_project.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SchoolSubject {
    private final int schoolSubjectID;
    private final Subject subject;
    private final School school;
    private final Professor professor;
    public static Set<SchoolSubject> schoolSubjects = new HashSet<>();

    public SchoolSubject(int id, int subjectID, int schoolID, int professorID) {
        this.schoolSubjectID = id;
        this.subject = Subject.getSubjectWithID(subjectID);
        this.school = School.getSchoolWithID(schoolID);
        this.professor = Professor.getProfessorWithID(professorID);
        Set<Subject> set;
        if (professor.schoolAndSubjects.containsKey(school)) {
            set = professor.schoolAndSubjects.get(school);
            set.add(subject);
        } else {
            set = new HashSet<>();
            set.add(subject);
            professor.schoolAndSubjects.put(school, set);
        }

        schoolSubjects.add(this);
    }

    public static SchoolSubject getSchoolSubjectWithID(int id) {
        for (SchoolSubject sb : schoolSubjects) {
            if (sb.schoolSubjectID == id) {
                return  sb;
            }
        }

        return null;
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

    public int getSchoolSubjectID() {
        return schoolSubjectID;
    }

    public static SchoolSubject getSchoolSubject(School school, Subject subject, Professor professor) {
        for (SchoolSubject sb : schoolSubjects) {
            if (sb.school.equals(school) && sb.subject.equals(subject) && sb.professor.equals(professor)) {
                return sb;
            }
        }

        return null;
    }

    public static ArrayList<SchoolSubject> getAllSchoolSubjectsOfProfessor(Professor professor) {
        ArrayList<SchoolSubject> list = new ArrayList<>();
        for (SchoolSubject sc : schoolSubjects) {
            if (sc.getProfessor().equals(professor)) {
                list.add(sc);
            }
        }

        return list;
    }

    @Override
    public String toString() {
        return this.professor.getFullName() + " za predmet " + this.subject.getName() + " " + this.subject.getSchoolGrade();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchoolSubject that = (SchoolSubject) o;

        return this.getSubject().equals(that.subject) && this.professor.equals(that.professor);
    }
}
