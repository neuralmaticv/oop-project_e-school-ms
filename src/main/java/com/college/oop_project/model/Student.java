package com.college.oop_project.model;

import com.college.oop_project.sql.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Student {
    private final String firstName;
    private final String lastName;
    private final String sex;
    private final AccessData accessData;
    public ArrayList<SchoolSubject> listOfSubjects = new ArrayList<>();
    public ArrayList<Grade> listOfGrades = new ArrayList<>();
    public ArrayList<Absences> listOfAbsences = new ArrayList<>();

    public Student(String firstName, String lastName, String sex, AccessData accessData) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.accessData = accessData;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSex() {
        return sex;
    }

    public AccessData getAccessData() {
        return accessData;
    }

    public static boolean getStudent(String firstName, String lastName) {
        Driver dr = new Driver();
        dr.startConnection();

        try {
            Statement statement = dr.getConn().createStatement();
            String query = "SELECT korisnicko_ime FROM pristupni_podaci";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                if (result.getString("korisnicko_ime").equals(firstName + "." + lastName)) {
                    System.out.println("Student exists in database.");
                    dr.endConnection();
                    return true;
                }
            }
            dr.endConnection();
        } catch (SQLException err) {
            err.printStackTrace();
        }

        return false;
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

    public void setSubjectRank(SchoolSubject subject) {
        // TODO:
        // If student has absences or has at least one grade for specified subject then student
        // has ability to give rate for that subject
        // for each absences/grades, check IDs
    }

    public void changePassword() {
        if (accessData.setNewPassword()) {
            System.out.println("...");
        }
    }
}
