package com.example.asus.assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ASUS on 14/11/2017.
 */

public class AddEvent extends AppCompatActivity {
    //private Button AddButton;
    private EditText NewDate, NewTime, NewTitle;
    private CheckBox NewNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);


        findViewById(R.id.AddEventButton).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v) {
                NewTitle = (EditText) findViewById(R.id.NewTitle);
                String EventTitle = NewTitle.getText().toString();

                NewTime = (EditText) findViewById(R.id.NewTime);
                String EventTime = NewTime.getText().toString();

                NewDate = (EditText) findViewById(R.id.NewDate);
                String EventDate = NewDate.getText().toString();

                NewNotification = (CheckBox) findViewById(R.id.NotificationBoolean);
                String EventNotification = NewNotification.getText().toString();


                DB_data_source db = new DB_data_source (v.getContext());
                AppEvent newEvent = new AppEvent(0,EventTitle,EventDate,EventTime,EventNotification);

                //open db
                db.open();
                db.insertEvent(newEvent);
                Toast.makeText(v.getContext(), "Added one Event", Toast.LENGTH_SHORT).show();
                db.close();
                finish();
            }//end of onclick
        });
    }
    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        // add data to Intent
        setResult(Activity.RESULT_OK, data);
        super.onBackPressed();
    }



}
