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

/**
 * Created by Daria on 29.11.2014.
 */

public class MyAdapter extends CursorAdapter {
    private Cursor cursor;
    Context ctx;
    LayoutInflater lInflater;
    List<Weather> list = new ArrayList<Weather>();

    MyAdapter(Context context, Cursor cur) {
        super(context, cur);
        cursor = cur;
        ctx = context;
     //   list = product;
    //    lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_item_list, parent, false);
        bindView(view, context, cursor);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ((TextView) view.findViewById(R.id.city)).setText(cursor.getString(cursor.getColumnIndex(MySQLite.CITY)));
        ((TextView) view.findViewById(R.id.temp)).setText(cursor.getString(cursor.getColumnIndex(MySQLite.TEMP)));
        if (cursor.getString(cursor.getColumnIndex(MySQLite.COMMENT)).equals("sunny")) {
            ((ImageView) view.findViewById(R.id.image)).setImageResource(R.drawable.sunny);
        }
        if (cursor.getString(cursor.getColumnIndex(MySQLite.COMMENT)).equals("snow")) {
            ((ImageView) view.findViewById(R.id.image)).setImageResource(R.drawable.snow);
        }
        if (cursor.getString(cursor.getColumnIndex(MySQLite.COMMENT)).equals("cloudy")) {
            ((ImageView) view.findViewById(R.id.image)).setImageResource(R.drawable.cloudy);
        }
        if (cursor.getString(cursor.getColumnIndex(MySQLite.COMMENT)).equals("rain")) {
            ((ImageView) view.findViewById(R.id.image)).setImageResource(R.drawable.rain);
        }
    }

    public void addEntry(Weather a) {
        list.add(a);
    }

    public void deleteAll() {
        list.clear();
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.my_item, parent, false);
        }

       // final Weather p = getIt(position);
        cursor.moveToPosition(position);
        ((TextView) view.findViewById(R.id.city)).setText(cursor.getString(cursor.getColumnIndex(MySQLite.CITY)));
        ((TextView) view.findViewById(R.id.temp)).setText(cursor.getString(cursor.getColumnIndex(MySQLite.TEMP)));
        if (cursor.getString(cursor.getColumnIndex(MySQLite.COMMENT)).equals("sunny")) {
            ((ImageView) view.findViewById(R.id.image)).setImageResource(R.drawable.sunny);
        }
        if (cursor.getString(cursor.getColumnIndex(MySQLite.COMMENT)).equals("snow")) {
            ((ImageView) view.findViewById(R.id.image)).setImageResource(R.drawable.snow);
        }
        if (cursor.getString(cursor.getColumnIndex(MySQLite.COMMENT)).equals("cloudy")) {
            ((ImageView) view.findViewById(R.id.image)).setImageResource(R.drawable.cloudy);
        }
        if (cursor.getString(cursor.getColumnIndex(MySQLite.COMMENT)).equals("rain")) {
            ((ImageView) view.findViewById(R.id.image)).setImageResource(R.drawable.rain);
        }
        return view;
    }

    Weather getIt(int position) {
        return ((Weather) getItem(position));
    }
}

