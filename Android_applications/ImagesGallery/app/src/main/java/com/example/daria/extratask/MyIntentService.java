package com.example.daria.extratask;

/**
 * Created by Daria on 16.01.2015.
 */

import java.io.BufferedReader;
import java.io.File;
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
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.REST;
import com.googlecode.flickrjandroid.photos.Photo;
import com.googlecode.flickrjandroid.photos.PhotosInterface;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
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
    public static final String ACTION_MYINTENTSERVICE = "com.example.daria.extratask.RESPONSE";
    final String KEY = "242909272044152ae00d4923bb414c7f";
    final String KEY_SECRET = "d02ef92a210d0384";
//    String FlickrQuery_url = "https://api.flickr.com/services/rest/?method=flickr.photos.search";
    String FlickrQuery_url = "https://api.flickr.com/services/rest/?method=flickr.photos.getRecent";
    public static int COUNT = 27;
    String FlickrQuery_per_page = "&per_page=" + COUNT;
    String FlickrQuery_nojsoncallback = "&nojsoncallback=12";
    String FlickrQuery_format = "&format=json";
//    String FlickrQuery_tag = "&tags=";
    String FlickrQuery_key = "&api_key=";
    String FlickrQuery_skey = "&api_key=";

    public MyIntentService() {
        super("IntentService");
    }

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(LOG_TAG, "onHandleIntent start ");
        String jsonText = QueryFlickr();
        ArrayList<String> urls = ParseJSON(jsonText);
        Cursor im = getContentResolver().query(MainActivity.DB_URI, null, null, null, null);
        while (im.moveToNext()) {
            String url = im.getString(im.getColumnIndex(MySQLiteDatabase.COLUMN_URL));
            String name = url.substring(url.lastIndexOf("/") + 1);
            File f = new File(MainActivity.PATH + "/" + name);
            if (f.exists()) f.delete();
        }
        for (int i = 0; i < urls.size(); i++) {
            Log.d(LOG_TAG, urls.get(i));
            Bitmap image = downloadBitmap(urls.get(i));

            Intent intentResponse = new Intent();
            intentResponse.setAction(ACTION_MYINTENTSERVICE);
            intentResponse.addCategory(Intent.CATEGORY_DEFAULT);
            intentResponse.putExtra("time", i);
            sendBroadcast(intentResponse);
            String name = urls.get(i).substring(urls.get(i).lastIndexOf("/") + 1);
            (new SavePicture()).savePicture(image, name, MainActivity.PATH);
        }
        Intent intentResponse = new Intent();
        intentResponse.setAction(ACTION_MYINTENTSERVICE);
        intentResponse.addCategory(Intent.CATEGORY_DEFAULT);
        intentResponse.putStringArrayListExtra("urls", urls);
        sendBroadcast(intentResponse);
        Log.d(LOG_TAG, "onHandleIntent end " + urls.size());
    }

    private String QueryFlickr(){
        String qResult = null;
//        String qString = FlickrQuery_url + FlickrQuery_per_page+ FlickrQuery_nojsoncallback+
//                FlickrQuery_format+ FlickrQuery_tag + q +
//                FlickrQuery_key + KEY;
        String qString = FlickrQuery_url + FlickrQuery_per_page+ FlickrQuery_nojsoncallback+
                FlickrQuery_format + FlickrQuery_key + KEY;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(qString);
        try {
            HttpEntity httpEntity = httpClient.execute(httpGet).getEntity();
            if (httpEntity != null){
                InputStream inputStream = httpEntity.getContent();
                Reader in = new InputStreamReader(inputStream);
                BufferedReader bufferedreader = new BufferedReader(in);
                StringBuilder stringBuilder = new StringBuilder();
                String stringReadLine = null;
                while ((stringReadLine = bufferedreader.readLine()) != null) {
                    stringBuilder.append(stringReadLine + "\n");
                }
                qResult = stringBuilder.toString();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return qResult;
    }

    private ArrayList<String> ParseJSON(String json){

        ArrayList<String> jResult = new ArrayList<>();
        try {
            //http://farm8.staticflickr.com/7487/15674684053_910e34eeda_m.jpg
            JSONObject JsonObject = new JSONObject(json);
            JSONObject Json_photos = JsonObject.getJSONObject("photos");
            JSONArray JsonArray_photo = Json_photos.getJSONArray("photo");
            for (int i = 0; i < JsonArray_photo.length(); i++) {
                JSONObject FlickrPhoto = JsonArray_photo.getJSONObject(i);
                String link = "http://farm" + FlickrPhoto.getString("farm") + ".staticflickr.com/" +
                        FlickrPhoto.getString("server") + "/" + FlickrPhoto.getString("id") + "_" +
                        FlickrPhoto.getString("secret") + ".jpg";
                jResult.add(link);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jResult;
    }

    private Bitmap downloadBitmap(String url) {
        Log.d(LOG_TAG, "start downloading image");
        final DefaultHttpClient client = new DefaultHttpClient();
        final HttpGet getRequest = new HttpGet(url);
        try {
            HttpResponse response = client.execute(getRequest);
            final int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                Log.w("ImageDownloader", "Error " + statusCode +
                        " while retrieving bitmap from " + url + " ");
                return null;
            }
            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = null;
                try {
                    inputStream = entity.getContent();
                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    entity.consumeContent();
                }
            }
        } catch (Exception e) {
            getRequest.abort();
            Log.e("ImageDownloader", "Something went wrong while" +
                    " retrieving bitmap from " + url + "\nError: " + e.toString());
        }
        return null;
    }

//    private String savePicture(Bitmap image, String name) {
//        try {
//            ContextWrapper contW = new ContextWrapper(getApplicationContext());
//            File directory = contW.getCacheDir();
//            Log.d(LOG_TAG, "start saving image");
//            OutputStream fOut = null;
//            File file = new File(directory, name);
//            fOut = new FileOutputStream(file);
//            image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
//            fOut.flush();
//            fOut.close();
////            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(),  file.getName());
//            Log.d(LOG_TAG, "image save " + directory.toString() + name);
//            return name;
//        }
//        catch (Exception e)
//        {
//            Log.d(LOG_TAG, e.getMessage());
//            return e.getMessage();
//        }
//
//    }

}