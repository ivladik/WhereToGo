package com.so_so_stuff.avoidpeople.wheretogo.screens.splash.dagger;

import com.so_so_stuff.avoidpeople.wheretogo.screens.splash.SplashActivity;

import dagger.Component;

/**
 * Created by ivladik on 27.10.2017.
 */
@SplashScope
@Component(modules = SplashModule.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
}
