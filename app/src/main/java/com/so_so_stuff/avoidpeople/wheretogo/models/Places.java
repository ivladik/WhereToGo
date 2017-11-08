package com.so_so_stuff.avoidpeople.wheretogo.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ivladik on 26.10.2017.
 */

public class Places {
    @SerializedName("results")
    private ArrayList<Place> results;

    public void setResults(ArrayList<Place> results) {
        this.results = results;
    }

    public ArrayList<Place> getResults() {
        return results;
    }
}
