package com.example.daria.rsssample;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Daria on 14.10.2014.
 */

public class MyAdapter<T> extends BaseAdapter{

    private List<T> data;

    public MyAdapter(List<T> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i; //position
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_item, viewGroup, false);
        ((TextView) v).setText(getItem(i).toString());
        if (i % 2 == 0) {
            v.setBackgroundColor(Color.BLACK);
            ((TextView) v).setTextColor(Color.WHITE);
        } else {
            v.setBackgroundColor(Color.WHITE);
        }
        return v;
    }
}
