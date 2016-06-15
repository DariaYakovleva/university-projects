package ru.ifmo.md.colloquium2;

/**
 * Created by Daria on 11.11.2014.
 */

import java.util.ArrayList;
import java.util.List;

import android.app.NotificationManager;
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
    List<Person> list = new ArrayList<Person>();

    MyAdapter(Context context) {
        ctx = context;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addPerson(Person a) {
        list.add(a);
        notifyDataSetChanged();
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

    public void updCnt(int position) {
        list.get(position).cnt++;
        notifyDataSetChanged();
    }

    public String getWinner() {
        int res = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).cnt > list.get(res).cnt) res = i;
        }
        return list.get(res).name;
    }
    public void clearAll() {
         for (int i = 0; i < list.size(); i++) {
             list.get(i).cnt = 0;
         }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.my_item, parent, false);
        }

        final Person p = getIt(position);

        ((TextView) view.findViewById(R.id.name)).setText(p.name);
        ((TextView) view.findViewById(R.id.cnt)).setText("" + p.cnt);

        return view;
    }

    Person getIt(int position) {
        return ((Person) getItem(position));
    }
}