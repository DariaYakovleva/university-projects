package ru.ifmo.md.lesson8;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daria on 29.11.2014.
 */

public class Weather {
    public final String city;
    public final String date;
    public final String temp;
    public final String comment;
    public final String sunrise;
    public final String sunset;
    List<Days> days;

    public Weather(String city, String date, String temp, String comment, String sunrise, String sunset) {
        this.city = city;
        this.date = date;
        this.temp = temp;
        this.comment = comment;
        this.sunrise = sunrise;
        this.sunset = sunset;
        days = new ArrayList<>();
    }
    void addDay(String date, String low, String high, String comment) {
        days.add(new Days(date, low, high, comment));
    }
}

class Days{
    public final String date;
    public final String low;
    public final String high;
    public final String comment;
    protected Days(String date, String low, String high, String comment) {
        this.date = date;
        this.low = low;
        this.high = high;
        this.comment = comment;
    }
}
