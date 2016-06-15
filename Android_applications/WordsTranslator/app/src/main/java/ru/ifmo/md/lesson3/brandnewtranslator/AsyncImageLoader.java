package ru.ifmo.md.lesson3.brandnewtranslator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadim on 28/09/14.
 */
public class AsyncImageLoader extends AsyncTask<String, Bitmap, List<Bitmap>> {
    protected static final String TAG = "AsyncImageLoader";

    @Override
    protected List<Bitmap> doInBackground(String... strings) {
        String word = strings[0].trim().replaceAll("\\s+", "%20");
//        try {
//            URL urlBing = new URL("https://api.datamarket.azure.com/Bing/SearchWeb/v1/Web?Query=%27xbox%27&$format=json");
//            String accountKey = "zANzAEQntVX+rarD6rtzvmZDNAi1epTJF51FdhR5yrE";
//            String key = Base64.encodeToString((accountKey + ":" + accountKey).getBytes(), Base64.NO_WRAP);
//            URLConnection urlConnection = urlBing.openConnection();
//            urlConnection.setRequestProperty("Authorization", "Basic %s" + key);
//            urlConnection.setRequestProperty("Query", word);
//            InputStream responseBing = urlConnection.getInputStream();
//            Log.d(TAG, "bing ok");
//            String res = readStream(response2);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String uri = "http://yandex.ru/images/search?text=" + word + "&isize=small&itype=jpg";
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString;
        List<Bitmap> bm = new ArrayList<Bitmap>();
        String[] urls = new String[AnotherActivity.NEED_IMAGES];
        try {
            response = httpclient.execute(new HttpGet(uri));
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
                int prev = 0;
                int i = 0;
                while (i < AnotherActivity.NEED_IMAGES) {
                    int pos2 = responseString.indexOf(".jpg", prev);
                    int pos = responseString.indexOf("http://", pos2 - 100);
                    if ((pos < pos2) && (pos != -1) && (pos2 != -1)) {
                        urls[i] = responseString.substring(pos, pos2 + 4);
                        Bitmap bitmap = downloadBitmap(urls[i]);
                        Log.d(TAG, "some image downloaded");
                        bm.add(bitmap);
                        publishProgress(bitmap);
                        i++;
                    }
                    prev = responseString.indexOf("img class", pos2 + 5);
                }
                return bm;
            } else {
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return bm;
    }

    private Bitmap downloadBitmap(String url) {
        Log.d(TAG, "start downloading");
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
}