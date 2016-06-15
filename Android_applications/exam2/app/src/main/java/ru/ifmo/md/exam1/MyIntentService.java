package ru.ifmo.md.exam1;

/**
 * Created by Daria on 16.01.2015.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MyIntentService extends IntentService {

    final String LOG_TAG = "IntentServiceLogs";
    public static final String ACTION_MYINTENTSERVICE = "ru.ifmo.md.exam1.RESPONSE";
    public static final String PATH = "/data/data/ru.ifmo.md.exam1/cache";
    public MyIntentService() {
        super("IntentService");
    }

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String url = intent.getStringExtra("URL");
        Log.d(LOG_TAG, "onHandleIntent start " + url);
        InputStream inputStream = null;
        try {
            Reader in = new InputStreamReader(getApplicationContext().getAssets().open("music.txt"));
            BufferedReader bufferedreader = new BufferedReader(in);
            StringBuilder stringBuilder = new StringBuilder();
            File file = new File(PATH, "music.txt");
            OutputStream fOut = null;
            fOut = new FileOutputStream(file);
            //добавить!!!
            fOut.flush();
            fOut.close();
            String stringReadLine = null;
            while ((stringReadLine = bufferedreader.readLine()) != null) {
                stringBuilder.append(stringReadLine + "\n");
            }
            String jsonText = stringBuilder.toString();
            ContentValues cv2 = new ContentValues();
            cv2.put(MySQLiteDatabase.ID, 1);
            cv2.put(MySQLiteDatabase.NAME, "All songs");
            getContentResolver().insert(MyActivity.DB_URI3, cv2);
            ArrayList<Song> songs = ParseJSON(jsonText);
            for (int i = 0; i < songs.size(); i++) {
                ContentValues cv = new ContentValues();
                cv.put(MySQLiteDatabase.ID, i);
                cv.put(MySQLiteDatabase.NAME, songs.get(i).name);
                cv.put(MySQLiteDatabase.URL, songs.get(i).url);
                cv.put(MySQLiteDatabase.DURATION, songs.get(i).duration);
                cv.put(MySQLiteDatabase.POPULARITY, songs.get(i).popularity);
                cv.put(MySQLiteDatabase.YEAR, songs.get(i).year);
                getContentResolver().insert(MyActivity.DB_URI1, cv);
                for (int j = 0; j < songs.get(i).genres.size(); j++) {
                    String term = songs.get(i).genres.get(j);
                    ContentValues cv3 = new ContentValues();
                    cv3.put(MySQLiteDatabase.ID, i);
                    cv3.put(MySQLiteDatabase.GENRE, term);
                    getContentResolver().insert(MyActivity.DB_URI2, cv3);
                }
                ContentValues cv4 = new ContentValues();
                cv4.put(MySQLiteDatabase.ID, 1);
                cv4.put(MySQLiteDatabase.SONG, i);
                getContentResolver().insert(MyActivity.DB_URI4, cv4);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intentResponse = new Intent();
        intentResponse.setAction(ACTION_MYINTENTSERVICE);
        intentResponse.addCategory(Intent.CATEGORY_DEFAULT);
        sendBroadcast(intentResponse);
        Log.d(LOG_TAG, "onHandleIntent end " + url);
    }

    private ArrayList<Song> ParseJSON(String json){

        ArrayList<Song> jResult = new ArrayList<>();
        try {
            JSONArray songs = new JSONArray(json);
            //name, url, duration, popularity, genres, year
            for (int i = 0; i < songs.length(); i++) {
                JSONObject song = songs.getJSONObject(i);
                String name = song.getString("name");
                String url = song.getString("url");;
                String duration = song.getString("duration");
                int popularity= song.getInt("popularity");
                JSONArray genres = song.getJSONArray("genres");
                List<String> genres2 = new ArrayList<>();
                for (int j = 0; j < genres.length(); j++) {
                    genres2.add(genres.getString(j));
                }
                int year = song.getInt("year");
                jResult.add(new Song(name, url, duration, popularity, genres2, year));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jResult;
    }

}