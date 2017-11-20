package com.example.asus.assignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ASUS on 20/11/2017.
 */

public class DeleteEvent extends AppCompatActivity {
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list_item);

        String tempId =  getIntent().getStringExtra("ID");
        id= Integer.parseInt(tempId);

        findViewById(R.id.Button_Delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //new db obj
                DB_data_source db = new DB_data_source (v.getContext());

                //open db
                db.open();

                //delete
                db.deleteEvent(id);//change this to event
                Toast.makeText(v.getContext(), "Event deleted", Toast.LENGTH_SHORT).show();
                db.close();
                finish();



            }});}
    }




