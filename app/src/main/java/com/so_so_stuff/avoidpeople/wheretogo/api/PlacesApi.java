package com.so_so_stuff.avoidpeople.wheretogo.api;

import com.so_so_stuff.avoidpeople.wheretogo.models.Places;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ivladik on 26.10.2017.
 */

public interface PlacesApi {
    @GET("search/")
    Observable<Places> getPlaces(@Query("q") String query, @Query("location") String location);
}
