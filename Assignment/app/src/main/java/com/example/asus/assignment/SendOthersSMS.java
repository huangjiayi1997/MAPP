package com.example.asus.assignment;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ASUS on 22/1/2018.
 */

public class SendOthersSMS extends Activity {
    //Intent intent=getIntent();
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        String title =RecyclerViewForSpecificDate.selectedEventToUpdate.getTitle();
        String Date =RecyclerViewForSpecificDate.selectedEventToUpdate.getDate();
        String time=RecyclerViewForSpecificDate.selectedEventToUpdate.getTime();
        Log.d("print smstitle",title);
        Log.d("size of contact list:",Integer.toString(SelectContact.contactToSend.size()));



        try{

            SmsManager smsManager=SmsManager.getDefault();
            for (int i=0;i<SelectContact.contactToSend.size();i++){
                //Need to check this contactToSend.get(i)
                //does it send phone number or just interger.

                Log.d("smssending",SelectContact.contactToSend.get(i));
                smsManager.sendTextMessage(SelectContact.contactToSend.get(i),null,"Hi an event was shared to you:"+title+" on " + Date+" at "+time,null,null);}
            Toast.makeText(getApplicationContext(),"Event Successfully Shared",Toast.LENGTH_LONG).show();
        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"Sharing failed",Toast.LENGTH_LONG).show();

        }
    }
}

