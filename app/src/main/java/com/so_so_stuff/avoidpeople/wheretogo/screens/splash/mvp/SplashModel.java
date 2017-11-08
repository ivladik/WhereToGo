package com.so_so_stuff.avoidpeople.wheretogo.screens.splash.mvp;

import com.so_so_stuff.avoidpeople.wheretogo.screens.splash.SplashActivity;

/**
 * Created by ivladik on 27.10.2017.
 */

public class SplashModel {
    private SplashActivity context;

    public SplashModel(SplashActivity context) {
        this.context = context;
    }

    public void goToPlacesListActivity() {
        context.goToPlacesListActivity();
    }
}
