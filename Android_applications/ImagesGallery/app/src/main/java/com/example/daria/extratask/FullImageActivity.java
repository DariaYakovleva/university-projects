package com.example.daria.extratask;

/**
 * Created by Daria on 16.01.2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FullImageActivity extends Activity {

    ImageView imageView;
    Button back;
    TextView text;
    Button save;
    private GestureDetector gestureDetector;
    Cursor cur;
    int curImage = -1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);
        gestureDetector = initGestureDetector();
        text = (TextView) findViewById(R.id.textView);
        View view = findViewById(R.id.LinearLayout);
        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
            }
        });
        Intent i = getIntent();
        int position = i.getIntExtra("position", 0);
        curImage = position;
        cur = getContentResolver().query(MainActivity.DB_URI, null, null, null, null);
        text.setText((curImage + 1) + " of " + cur.getCount());

        Toast.makeText(getApplicationContext(), "Slide to the left: previous, \nto the right: next, \nup: return to the Gallery", Toast.LENGTH_LONG).show();
        cur.moveToPosition(curImage);
        String url = cur.getString(cur.getColumnIndex(MySQLiteDatabase.COLUMN_URL));
        String name = url.substring(url.lastIndexOf("/") + 1);
        Bitmap bitmap = BitmapFactory.decodeFile(MainActivity.PATH + "/" + name);
        imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageBitmap(bitmap);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor im = getContentResolver().query(MainActivity.DB_URI, null, null, null, null);
                im.moveToPosition(curImage);
                String url = im.getString(im.getColumnIndex(MySQLiteDatabase.COLUMN_URL));
                String name = url.substring(url.lastIndexOf("/") + 1);
                Bitmap bitmap = BitmapFactory.decodeFile(MainActivity.PATH + "/" + name);
                (new SavePicture()).savePicture(bitmap, name, Environment.getExternalStorageDirectory().toString());
                Toast.makeText(getApplicationContext(), "Image save on SD-card", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        curImage = savedInstanceState.getInt("curImage");
        Log.d("full", "onRestore");
        text.setText((curImage + 1) + " of " + cur.getCount());
        cur.moveToPosition(curImage);
        String url = cur.getString(cur.getColumnIndex(MySQLiteDatabase.COLUMN_URL));
        String name = url.substring(url.lastIndexOf("/") + 1);
        Bitmap bitmap = BitmapFactory.decodeFile(MainActivity.PATH + "/" + name);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("curImage", curImage);
        super.onSaveInstanceState(outState);
    }

    private GestureDetector initGestureDetector() {
        return new GestureDetector(new GestureDetector.SimpleOnGestureListener() {

            private SwipeDetector detector = new SwipeDetector();

            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                try {
                    if (detector.isSwipeDown(e1, e2, velocityY)) {
                        return false;
                    } else if (detector.isSwipeUp(e1, e2, velocityY)) {
                        showToast("Go to Gallery");
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else if (detector.isSwipeLeft(e1, e2, velocityX)) {
                        curImage = Math.min(cur.getCount() - 1, curImage + 1);
                        text.setText((curImage + 1) + " of " + cur.getCount());
                        cur.moveToNext();
                        String url = cur.getString(cur.getColumnIndex(MySQLiteDatabase.COLUMN_URL));
                        String name = url.substring(url.lastIndexOf("/") + 1);
                        Bitmap bitmap = BitmapFactory.decodeFile(MainActivity.PATH + "/" + name);
                        imageView.setImageBitmap(bitmap);
                    } else if (detector.isSwipeRight(e1, e2, velocityX)) {
                        curImage = Math.max(0, curImage - 1);
                        text.setText((curImage + 1) + " of " + cur.getCount());
                        cur.moveToPrevious();
                        String url = cur.getString(cur.getColumnIndex(MySQLiteDatabase.COLUMN_URL));
                        String name = url.substring(url.lastIndexOf("/") + 1);
                        Bitmap bitmap = BitmapFactory.decodeFile(MainActivity.PATH + "/" + name);
                        imageView.setImageBitmap(bitmap);
                    }
                } catch (Exception e) {}
                return false;
            }

            private void showToast(String phrase){
                Toast.makeText(getApplicationContext(), phrase, Toast.LENGTH_SHORT).show();
            }
        });
    }
}