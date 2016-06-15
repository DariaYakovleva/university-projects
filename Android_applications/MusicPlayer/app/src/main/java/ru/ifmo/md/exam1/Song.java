package ru.ifmo.md.exam1;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by Daria on 19.10.2014.
 */
public class Song {
    public final String name;
    public final String url;
    public final String duration;
    public final int popularity;
    List<String> genres;
    int year;
    //name, url, duration, popularity, genres, year
    protected Song(String name, String url, String duration, int popularity, List<String> genres, int year) {
        this.name = name;
        this.url = url;
        this.duration = duration;
        this.popularity = popularity;
        this.genres = genres;
        this.year = year;
    }
}