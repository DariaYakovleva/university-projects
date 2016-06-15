package ru.ifmo.md.colloquium3;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.UriMatcher;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import ru.ifmo.md.colloquium3.DummyContent;

/**
 * Created by Daria on 29.11.2014.
 */
public class MyMoneyDB extends ContentProvider {
    final String LOG_TAG = "DB";
    static final String DB_NAME = "mydb3";
    static final int DB_VERSION = 1;
    static final String MONEY_TABLE = "money";
    static final String MONEY_ID = "_id";

    static final String CURRENCY = "currency";
    static final String VALUE = "value";

    static final String DB_CREATE = "create table " + MONEY_TABLE + "("
            + MONEY_ID + " integer primary key autoincrement, "
            + CURRENCY + " text, "
            + VALUE + " real" + ");";

    static final String AUTHORITY = "ru.ifmo.md.colloquium3.providers.money";

    // path
    static final String CONTACT_PATH = "money";

    // Общий Uri
    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + CONTACT_PATH);

    // Типы данных
    // набор строк
    static final String CONTACT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + CONTACT_PATH;

    // одна строка
    static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + CONTACT_PATH;

    //// UriMatcher
    // общий Uri
    static final int URI_MONEY = 1;

    // Uri с указанным ID
    static final int URI_MONEY_ID = 2;

    // описание и создание UriMatcher
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH, URI_MONEY);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH + "/#", URI_MONEY_ID);
    }

    DBHelper dbHelper;
    SQLiteDatabase db;

    public boolean onCreate() {
        Log.d(LOG_TAG, "onCreate db");
        dbHelper = new DBHelper(getContext());

        return true;
    }

    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d(LOG_TAG, "query, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_MONEY:
                Log.d(LOG_TAG, "URI_MONEY");
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = MONEY_ID + " ASC";
                }
                break;
            case URI_MONEY_ID:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_MONEY_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = MONEY_ID + " = " + id;
                } else {
                    selection = selection + " AND " + MONEY_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }

        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(MONEY_TABLE, projection, selection,
                selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),
                CONTACT_CONTENT_URI);
        return cursor;
    }

    public Uri insert(Uri uri, ContentValues values) {
        Log.d(LOG_TAG, "insert, " + uri.toString());
        if (uriMatcher.match(uri) != URI_MONEY)
            throw new IllegalArgumentException("Wrong URI: " + uri);

        db = dbHelper.getWritableDatabase();
        long rowID = db.insert(MONEY_TABLE, null, values);
        Uri resultUri = ContentUris.withAppendedId(CONTACT_CONTENT_URI, rowID);
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(LOG_TAG, "delete, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_MONEY:
                Log.d(LOG_TAG, "URI_MONEY");
                break;
            case URI_MONEY_ID:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_MONEY_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = MONEY_ID + " = " + id;
                } else {
                    selection = selection + " AND " + MONEY_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.delete(MONEY_TABLE, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d(LOG_TAG, "update, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_MONEY:
                Log.d(LOG_TAG, "URI_MONEY");

                break;
            case URI_MONEY_ID:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_MONEY_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = MONEY_ID + " = " + id;
                } else {
                    selection = selection + " AND " + MONEY_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }

        db = dbHelper.getWritableDatabase();
        int cnt = db.update(MONEY_TABLE, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    public String getType(Uri uri) {
        Log.d(LOG_TAG, "getType, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_MONEY:
                return CONTACT_CONTENT_TYPE;
            case URI_MONEY_ID:
                return CONTACT_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    private class DBHelper extends SQLiteOpenHelper {
//        Context context;
        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            Log.d(LOG_TAG, "DBHelper constr");
        }

        public void onCreate(final SQLiteDatabase db) {
            Log.d(LOG_TAG, "create dbMONEY!!!!!!!!!!!!");
            db.execSQL(DB_CREATE);
//            db.insert(MONEY_TABLE, null, cv);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}