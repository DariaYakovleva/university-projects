package com.example.daria.lesson5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daria on 09.11.2014.
 */
public class MySQLiteDatabase extends SQLiteOpenHelper {


    private static final String DB_NAME = "entries.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "entries2";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_LINK = "link";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_URL = "url";

    public static final String DB_CREATE =
            "create table " + TABLE_NAME + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_TITLE + " text, " +
                    COLUMN_LINK + " text, " +
                    COLUMN_DESCRIPTION + " text, " +
                    COLUMN_URL + " text" +
                    ");";
    private static final String DB_DELETE = "DROP TABLE IF EXISTS "
            + TABLE_NAME;

    public MySQLiteDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // создаем и заполняем БД
    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(DB_DELETE);
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldV, int newV) {
        Log.w("LOG_TAG", "Обновление базы данных с версии " + oldV
                + " до версии " + newV + ", которое удалит все старые данные");
        db.execSQL(DB_DELETE);
        onCreate(db);
    }

    public Cursor getAll() {
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Log.e("SQLite", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }

    public void deleteAll(android.database.sqlite.SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_NAME + ";");
    }

//    // удалить запись из DB_TABLE
//    public void delRec(long id) {
//        mDB.delete(TABLE_NAME, COLUMN_ID + " = " + id, null);
//    }
}
