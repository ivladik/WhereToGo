package com.so_so_stuff.avoidpeople.wheretogo.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ivladik on 26.10.2017.
 */

public class Place implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("item_url")
    private String itemUrl;
    @SerializedName("first_image")
    private FirstImage firstImage;
    @SerializedName("age_restriction")
    private int ageRestriction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public void setFirstImage(FirstImage firstImage) {
        this.firstImage = firstImage;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public FirstImage getFirstImage() {
        return firstImage;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }
}
