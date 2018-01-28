package com.example.asus.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by ASUS on 14/11/2017.
 */

public class DB_data_source {
<<<<<<< HEAD
    //Create a variable, l_database capable of referencing the SQLiteDatabase
    private SQLiteDatabase l_database;
    //Create a variable, m_dbHelper capable of helping us make the code more readable
=======
    private SQLiteDatabase m_database;
>>>>>>> 6048a07e53429beb951f2fc6bb62a425e74764bd
    private DBhelper m_dbHelper;
    private Context m_context;

    public DB_data_source(Context context){
        m_context = context;
        m_dbHelper = new DBhelper(m_context);
    }

    //open data base
    public void open() throws SQLException {
        l_database = m_dbHelper.getWritableDatabase();
    }
    //close
    public void close() {
        l_database.close();
    }


    //insert //correct
    public void insertEvent(AppEvent AppEvent) {
        l_database.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            values.put(DBhelper.COLUMN_TITLE, AppEvent.getTitle());
            values.put(DBhelper.COLUMN_DATE, AppEvent.getDate());
            Log.d("the date in datesrc is",AppEvent.getDate());
            values.put(DBhelper.COLUMN_TIME, AppEvent.getTime());
            values.put(DBhelper.COLUMN_NOTIFICATION, AppEvent.getNotification());

            l_database.insert(DBhelper.TABLE_EVENTS, null, values);

            l_database.setTransactionSuccessful();
        } finally {
            l_database.endTransaction();
        }
    }

    public Cursor selectAllEvents(){
        Cursor cursor = l_database.rawQuery("Select * from " + DBhelper.TABLE_EVENTS, null);
        return cursor;
    }

    public Cursor selectOneEvent(String calendarDate){
        Log.d("heelo","error");
        Cursor cursor = l_database.rawQuery("Select * from " + DBhelper.TABLE_EVENTS+" where "
                + DBhelper.COLUMN_DATE +" =  " + calendarDate + " ", null);
        Log.d("heelo","error2");
        return cursor;
    }

<<<<<<< HEAD
  //update
    public boolean updateEvent(int inId, String title, String time, String notification){
=======

    public boolean updateEvent(int inId, String title, String time,String date, String description){
>>>>>>> 6048a07e53429beb951f2fc6bb62a425e74764bd
        ContentValues values = new ContentValues();
        int success = -1;
        values.put(DBhelper.COLUMN_TITLE, title);
        values.put(DBhelper.COLUMN_TIME,time);
      //  values.put(DBhelper.COLUMN_DATE,date);
        values.put(DBhelper.COLUMN_NOTIFICATION,notification);
        success =  l_database.update(
                DBhelper.TABLE_EVENTS,
                values,
                DBhelper.COLUMN_ID + " = " + inId,
                null

        );
        if(success != -1 && success != 0) {
            return true;
        } else {
            return false;
        }

    }

<<<<<<< HEAD
    //delete
=======



>>>>>>> 6048a07e53429beb951f2fc6bb62a425e74764bd
    public boolean deleteEvent(int inId) {
        int success = -1;
        success = l_database.delete(
                DBhelper.TABLE_EVENTS,
                DBhelper.COLUMN_ID + " = " + inId,
                null
        );
        if(success != -1 && success !=0) {
            return true;
        } else {
            return false;
        }
    }
}
