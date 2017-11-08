package com.so_so_stuff.avoidpeople.wheretogo.screens.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.so_so_stuff.avoidpeople.wheretogo.models.Place;
import com.so_so_stuff.avoidpeople.wheretogo.screens.details.dagger.DaggerPlaceDetailsComponent;
import com.so_so_stuff.avoidpeople.wheretogo.screens.details.dagger.PlaceDetailsModule;
import com.so_so_stuff.avoidpeople.wheretogo.screens.details.mvp.PlaceDetailsView;

import javax.inject.Inject;

public class DetailsActivity extends AppCompatActivity {
    @Inject
    PlaceDetailsView placeDetailsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Place place = (Place) getIntent().getSerializableExtra("place");
        DaggerPlaceDetailsComponent.builder()
                .placeDetailsModule(new PlaceDetailsModule(this, place))
                .build()
                .inject(this);
        setContentView(placeDetailsView.getView());
    }
}
