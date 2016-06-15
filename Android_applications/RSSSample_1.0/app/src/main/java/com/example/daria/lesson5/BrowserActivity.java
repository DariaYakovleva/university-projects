package com.example.daria.lesson5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by Daria on 20.10.2014.
 */
public class BrowserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);

        WebView webView = (WebView) findViewById(R.id.webView);
        Intent intent = getIntent();
        webView.loadUrl(intent.getStringExtra("url"));
    }
}
