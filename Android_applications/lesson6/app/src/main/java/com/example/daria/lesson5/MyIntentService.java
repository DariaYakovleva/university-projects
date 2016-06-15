package com.example.daria.lesson5;

/**
 * Created by Daria on 16.01.2015.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MyIntentService extends IntentService {

    final String LOG_TAG = "IntentServiceLogs";
    public static final String ACTION_MYINTENTSERVICE = "com.example.daria.lesson5.RESPONSE";
    public static final String EXTRA_KEY_OUT = "EXTRA_OUT";
    String extraOut = "aga";

    public MyIntentService() {
        super("IntentService");
        // TODO Auto-generated constructor stub
    }

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // TODO Auto-generated method stub
        String url = intent.getStringExtra("URL");
        Log.d(LOG_TAG, "onHandleIntent start " + url);
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        HttpGet request = new HttpGet(url);Intent intentResponse = new Intent();
        HttpResponse response = null;
        try {
            response = httpclient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity resEntity = response.getEntity();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        sendBroadcast(intentResponse);
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        MySAXParser saxp = new MySAXParser();
        try {
            parser.parse(resEntity.getContent(), saxp);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return saxp.entries;
        intentResponse.setAction(ACTION_MYINTENTSERVICE);
        intentResponse.addCategory(Intent.CATEGORY_DEFAULT);
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> desc = new ArrayList<>();
        ArrayList<String> links = new ArrayList<>();
        ArrayList<String> urls = new ArrayList<>();
        for (int i = 0; i < saxp.entries.size(); i++) {
            Entry ent = saxp.entries.get(i);
            titles.add(ent.title);
            links.add(ent.link);
            urls.add(ent.url);
            desc.add(ent.description);
        }
        intentResponse.putStringArrayListExtra("TITLE", titles);
        intentResponse.putStringArrayListExtra("LINK", links);
        intentResponse.putStringArrayListExtra("DESCRIPTION", desc);
        intentResponse.putStringArrayListExtra("URL", urls);
        sendBroadcast(intentResponse);
        Log.d(LOG_TAG, "onHandleIntent end " + url);
    }
}