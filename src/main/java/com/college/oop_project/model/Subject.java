package com.college.oop_project.model;

import com.college.oop_project.sql.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Subject {
    private String name;
    private int schoolGrade;
    public static ArrayList<Subject> subjects = new ArrayList<>();

    public Subject(String name, int schoolGrade) {
        this.name = name;
        this.schoolGrade = schoolGrade;

        if (!subjectExist(this)) {
            subjects.add(this);
            System.out.println("Dodat je predmet " + name + " za " + schoolGrade + " razred");
        } else {
            // TODO:
            // Create exception
            System.out.println("Predmet vec postoji");
        }
    }

    public static void getSubjectsFromDB() {
        Driver dr = new Driver();
        dr.startConnection();

        try {
            Statement statement = dr.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from predmet");

            while (resultSet.next()) {
                subjects.add(new Subject(resultSet.getString("naziv"), Integer.parseInt(resultSet.getString("razred"))));
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }

        dr.endConnection();
    }

    public String getName() {
        return name;
    }

    public int getSchoolGrade() {
        return schoolGrade;
    }

    @Override
    public String toString() {
        return "Subject{" + "name='" + name + ", schoolGrade=" + schoolGrade + '}';
    }

    private boolean subjectExist(Subject subject) {
        for (Subject s: subjects) {
            if (s.name.equals(subject.name) && s.schoolGrade == subject.schoolGrade) {
                return true;
            }
        }

        return false;
    }
}
