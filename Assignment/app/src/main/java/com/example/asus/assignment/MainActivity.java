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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<AppEvent> m_EventArrayList;
    RecyclerView m_recyclerView;
    public static EventArrayAdapter m_EventArrayAdapter;
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
            String title = selectedEventToUpdate.getTitle();
            String date = selectedEventToUpdate.getDate();
            String time=selectedEventToUpdate.getTime();
            String notification=selectedEventToUpdate.getNotification();

            Intent intent = new Intent(MainActivity.this, UpdateEvent.class);

            intent.putExtra("ID", Integer.toString(id));
            intent.putExtra("Title", title);
            intent.putExtra("Date", date);
            intent.putExtra("Time",time);
            intent.putExtra("Notification",notification);


            startActivityForResult(intent,5);
        }
        };
        m_EventArrayAdapter = new EventArrayAdapter(R.layout.event_list_item, m_EventArrayList,listener);
        m_recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_EventList);
        m_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        m_recyclerView.setItemAnimator(new DefaultItemAnimator());
        m_recyclerView.setAdapter(m_EventArrayAdapter);

        loadEvents();
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
       // Log.d("event error","0");
        //Reference: http://www.vogella.com/tutorials/AndroidActionBar/article.html
        MenuInflater inflater = getMenuInflater();
       // Log.d("event error","9");
        inflater.inflate(R.menu.about_menu, menu);
        //Log.d("event error","8");
        return true;
    }//all executed

    @Override
    //use this to do hamburger
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("event error","7");
        switch (item.getItemId()) {

            // action with ID action_goto_add_customer was selected
            case R.id.About_app:
                Log.d("event error","5");
                Intent i = new Intent(MainActivity.this,AboutEvent.class);
                Log.d("event error","b");
                startActivity(i);
                Log.d("event error","a");
                break;
            default:
                break;
        }
        return true;
    }


    protected void loadEvents() {
        //TextView textViewResults = findViewById(R.id.ViewEvent);//this line causes the result to display in line without formatting

        //textViewResults.setText("");
        //DB_data_source database = new DB_data_source(this);
        //database.open();
        //Cursor cursor = database.selectAllEvents();
        //cursor.moveToFirst();
        //changed to:
        AppEvent event;
        //Note: the m_customerArrayList is declared as class member variable
        //Clear the m_customerArrayList first before openning the database
        m_EventArrayList.clear();
        DB_data_source database = new DB_data_source(this);
        database.open();
        //The following command will retrieve all data from the database
        Cursor cursor = database.selectAllEvents();
        //The following block of code is frequently used by developers to
        //(1)loop through one record at a time and (2)quickily display in a TextView
        //to have some assurance that the database has the records.
        //I obtained these code from
        //https://stackoverflow.com/questions/10723770/whats-the-best-way-to-iterate-an-android-cursor
        cursor.moveToFirst();
        Log.d("Operation error","0");
        while (!cursor.isAfterLast()) {
            String Title = cursor.getString(cursor.getColumnIndex("TITLE"));
            String Date = cursor.getString(cursor.getColumnIndex("DATE"));
            String Time = cursor.getString(cursor.getColumnIndex("TIME"));
            String Notification = cursor.getString(cursor.getColumnIndex("NOTIFICATION"));
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            Log.d("Operation error","1");

            //change this:
            //textViewResults.append(id + " - " + Title + " - " + Date + " - " + Time +" - " +Notification + "\n");
            //cursor.moveToNext();

            //change to:
            event=new AppEvent(id,Title,Date,Time,Notification);
            Log.d("Operation error","2");
            m_EventArrayList.add(event);
            Log.d("Operation error","3");
            cursor.moveToNext();
            Log.d("Operation error","4");

        }
        database.close();
    }

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    //I was not sure whether this onActivityResult will execute.
    //Therefore, I used Log.d to check.
    Log.v("MainActivity","Request code" + requestCode);
    if (requestCode == 4) {
        Log.v("MainActivity","Executed onActivityResult 4");
        loadEvents();//Update the recylcerview to reflect the changes
        m_EventArrayAdapter.notifyDataSetChanged();
    }

    if ((requestCode == 5)&&(resultCode == Activity.RESULT_CANCELED)) {
        Log.v("MainActivity","You visited the Edit screen and clicked Home");
        Toast.makeText(getBaseContext(), "You visited the Edit screen and clicked Home or Back", Toast.LENGTH_SHORT).show();
        loadEvents();//Update the recylcerview to reflect the changes
        m_EventArrayAdapter.notifyDataSetChanged();
    }
}}
