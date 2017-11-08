package com.so_so_stuff.avoidpeople.wheretogo.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ivladik on 27.10.2017.
 */

public class FirstImage implements Serializable {
    @SerializedName("image")
    String imageUrl;

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
