package ru.ifmo.md.lesson8;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ItemDetailActivity extends ActionBarActivity {
    public static String DEBUG_TAG = "itemdetailactivity";
    Button button;
    TextView text;
    List<Weather> days;
    ItemDetailFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Log.d(DEBUG_TAG, "start");
        final Uri DB_URI = Uri.parse("content://ru.startandroid.providers.weather/weather");
        Cursor cursor = getContentResolver().query(DB_URI, null, null, null, null);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID));
            fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWeatherTask() {
                @Override
                protected void onPostExecute(Weather result) {
                    Log.d(DEBUG_TAG, "onPostExecute.." + result.city);
                    ((TextView)fragment.getView().findViewById(R.id.comment)).setText("Current Temperature: " + result.temp
                            + "\n" + result.comment + "\n" + "Sunrise: " + result.sunrise + "\n" + "Sunset: " + result.sunset);
                    ((TextView) fragment.getView().findViewById(R.id.city)).setText(result.city);
                    ((TextView) fragment.getView().findViewById(R.id.date)).setText(result.date);
                    ContentValues cv = new ContentValues();
                    cv.put(MySQLite.CITY, result.city);
                    cv.put(MySQLite.DAY, result.date);
                    cv.put(MySQLite.TEMP, result.temp);
                    cv.put(MySQLite.COMMENT, result.comment);
                    cv.put(MySQLite.SUNRISE, result.sunrise);
                    cv.put(MySQLite.SUNSET, result.sunset);
                    Uri newUri = getContentResolver().insert(DB_URI, cv);
                    Log.d(DEBUG_TAG, "insert, result Uri : " + newUri.toString());

                    setDay(result.days.get(0), R.id.day1);
                    setDay(result.days.get(1), R.id.day2);
                    setDay(result.days.get(2), R.id.day3);

                }
            }.execute("http://weather.yahooapis.com/forecastrss?w=2123260&u=c");
        }
    }

    void setDay(Days d, int r) {
        ((TextView) fragment.getView().findViewById(r)).setText(d.date + "\n" +
                "from " + d.low + " to " + d.high + "\n" +
                d.comment);
        ((TextView) fragment.getView().findViewById(r)).setBackgroundResource(R.drawable.sunny);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, ItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
