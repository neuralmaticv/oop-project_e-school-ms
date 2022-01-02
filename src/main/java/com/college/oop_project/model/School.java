package com.college.oop_project.model;

import java.util.ArrayList;

public class School {
    private String schoolName;
    private String city;
    private String place;
    private String country;
    public ArrayList<School> schools = new ArrayList<>();

    public School(String schoolName, String city, String place, String country) {
        this.schoolName = schoolName;
        this.city = city;
        this.place = place;
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
        // TODO
        return false;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getCity() {
        return city;
    }

    public String getPlace() {
        return place;
    }

    public String getCountry() {
        return country;
    }
}
