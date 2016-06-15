package com.example.daria.lesson5;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import java.util.concurrent.TimeUnit;

/**
 * Created by Daria on 10.11.2014.
 */
public class MyCursorLoader extends CursorLoader {

    MySQLiteDatabase db;
    public MyCursorLoader(Context context, MySQLiteDatabase db) {
        super(context);
        this.db = db;
    }

    @Override
    public Cursor loadInBackground() {
        Cursor cursor = db.getAll();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cursor;
    }

}
