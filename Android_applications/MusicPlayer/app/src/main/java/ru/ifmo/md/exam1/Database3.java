package ru.ifmo.md.exam1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

/**
 * Created by Daria on 29.11.2014.
 */
public class Database3 extends ContentProvider {
    final String LOG_TAG = "DB";
    static final int DB_VERSION = 1;
    private static final String DB_NAME = "exam.db";
    public static final String TABLE_NAME3 = "exam";
    public static final String TABLE_NAME2 = "genres";
    public static final String TABLE_NAME = "playlists";
    public static final String TABLE_NAME4 = "playtosong";
    public static final String COLUMN_ID = "_id";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String URL = "url";
    public static final String DURATION = "duration";
    public static final String POPULARITY = "popularity";
    public static final String YEAR = "year";
    public static final String GENRE = "genre";
    public static final String SONG = "song";

    //name, url, duration, popularity, genres, year
    public static final String DB_CREATE3 =
            "create table " + TABLE_NAME3 + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    ID + " integer, " +
                    NAME + " text, " +
                    URL + " text, " +
                    DURATION + " text," +
                    POPULARITY + " integer, " +
                    YEAR + " integer " +
                    ");";

    public static final String DB_CREATE2 =
            "create table " + TABLE_NAME2 + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    ID + " integer, " +
                    GENRE + " text " +
                    ");";
    public static final String DB_CREATE =
            "create table " + TABLE_NAME + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    ID + " integer, " +
                    NAME + " text " +
                    ");";

    public static final String DB_CREATE4 =
            "create table " + TABLE_NAME4 + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    ID + " integer, " +
                    SONG + " integer " +
                    ");";


    static final String AUTHORITY = "ru.ifmo.md.exam1.providers.exam3";

    static final String CONTACT_PATH = "playlists";

    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + CONTACT_PATH);

    static final String CONTACT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + CONTACT_PATH;

    static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + CONTACT_PATH;

    static final int URI_WEATHER = 1;

    static final int URI_COLUMN_ID = 2;

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH, URI_WEATHER);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH + "/#", URI_COLUMN_ID);
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
            case URI_WEATHER:
                Log.d(LOG_TAG, "URI_EXAM");
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = COLUMN_ID + " ASC";
                }
                break;
            case URI_COLUMN_ID:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_COLUMN_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = COLUMN_ID + " = " + id;
                } else {
                    selection = selection + " AND " + COLUMN_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }

        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, projection, selection,
                selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),
                CONTACT_CONTENT_URI);
        return cursor;
    }

    public Uri insert(Uri uri, ContentValues values) {
        Log.d(LOG_TAG, "insert, " + uri.toString());
        if (uriMatcher.match(uri) != URI_WEATHER)
            throw new IllegalArgumentException("Wrong URI: " + uri);

        db = dbHelper.getWritableDatabase();
        long rowID = db.insert(TABLE_NAME, null, values);
        Uri resultUri = ContentUris.withAppendedId(CONTACT_CONTENT_URI, rowID);
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(LOG_TAG, "delete, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_WEATHER:
                Log.d(LOG_TAG, "URI_EXAM");
                break;
            case URI_COLUMN_ID:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_COLUMN_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = COLUMN_ID + " = " + id;
                } else {
                    selection = selection + " AND " + COLUMN_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.delete(TABLE_NAME, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d(LOG_TAG, "update, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_WEATHER:
                Log.d(LOG_TAG, "URI_EXAM");

                break;
            case URI_COLUMN_ID:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_COLUMN_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = COLUMN_ID + " = " + id;
                } else {
                    selection = selection + " AND " + COLUMN_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }

        db = dbHelper.getWritableDatabase();
        int cnt = db.update(TABLE_NAME, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    public String getType(Uri uri) {
        Log.d(LOG_TAG, "getType, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_WEATHER:
                return CONTACT_CONTENT_TYPE;
            case URI_COLUMN_ID:
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
            Log.d(LOG_TAG, "create db3!!!!!!!!!!!!");
            db.execSQL(DB_CREATE);
            db.execSQL(DB_CREATE2);
            db.execSQL(DB_CREATE3);
            db.execSQL(DB_CREATE4);
            Intent intentMyIntentService = new Intent(getContext(), MyIntentService.class);
            getContext().startService(intentMyIntentService);
            IntentFilter intentFilter = new IntentFilter(MyIntentService.ACTION_MYINTENTSERVICE);
            intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}