package com.example.asus.assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by tanwe on 22-Nov-17.
 */

public class SplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread splashThread = new Thread() {

            public void run() {
                try {
                    sleep(3000);
                }  catch(InterruptedException e) {
                    e.printStackTrace();
                } finally
                {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        };
        splashThread.start();
    }
}
