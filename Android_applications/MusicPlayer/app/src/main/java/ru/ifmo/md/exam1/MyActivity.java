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


public class MyActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    Button add;
    EditText text;
    MyAdapter adapter;
    public static final String DEBUG_TAG = "MyActivity";
    ListView list;
    public static final Uri DB_URI1 = Uri.parse("content://ru.ifmo.md.exam1.providers.exam1/exam");
    public static final Uri DB_URI2 = Uri.parse("content://ru.ifmo.md.exam1.providers.exam2/genres");
    public static final Uri DB_URI3 = Uri.parse("content://ru.ifmo.md.exam1.providers.exam3/playlists");
    public static final Uri DB_URI4 = Uri.parse("content://ru.ifmo.md.exam1.providers.exam4/playtosong");
    private static final int LOADER_ID = 1;
    private LoaderManager.LoaderCallbacks<Cursor> mCallbacks;
    private MyBroadcastReceiver myBroadcastReceiver;
    private List<String> links = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCallbacks = this;

        LoaderManager lm = getLoaderManager();
        lm.initLoader(LOADER_ID, null, mCallbacks);

        setContentView(R.layout.activity_my);
        add = (Button) findViewById(R.id.add);
        text = (EditText) findViewById(R.id.editText);
        list = (ListView) findViewById(R.id.list);
        adapter = new MyAdapter(this, null);
        list.setAdapter(adapter);
        myBroadcastReceiver = new MyBroadcastReceiver();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cursor cur = adapter.getCursor();
                cur.moveToPosition(position);
//                String link = cur.getString(cur.getColumnIndex(MySQLiteDatabase.COLUMN_LINK));
                Intent intent = new Intent(MyActivity.this, PlayActivity.class);
                startActivity(intent.putExtra("selection", position));
            }
        });

        text.setText("");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(MyActivity.this, DB_URI3,
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

    public void toClick() {
        Toast.makeText(MyActivity.this, "Please, wait", Toast.LENGTH_SHORT).show();
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
