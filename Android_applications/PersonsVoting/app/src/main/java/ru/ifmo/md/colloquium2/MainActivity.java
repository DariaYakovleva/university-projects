package ru.ifmo.md.colloquium2;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private static final String DEBUG_TAG = "Activity";
    EditText addName;
    Button add;
    Button start;
    Button end;
    Button clear;
    MyAdapter adapter;
    ListView list;
    MyDatabase db;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addName = (EditText) findViewById(R.id.editText);
        add = (Button) findViewById(R.id.button);
        end = (Button) findViewById(R.id.button3);
        clear = (Button) findViewById(R.id.button4);
        start = (Button) findViewById(R.id.button2);
        list = (ListView) findViewById(R.id.list);
        adapter = new MyAdapter(MainActivity.this);
        list.setAdapter(adapter);
        db = new MyDatabase(MainActivity.this);
        database = db.getWritableDatabase();
        String query = "SELECT * FROM " + MyDatabase.TABLE_NAME;
//        Cursor cursor = database.rawQuery(query, null);
//        if (cursor.getInt(cursor.getColumnIndex(MyDatabase.COLUMN_CNT)) == 1) {
//            start.setVisibility(View.GONE);
//            addName.setVisibility(View.GONE);
//            add.setVisibility(View.GONE);
//        } else {
//            end.setVisibility(View.GONE);
//        }
//        cursor.close();
        addToList();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String person = addName.getText().toString();
                addName.setText("");
                Log.d(DEBUG_TAG, "insert");
                ContentValues cv = new ContentValues();
                cv.put(MyDatabase.COLUMN_NAME, person);
                cv.put(MyDatabase.COLUMN_CNT, 0);
                cv.put(MyDatabase.COLUMN_V, 0);
                database.insert(MyDatabase.TABLE_NAME, null, cv);
                adapter.addPerson(new Person(person, 0));
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add.setVisibility(View.GONE);
                addName.setVisibility(View.GONE);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Log.d(DEBUG_TAG, "click on person ");
                        adapter.updCnt(position);
//                        db.updatePerson(database, adapter.getIt(position).name, adapter.getIt(position).cnt);
                        ContentValues cv = new ContentValues();
                        cv.put(MyDatabase.COLUMN_NAME, adapter.getIt(position).name);
                        cv.put(MyDatabase.COLUMN_CNT, adapter.getIt(position).cnt);
                        database.update(MyDatabase.TABLE_NAME, cv, MyDatabase.COLUMN_NAME +" = '" +adapter.getIt(position).name + "'", null);
                        ContentValues cv2 = new ContentValues();
                        cv2.put(MyDatabase.COLUMN_V, 1);
                        database.update(MyDatabase.TABLE_NAME, cv2, "voting = 0", null);
                    }
                });
                start.setVisibility(View.GONE);
                end.setVisibility(View.VISIBLE);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, adapter.getWinner() + " win!", Toast.LENGTH_SHORT).show();
                add.setVisibility(View.VISIBLE);
                addName.setVisibility(View.VISIBLE);
                start.setVisibility(View.VISIBLE);
                end.setVisibility(View.GONE);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Log.d(DEBUG_TAG, "click on person ");
                    }
                });
                ContentValues cv2 = new ContentValues();
                cv2.put(MyDatabase.COLUMN_V, 0);
                database.update(MyDatabase.TABLE_NAME, cv2, "voting = 1", null);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clearAll();
                for (int i = 0; i < adapter.getCount(); i++) {
                    ContentValues cv = new ContentValues();
                    cv.put(MyDatabase.COLUMN_NAME, adapter.getIt(i).name);
                    cv.put(MyDatabase.COLUMN_CNT, 0);
                    database.update(MyDatabase.TABLE_NAME, cv, MyDatabase.COLUMN_NAME +" = '" +adapter.getIt(i).name + "'", null);
                }
            }
        });
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    public void addToList() {
        String query = "SELECT * FROM " + MyDatabase.TABLE_NAME;
        Log.d(DEBUG_TAG, "add to list");
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            adapter.addPerson(new Person(cursor.getString(cursor.getColumnIndex(MyDatabase.COLUMN_NAME)),
                    cursor.getInt(cursor.getColumnIndex(MyDatabase.COLUMN_CNT))));
        }
        cursor.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
