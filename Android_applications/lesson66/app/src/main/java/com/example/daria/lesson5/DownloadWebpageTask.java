package com.example.daria.lesson5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Daria on 19.10.2014.
 */

public class DownloadWebpageTask extends AsyncTask<String, Void, List<Entry>> {
    private static final String DEBUG_TAG = "AsyncTask";
    private static final String ns = null;
    private static String url = null;


    @Override
    protected List<Entry> doInBackground(String... urls) {

        try {
            url = urls[0];
            Log.d(DEBUG_TAG, "url " + url);
            return downloadUrl();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(DEBUG_TAG, "URL may be invalid");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            Log.d(DEBUG_TAG, "parserconf exception");
        } catch (SAXException e) {
            e.printStackTrace();
            Log.d(DEBUG_TAG, "saxparser exception");
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            Log.d(DEBUG_TAG, "xmlparser exception");
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Entry> result) {

    };

    private List<Entry> downloadUrl() throws IOException, XmlPullParserException, ParserConfigurationException, SAXException {
        Log.d(DEBUG_TAG, "download news started");
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpclient.execute(request);
        HttpEntity resEntity = response.getEntity();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        MySAXParser saxp = new MySAXParser();

        parser.parse(resEntity.getContent(), saxp);

        Log.d(DEBUG_TAG, "size = "+saxp.entries.size());
//        for (int i = 0; i < saxp.entries.size(); i++) {
//            if (!saxp.entries.get(i).url.equals("")) {
//                saxp.entries.get(i).image = downloadBitmap(saxp.entries.get(i).url);
//            }
//        }

        return saxp.entries;
    }

    private Bitmap downloadBitmap(String url) {
        Log.d(DEBUG_TAG, "start downloading image");
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
