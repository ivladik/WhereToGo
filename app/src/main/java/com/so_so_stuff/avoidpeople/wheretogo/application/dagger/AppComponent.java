package com.so_so_stuff.avoidpeople.wheretogo.application.dagger;

import com.so_so_stuff.avoidpeople.wheretogo.api.PlacesApi;
import com.so_so_stuff.avoidpeople.wheretogo.storage.DBHelper;

import dagger.Component;

/**
 * Created by ivladik on 27.10.2017.
 */
@AppScope
@Component(modules = {AppContextModule.class, NetworkModule.class, PlacesApiService.class, DatabaseModule.class})
public interface AppComponent {
    PlacesApi getPlacesApi();
    DBHelper getDbHelper();
}
