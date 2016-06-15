package com.example.daria.lesson5;

/**
 * Created by Daria on 16.01.2015.
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {
    private Context context;
    private int textViewResourceId;
    private List<String> objects = new ArrayList<>();
    public static boolean flag = false;
    public CustomAdapter(Context context, int textViewResourceId, List<String> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.textViewResourceId = textViewResourceId;
        this.objects = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = View.inflate(context, textViewResourceId, null);
        if (flag != false) {
            TextView tv = (TextView) convertView;
            tv.setText(objects.get(position));
        }
        return convertView;
    }

    public String getEntry(int position) {
        return objects.get(position);
    }
    public void addObject(String a) {
        objects.add(a);
    }
}
