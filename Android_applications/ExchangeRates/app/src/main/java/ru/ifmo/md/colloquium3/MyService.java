package ru.ifmo.md.colloquium3;

import android.app.IntentService;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * Created by Daria on 23.12.2014.
 */
public class MyService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    final String LOG_TAG = "myLogs";
    Cursor result;
    public static final Uri DB_URI = Uri.parse("content://ru.ifmo.md.colloquium3.providers.cur/cur");

    public MyService() {
        super("myname");
    }

    public void onCreate() {
        super.onCreate();
        result = getContentResolver().query(DB_URI, null, null, null, null);
        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int tm = intent.getIntExtra("time", 1);
        String label = intent.getStringExtra("label");
        Log.d(LOG_TAG, "onHandleIntent start " + label);
        try {
            while (true) {
                for (int i = 0; i < DummyContent.ITEMS.size(); i++) {
                    DummyContent.DummyItem mItem = DummyContent.ITEMS.get(i);
                    result.moveToPosition(Integer.parseInt(mItem.id) - 1);
                    final Uri updUri = ContentUris.withAppendedId(DB_URI, Integer.parseInt(mItem.id));
                    Log.d(LOG_TAG, "update" + updUri.toString() + " from " + mItem.id);
                    ContentValues cv = new ContentValues();
                    cv.put(MyCurDB.CURRENCY, mItem.content);
                    double change = (Math.random() * 2) - 1;
                    cv.put(MyCurDB.VALUE, result.getDouble(result.getColumnIndex(MyCurDB.VALUE)) + change);
                    int cnt = getContentResolver().update(updUri, cv, null, null);
                    Log.d(LOG_TAG, "update" + updUri.toString() + " from " + mItem.id);
                }
                TimeUnit.SECONDS.sleep(tm);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(LOG_TAG, "onHandleIntent end " + label);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
}
