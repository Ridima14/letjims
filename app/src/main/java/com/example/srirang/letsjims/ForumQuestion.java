package com.example.srirang.letsjims;

/**
 * Created by Srirang on 2/12/2018.
 */

public class ForumQuestion {
    public String question;
    public String user;
    public String date;

    public ForumQuestion() {
    }

    public ForumQuestion(String question, String user, String date) {
        this.question = question;
        this.user = user;
        this.date = date;
    }

    public String getQuestion() {

        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
