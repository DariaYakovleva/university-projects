package com.example.daria.extratask;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v4.app.FragmentManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;


public class MainActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String DEBUG_TAG = "MainActivity";

    MyBroadcastReceiver myBroadcastReceiver;
    Button load;
    public ArrayList<Bitmap> images = new ArrayList<>();
    public static final Uri DB_URI = Uri.parse("content://com.example.daria.extratask.providers.urls/urls");
    public static final String PATH = "/data/data/com.example.daria.extratask/cache";
    private static final int LOADER_ID = 1;
    private LoaderManager.LoaderCallbacks<Cursor> mCallbacks;

    ViewPager pager;
    PagerAdapter pagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCallbacks = this;
        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Log.d(DEBUG_TAG, "onPageSelected, position = " + position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        LoaderManager lm = getLoaderManager();
        lm.initLoader(LOADER_ID, null, mCallbacks);
        load = (Button) findViewById(R.id.load);
        Cursor cur = getContentResolver().query(DB_URI, null, null, null, null);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toClick();
            }
        });
        myBroadcastReceiver = new MyBroadcastReceiver();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(MainActivity.this, DB_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()) {
            case LOADER_ID:
//                adapter.swapCursor(cursor);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
//        adapter.swapCursor(null);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //  mCurrentPhotoPath = savedInstanceState.getString("mCurrentPhotoPath");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // outState.putString("mCurrentPhotoPath", mCurrentPhotoPath);
        super.onSaveInstanceState(outState);
    }

    public byte[] getByteArrayFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        return bos.toByteArray();
    }

    ProgressDialog progressDialog;

    public void toClick() {
        Toast.makeText(MainActivity.this, "Please, wait", Toast.LENGTH_SHORT).show();
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Downloading ...");
            progressDialog.setCancelable(false);
            progressDialog.setMax(100);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
            Intent myService = new Intent(this, MyIntentService.class);
            IntentFilter intentFilter = new IntentFilter(MyIntentService.ACTION_MYINTENTSERVICE);
            intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
            registerReceiver(myBroadcastReceiver, intentFilter);
            startService(myService);
        } else {
            Toast.makeText(MainActivity.this, "No network connection", Toast.LENGTH_SHORT).show();
            Log.d(DEBUG_TAG, "no network connection");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(myBroadcastReceiver);
        } catch (IllegalArgumentException e) {
        }
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        private static final String DEBUG_TAG = "BroadcastReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(DEBUG_TAG, "start BR");
            int progress = intent.getIntExtra("time", 0);
            double per = progress / (double)MyIntentService.COUNT * 100;
            if (progress != 0) {
                progressDialog.setProgress((int)per);
            }
            ArrayList<String> urls = intent.getStringArrayListExtra("urls");
            if (urls == null) return;
            String where = "_id > 0";
            getContentResolver().delete(DB_URI, where, null);
            for (int i = 0; i < urls.size(); i++) {
                ContentValues cv = new ContentValues();
                cv.put(MySQLiteDatabase.COLUMN_URL, urls.get(i));
                cv.put(MySQLiteDatabase.NUMBER, i);
                getContentResolver().insert(MainActivity.DB_URI, cv);
            }
            progressDialog.hide();
            Log.d(DEBUG_TAG, "add" + images.size());
        }
    }
}
