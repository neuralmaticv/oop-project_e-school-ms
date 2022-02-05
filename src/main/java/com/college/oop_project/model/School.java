package com.college.oop_project.model;

import java.util.ArrayList;

public class School {
    private final int schoolID;
    private final String schoolName;
    private final String place;
    private final String city;
    private final String country;
    public static ArrayList<School> schools = new ArrayList<>();

    public School(int schoolID, String schoolName, String place, String city, String country) throws Exception {
        this.schoolID = schoolID;
        this.schoolName = schoolName;
        this.place = place;
        this.city = city;
        this.country = country;

        if (!schoolExist(this)) {
            schools.add(this);
        } else {
            throw new Exception("Škola sa zadatim informacijama vec postoji u sistemu!");
        }
    }

    public static School getSchoolWithID(int id) {
        return schools.get(id - 1);
    }

    private boolean schoolExist(School school) {
        for (School s : schools) {
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
