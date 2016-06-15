package ru.ifmo.md.lesson8;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

import ru.ifmo.md.lesson8.dummy.DummyContent;


/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ItemListFragment} and the item details
 * (if present) is a {@link ItemDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link ItemListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class ItemListActivity extends FragmentActivity
        implements ItemListFragment.Callbacks {

    private static final String LOG_TAG = "intemlistactivity";

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Log.d(LOG_TAG, "itemlistactivity");

//        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String res = getLocation();
//                ((TextView) findViewById(R.id.textView)).setText(res);
//            }
//        });
        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ItemListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.item_list))
                    .setActivateOnItemClick(true);
        }
    }

    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ItemDetailActivity.class);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
    String tvEnabledGPS = "";
    String tvStatusGPS = "";
    String tvLocationGPS = "";
    String tvEnabledNet = "";
    String tvStatusNet = "";
    String tvLocationNet = "";

    public LocationManager locationManager;
    StringBuilder sbGPS = new StringBuilder();
    StringBuilder sbNet = new StringBuilder();
    public String getLocation() {
        Log.d(LOG_TAG, "getLocation");
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Log.d(LOG_TAG, "request1");
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 10, 10, locationListener);
        Log.d(LOG_TAG, "request2");
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 10, 10, locationListener);
        checkEnabled();
        String res = tvEnabledGPS + "\n" + tvStatusGPS + "\n" + tvLocationGPS + "\n\n" +
                tvEnabledNet + "\n" + tvStatusNet + "\n" + tvLocationNet;
        Log.d(LOG_TAG, res);
        locationManager.removeUpdates(locationListener);
        return res;
    }
    private void showLocation(Location location) {
        if (location == null)
            return;
        Log.d(LOG_TAG, "showLocation");
        if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
            tvLocationGPS = formatLocation(location);
        } else if (location.getProvider().equals(LocationManager.NETWORK_PROVIDER)) {
            tvLocationNet = formatLocation(location);
        }
    }

    private String formatLocation(Location location) {
        if (location == null)
            return "";
        Log.d(LOG_TAG, "formatLocation");
        return String.format(
                "Coordinates: lat = %1$.4f, lon = %2$.4f, time = %3$tF %3$tT",
                location.getLatitude(), location.getLongitude(), new Date(
                        location.getTime()));
    }

    private void checkEnabled() {
        Log.d(LOG_TAG, "checkEnabled");
        tvEnabledGPS = "Enabled: " + locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        tvEnabledNet = "Enabled: " + locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
    public LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            Log.d(LOG_TAG, "onLocation");
            showLocation(location);
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d(LOG_TAG, "onProvider");
            checkEnabled();
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d(LOG_TAG, "onProviderE");
            checkEnabled();
            showLocation(locationManager.getLastKnownLocation(provider));
        }


        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d(LOG_TAG, "onStatus");
            if (provider.equals(LocationManager.GPS_PROVIDER)) {
                tvStatusGPS = "Status: " + String.valueOf(status);
            } else if (provider.equals(LocationManager.NETWORK_PROVIDER)) {
                tvStatusNet = "Status: " + String.valueOf(status);
            }
        }
    };

}
