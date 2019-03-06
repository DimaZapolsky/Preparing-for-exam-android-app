package com.example.dima_zapolsky.exam;

import java.util.ArrayList;

public class Question {
    private String name;
    private ArrayList<String> answers;
    private ArrayList<Integer> rightAnswers;

    public Question() {
        name = "";
        answers = new ArrayList<String>();
        rightAnswers = new ArrayList<Integer>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public void setRightAnswers(ArrayList<Integer> rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getRightAnswers() {
        return rightAnswers;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void addAnswer(String answer) {
        answers.add(answer);
    }

    public void addRightAnswer(Integer answer) {
        rightAnswers.add(answer);
    }

    public Integer getAnswersSize() {
        return answers.size();
    }

    public Integer getRightAnswersSize() {
        return rightAnswers.size();
    }
}

