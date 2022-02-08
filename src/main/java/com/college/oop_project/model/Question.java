package com.college.oop_project.model;

import java.util.ArrayList;

public class Question {
    private final int questionID;
    private final String question;
    public static ArrayList<Question> questions = new ArrayList<>();

    public Question(int questionID, String question) {
        this.questionID = questionID;
        this.question = question;

        questions.add(this);
    }

    public static String getQuestionText(int index) {
        return questions.get(index).question;
    }

    public String getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.question);

        return sb.toString();
    }
}
