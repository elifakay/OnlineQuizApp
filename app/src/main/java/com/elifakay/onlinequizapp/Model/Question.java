package com.elifakay.onlinequizapp.Model;

/**
 * Created by elf_4 on 23.10.2017.
 */

public class Question {
    private String Question;
    private String AnswerA,AnswerB,AnswerC,AnswerD;
    private String CorrectAnswer;
    private String CategoryId;
    private String IsImageQuestion;


    public Question()
    {

    }

    public Question(String Question,String AnswerA,String AnswerB,String AnswerC,String AnswerD,String CorrectAnswer,String CategoryId,String IsImageQuestion)
    {
        this.Question=Question;
        this.AnswerA=AnswerA;
        this.AnswerB=AnswerB;
        this.AnswerC=AnswerC;
        this.AnswerD=AnswerD;
        this.CorrectAnswer=CorrectAnswer;
        this.CategoryId=CategoryId;
        this.IsImageQuestion=IsImageQuestion;
    }

    public String getQuestion() {
        return Question;
    }
    public void setQuestion(String question) {
        Question = question;
    }
    public String getAnswerA() {
        return AnswerA;
    }
    public void setAnswerA(String answerA) {
        AnswerA = answerA;
    }
    public String getAnswerB() {
        return AnswerB;
    }
    public void setAnswerB(String answerB) {
        AnswerA = answerB;
    }
    public String getAnswerC() {
        return AnswerC;
    }
    public void setAnswerC(String answerC) {
        AnswerA = answerC;
    }
    public String getAnswerD() {
        return AnswerD;
    }
    public void setAnswerD(String answerD) {
        AnswerA = answerD;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        CorrectAnswer = correctAnswer;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        this.CategoryId = categoryId;
    }

    public String getIsImageQuestion() {
        return IsImageQuestion;
    }

    public void setIsImageQuestion(String isImageQuestion) {
        this.IsImageQuestion = isImageQuestion;
    }
}
