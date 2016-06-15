package com.example.daria.extratask;

/**
 * Created by Daria on 18.01.2015.
 */
import java.util.Random;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class PageFragment extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    int pageNumber;

    static PageFragment newInstance(int page) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        Log.d("pageF", "" + pageNumber);
        View view = inflater.inflate(R.layout.page, null);
        GridView gridview;
        ImageAdapter adapter;
        String where = "num >= " + (pageNumber * 9) + " AND num <= " + (pageNumber * 9 + 8);
        Cursor cur = getActivity().getContentResolver().query(MainActivity.DB_URI, null, where, null, null);
        gridview = (GridView) view.findViewById(R.id.gridView1);
        adapter = new ImageAdapter(getActivity(), cur);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), FullImageActivity.class);
                intent.putExtra("position", position + (pageNumber * 9));
                startActivity(intent);
            }
        });

        return view;
    }
}
