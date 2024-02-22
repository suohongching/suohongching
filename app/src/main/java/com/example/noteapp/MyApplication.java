package com.example.noteapp;

import android.app.Application;

import java.util.Calendar;
import java.util.Locale;

public class MyApplication extends Application {

    @Override
    public void onCreate() { super.onCreate(); }

    public static final String formatTimeStamp(long timestamp){

        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timestamp);

        String DataFormat = null;
        String date = DataFormat.format("DD/MM/YYYY", cal).toString();
        return date;
    }
}
