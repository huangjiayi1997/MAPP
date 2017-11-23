package com.example.asus.assignment;

/**
 * Created by ASUS on 14/11/2017.
 */

public class AppEvent {
    private int ID;
    private String Title;
    private String Date;
    private String Time;
    private String Description;
    private String Notification;
    //you need to create a new db when you change the column, the description thing not thereeeee,check your codeeeeee i thnk its when u call from the db idk got tons of dependencies

    public AppEvent(int inID, String inTitle, String inDate, String inTime, String inDescription) {
        this.ID = inID;
        this.Title = inTitle;
        this.Date = inDate;
        this.Time = inTime;
        this.Description = inDescription;
       // this.Notification = inNotification;
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
        return Date;
    }
    public void setDate(String inDate) {
        this.Date = inDate;
    }
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

    public String getDescription() {
        return Description;
    }
    public void setDescription(String inDescription) {
        this.Description = inDescription;
    }
}
