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
public class MyCurDB extends ContentProvider {
    final String LOG_TAG = "DB";
    static final String DB_NAME = "mydb3";
    static final int DB_VERSION = 1;
    static final String CUR_TABLE = "cur";
    static final String CUR_ID = "_id";

    static final String CURRENCY = "currency";
    static final String VALUE = "value";

    static final String DB_CREATE = "create table " + CUR_TABLE + "("
            + CUR_ID + " integer primary key autoincrement, "
            + CURRENCY + " text, "
            + VALUE + " real" + ");";

    static final String AUTHORITY = "ru.ifmo.md.colloquium3.providers.cur";

    // path
    static final String CONTACT_PATH = "cur";

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
    static final int URI_CUR = 1;

    // Uri с указанным ID
    static final int URI_CUR_ID = 2;

    // описание и создание UriMatcher
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH, URI_CUR);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH + "/#", URI_CUR_ID);
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
            case URI_CUR:
                Log.d(LOG_TAG, "URI_CUR");
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = CUR_ID + " ASC";
                }
                break;
            case URI_CUR_ID:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_CUR_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = CUR_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CUR_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }

        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(CUR_TABLE, projection, selection,
                selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),
                CONTACT_CONTENT_URI);
        return cursor;
    }

    public Uri insert(Uri uri, ContentValues values) {
        Log.d(LOG_TAG, "insert, " + uri.toString());
        if (uriMatcher.match(uri) != URI_CUR)
            throw new IllegalArgumentException("Wrong URI: " + uri);

        db = dbHelper.getWritableDatabase();
        long rowID = db.insert(CUR_TABLE, null, values);
        Uri resultUri = ContentUris.withAppendedId(CONTACT_CONTENT_URI, rowID);
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(LOG_TAG, "delete, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_CUR:
                Log.d(LOG_TAG, "URI_CUR");
                break;
            case URI_CUR_ID:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_CUR_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = CUR_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CUR_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.delete(CUR_TABLE, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d(LOG_TAG, "update, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_CUR:
                Log.d(LOG_TAG, "URI_CUR");

                break;
            case URI_CUR_ID:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_CUR_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = CUR_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CUR_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }

        db = dbHelper.getWritableDatabase();
        int cnt = db.update(CUR_TABLE, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    public String getType(Uri uri) {
        Log.d(LOG_TAG, "getType, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_CUR:
                return CONTACT_CONTENT_TYPE;
            case URI_CUR_ID:
                return CONTACT_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    private class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            Log.d(LOG_TAG, "DBHelper constr");
        }

        public void onCreate(final SQLiteDatabase db) {
            Log.d(LOG_TAG, "create dbCUR!!!!!!!!!!!!");
            db.execSQL(DB_CREATE);
            db.execSQL(MyMoneyDB.DB_CREATE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}