package com.masson.alex.jsonplaceholder.application;

import android.app.Application;

/**
 * Created by alex on 24/03/2018.
 */

public class MyApplication extends Application {

    private static MyApplication INSTANCE ;
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static MyApplication app(){
        return INSTANCE;
    }


}
