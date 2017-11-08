package com.so_so_stuff.avoidpeople.wheretogo.screens.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.so_so_stuff.avoidpeople.wheretogo.screens.places.PlacesListActivity;
import com.so_so_stuff.avoidpeople.wheretogo.screens.splash.dagger.DaggerSplashComponent;
import com.so_so_stuff.avoidpeople.wheretogo.screens.splash.dagger.SplashModule;
import com.so_so_stuff.avoidpeople.wheretogo.screens.splash.mvp.SplashPresenter;
import com.so_so_stuff.avoidpeople.wheretogo.screens.splash.mvp.SplashView;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity {
    public static final String LOG = SplashActivity.class.getSimpleName();
    @Inject
    SplashView splashView;
    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSplashComponent.builder()
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);

        setContentView(splashView.getView());
        splashPresenter.onCreate();
    }

    public void goToPlacesListActivity() {
        Log.d(LOG, "Go to Places List Activity");
        splashPresenter.onDestroy();
        Intent i = new Intent(this, PlacesListActivity.class);
        startActivity(i);
        finish();
    }
}