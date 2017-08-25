package com.example.srirang.letsjims;

/**
 * Created by Srirang on 8/12/2017.
 */

public class MakeUser {
    public String name;
    public String email;
    public String branch;
    public String year;
    public String branchyear;

    public MakeUser(){

    }
    public MakeUser(String name,String email,String branch,String year,String branchyear)
    {
        this.name=name;
        this.email=email;
        this.branch=branch;
        this.year=year;
        this.branchyear=branchyear;
    }

    public String getBranchyear() {
        return branchyear;
    }

    public void setBranchyear(String branchyear) {
        this.branchyear = branchyear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
