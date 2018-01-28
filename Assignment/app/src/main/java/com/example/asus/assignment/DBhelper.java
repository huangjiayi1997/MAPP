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
    public static final String TABLE_EVENTS ="EVENTS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_TIME = "TIME";
<<<<<<< HEAD
    public static final String COLUMN_NOTIFICATION = "NOTIFICATION";



    private static final String DB_NAME = "database4.db";
=======
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
 //   public static final String COLUMN_NOTIFICATION = "NOTIFICATION";


    private static final String DB_NAME = "database2.db";
>>>>>>> 6048a07e53429beb951f2fc6bb62a425e74764bd
    private static final int DB_VER = 1;
    private static final String DB_CREATE = "CREATE TABLE "+ TABLE_EVENTS+ " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + ", " + COLUMN_TITLE + " TEXT " + ", " + COLUMN_DATE + " TEXT,"+ COLUMN_TIME + " TEXT " + ", "+ COLUMN_NOTIFICATION + " TEXT) " ;


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
