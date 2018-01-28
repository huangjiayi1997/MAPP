package com.example.asus.assignment;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ASUS on 22/1/2018.
 */

public class RecyclerViewForSearch  extends AppCompatActivity  implements SearchView.OnQueryTextListener {
    public static ArrayList<AppEvent> m_searchArrayList;
    RecyclerView m_recyclerView;
    public static ArrayList<AppEvent> searchArrayList;
    public static SearchArrayAdapter m_searchArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_search);
        m_searchArrayList = new ArrayList<AppEvent>();
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
        @Override
        public void onClick(View view, int position) {
            Toast.makeText(view.getContext(), "You have selected position " + position, Toast.LENGTH_SHORT).show();
            AppEvent selectedEventToUpdate = m_searchArrayList.get(position);

            int id = selectedEventToUpdate.getID();

            Log.d("your landed","selectView");
            Log.d("Button_edit","error A");
            String title = selectedEventToUpdate.getTitle();
            String date = selectedEventToUpdate.getDate();
            String time=selectedEventToUpdate.getTime();
            String notification=selectedEventToUpdate.getNotification();

            Intent intent = new Intent(RecyclerViewForSearch.this, SearchViewEvent.class);

            intent.putExtra("ID", Integer.toString(id));
            intent.putExtra("Title", title);
            intent.putExtra("Date", date);
            intent.putExtra("Time",time);
            intent.putExtra("Notification",notification);


            startActivityForResult(intent,5);



        }
    };
    m_searchArrayAdapter = new SearchArrayAdapter(R.layout.search_list_item, m_searchArrayList,listener);

    m_recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_EventList);
        m_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        m_recyclerView.setItemAnimator(new DefaultItemAnimator());
        m_recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        m_recyclerView.setAdapter(m_searchArrayAdapter);

    loadEvents();
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tool_bar_search, menu);


        //for search
        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchview= (SearchView) MenuItemCompat.getActionView(menuItem);
        searchview.setOnQueryTextListener(this);
        return true;
    }





    protected void loadEvents() {

        AppEvent event;

        m_searchArrayList.clear();
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
        cursor=database.selectAllEvents();
        Log.d("error is ","here");

        cursor.moveToFirst();
        //get result back from db
        Log.d("error is ","here2");
        while (!cursor.isAfterLast()) {
            String Date = cursor.getString(cursor.getColumnIndex("DATE"));
            Log.d("still cannot",Date);
            String Title = cursor.getString(cursor.getColumnIndex("TITLE"));
            String Time = cursor.getString(cursor.getColumnIndex("TIME"));
            String Notification = cursor.getString(cursor.getColumnIndex("NOTIFICATION"));
            int id = cursor.getInt(cursor.getColumnIndex("ID"));

            event=new AppEvent(id,Title,Date,Time,Notification);
            // Log.d("Operation error","2");
            m_searchArrayList.add(event);
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
            m_searchArrayAdapter.notifyDataSetChanged();
            Log.d("tracker","tracker3");
        }

        if ((requestCode == 5)&&(resultCode == Activity.RESULT_CANCELED)) {
            Log.v("MainActivity","You visited the Edit screen and clicked Home");
            Toast.makeText(getBaseContext(), "You visited the Edit screen and clicked Home or Back", Toast.LENGTH_SHORT).show();
            loadEvents();//Update the recylcerview to reflect the changes
            m_searchArrayAdapter.notifyDataSetChanged();
            Log.d("tracker","tracker4");
        }
    }
    @Override
    public boolean onQueryTextSubmit(String query){
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText){
        newText =newText.toLowerCase();
        ArrayList<AppEvent> newList = new ArrayList<>();
        for (AppEvent appEvent: m_searchArrayList){
            String title=appEvent.getTitle().toLowerCase();
            if(title.contains(newText)){
                newList.add(appEvent);
            }
        }
            searchArrayList=new ArrayList<>();
            searchArrayList.addAll(newList);

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(view.getContext(), "You have selected position " + position, Toast.LENGTH_SHORT).show();
                AppEvent selectedEventToUpdate = searchArrayList.get(position);

                int id = selectedEventToUpdate.getID();

                Log.d("your landed","selectView");
                Log.d("Button_edit","error A");
                String title = selectedEventToUpdate.getTitle();
                String date = selectedEventToUpdate.getDate();
                String time=selectedEventToUpdate.getTime();
                String notification=selectedEventToUpdate.getNotification();

                Intent intent = new Intent(RecyclerViewForSearch.this, UpdateEvent.class);

                intent.putExtra("ID", Integer.toString(id));
                intent.putExtra("Title", title);
                intent.putExtra("Date", date);
                intent.putExtra("Time",time);
                intent.putExtra("Notification",notification);


                startActivityForResult(intent,5);



            }
        };
        m_searchArrayAdapter = new SearchArrayAdapter(R.layout.search_list_item, searchArrayList,listener);

        m_recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_EventList);
        m_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        m_recyclerView.setItemAnimator(new DefaultItemAnimator());
        m_recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        m_recyclerView.setAdapter(m_searchArrayAdapter);

        loadEvents();

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(RecyclerViewForSearch.this,RecyclerViewForSpecificDate.class));
        RecyclerViewForSearch.m_searchArrayAdapter.notifyDataSetChanged();
        finish();
    }

}

