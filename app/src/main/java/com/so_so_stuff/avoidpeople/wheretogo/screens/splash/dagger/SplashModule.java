package com.so_so_stuff.avoidpeople.wheretogo.screens.splash.dagger;

import com.so_so_stuff.avoidpeople.wheretogo.screens.splash.SplashActivity;
import com.so_so_stuff.avoidpeople.wheretogo.screens.splash.mvp.SplashModel;
import com.so_so_stuff.avoidpeople.wheretogo.screens.splash.mvp.SplashPresenter;
import com.so_so_stuff.avoidpeople.wheretogo.screens.splash.mvp.SplashView;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ivladik on 27.10.2017.
 */
@Module
public class SplashModule {
    private final SplashActivity context;

    public SplashModule(SplashActivity context) {
        this.context = context;
    }

    @SplashScope
    @Provides
    SplashActivity provideContext() {
        return context;
    }

    @SplashScope
    @Provides
    SplashModel provideSplashModel(SplashActivity context) {
        return new SplashModel(context);
    }

    @SplashScope
    @Provides
    SplashView provideSplashView(SplashActivity context) {
        return new SplashView(context);
    }

    @SplashScope
    @Provides
    SplashPresenter provideSplashPresenter(SplashModel splashModel) {
        CompositeSubscription subscriptions = new CompositeSubscription();
        return new SplashPresenter(splashModel, subscriptions);
    }
}
