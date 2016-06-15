package ru.ifmo.md.colloquium3;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Daria on 29.11.2014.
 */

public class MyAdapterMoney extends CursorAdapter {
//    private Cursor cursor;
//    Context ctx;
//    LayoutInflater inflater;

    MyAdapterMoney(Context context, Cursor cur) {
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
        ((TextView) view.findViewById(R.id.cur)).setText(cursor.getString(cursor.getColumnIndex(MyCurDB.CURRENCY)));
        ((TextView) view.findViewById(R.id.valueCur)).setText(cursor.getString(cursor.getColumnIndex(MyCurDB.VALUE)));
    }

    Currency getIt(int position) {
        return ((Currency) getItem(position));
    }
}

