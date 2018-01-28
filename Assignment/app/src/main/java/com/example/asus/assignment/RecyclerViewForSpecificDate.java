package com.example.asus.assignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ASUS on 16/1/2018.
 */

public class RecyclerViewForSpecificDate extends AppCompatActivity{
    public static ArrayList<AppEvent> m_EventArrayList;
    RecyclerView m_recyclerView;
    public static AppEvent selectedEventToUpdate;
    public static EventArrayAdapter m_EventArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_events);

        m_EventArrayList = new ArrayList<AppEvent>();
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(view.getContext(), "You have selected position " + position, Toast.LENGTH_SHORT).show();
                 selectedEventToUpdate = m_EventArrayList.get(position);
                int id = selectedEventToUpdate.getID();

                int viewid=view.getId();
                switch(viewid){
                    case R.id.EnableShare2:
                        Intent i = new Intent(RecyclerViewForSpecificDate.this,SelectContact.class);


                        //Bundle bundle = new Bundle();
                        //bundle.putSerializable("ShareEventAL", selectedEventToUpdate);
                        //i.putExtra("EventBundle",bundle);
                        startActivity(i);
                        break;
                    case R.id.Button_Edit:
                        Log.d("Button_edit","error A");
                        String title = selectedEventToUpdate.getTitle();
                        String date = selectedEventToUpdate.getDate();
                        String time=selectedEventToUpdate.getTime();
                        String notification=selectedEventToUpdate.getNotification();

                        Intent intent = new Intent(RecyclerViewForSpecificDate.this, UpdateEvent.class);

                        intent.putExtra("ID", Integer.toString(id));
                        intent.putExtra("Title", title);
                        intent.putExtra("Date", date);
                        intent.putExtra("Time",time);
                        intent.putExtra("Notification",notification);


                        startActivityForResult(intent,5);
                        break;
                    case R.id.Button_Delete:

                        deleteActivity(view,id);

                        Log.d("Button_delete","error A");

                        break;
                }
            }
        };
        m_EventArrayAdapter = new EventArrayAdapter(R.layout.event_list_item, m_EventArrayList,listener);
        m_recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_EventList);
        m_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        m_recyclerView.setItemAnimator(new DefaultItemAnimator());
        m_recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
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
                Intent i = new Intent(RecyclerViewForSpecificDate.this,AddEvent.class);
                Log.d("Data pass to addevent",getIntent().getStringExtra("date"));
                i.putExtra("Date",getIntent().getStringExtra("date"));
                startActivityForResult(i,4);//request code allow you to identify from which intent you came back
                break;//allow refresh
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
                Intent i = new Intent(RecyclerViewForSpecificDate.this,AboutEvent.class);
                startActivity(i);
                break;
            case R.id.action_search1:
                Intent i2 = new Intent(RecyclerViewForSpecificDate.this,RecyclerViewForSearch.class);
                startActivity(i2);
                m_EventArrayAdapter.notifyDataSetChanged();
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
        Cursor cursor ;

        //if currentdate is focused, show events in currentdate, else show the selected date
        //Cursor cursor = database.selectAllEvents();
            cursor =database.selectAllEvents();
            //Intent i = getIntent();
            //String calendarDate =  getIntent().getStringExtra("date");
           // Log.d("whyyyyyyyyy:",calendarDate);
          //  cursor = database.selectOneEvent(calendarDate);
          /*  if (cursor == null) {
                Log.d("nullerror searchbydate", "jw is keev");
            }
            else {
                Log.d("searchbydate not null", "keev");
        }*/
            cursor=database.selectOneEvent(getIntent().getStringExtra("date"));
            Log.d("error is ","here");

            cursor.moveToFirst();
            //get result back from db
            Log.d("error is ","here2");
            while (!cursor.isAfterLast()) {
                String Date = cursor.getString(cursor.getColumnIndex("DATE"));
                Log.d("still cannot1",Date);
                String Title = cursor.getString(cursor.getColumnIndex("TITLE"));
                String Time = cursor.getString(cursor.getColumnIndex("TIME"));
                String Notification = cursor.getString(cursor.getColumnIndex("NOTIFICATION"));
                int id = cursor.getInt(cursor.getColumnIndex("ID"));

                event=new AppEvent(id,Title,Date,Time,Notification);
                // Log.d("Operation error","2");
                m_EventArrayList.add(event);
                // Log.d("Operation error","3");
                cursor.moveToNext();
                Log.d("Operation error","4");}




        database.close();
        Log.d("tracker","tracker2");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("MainActivity","Request code" + requestCode);
        if (requestCode == 4) {
            Log.v("MainActivity","Executed onActivityResult 4");
            loadEvents();//Update the recylcerview to reflect the changes
            m_EventArrayAdapter.notifyDataSetChanged();
            Log.d("tracker","tracker3");
        }

        if ((requestCode == 5)&&(resultCode == Activity.RESULT_CANCELED)) {
            Log.v("MainActivity","You visited the Edit screen and clicked Home");
            Toast.makeText(getBaseContext(), "You visited the Edit screen and clicked Home or Back", Toast.LENGTH_SHORT).show();
            loadEvents();//Update the recylcerview to reflect the changes
            m_EventArrayAdapter.notifyDataSetChanged();
            Log.d("tracker","tracker4");
        }
    }}
