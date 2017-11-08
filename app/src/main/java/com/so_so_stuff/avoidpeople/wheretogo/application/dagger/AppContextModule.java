package com.so_so_stuff.avoidpeople.wheretogo.application.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ivladik on 27.10.2017.
 */
@Module
public class AppContextModule {
    private Context context;

    public AppContextModule(Context context) {
        this.context = context;
    }

    @AppScope
    @Provides
    Context provideContext() {
        return context;
    }
}
