package com.college.oop_project.model;

import java.util.*;

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
        return allStudents.get(id - 1);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getSchoolGrade() {
        return schoolGrade.toString();
    }

    public void setSchoolGrade(SchoolGrade grade) {
        this.schoolGrade = grade;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Info about student:").append("\n");
        sb.append("First name: ").append(this.firstName).append("\n");
        sb.append("Last name: ").append(this.lastName).append("\n");
        sb.append("Sex: ").append(this.sex).append("\n");
        sb.append("Grade: ").append(this.schoolGrade).append("\n");
        sb.append("Username: ").append(this.accessData.getUserName()).append("\n");
        sb.append("Email: ").append(this.accessData.getUserMail()).append("\n");
        sb.append("Password: ").append(this.accessData.getUserPassword()).append("\n");

        return sb.toString();
    }
}
