package com.example.srirang.letsjims;

import java.util.Calendar;

/**
 * Created by Srirang on 8/15/2017.
 */

public class GeneralClgNewsClass {

    String subject;
    String content;
    String origin;
    String date;

    public GeneralClgNewsClass(String subject, String content, String origin) {
        this.subject = subject;
        this.content=content;
        this.origin=origin;
        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        this.date=mydate;
    }





    public GeneralClgNewsClass(){}

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}