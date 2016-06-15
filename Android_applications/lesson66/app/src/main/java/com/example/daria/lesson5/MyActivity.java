package com.example.daria.lesson5;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class MyActivity extends Activity {

    Button buttonBBC;
    Button buttonBash;
    Button deleteAll;
    MyAdapter adapter;
    public static final String DEBUG_TAG = "MyActivity";
    ListView list;
    MySQLiteDatabase db;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        buttonBBC = (Button) findViewById(R.id.button3);
        buttonBash = (Button) findViewById(R.id.button4);
        deleteAll = (Button) findViewById(R.id.button5);
        list = (ListView) findViewById(R.id.list);
        db = new MySQLiteDatabase(MyActivity.this);
        database = db.getWritableDatabase();
        adapter = new MyAdapter(MyActivity.this);
        list.setAdapter(adapter);
        addToList();
        Log.d(DEBUG_TAG, "set adapter");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.d(DEBUG_TAG, "link = " + adapter.getLink(position));
                Intent intent = new Intent(MyActivity.this, BrowserActivity.class);
                intent.putExtra("url", adapter.getLink(position));
                startActivity(intent);
            }
        });
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new MySQLiteDatabase(MyActivity.this);
                database = db.getWritableDatabase();
                adapter.deleteAll();
                list.setAdapter(adapter);
                db.deleteAll(database);

            }
        });
        buttonBBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toClick("http://feeds.bbci.co.uk/news/rss.xml");
            }
        });
        buttonBash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toClick("http://bash.im/rss/");
            }
        });
    }
    public void addToList() {
        String query = "SELECT * FROM " + MySQLiteDatabase.TABLE_NAME;
        Log.d(DEBUG_TAG, "add to list");
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            adapter.addEntry(new Entry(cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.COLUMN_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.COLUMN_LINK)),
                    cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.COLUMN_URL))));
        }
        cursor.close();
    }

    public void toClick(String url) {
        Toast.makeText(MyActivity.this, "Please, wait", Toast.LENGTH_SHORT).show();
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask() {
                @Override
                protected void onPostExecute(List<Entry> result) {
                    Log.d(DEBUG_TAG, "onPostExecute.." + result.size());
                    db = new MySQLiteDatabase(MyActivity.this);
                    database = db.getWritableDatabase();
                    for (int i = 0; i < result.size(); i++) {
                        Log.d(DEBUG_TAG, "insert");
                        ContentValues cv = new ContentValues();
                        cv.put(MySQLiteDatabase.COLUMN_TITLE, result.get(i).title);
                        cv.put(MySQLiteDatabase.COLUMN_LINK, result.get(i).link);
                        cv.put(MySQLiteDatabase.COLUMN_DESCRIPTION, result.get(i).description);
                        cv.put(MySQLiteDatabase.COLUMN_URL, result.get(i).url);
                        database.insert(MySQLiteDatabase.TABLE_NAME, null, cv);
                    }
                    addToList();
                    list.setAdapter(adapter);
                }
            }.execute(url);
        } else {
            Toast.makeText(MyActivity.this, "No network connection", Toast.LENGTH_SHORT).show();
            Log.d(DEBUG_TAG, "no network connection");
        }
    }
}
