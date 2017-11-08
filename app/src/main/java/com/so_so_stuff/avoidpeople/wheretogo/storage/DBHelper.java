package com.so_so_stuff.avoidpeople.wheretogo.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.so_so_stuff.avoidpeople.wheretogo.models.FirstImage;
import com.so_so_stuff.avoidpeople.wheretogo.models.Place;

import java.util.ArrayList;

import static com.so_so_stuff.avoidpeople.wheretogo.storage.Contract.Entry.*;

/**
 * Created by ivladik on 01.11.2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = DBHelper.class.getSimpleName();
    private static final String NAME = "places.db";
    private static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "create table " + TABLE_NAME + "("
                + COLUMN_ID + " integer primary key, "
                + COLUMN_TITLE + " text not null, "
                + COLUMN_DESCRIPTION + " text not null, "
                + COLUMN_ITEM_URL + " text not null, "
                + COLUMN_IMAGE + " text not null, "
                + COLUMN_AGE_RESTRICTION + " integer not null" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_TABLE = "drop table if exists " + TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addPlace(Place place) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, place.getId());
        values.put(COLUMN_TITLE, place.getTitle());
        values.put(COLUMN_DESCRIPTION, place.getDescription());
        values.put(COLUMN_ITEM_URL, place.getItemUrl());
        values.put(COLUMN_IMAGE, place.getFirstImage().getImageUrl());
        values.put(COLUMN_AGE_RESTRICTION, place.getAgeRestriction());

        try {
            db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }

        db.close();
    }

    public ArrayList<Place> getSavedPlaces() {
        ArrayList<Place> placesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    Place place = new Place();
                    place.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                    place.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                    place.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                    place.setItemUrl(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_URL)));
                    FirstImage firstImage = new FirstImage();
                    firstImage.setImageUrl(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));
                    place.setFirstImage(firstImage);
                    place.setAgeRestriction(cursor.getInt(cursor.getColumnIndex(COLUMN_AGE_RESTRICTION)));

                    placesList.add(place);
                }
                cursor.close();
            }
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }
        return placesList;
    }
}
