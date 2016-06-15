package ru.ifmo.md.lesson8;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

import ru.ifmo.md.lesson8.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final Uri DB_URI = Uri.parse("content://ru.ifmo.md.lesson8.providers.weather/weather");
    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    public static final String DEBUG_TAG = "itemdetailactivity";
    MyBroadcastReceiver myBroadcastReceiver;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }

        myBroadcastReceiver = new MyBroadcastReceiver();
    }

    View setDay(View v, Days d, int r) {
        ((TextView) v.findViewById(r)).setText(d.date + "\n" + d.low + " to " + d.high + "\n" + d.comment);
        return v;
    }

    View setParam(View v) {
        Cursor result = getActivity().getContentResolver().query(DB_URI, null, "number > 10", null, null);
        result.moveToPosition(Integer.parseInt(mItem.id) - 1);
        ((TextView) v.findViewById(R.id.comment)).setText("Current Temperature: "
                + result.getString(result.getColumnIndex(MySQLite.TEMP))
                + "\n" + result.getString(result.getColumnIndex(MySQLite.COMMENT))
                + "\n" + "Sunrise: " + result.getString(result.getColumnIndex(MySQLite.SUNRISE))
                + "\n" + "Sunset: " + result.getString(result.getColumnIndex(MySQLite.SUNSET)));
        ((TextView) v.findViewById(R.id.city)).setText(result.getString(result.getColumnIndex(MySQLite.CITY)));
        ((TextView) v.findViewById(R.id.date)).setText(result.getString(result.getColumnIndex(MySQLite.DAY)));
        String city = result.getString(result.getColumnIndex(MySQLite.NUM));
        Log.d(DEBUG_TAG, "city = " + city);
        int days[] = {R.id.day1, R.id.day2, R.id.day3};
        for (int i = 0; i < 3; i++) {
            Cursor dayC = getActivity().getContentResolver().query(DB_URI, null,
                    "city = " + city + " AND number = " + (i + 1), null, null);
            dayC.moveToFirst();
            Log.d(DEBUG_TAG, dayC.getCount() + " " +result.getColumnIndex(MySQLite.DAY) + " "+dayC.getColumnIndex(MySQLite.DAY) );
            String date = dayC.getString(result.getColumnIndex(MySQLite.DAY));
            String low = dayC.getString(result.getColumnIndex(MySQLite.SUNRISE));
            String high = dayC.getString(result.getColumnIndex(MySQLite.SUNSET));
            String comment = dayC.getString(result.getColumnIndex(MySQLite.COMMENT));
            v = setDay(v, new Days(date, low, high, comment), days[i]);
        }

        boolean findImage = false;
        for (int i = 0; i < DummyContent.images.size(); i++) {
            String name = DummyContent.images.get(i).getWeather();
            int image = DummyContent.images.get(i).getImage();
            if (result.getString(result.getColumnIndex(MySQLite.COMMENT)).contains(name)) {
                ((LinearLayout) v.findViewById(R.id.item_detail)).setBackgroundResource(image);
                findImage = true;
            }
        }
        if (!findImage) ((LinearLayout) v.findViewById(R.id.item_detail)).setBackgroundResource(R.drawable.background);
        ((LinearLayout) v.findViewById(R.id.days)).setBackgroundResource(R.drawable.background);
        ((Button) v.findViewById(R.id.update)).setBackgroundResource(R.drawable.background);
        return v;
    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        if (mItem != null) {
            rootView = setParam(rootView);
            ((Button) rootView.findViewById(R.id.update)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        Log.d(DEBUG_TAG, "update from " + mItem.id);
                        Intent myService = new Intent(getActivity(), MyIntentService.class);
                        IntentFilter intentFilter = new IntentFilter(MyIntentService.ACTION_MYINTENTSERVICE);
                        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
                        myService.putExtra("url", "http://weather.yahooapis.com/forecastrss?w=" + mItem.content + "&u=c");
                        myService.putExtra("id", mItem.id);
                        myService.putExtra("action", "update");
                        myService.putExtra("city", mItem.content);
                        getActivity().registerReceiver(myBroadcastReceiver, intentFilter);
                        getActivity().startService(myService);
                        rootView = setParam(rootView);
                        Log.d(DEBUG_TAG, "update ok from " + mItem.id);
                    }
                }
            });
        }
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            getActivity().unregisterReceiver(myBroadcastReceiver);
        } catch (IllegalArgumentException e) {
        }
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        private static final String DEBUG_TAG = "BroadcastReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(DEBUG_TAG, "start BR");
        }
    }

}
