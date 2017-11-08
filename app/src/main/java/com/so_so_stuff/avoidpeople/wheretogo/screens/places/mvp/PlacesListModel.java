package com.so_so_stuff.avoidpeople.wheretogo.screens.places.mvp;

import com.so_so_stuff.avoidpeople.wheretogo.api.PlacesApi;
import com.so_so_stuff.avoidpeople.wheretogo.models.Place;
import com.so_so_stuff.avoidpeople.wheretogo.models.Places;
import com.so_so_stuff.avoidpeople.wheretogo.screens.places.PlacesListActivity;
import com.so_so_stuff.avoidpeople.wheretogo.storage.DBHelper;
import com.so_so_stuff.avoidpeople.wheretogo.utils.NetworkUtils;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by admin on 30.10.2017.
 */

public class PlacesListModel {
    private PlacesListActivity context;
    private PlacesApi placesApi;
    private DBHelper dbHelper;

    public PlacesListModel(PlacesListActivity context, PlacesApi placesApi, DBHelper dbHelper) {
        this.context = context;
        this.placesApi = placesApi;
        this.dbHelper = dbHelper;
    }

    Observable<Places> providePlacesList() {
        return placesApi.getPlaces("выставка", "spb");
    }

    boolean isNetworkAvailable() { // delete
//        return NetworkUtils.isNetworkAvailableObservable(context);
        return NetworkUtils.isNetworkAvailable(context);
    }

    public void goToPlaceDetailsActivity(Place place) {
        context.goToPlaceDetailsActivity(place);
    }

    public Observable<Places> getPlacesListFromDb() {
        Places places = new Places();
        places.setResults(dbHelper.getSavedPlaces());
        return Observable.just(places);
    }

    public void addPlacesToDb(ArrayList<Place> placesList) {
        for (Place place : placesList) {
            dbHelper.addPlace(place);
        }
    }
}
