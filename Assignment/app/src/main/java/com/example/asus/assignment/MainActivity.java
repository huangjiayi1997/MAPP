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


        });}
}

