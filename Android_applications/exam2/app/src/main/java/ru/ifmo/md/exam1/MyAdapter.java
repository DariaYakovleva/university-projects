package ru.ifmo.md.exam1;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Daria on 29.11.2014.
 */

public class MyAdapter extends CursorAdapter {

    MyAdapter(Context context, Cursor cur) {
        super(context, cur);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_item, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view.findViewById(R.id.descr)).setText(cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.NAME)));
        ((TextView) view.findViewById(R.id.title)).setText(cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.ID)));
    }

    Song getIt(int position) {
        return (Song)(getItem(position));
    }
}

