package com.example.asus.assignment;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by ASUS on 14/11/2017.
 */

public class AppEvent implements Serializable{
    private int ID;
    private String Title;
    private String Date;
    private String Time;
    private String Notification;

<<<<<<< HEAD
    public AppEvent(int inID, String inTitle,String inDate, String inTime, String inNotification) {
=======

    public AppEvent(int inID, String inTitle, String inDate, String inTime, String inDescription) {
>>>>>>> 6048a07e53429beb951f2fc6bb62a425e74764bd
        this.ID = inID;
        this.Title = inTitle;
        this.Date = inDate;
        this.Time = inTime;
        this.Notification = inNotification;
    }
    public int getID() {
        return ID;
    }
    public void setID(int inID) {
        this.ID = inID;
    }
    public String getTitle() {
        return Title;
    }
    public void setTitle(String inTitle) {
        this.Title = inTitle;
    }
    public String getDate() {
        Log.d("appevent is:",Date);
    return Date;}
    public void setDate(String inDate) {this.Date = inDate;}
    public String getTime() {
        return Time;
    }
    public void setTime(String inTime) {
        this.Time = inTime;
    }
    public String getNotification() {
        return Notification;
    }
    public void setNotification(String inNotification) {
        this.Notification = inNotification;
    }

}
