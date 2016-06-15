package ru.ifmo.md.lesson8;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    View setDay(View v, Days d, int r) {
        ((TextView) v.findViewById(r)).setText(d.date + "\n" + d.low + " to " + d.high + "\n" + d.comment);
        return v;
    }

    View setParam(View v) {
        Cursor result = getActivity().getContentResolver().query(DB_URI, null, null, null, null);
        result.moveToPosition(Integer.parseInt(mItem.id) - 1);
        ((TextView) v.findViewById(R.id.comment)).setText("Current Temperature: "
                + result.getString(result.getColumnIndex(MySQLite.TEMP))
                + "\n" + result.getString(result.getColumnIndex(MySQLite.COMMENT))
                + "\n" + "Sunrise: " + result.getString(result.getColumnIndex(MySQLite.SUNRISE))
                + "\n" + "Sunset: " + result.getString(result.getColumnIndex(MySQLite.SUNSET)));
        ((TextView) v.findViewById(R.id.city)).setText(result.getString(result.getColumnIndex(MySQLite.CITY)));
        ((TextView) v.findViewById(R.id.date)).setText(result.getString(result.getColumnIndex(MySQLite.DAY)));
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        if (mItem != null) {
            final Uri updUri = ContentUris.withAppendedId(DB_URI, Integer.parseInt(mItem.id));
            Log.d(DEBUG_TAG, "update" + updUri.toString() + " from " + mItem.id);
            rootView = setParam(rootView);
            ((Button) rootView.findViewById(R.id.update)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        new DownloadWeatherTask() {

                            @Override
                            protected void onPostExecute(Weather result) {
                                ContentValues cv = new ContentValues();
                                cv.put(MySQLite.CITY, result.city);
                                cv.put(MySQLite.DAY, result.date);
                                cv.put(MySQLite.TEMP, result.temp);
                                cv.put(MySQLite.COMMENT, result.comment);
                                cv.put(MySQLite.SUNRISE, result.sunrise);
                                cv.put(MySQLite.SUNSET, result.sunset);
                                int cnt = getActivity().getContentResolver().update(updUri, cv, null, null);
                                Log.d(DEBUG_TAG, "update" + updUri.toString() + " from " + mItem.id);
                                rootView = setParam(rootView);
                                rootView = setDay(rootView, result.days.get(0), R.id.day1);
                                rootView = setDay(rootView, result.days.get(1), R.id.day2);
                                rootView = setDay(rootView, result.days.get(2), R.id.day3);

                            }
                        }.execute("http://weather.yahooapis.com/forecastrss?w=" + mItem.content + "&u=c");
                    }
                }
            });
        }
        return rootView;
    }
}
