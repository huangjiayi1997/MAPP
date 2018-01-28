package com.example.asus.assignment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private CalendarView mCalendarView;
    private static final String TAG="CalendarActivity";
    public  static String CalendarDate;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCalendarView=(CalendarView)findViewById(R.id.calendarView);

        //if user select a date
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2){
                CalendarDate= (i1+1) + "/" + i2 + "/" + i ;
                Log.d(TAG,"onSelectedDayChange: mm/dd/yyyy : "+CalendarDate);

                Intent intent = new Intent (MainActivity.this,RecyclerViewForSpecificDate.class);
                intent.putExtra("date",CalendarDate);
               startActivity(intent);
            }
<<<<<<< HEAD
=======
        }
        };
        m_EventArrayAdapter = new EventArrayAdapter(R.layout.event_list_item, m_EventArrayList,listener);
        m_recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_EventList);
        m_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        m_recyclerView.setItemAnimator(new DefaultItemAnimator());
        m_recyclerView.setAdapter(m_EventArrayAdapter);

        loadEvents();
    }

    //delete record from db
    public void deleteActivity(View view,int id){
        DB_data_source db = new DB_data_source (view.getContext());
        db.open();
        db.deleteEvent(id);//change this to event
        Toast.makeText(view.getContext(), "Event deleted", Toast.LENGTH_SHORT).show();
        db.close();
    }

//refresh recyclerview to add in data
    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.AddEventButton:
                Intent i = new Intent(MainActivity.this,AddEvent.class);
                startActivityForResult(i,4);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.about_menu, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.About_app:
                Intent i = new Intent(MainActivity.this,AboutEvent.class);
                startActivity(i);
                break;
            default:
                break;
        }
        return true;
    }


    protected void loadEvents() {

        AppEvent event;

        m_EventArrayList.clear();
        DB_data_source database = new DB_data_source(this);
        database.open();

        Cursor cursor = database.selectAllEvents();

        cursor.moveToFirst();
       // Log.d("Operation error","0");
        while (!cursor.isAfterLast()) {
            String Title = cursor.getString(cursor.getColumnIndex("TITLE"));
            String Date = cursor.getString(cursor.getColumnIndex("DATE"));
            String Time = cursor.getString(cursor.getColumnIndex("TIME"));
            String Description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            Log.d("Operation error","1");
            event=new AppEvent(id,Title,Date,Time,Description);
           // Log.d("Operation error","2");
            m_EventArrayList.add(event);
           // Log.d("Operation error","3");
            cursor.moveToNext();
            Log.d("Operation error","4");
>>>>>>> 6048a07e53429beb951f2fc6bb62a425e74764bd


        });}
}

