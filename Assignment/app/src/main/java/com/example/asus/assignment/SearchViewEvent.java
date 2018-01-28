package com.example.asus.assignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by ASUS on 23/1/2018.
 */

public class SearchViewEvent extends AppCompatActivity {
    private int id;
    private String Notification,Title,Date,Time;
    private EditText editTextTitle,editTextDate,editTextTime,editTextNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_event);

        String tempId =  getIntent().getStringExtra("ID");
        id= Integer.parseInt(tempId);

        Title = getIntent().getStringExtra("Title");
        // Date = getIntent().getStringExtra("Date");
        Notification=getIntent().getStringExtra("Notification");
        Time=getIntent().getStringExtra("Time");


        //After collecting the data, the data is used to display inside
        //the correct controls

        //for title
        editTextTitle= (EditText)findViewById(R.id.EditText_title);
        editTextTitle.setText(Title);



        //for Date
        //EditText editTextMobileContact= (EditText)findViewById(R.id.EditText_MobileContact);
        //editTextDate= (EditText)findViewById(R.id.EditText_date);
        //editTextDate.setText(Date);


        //for time
        editTextTime=(EditText)findViewById(R.id.EditText_time);
        editTextTime.setText(Time);


        //for notification
        editTextNotification=(EditText)findViewById((R.id.EditText_notification));
        editTextNotification.setText(Notification);
}}
