package com.college.oop_project.sql;

import com.college.oop_project.model.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DBUtils {
    private static final Driver dr = new Driver();

    public static void getDataFromDB() {
        dr.startConnection();

        getAccessDataFromDB();
        getSchoolsFromDB();
        getProfessorsFromDB();
        getStudentsFromDB();
        getSubjectsFromDB();
        getSchoolSubjectsFromDB();
        getGradesFromDB();
        getQuestionsFromDB();
        getAbsencesFromDB();


        dr.endConnection();
    }

    private static void getAccessDataFromDB() {
        try {
            Statement statement = dr.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from pristupni_podaci");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("korisnicko_ime");
                String mail = resultSet.getString("email");
                String pw = resultSet.getString("sifra");

                new AccessData(id, userName, mail, pw);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    private static void getSchoolsFromDB() {
        try {
            Statement statement = dr.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from skola");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String naziv = resultSet.getString("naziv");
                String mjesto = resultSet.getString("mjesto");
                String grad = resultSet.getString("grad");
                String drzava = resultSet.getString("drzava");

                new School(id, naziv, mjesto, grad, drzava);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    private static void getProfessorsFromDB() {
        try {
            Statement statement = dr.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from profesor");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("ime");
                String surname = resultSet.getString("prezime");
                int sexCode = resultSet.getInt("pol");
                int dataID = resultSet.getInt("pristupni_podaci_id");


                new Professor(id, name, surname, sexCode, dataID);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    private static void getStudentsFromDB() {
        try {
            Statement statement = dr.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ucenik");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("ime");
                String surname = resultSet.getString("prezime");
                int sexID = resultSet.getInt("pol");
                int dataID = resultSet.getInt("pristupni_podaci_id");

                new Student(id, name, surname, sexID, dataID);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    private static void getSubjectsFromDB() {
        try {
            Statement statement = dr.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from predmet");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("naziv");
                int grade = resultSet.getInt("razred");

                new Subject(id, name, grade);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    private static void getSchoolSubjectsFromDB() {
        try {
            Statement statement = dr.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from predmet_u_skoli");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int subjectID = resultSet.getInt("predmet_id");
                int schoolID = resultSet.getInt("skola_id");
                int professorID = resultSet.getInt("profesor_id");

                new SchoolSubject(id, subjectID, schoolID, professorID);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    private static void getGradesFromDB() {
        try {
            Statement statement = dr.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ocjena");

            while (resultSet.next()) {
                int studentID = resultSet.getInt("ucenik_id");
                int schoolSubjectID = resultSet.getInt("predmet_u_skoli_id");
                int grade = resultSet.getInt("ocjena");
                String date = resultSet.getString("datum");

                new Grade(studentID, schoolSubjectID, grade, date);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    private static void getQuestionsFromDB() {
        try {
            Statement statement = dr.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from pitanje");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String question = resultSet.getString("pitanje");

                new Question(id, question);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    private static void getAbsencesFromDB() {
        try {
            Statement statement = dr.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from izostanci");

            while (resultSet.next()) {
                int studentID = resultSet.getInt("ucenik_id");
                int schoolSubjectID = resultSet.getInt("predmet_u_skoli_id");
                String date = resultSet.getString("datum");

                new Absences(studentID, schoolSubjectID, date);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }











    public static void addSchoolToDB(String name, String place, String city, String country) {
        dr.startConnection();

        try {
            String query = "INSERT INTO skola(naziv, grad, mjesto, drzava) VALUES (?,?,?,?)";
            PreparedStatement statement = dr.getConn().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, city);
            statement.setString(3, place);
            statement.setString(4, country);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int newID = rs.getInt(1);

            new School(newID, name, place, city, country);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dr.endConnection();
    }

    public static int addAccessDataToDB(String username, String mail, String password) {
        dr.startConnection();
        int newID = 0;

        try {
            String query = "INSERT INTO pristupni_podaci(korisnicko_ime, email, sifra)  VALUES (?,?,?)";
            PreparedStatement statement = dr.getConn().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, username);
            statement.setString(2, mail);
            statement.setString(3, getHashValue(password));
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            newID = rs.getInt(1);

            new AccessData(newID, username, mail, getHashValue(password));
        } catch (Exception e) {
            e.printStackTrace();
        }

        dr.endConnection();
        return newID;
    }

    public static void addProfessorToDB(String firstName, String lastName, int sex, int id) {
        dr.startConnection();

        try {
            String query = "INSERT INTO profesor(ime, prezime, pol, pristupni_podaci_id) VALUES (?,?,?,?)";
            PreparedStatement statement = dr.getConn().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, sex);
            statement.setInt(4, id);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int newID = rs.getInt(1);

            new Professor(newID, firstName, lastName, sex, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dr.endConnection();
    }

    public static void addStudentToDB(String firstName, String lastName, int sex, int id) {
        dr.startConnection();

        try {
            String query = "INSERT INTO ucenik(ime, prezime, pol, pristupni_podaci_id)  VALUES (?,?,?,?)";
            PreparedStatement statement = dr.getConn().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, sex);
            statement.setInt(4, id);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int newID = rs.getInt(1);

            new Student(newID, firstName, lastName, sex, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dr.endConnection();
    }

    public static void addSubjectToDB(String name, int grade) {
        dr.startConnection();

        try {
            String query = "INSERT INTO predmet(naziv, razred) VALUES (?,?)";
            PreparedStatement statement = dr.getConn().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setInt(2, grade);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int newID = rs.getInt(1);

            new Subject(newID, name, grade);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dr.endConnection();
    }

    public static void addSchoolSubjectToDB(int subjectID, int schoolID, int professorID) {
        dr.startConnection();

        try {
            String query = "INSERT INTO predmet_u_skoli(predmet_id, skola_id, profesor_id) VALUES (?,?,?)";
            PreparedStatement statement = dr.getConn().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, subjectID);
            statement.setInt(2, schoolID);
            statement.setInt(3, professorID);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int newID = rs.getInt(1);

            new SchoolSubject(newID, subjectID, schoolID, professorID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dr.endConnection();
    }

    public static void addAbsenceToDB(int studentID, int schoolSubjectID, String date) {
        dr.startConnection();

        try {
            String query = "INSERT INTO izostanci(ucenik_id, predmet_u_skoli_id, datum) VALUES (?,?,?)";
            PreparedStatement statement = dr.getConn().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, studentID);
            statement.setInt(2, schoolSubjectID);
            statement.setDate(3, Date.valueOf(date));
            statement.executeUpdate();

            new Absences(studentID, schoolSubjectID, date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dr.endConnection();
    }

    public static void addGradeToDB(int studentID, int schoolSubjectID, int grade, String date) {
        dr.startConnection();

        try {
            String query = "INSERT INTO ocjena(ucenik_id, predmet_u_skoli_id, ocjena, datum) VALUES (?,?,?,?)";
            PreparedStatement statement = dr.getConn().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, studentID);
            statement.setInt(2, schoolSubjectID);
            statement.setInt(2, grade);
            statement.setDate(4, Date.valueOf(date));
            statement.executeUpdate();

            new Grade(studentID, schoolSubjectID, grade, date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dr.endConnection();
    }

    public static String getHashValue(String password) {
        StringBuilder sb = new StringBuilder();

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            byte[] bytesOfPassword = password.getBytes(StandardCharsets.UTF_8);
            byte[] hash = md5.digest(bytesOfPassword);

            // Convert hash into HEX value
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
