package com.example.daria.lesson5;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    Button add;
    EditText text;
    Button deleteAll;
    MyAdapter adapter;
    Spinner spinner;
    public static final String DEBUG_TAG = "MyActivity";
    ListView list;
    private static final Uri DB_URI = Uri.parse("content://com.example.daria.lesson5.providers.entries/entries");
    private static final int LOADER_ID = 1;
    private LoaderManager.LoaderCallbacks<Cursor> mCallbacks;
    MyBroadcastReceiver myBroadcastReceiver;
    List<String> links = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCallbacks = this;

        LoaderManager lm = getLoaderManager();
        lm.initLoader(LOADER_ID, null, mCallbacks);

        setContentView(R.layout.activity_my);
        add = (Button) findViewById(R.id.add);
        text = (EditText) findViewById(R.id.editText);
        deleteAll = (Button) findViewById(R.id.button5);
        spinner = (Spinner) findViewById(R.id.spinner);
        links.add("http://feeds.bbci.co.uk/news/rss.xml");
        links.add("http://stackoverflow.com/feeds/tag/android");
        links.add("http://echo.msk.ru/interview/rss-fulltext.xml");
        final CustomAdapter sAdapter = new CustomAdapter(this, android.R.layout.simple_spinner_item, links);
        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(sAdapter);
        list = (ListView) findViewById(R.id.list);
        adapter = new MyAdapter(this, null);
        list.setAdapter(adapter);
        myBroadcastReceiver = new MyBroadcastReceiver();

        Log.d(DEBUG_TAG, "set adapter");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cursor cur = adapter.getCursor();
                cur.moveToPosition(position);
                String link = cur.getString(cur.getColumnIndex(MySQLiteDatabase.COLUMN_LINK));
                Log.d(DEBUG_TAG, "link = " + link);
                Intent intent = new Intent(MyActivity.this, BrowserActivity.class);
                intent.putExtra("url", link);
                startActivity(intent);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                toClick(sAdapter.getEntry(pos));
                CustomAdapter.flag = true;
            }
        });
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String where = "_id > 0";
                getContentResolver().delete(DB_URI, where, null);

            }
        });
        text.setText("");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = text.getText().toString();
                if (!link.isEmpty() && !link.contentEquals("bash.im/rss")) {
                    sAdapter.addObject(link);
                    sAdapter.notifyDataSetChanged();
                    toClick(link);
                } else {
                    Toast.makeText(MyActivity.this, "Bad link", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(MyActivity.this, DB_URI,
                null, null, null, null);
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

    public void toClick(String url) {
        Toast.makeText(MyActivity.this, "Please, wait", Toast.LENGTH_SHORT).show();
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Intent intentMyIntentService = new Intent(this, MyIntentService.class);
            startService(intentMyIntentService.putExtra("URL", url));
            IntentFilter intentFilter = new IntentFilter(MyIntentService.ACTION_MYINTENTSERVICE);
            intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
            registerReceiver(myBroadcastReceiver, intentFilter);
        } else {
            Toast.makeText(MyActivity.this, "No network connection", Toast.LENGTH_SHORT).show();
            Log.d(DEBUG_TAG, "no network connection");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<String> titles = intent.getStringArrayListExtra("TITLE");
            ArrayList<String> desc = intent.getStringArrayListExtra("DESCRIPTION");
            ArrayList<String> links = intent.getStringArrayListExtra("LINK");
            ArrayList<String> urls = intent.getStringArrayListExtra("URL");
            for (int i = 0; i < titles.size(); i++) {
                ContentValues cv = new ContentValues();
                cv.put(MySQLiteDatabase.COLUMN_TITLE, titles.get(i));
                cv.put(MySQLiteDatabase.COLUMN_LINK, links.get(i));
                cv.put(MySQLiteDatabase.COLUMN_DESCRIPTION, desc.get(i));
                cv.put(MySQLiteDatabase.COLUMN_URL, urls.get(i));
                getContentResolver().insert(DB_URI, cv);

            }
            Log.d(DEBUG_TAG, "add" + titles.size());
        }
    }
}
