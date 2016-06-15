package ru.ifmo.md.lesson8;

import android.app.IntentService;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Daria on 29.11.2014.
 */
public class MyIntentService extends IntentService {
    private static final String DEBUG_TAG = "IntentService";
    private static String url = null;
    public static final String ACTION_MYINTENTSERVICE = "com.example.daria.extratask.RESPONSE";

    public MyIntentService() {
        super("IntentService");
    }

    public void onCreate() {
        super.onCreate();
        Log.d(DEBUG_TAG, "onCreate");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(DEBUG_TAG, "download weather started");
        url = intent.getStringExtra("url");
        String id = intent.getStringExtra("id");
        String nCity = intent.getStringExtra("city");
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        HttpGet request = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = httpclient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity resEntity = response.getEntity();
        SAXParserFactory factory = SAXParserFactory.newInstance();
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

        Log.d(DEBUG_TAG, "size = " + saxp.city);
        Weather result = saxp.weather;
        ContentValues cv = new ContentValues();
        cv.put(MySQLite.CITY, result.city);
        cv.put(MySQLite.NUM, Integer.valueOf(nCity));
        cv.put(MySQLite.DAY, result.date);
        cv.put(MySQLite.TEMP, result.temp);
        cv.put(MySQLite.COMMENT, result.comment);
        cv.put(MySQLite.SUNRISE, result.sunrise);
        cv.put(MySQLite.SUNSET, result.sunset);
        String action = intent.getStringExtra("action");
        if (action.equals("add")) {
            getContentResolver().insert(ItemDetailFragment.DB_URI, cv);
        } else {
            int cnt = getContentResolver().update(ItemDetailFragment.DB_URI, cv,MySQLite.NUM + " = " + nCity, null);
        }
        Log.d(DEBUG_TAG, action + " " + result.city + " " + nCity);
        for (int i = 0; i < result.days.size(); i++) {
            Days day = result.days.get(i);
            ContentValues cv2 = new ContentValues();
            cv2.put(MySQLite.CITY, nCity);
            cv2.put(MySQLite.NUM, i + 1);
            cv2.put(MySQLite.DAY, day.date);
            cv2.put(MySQLite.TEMP, result.temp);
            cv2.put(MySQLite.COMMENT, day.comment);
            cv2.put(MySQLite.SUNRISE, day.high);
            cv2.put(MySQLite.SUNSET, day.low);
            if (action.equals("add")) {
                getContentResolver().insert(ItemDetailFragment.DB_URI, cv2);
            } else {
                int cnt = getContentResolver().update(ItemDetailFragment.DB_URI, cv2,
                        "city = " + nCity + " AND number = " + (i + 1) , null);
            }
            Log.d(DEBUG_TAG, action + " " + nCity + " " + (i + 1));
        }

    }
}
