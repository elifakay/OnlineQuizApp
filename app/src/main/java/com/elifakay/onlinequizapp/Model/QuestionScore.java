package com.elifakay.onlinequizapp.Model;

/**
 * Created by elf_4 on 23.10.2017.
 */

public class QuestionScore
{
    private String QuestionScore;
    private String User;
    private String Score;

    public QuestionScore()
    {

    }
    public QuestionScore(String questionScore,String user,String score)
    {
        this.QuestionScore=questionScore;
        this.User=user;
        this.Score=score;
    }

    public String getQuestionScore() {
        return QuestionScore;
    }

    public void setQuestionScore(String questionScore) {
        QuestionScore = questionScore;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }
}

