package com.college.oop_project.model;

import java.util.ArrayList;

public class Question {
    private final int questionID;
    private final String question;
    private String answer;
    public static ArrayList<Question> questions = new ArrayList<>();

    public Question(int questionID, String question) {
        this.questionID = questionID;
        this.question = question;

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
