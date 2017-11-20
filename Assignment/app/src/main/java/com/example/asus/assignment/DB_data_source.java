package com.example.asus.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ASUS on 14/11/2017.
 */

public class DB_data_source {
    //Create a variable, m_database capable of referencing the SQLiteDatabase
    private SQLiteDatabase m_database;
    //Create a variable, m_dbHelper capable of helping us make the code more readable
    private DBhelper m_dbHelper;
    //Create a variable which capable of describing the running app environment
    private Context m_context;

    public DB_data_source(Context context){
        m_context = context;
        m_dbHelper = new DBhelper(m_context);
    }

    //open data base
    public void open() throws SQLException {
        m_database = m_dbHelper.getWritableDatabase();
    }
    //close
    public void close() {
        m_database.close();
    }


    //insert
    public void insertEvent(AppEvent AppEvent) {
        m_database.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            values.put(DBhelper.COLUMN_TITLE, AppEvent.getTitle());
            values.put(DBhelper.COLUMN_DATE, AppEvent.getDate());
            values.put(DBhelper.COLUMN_TIME, AppEvent.getTime());
            values.put(DBhelper.COLUMN_NOTIFICATION, AppEvent.getNotification());

            m_database.insert(DBhelper.TABLE_EVENTS, null, values);

            m_database.setTransactionSuccessful();
        } finally {
            m_database.endTransaction();
        }
    }

    //select
    public Cursor selectAllEvents(){
        Cursor cursor = m_database.rawQuery("Select * from " + DBhelper.TABLE_EVENTS, null);
        return cursor;
    }

    public Cursor selectOneEvent(int inId){
        Cursor cursor = m_database.rawQuery("Select * from " + DBhelper.TABLE_EVENTS+" where "
                + DBhelper.COLUMN_ID+" = " + inId, null);
        return cursor;
    }

  //update
    public boolean updateEvent(int inId, String title, String time,String date, String notification){
        ContentValues values = new ContentValues();
        int success = -1;
        values.put(DBhelper.COLUMN_TITLE, title);
        values.put(DBhelper.COLUMN_TIME,time);
        values.put(DBhelper.COLUMN_DATE,date);
        values.put(DBhelper.COLUMN_NOTIFICATION,notification);
        success =  m_database.update(
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



    //delete
    public boolean deleteEvent(int inId) {
        int success = -1;
        success = m_database.delete(
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

    public boolean deleteAllCustomers() {
        int success = -1;
        success = m_database.delete(
                DBhelper.TABLE_EVENTS,
                null,
                null
        );
        if(success != -1 ) {
            return true;
        } else {
            return false;
        }
    }
}
