package com.so_so_stuff.avoidpeople.wheretogo.screens.details.dagger;

import com.so_so_stuff.avoidpeople.wheretogo.models.Place;
import com.so_so_stuff.avoidpeople.wheretogo.screens.details.DetailsActivity;
import com.so_so_stuff.avoidpeople.wheretogo.screens.details.mvp.PlaceDetailsView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ivladik on 30.10.2017.
 */
@Module
public class PlaceDetailsModule {
    private DetailsActivity context;
    private Place place;

    public PlaceDetailsModule(DetailsActivity context, Place place) {
        this.context = context;
        this.place = place;
    }

    @PlaceDetailsScope
    @Provides
    PlaceDetailsView providePlaceDetailsModel(DetailsActivity context, Place place) {
        return new PlaceDetailsView(context, place);
    }

    @PlaceDetailsScope
    @Provides
    DetailsActivity provideDetailsActivity() {
        return context;
    }

    @PlaceDetailsScope
    @Provides
    Place providePlace() {
        return place;
    }
}
