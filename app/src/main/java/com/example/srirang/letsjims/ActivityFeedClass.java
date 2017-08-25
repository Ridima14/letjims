package com.example.srirang.letsjims;

import java.util.Calendar;

/**
 * Created by Srirang on 8/15/2017.
 */

public class ActivityFeedClass {

    String subject;
    String owner;
    String origin;
    String classname;
    String date;

    public ActivityFeedClass(String subject, String owner, String origin,String classname) {
        this.subject = subject;
        this.owner=owner;
        this.origin=origin;
        this.classname=classname;
        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        this.date=mydate;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public ActivityFeedClass(){}

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
