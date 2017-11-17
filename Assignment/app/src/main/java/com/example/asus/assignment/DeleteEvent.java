package com.example.asus.assignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ASUS on 15/11/2017.
 */

public class DeleteEvent extends AppCompatActivity{
    private int id;
    private String Title;
    private String Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String tempId =  getIntent().getStringExtra("ID");
        id= Integer.parseInt(tempId);


}
