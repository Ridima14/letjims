package com.example.srirang.letsjims;

/**
 * Created by Srirang on 8/4/2017.
 */

public class FacultyDetails {
    protected String Fname;
    protected String Fpassword;

   public FacultyDetails(){}
    public FacultyDetails(String Fname,String Fpassword)
    {
        this.Fname=Fname;
        this.Fpassword=Fpassword;
    }

    public String getFname() {
        return this.Fname;
    }

    public void setFname(String fname) {
        this.Fname = fname;
    }

    public String getFpassword() {
        return this.Fpassword;
    }

    public void setFpassword(String fpassword) {
        this.Fpassword = fpassword;
    }
}

