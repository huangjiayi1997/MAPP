package com.example.asus.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ASUS on 8/11/2017.
 */

public class DBhelper extends SQLiteOpenHelper {
    //declaration of database variables
    public static final String TABLE_EVENTS ="EVENTS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_TIME = "TIME";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_NOTIFICATION = "NOTIFICATION";



    private static final String DB_NAME = "database.db";
    private static final int DB_VER = 1;
    //notification is omitted because we are not using it yet
    private static final String DB_CREATE = "CREATE TABLE "+ TABLE_EVENTS+ " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + ", " + COLUMN_TITLE + " TEXT " + ", " + COLUMN_DATE + " TEXT,"+ COLUMN_TIME + " TEXT " + ", "+ COLUMN_DESCRIPTION + " TEXT) " ;


    public DBhelper(Context context){
        super(context, DB_NAME, null, DB_VER);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
