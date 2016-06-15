package ru.ifmo.md.lesson3.brandnewtranslator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by vadim on 28/09/14.
 */
public class AnotherActivity extends Activity /*extends ListActivity*/ {
    public static final int NEED_IMAGES = 10;

    private static final String TAG = "AnotherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        Log.d(TAG, "onCreate() started");

        Intent intent = getIntent();
        final String word = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        final ArrayAdapter<Bitmap> adapter = new ArrayAdapter<Bitmap>(this, android.R.layout.simple_list_item_1, R.id.listViewItem) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.d("Adapter", "works!");
                ImageView imageView;
                if (convertView == null) {
                    imageView = new ImageView(AnotherActivity.this);
                } else {
                    imageView = (ImageView) convertView;
                }
                Bitmap bitmap = getItem(position);
                if (bitmap == null) {
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.not_found);
                }
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                imageView.setMinimumHeight(bitmap.getHeight());
                imageView.setMinimumWidth(bitmap.getWidth());
                imageView.setImageBitmap(bitmap);
                return imageView;
            }
        };

        final TextView textView = (TextView) findViewById(R.id.translatedTextView);
        final ListView listView = (ListView) findViewById(R.id.imageListView);
        listView.setAdapter(adapter);

        new AsyncTranslator() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d(super.TAG, "onPostExecute() works: " + s);
                String output = word + " - " + s.substring(2, s.length() - 2);
                textView.setText(output.toCharArray(), 0, output.length());
                textView.invalidate();
            }
        }.execute(word);

        new AsyncImageLoader() {
            @Override
            protected void onProgressUpdate(Bitmap... values) {
                super.onProgressUpdate(values);
                Log.d(super.TAG, "onProgressUpdate() works");
                adapter.add(values[0]);
            }
        }.execute(word);
    }
}
