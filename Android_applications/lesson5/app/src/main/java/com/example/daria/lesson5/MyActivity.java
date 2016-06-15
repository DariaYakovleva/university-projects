package com.example.daria.lesson5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class MyActivity extends Activity {

    Button button;
    MyAdapter adapter;
    public static final String DEBUG_TAG = "MyActivity";
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        button = (Button) findViewById(R.id.button3);
        list = (ListView) findViewById(R.id.list);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyActivity.this, "Please, wait", Toast.LENGTH_SHORT).show();
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadWebpageTask() {
                        @Override
                        protected void onPostExecute(List<Entry> result) {
                            Log.d(DEBUG_TAG, "onPostExecute.." + result.size());
                            adapter = new MyAdapter(MyActivity.this, result);
                            list.setAdapter(adapter);
                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                    Log.d(DEBUG_TAG, "link = " + adapter.getLink(position));
                                    Intent intent = new Intent(MyActivity.this, BrowserActivity.class);
                                    intent.putExtra("url", adapter.getLink(position));
                                    startActivity(intent);
                                }
                            });
                        }
                    }.execute();

                } else {
                    Toast.makeText(MyActivity.this, "No network connection", Toast.LENGTH_SHORT).show();
                    Log.d(DEBUG_TAG, "no network connection");
                }
            }
        });

    }

}
