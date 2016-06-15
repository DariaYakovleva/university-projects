package ru.ifmo.md.colloquium2;

/**
 * Created by Daria on 11.11.2014.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Daria on 09.11.2014.
 */
public class MyDatabase extends SQLiteOpenHelper {


    private static final String DB_NAME = "voting.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "voting2";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CNT = "cnt";
    public static final String COLUMN_V = "voting";

    public static final String DB_CREATE =
            "create table " + TABLE_NAME + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_NAME + " text, " +
                    COLUMN_CNT + " integer," +
                    COLUMN_V + " integer " +
                    ");";
    private static final String DB_DELETE = "DROP TABLE IF EXISTS "
            + TABLE_NAME;

    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldV, int newV) {
        Log.w("LOG_TAG", "Обновление базы данных с версии " + oldV
                + " до версии " + newV + ", которое удалит все старые данные");
        db.execSQL(DB_DELETE);
        onCreate(db);
    }

    public void updatePerson(android.database.sqlite.SQLiteDatabase db, String name, int cnt) {
        db.execSQL("UPDATE " + TABLE_NAME + " SET " +  COLUMN_CNT +  " = " + cnt + " WHERE " + COLUMN_NAME + " = " + name + ";");

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

