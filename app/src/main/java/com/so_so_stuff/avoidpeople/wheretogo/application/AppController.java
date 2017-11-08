package com.so_so_stuff.avoidpeople.wheretogo.application;

import android.app.Application;

import com.so_so_stuff.avoidpeople.wheretogo.application.dagger.AppComponent;
import com.so_so_stuff.avoidpeople.wheretogo.application.dagger.AppContextModule;
import com.so_so_stuff.avoidpeople.wheretogo.application.dagger.DaggerAppComponent;

/**
 * Created by ivladik on 27.10.2017.
 */

public class AppController extends Application {
    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appContextModule(new AppContextModule(this)).build();
    }
}
