package com.so_so_stuff.avoidpeople.wheretogo.screens.places.mvp;

import android.util.Log;

import com.so_so_stuff.avoidpeople.wheretogo.models.Place;

import java.util.ArrayList;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by admin on 30.10.2017.
 */

public class PlacesListPresenter {
    private static final String LOG = PlacesListPresenter.class.getSimpleName();
    private PlacesListModel placesListModel;
    private PlacesListView placesListView;
    private CompositeSubscription subscriptions;
    private ArrayList<Place> placesList = new ArrayList<>();

    public PlacesListPresenter(PlacesListModel placesListModel, PlacesListView placesListView, CompositeSubscription subscriptions) {
        this.placesListModel = placesListModel;
        this.placesListView = placesListView;
        this.subscriptions = subscriptions;
    }

    public void onCreate() {
//        Log.d(LOG, "onCreate");
        subscriptions.add(getHeroesList());
        subscriptions.add(respondToClick());
    }

    private Subscription respondToClick() {
        return placesListView.observeClickedItem()
                .subscribe(placePosition -> placesListModel.goToPlaceDetailsActivity(placesList.get(placePosition)));
    }

    private Subscription getHeroesList() {
        if (placesListModel.isNetworkAvailable()) {
            return placesListModel.providePlacesList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(places -> {
                        placesList = places.getResults();
                        placesListModel.addPlacesToDb(placesList);
                        placesListView.swapAdapter(placesList);
                    }, throwable -> Log.d(LOG, "Exception while subscribing", throwable));
        } else {
            return placesListModel.getPlacesListFromDb()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(places -> {
                        placesList = places.getResults();
                        placesListView.swapAdapter(placesList);
                    }, throwable -> Log.d(LOG, "Exception while subscribing", throwable));
        }
    }

    public void onDestroy() {
        subscriptions.clear();
    }


}
