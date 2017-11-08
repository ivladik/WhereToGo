package com.so_so_stuff.avoidpeople.wheretogo.application.dagger;

import android.content.Context;

import com.so_so_stuff.avoidpeople.wheretogo.storage.DBHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ivladik on 02.11.2017.
 */
@Module
public class DatabaseModule {
    @AppScope
    @Provides
    DBHelper provideDbHelper(Context context) {
        return new DBHelper(context);
    }
}
