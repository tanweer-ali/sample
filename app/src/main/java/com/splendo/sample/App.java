package com.splendo.sample;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ali on 17/06/2017.
 */

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }


    public static Context getContext() {
        return context;
    }
}
