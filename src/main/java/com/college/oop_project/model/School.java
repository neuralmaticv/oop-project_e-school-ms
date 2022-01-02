package com.college.oop_project.model;

import com.college.oop_project.sql.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class School {
    private String schoolName;
    private String place;
    private String city;
    private String country;
    public static ArrayList<School> schools = new ArrayList<>();

    public static void getSchoolsFromDB() {
        Driver dr = new Driver();
        dr.startConnection();

        try {
            Statement statement = dr.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from skola");

            while (resultSet.next()) {
                String naziv = resultSet.getString("naziv");
                String mjesto = resultSet.getString("mjesto");
                String grad = resultSet.getString("grad");
                String drzava = resultSet.getString("drzava");

                new School(naziv, mjesto, grad, drzava);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }

        dr.endConnection();
    }

    public School(String schoolName, String place, String city, String country) {
        this.schoolName = schoolName;
        this.place = place;
        this.city = city;
        this.country = country;

        if (!schoolExist(this)) {
            schools.add(this);
        } else {
            // TODO:
            // Create exception
            System.out.println("Skola vec postoji");
        }
    }

    private boolean schoolExist(School school) {
        for (School s: schools) {
            if (s.schoolName.equals(school.schoolName) && s.city.equals(school.city)
            && s.place.equals(school.place) && s.country.equals(school.country)) {
                return true;
            }
        }

        return false;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getPlace() {
        return place;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Naziv: ").append("\"").append(this.schoolName).append("\", ");
        sb.append(this.place).append(", ").append(this.city).append(", ").append(this.country);

        return sb.toString();
    }
}
