package com.example.daria.extratask;

/**
 * Created by Daria on 16.01.2015.
 */
import android.content.Context;
import android.content.ContextWrapper;
import android.content.CursorLoader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class ImageAdapter extends CursorAdapter {

    public ImageAdapter(Context c, Cursor cur) {
        super(c, cur);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_item, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String url = cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.COLUMN_URL));
        String name = url.substring(url.lastIndexOf("/") + 1);
        Bitmap bitmap = BitmapFactory.decodeFile(MainActivity.PATH + "/" + name);
        ((ImageView) view.findViewById(R.id.image)).setImageBitmap(bitmap);
//        context.getApplicationContext();
    }

    public long getItemId(int position) {
        return position;
    }


}
