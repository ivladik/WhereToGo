package com.so_so_stuff.avoidpeople.wheretogo.screens.details.dagger;

import com.so_so_stuff.avoidpeople.wheretogo.screens.details.DetailsActivity;

import dagger.Component;

/**
 * Created by ivladik on 30.10.2017.
 */
@PlaceDetailsScope
@Component(modules = PlaceDetailsModule.class)
public interface PlaceDetailsComponent {
    void inject(DetailsActivity activity);
}
