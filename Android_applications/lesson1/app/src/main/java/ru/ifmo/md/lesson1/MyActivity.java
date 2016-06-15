package ru.ifmo.md.lesson1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MyActivity extends Activity {

    private WhirlView whirlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        whirlView = new WhirlView(this);
        setContentView(whirlView);
        ListView list = new ListView(this);
        list.setAdapter(new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, new String[]{"fsd", "sdf"}));
//        setContentView(R.layout);

    }

    @Override
    public void onResume() {
        //когда активити снова видна
        super.onResume();
        whirlView.resume();
    }

    @Override
    public void onPause() {
        //когда активити уходит с экрана
        super.onPause();
        whirlView.pause();
    }
}
