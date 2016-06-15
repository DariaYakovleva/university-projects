package ru.ifmo.md.lesson3.brandnewtranslator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
    public static final String EXTRA_MESSAGE = "brandnewtranslator.MainActivity extra message";

    private static final String TAG = "MainActivity";

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() started");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.wordField);
        button = (Button) findViewById(R.id.translateButton);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                // nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                // nothing
            }

            @Override
            public void afterTextChanged(Editable editable) {
                button.setEnabled(editText.getText().length() > 0);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "Anon OnClickListener";

            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick() works");
                Intent intent = new Intent(MainActivity.this, AnotherActivity.class);
                String word = editText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, word);
                editText.setText("", TextView.BufferType.EDITABLE);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
