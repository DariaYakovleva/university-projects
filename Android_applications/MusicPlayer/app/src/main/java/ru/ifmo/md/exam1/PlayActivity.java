package ru.ifmo.md.exam1;


import android.app.Activity;
import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class PlayActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    PlayAdapter adapter;
    public static final String DEBUG_TAG = "MyActivity";
    ListView list;
    private static final int LOADER_ID = 1;
    private LoaderManager.LoaderCallbacks<Cursor> mCallbacks;
    private MyBroadcastReceiver myBroadcastReceiver;
    String selection = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCallbacks = this;
        Intent intent = getIntent();
        selection = intent.getStringExtra("selection");
        LoaderManager lm = getLoaderManager();
        lm.initLoader(LOADER_ID, null, mCallbacks);

        setContentView(R.layout.play);
        list = (ListView) findViewById(R.id.list2);
        adapter = new PlayAdapter(this, null);
        list.setAdapter(adapter);
        myBroadcastReceiver = new MyBroadcastReceiver();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(PlayActivity.this, MyActivity.DB_URI4,
                null, selection, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()) {
            case LOADER_ID:
                adapter.swapCursor(cursor);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(DEBUG_TAG, "receive");
        }
    }
}
