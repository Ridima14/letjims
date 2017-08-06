package com.example.srirang.letsjims;

import java.util.Date;

/**
 * Created by Srirang on 7/17/2017.
 */

public class Message {
    private String mText;
    private String mSender;
    private Date mDate;

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getmSender() {

        return mSender;
    }

    public void setmSender(String mSender) {
        this.mSender = mSender;
    }

    public String getmText() {

        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }
}
