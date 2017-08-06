package com.example.srirang.letsjims;

import com.google.firebase.database.Exclude;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Srirang on 8/3/2017.
 */

public class NotifyStudentsClass {
    public String subject;
    public String content;
    public String datens;
    public String facultyn;

    public NotifyStudentsClass(String subject, String content,String datens,String facultyn) {
        this.subject = subject;
        this.content = content;
       this.datens = datens;
        this.facultyn=facultyn;
    }
  public NotifyStudentsClass(){}

}
