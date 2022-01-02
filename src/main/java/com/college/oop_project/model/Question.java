package com.college.oop_project.model;

import com.college.oop_project.sql.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Question {
    private final String question;
    private String answer;
    public static ArrayList<Question> questions = new ArrayList<>();

    public static void getQuestionsFromDB() {
        Driver dr = new Driver();
        dr.startConnection();

        try {
            Statement statement = dr.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from pitanje");

            while (resultSet.next()) {
                new Question(resultSet.getString("pitanje"), "");
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }

        dr.endConnection();
    }

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;

        questions.add(this);
    }

    public static Question getQuestion(int index) {
        return questions.get(index);
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.question).append(" | Odgovor: ").append(this.answer);

        return sb.toString();
    }
}
