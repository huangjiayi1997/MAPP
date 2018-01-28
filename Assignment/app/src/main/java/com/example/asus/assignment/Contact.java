package com.example.asus.assignment;

import android.util.Log;

/**
 * Created by ASUS on 10/1/2018.
 */

public class Contact {
    private String ID;
    private String Name;
    private String Email;
    private String Contact;

    public Contact(String inID, String inName, String inEmail,String inContact) {
        this.ID = inID;
        this.Name = inName;
        this.Email = inEmail;
        this.Contact=inContact;
    }
    public String getID() {
        return ID;
    }
    public void setID(String inID) {
        this.ID = inID;
    }
    public String getName() {
        return Name;
    }
    public void setName(String inName) {
        this.Name = inName;
    }
    public String getEmail() {return Email;}
    public void setEmail(String inEmail) {this.Email = inEmail;}
    public String getContact() {return Contact;}
    public void setContact(String inContact) {this.Contact = inContact;}

}

