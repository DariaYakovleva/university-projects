package com.example.daria.lesson5;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<Entry> list = new ArrayList<Entry>();

    MyAdapter(Context context) {
        ctx = context;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addEntry(Entry a) {
        list.add(a);
    }
    public void deleteAll() {
        list.clear();
    }
    @Override
    public int getCount() {
        return list.size();
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

        final Entry p = getIt(position);

        ((TextView) view.findViewById(R.id.descr)).setText(p.description);
        ((TextView) view.findViewById(R.id.title)).setText(p.title + "");
        ((ImageView) view.findViewById(R.id.image)).setImageBitmap(p.image);

        return view;
    }

    public String getLink(int position) {
        return list.get(position).link;
    }

    Entry getIt(int position) {
        return ((Entry) getItem(position));
    }
}