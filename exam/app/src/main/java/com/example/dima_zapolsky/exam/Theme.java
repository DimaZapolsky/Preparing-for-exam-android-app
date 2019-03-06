package com.example.dima_zapolsky.exam;

import java.util.ArrayList;

public class Theme {
    private String name;
    private ArrayList<Question> questions;

    public Theme(){
        name = "";
        questions = new ArrayList<Question>();
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}
