package com.so_so_stuff.avoidpeople.wheretogo.screens.splash.mvp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.so_so_stuff.avoidpeople.wheretogo.R;
import com.so_so_stuff.avoidpeople.wheretogo.screens.splash.SplashActivity;

/**
 * Created by ivladik on 27.10.2017.
 */

public class SplashView {
    View view;

    public SplashView(SplashActivity context) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_splash, parent, true);
    }

    public View getView() {
        return view;
    }
}
