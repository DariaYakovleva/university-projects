package ru.ifmo.md.lesson4;

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

/**
 * Created by Daria on 03.10.2014.
 */
public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    private EditText editText;
    private Button buttonEval;
    private Button buttonClear;
    private Button buttonC;
    private TextView textView;
    int[] idList = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.buttonPlus,
            R.id.buttonSub, R.id.buttonMul, R.id.buttonDiv, R.id.buttonDot, R.id.buttonClose, R.id.buttonOpen};

    public DummyCalculateEngine expression = new DummyCalculateEngine();
    boolean first = true;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() started");

        super.onCreate(savedInstanceState);
        View.OnClickListener mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick() works");
                if (first) {
                    textView.setText("");
                    first = false;
                }
                textView.append(((Button) v).getText());
            }
        };
        setContentView(R.layout.activity_main);
        for (int id : idList) {
            View v = findViewById(id);
            v.setOnClickListener(mClickListener);
        }

        buttonEval = (Button) findViewById(R.id.buttonEval);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonC = (Button) findViewById(R.id.buttonC);
        textView = (TextView) findViewById(R.id.textView);
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cur = textView.getText().toString();
                textView.setText(cur.substring(0, cur.length() - 1));
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Enter your expression");
                first = true;
            }
        });
        buttonEval.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick() works");
                try {
                    Double val = expression.calculate(textView.getText().toString());
                    textView.setText(val.toString());
                } catch (CalculationException e) {
                    textView.setText(e.getMessage());
                    first = true;
                }
            }
        });
    }

}