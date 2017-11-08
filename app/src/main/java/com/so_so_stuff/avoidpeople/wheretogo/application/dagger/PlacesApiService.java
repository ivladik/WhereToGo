package com.so_so_stuff.avoidpeople.wheretogo.application.dagger;

import com.so_so_stuff.avoidpeople.wheretogo.api.PlacesApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ivladik on 27.10.2017.
 */
@Module
public class PlacesApiService {
    private static final String BASE_URL = "https://kudago.com/public-api/v1.3/";

    @AppScope
    @Provides
    PlacesApi providePlacesApi(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory, RxJavaCallAdapterFactory rxJavaCallAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .baseUrl(BASE_URL)
                .build();

        return retrofit.create(PlacesApi.class);
    }
}
