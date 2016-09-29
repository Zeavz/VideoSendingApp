package com.example.jenis.videosendingapp;

import com.firebase.client.Firebase;

/**
 * Created by Jenis on 9/24/2016.
 */

public class Connector extends android.app.Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}