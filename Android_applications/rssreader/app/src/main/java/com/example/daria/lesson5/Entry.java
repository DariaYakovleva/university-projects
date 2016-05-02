package com.example.daria.lesson5;

import android.graphics.Bitmap;

/**
 * Created by Daria on 19.10.2014.
 */
public class Entry {
    public final String title;
    public final String link;
    public final String description;
    public final Bitmap image;

    protected Entry(String title, String description, String link, Bitmap image) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.image = image;
    }
}