package ru.ifmo.md.lesson8;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Daria on 29.11.2014.
 */
public class DownloadWeatherTask extends AsyncTask<String, Void, Weather> {
    private static final String DEBUG_TAG = "AsyncTask";
    private static String url = null;

    @Override
    protected Weather doInBackground(String... urls) {

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
    protected void onPostExecute(Weather result) {
    };

    private Weather downloadUrl() throws IOException, XmlPullParserException, ParserConfigurationException, SAXException {
        Log.d(DEBUG_TAG, "download weather started");
        HttpClient httpclient = new DefaultHttpClient();

        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpclient.execute(request);
        HttpEntity resEntity = response.getEntity();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        MySAXParser saxp = new MySAXParser();

        parser.parse(resEntity.getContent(), saxp);

        Log.d(DEBUG_TAG, "size = " + saxp.city);
//        for (int i = 0; i < saxp.entries.size(); i++) {
//            if (!saxp.entries.get(i).url.equals("")) {
//                saxp.entries.get(i).image = downloadBitmap(saxp.entries.get(i).url);
//            }
//        }

        return saxp.weather;
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
