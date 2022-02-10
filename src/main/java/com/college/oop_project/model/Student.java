package com.college.oop_project.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private final int studentID;
    private final String firstName;
    private final String lastName;
    private School school;
    private SchoolGrade schoolGrade;
    private final Sex sex;
    private final AccessData accessData;
    public ArrayList<SchoolSubject> listOfSubjects = new ArrayList<>();
    public Map<Professor, Map<Question, String>> listOfProfessors = new HashMap<>();
    public ArrayList<Grade> listOfGrades = new ArrayList<>();
    public ArrayList<Absences> listOfAbsences = new ArrayList<>();
    public static ArrayList<Student> allStudents = new ArrayList<>();

    public Student(int studentID, String firstName, String lastName, int sexID, int dataID) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = Sex.fromInt(sexID);
        this.accessData = AccessData.getUser(dataID);

        allStudents.add(this);
    }

    public static Student getStudentWithID(int id) {
        for (Student s : allStudents) {
            if (s.studentID == id) {
                return s;
            }
        }
        return null;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public SchoolGrade getSchoolGrade() {
        return schoolGrade;
    }

    public void setSchoolGrade(SchoolGrade grade) {
        this.schoolGrade = grade;
    }
    public void setSchoolGrade(int grade) {
        this.schoolGrade = SchoolGrade.fromInt(grade);
    }

    public String getSex() {
        return sex.toString();
    }

    public AccessData getAccessData() {
        return accessData;
    }

    public ArrayList<SchoolSubject> getListOfSubjects() {
        return listOfSubjects;
    }

    public ArrayList<Grade> getListOfGrades() {
        return listOfGrades;
    }

    public int getStudentID() {
        return studentID;
    }

    public ArrayList<Grade> getListOfGradesForSubject(String subjectName) {
        ArrayList<Grade> list = new ArrayList<>();

        for (Grade g : listOfGrades) {
            if (g.getSubject().getName().equals(subjectName)) {
                list.add(g);
            }
        }

        return list;
    }

    public ArrayList<Absences> getListOfAbsences() {
        return listOfAbsences;
    }

    public static Student getStudent(String username, String password) {
        for (Student s : allStudents) {
            if (s.accessData.getUserName().equals(username) && s.accessData.getUserPassword().equals(password)) {
                return s;
            }
        }

        return null;
    }

    public boolean isInClass(Subject subject, LocalDate date, Professor professor) {
        for (Absences ab : listOfAbsences) {
            if (ab.getSchoolSubject().getSubject().equals(subject) && ab.getDate().equals(date) && ab.getSchoolSubject().getProfessor().equals(professor)) {
                return false;
            }
        }

        return true;
    }

    public int smallestDifferenceBetweenDates(Subject subject, LocalDate date) {
        int maxDifference = 100;
        for (Absences ab : listOfAbsences) {
            if (ab.getSchoolSubject().getSubject().equals(subject)) {
                Period difference = Period.between(ab.getDate(), date);
                int diff = Math.abs(difference.getDays());

                if (diff < maxDifference) {
                    maxDifference = diff;
                }
            }
        }

        return maxDifference;
    }

    public boolean hasReceivedTwoOrMoreGrades(LocalDate date) {
        int counter = 0;
        for (Grade g : listOfGrades) {
            if (g.getDate().equals(date)) {
                counter++;
            }
        }

        if (counter >= 2) {
            return true;
        } else {
            return false;
        }
    }

    public Map<Question, String> getQuestionsAndAnswersForProfessor(String fullName) {
        Map<Question, String> list = new HashMap<>();

        for (Map.Entry<Professor, Map<Question, String>> pq : this.listOfProfessors.entrySet()) {
            if (pq.getKey().getFullName().equals(fullName) && !pq.getValue().isEmpty()) {
                for (Map.Entry<Question, String> qs : pq.getValue().entrySet()) {
                    if (!qs.getValue().isBlank()) {
                        list.put(qs.getKey(), qs.getValue());
                    }
                }
            }
        }

        return list;
    }

    public boolean hasAtLeastOneGrade(Subject subject) {
        for (Grade g: listOfGrades) {
            if (g.getSubject().equals(subject)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAtLeastOneAbsence(Subject subject) {
        for (Absences ab: listOfAbsences) {
            if (ab.getSubject().equals(subject)) {
                return true;
            }
        }
        return false;
    }

    public static Student getStudentWithName(String name, String surname) {
        for (Student s : allStudents) {
            if (s.firstName.equals(name) && s.lastName.equals(surname)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.firstName).append(" ").append(this.lastName);

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return this.firstName.equals(student.firstName) && this.lastName.equals(student.lastName) &&
                this.sex.equals(student.sex);
    }
}
