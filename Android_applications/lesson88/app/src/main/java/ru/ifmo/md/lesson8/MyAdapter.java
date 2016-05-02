package ru.ifmo.md.lesson8;

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

import ru.ifmo.md.lesson8.dummy.DummyContent;

/**
 * Created by Daria on 29.11.2014.
 */

public class MyAdapter extends CursorAdapter {
//    private Cursor cursor;
//    Context ctx;
//    LayoutInflater inflater;

    MyAdapter(Context context, Cursor cur) {
        super(context, cur);
//        cursor = cur;
//        ctx = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_item, parent, false);
//        bindView(view, context, cursor);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ((TextView) view.findViewById(R.id.city)).setText(cursor.getString(cursor.getColumnIndex(MySQLite.CITY)));
        ((TextView) view.findViewById(R.id.temp)).setText(cursor.getString(cursor.getColumnIndex(MySQLite.TEMP)));
        for (int i = 0; i < DummyContent.images.size(); i++) {
            String name = DummyContent.images.get(i).getWeather();
            int icon = DummyContent.images.get(i).getIcon();
            if (cursor.getString(cursor.getColumnIndex(MySQLite.COMMENT)).contains(name)) {
                ((ImageView) view.findViewById(R.id.image)).setImageResource(icon);
            }
        }
    }

    Weather getIt(int position) {
        return ((Weather) getItem(position));
    }
}

