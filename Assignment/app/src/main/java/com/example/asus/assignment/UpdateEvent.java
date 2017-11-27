package com.example.asus.assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateEvent extends AppCompatActivity {
    private int id;
    private String /*Notification*/Description,Title,Date,Time;
    private EditText editTextTitle,editTextDate,editTextTime,editTextDescription;/*editTextNotification;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_event);

        String tempId =  getIntent().getStringExtra("ID");
        id= Integer.parseInt(tempId);

        Title = getIntent().getStringExtra("Title");
        Date = getIntent().getStringExtra("Date");
        //Notification=getIntent().getStringExtra("Notification");
        Time=getIntent().getStringExtra("Time");
        Description=getIntent().getStringExtra("Description");//NEED TO FIND THIS NAME  WAOIT


        //After collecting the data, the data is used to display inside

        //for title
        editTextTitle= (EditText)findViewById(R.id.EditText_title);
        editTextTitle.setText(Title);



        //for Date
        //EditText editTextMobileContact= (EditText)findViewById(R.id.EditText_MobileContact);
        editTextDate= (EditText)findViewById(R.id.EditText_date);
        editTextDate.setText(Date);


        //for time
        editTextTime=(EditText)findViewById(R.id.EditText_time);
        editTextTime.setText(Time);


        //for notification
/*        editTextNotification=(EditText)findViewById((R.id.EditText_notification));
        editTextNotification.setText(Notification);*/

        //for description
        editTextDescription=(EditText) findViewById(R.id.EditText_description); // whr the findview get this attribute like which file xml which yay
        editTextDescription.setText(Description);
        findViewById(R.id.Button_Save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String EventTitle = editTextTitle.getText().toString();
                String EventDate = editTextDate.getText().toString();
                String EventTime = editTextTime.getText().toString();
                String EventDescription= editTextDescription.getText().toString();

        //updating db
        DB_data_source db = new DB_data_source (v.getContext());

        //open db
                db.open();
                db.updateEvent(id, EventTitle,EventTime,EventDate, EventDescription);
                Toast.makeText(v.getContext(), "Event Updated", Toast.LENGTH_SHORT).show();
                db.close();
                finish();

            };});}

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        // add data to Intent
        setResult(Activity.RESULT_CANCELED, data);
        super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent data = new Intent();
                // add data to Intent
                setResult(Activity.RESULT_CANCELED, data);
                Toast.makeText(getApplicationContext(),"Back button clicked", Toast.LENGTH_SHORT).show();
                finish();
                //Don't apply break statement. It will stop the home action.
        }
        return true;
    }

}
