package com.example.asus.assignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ASUS on 20/11/2017.
 */

public class DeleteEvent extends AppCompatActivity {
    private int id,position;
    public static EventArrayAdapter m_EventArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.delete_pop_up);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.5));
        String tempId =  getIntent().getStringExtra("ID");
        String temppos =  getIntent().getStringExtra("position");

<<<<<<< HEAD
        //problem: tempid cannot be retrieve, its null.(resolved)
        position=Integer.parseInt(temppos);
=======

        position=Integer.parseInt(tempPos);
>>>>>>> 6048a07e53429beb951f2fc6bb62a425e74764bd
        id= Integer.parseInt(tempId);

        Log.d("Delete Event","ID"+id);


        findViewById(R.id.Button_Delete_pop_up).setOnClickListener(new View.OnClickListener() {
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
                Log.d("Button_delete","error C"+position);
<<<<<<< HEAD
                //m_contactArrayAdapter.removeItem(position);
=======

>>>>>>> 6048a07e53429beb951f2fc6bb62a425e74764bd

            }});}
    }




