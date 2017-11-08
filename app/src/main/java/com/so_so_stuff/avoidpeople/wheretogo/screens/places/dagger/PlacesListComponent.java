package com.so_so_stuff.avoidpeople.wheretogo.screens.places.dagger;

import com.so_so_stuff.avoidpeople.wheretogo.application.dagger.AppComponent;
import com.so_so_stuff.avoidpeople.wheretogo.screens.places.PlacesListActivity;

import dagger.Component;

/**
 * Created by ivladik on 30.10.2017.
 */
@PlacesListScope
@Component(modules = PlacesListModule.class, dependencies = AppComponent.class)
public interface PlacesListComponent {
    void inject(PlacesListActivity activity);
}
