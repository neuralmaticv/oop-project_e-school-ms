package com.college.oop_project.model;

import java.util.ArrayList;

public class Student {
    private final int studentID;
    private final String firstName;
    private final String lastName;
    private School school;
    private SchoolGrade schoolGrade;
    private final Sex sex;
    private final AccessData accessData;
    public ArrayList<SchoolSubject> listOfSubjects = new ArrayList<>();
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

    // TODO:
    // sort grades by date and then return result
    // return result should be Map (<subject:grade>)?
    public ArrayList<Grade> getListOfGrades() {
        return listOfGrades;
    }

    public ArrayList<Grade> getGradesForSubject(SchoolSubject subject) {
        ArrayList<Grade> list = new ArrayList<>();

        for (Grade grade : listOfGrades) {
            if (grade.getSubject() == subject.getSubject()) {
                System.out.println(grade.getDate() + " -> " + subject.getSubject().getName() + " ->" + grade.getGrade());
                list.add(grade);
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

    public void setSubjectRank(SchoolSubject subject) {
        // TODO:
        // If student has absences or has at least one grade for specified subject then student
        // has ability to give rate for that subject
        // for each absences/grades, check IDs
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
