package com.dotdex.frenzy.app;/**
 * Created by DABBY(3pleMinds) on 09-Mar-16.
 */

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.facebook.FacebookSdk;

/**
 * DABBY(3pleMinds) 09-Mar-16 1:39 PM 2016 03
 * 09 13 39 Frenzy
 **/
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        // Initialize the SDK before executing any other operations,
        // especially, if you're using Facebook UI elements.
    }
}
