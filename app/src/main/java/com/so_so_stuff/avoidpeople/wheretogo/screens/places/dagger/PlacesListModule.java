package com.so_so_stuff.avoidpeople.wheretogo.screens.places.dagger;

import com.so_so_stuff.avoidpeople.wheretogo.api.PlacesApi;
import com.so_so_stuff.avoidpeople.wheretogo.screens.places.PlacesListActivity;
import com.so_so_stuff.avoidpeople.wheretogo.screens.places.mvp.PlacesListModel;
import com.so_so_stuff.avoidpeople.wheretogo.screens.places.mvp.PlacesListPresenter;
import com.so_so_stuff.avoidpeople.wheretogo.screens.places.mvp.PlacesListView;
import com.so_so_stuff.avoidpeople.wheretogo.storage.DBHelper;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ivladik on 30.10.2017.
 */
@Module
public class PlacesListModule {
    private final PlacesListActivity context;

    public PlacesListModule(PlacesListActivity context) {
        this.context = context;
    }

    @PlacesListScope
    @Provides
    PlacesListActivity provideContext() {
        return context;
    }

    @PlacesListScope
    @Provides
    PlacesListModel providePlacesListModel(PlacesListActivity context, PlacesApi placesApi, DBHelper dbHelper) {
        return new PlacesListModel(context, placesApi, dbHelper);
    }

    @PlacesListScope
    @Provides
    PlacesListView providePlacesListView(PlacesListActivity context) {
        return new PlacesListView(context);
    }

    @PlacesListScope
    @Provides
    PlacesListPresenter providePlacesListPresenter(PlacesListModel placesListModel, PlacesListView placesListView) {
        CompositeSubscription subscriptions = new CompositeSubscription();
        return new PlacesListPresenter(placesListModel, placesListView, subscriptions);
    }
}
