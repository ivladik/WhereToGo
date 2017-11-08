package com.so_so_stuff.avoidpeople.wheretogo.screens.places;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.so_so_stuff.avoidpeople.wheretogo.application.AppController;
import com.so_so_stuff.avoidpeople.wheretogo.models.Place;
import com.so_so_stuff.avoidpeople.wheretogo.screens.details.DetailsActivity;
import com.so_so_stuff.avoidpeople.wheretogo.screens.places.dagger.DaggerPlacesListComponent;
import com.so_so_stuff.avoidpeople.wheretogo.screens.places.dagger.PlacesListModule;
import com.so_so_stuff.avoidpeople.wheretogo.screens.places.mvp.PlacesListPresenter;
import com.so_so_stuff.avoidpeople.wheretogo.screens.places.mvp.PlacesListView;

import javax.inject.Inject;

public class PlacesListActivity extends AppCompatActivity {
    @Inject
    PlacesListView view;
    @Inject
    PlacesListPresenter presenter;
    private static final String LOG = PlacesListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerPlacesListComponent.builder()
                .appComponent(AppController.getAppComponent())
                .placesListModule(new PlacesListModule(this))
                .build()
                .inject(this);
        setContentView(view.getView());
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public void goToPlaceDetailsActivity(Place place) {
        Log.d(LOG, "goToPlaceDetailsActivity");
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("place", place);
        startActivity(intent);
    }
}
