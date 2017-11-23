package com.example.asus.assignment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.EventLogTags;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<AppEvent> m_EventArrayList;
    RecyclerView m_recyclerView;
    public static EventArrayAdapter m_EventArrayAdapter;
   // private int deleteid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initializing list view with the custom adapter
        m_EventArrayList = new ArrayList<AppEvent>();
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
        @Override
        public void onClick(View view, int position) {
            Toast.makeText(view.getContext(), "You have selected position " + position, Toast.LENGTH_SHORT).show();
            AppEvent selectedEventToUpdate = m_EventArrayList.get(position);
            int id = selectedEventToUpdate.getID();

            int viewid=view.getId();
            switch(viewid){
                case R.id.Button_Edit:
                    Log.d("Button_edit","error A");
            String title = selectedEventToUpdate.getTitle();
            String date = selectedEventToUpdate.getDate();
            String time=selectedEventToUpdate.getTime();
            String description=selectedEventToUpdate.getDescription();

            //add here OH WAIT

            Intent intent = new Intent(MainActivity.this, UpdateEvent.class);

            intent.putExtra("ID", Integer.toString(id));
            intent.putExtra("Title", title);
            intent.putExtra("Date", date);
            intent.putExtra("Time",time);
            intent.putExtra("Description", description);
            //intent.putExtra("Notification",notification);


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

        }
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
