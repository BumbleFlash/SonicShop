package com.example.sudarshan.sonicshop;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Sudarshan on 06-04-2017.
 */

public class Myapp extends Application {

    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
