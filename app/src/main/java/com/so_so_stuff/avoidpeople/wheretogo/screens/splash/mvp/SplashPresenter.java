package com.so_so_stuff.avoidpeople.wheretogo.screens.splash.mvp;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ivladik on 27.10.2017.
 */

public class SplashPresenter {
    public static final String LOG = SplashPresenter.class.getSimpleName();
    private SplashModel splashModel;
    private CompositeSubscription subscriptions;

    public SplashPresenter(SplashModel splashModel, CompositeSubscription subscriptions) {
        this.splashModel = splashModel;
        this.subscriptions = subscriptions;
    }

    public void onCreate() {
        subscriptions.add(getHeroesList());
    }

    private Subscription getHeroesList() {
        return Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .filter(aLong -> aLong == 2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    Log.d(LOG, String.valueOf(aLong));
                    splashModel.goToPlacesListActivity();
                }, throwable -> Log.d(LOG, "Exception while subscribing", throwable));
    }

    public void onDestroy() {
        subscriptions.clear();
    }
}
