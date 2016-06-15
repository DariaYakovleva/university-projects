package com.example.daria.lesson5;

import android.graphics.Bitmap;
import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daria on 09.11.2014.
 */
public class MySAXParser extends DefaultHandler {

    final static int N = 20;

    String name = null;
    String url = null;
    String title = null;
    String description = null;
    String link = null;
    boolean item = false;
    int cnt = 0;
    List<Entry> entries = new ArrayList<Entry>();

    public List<Entry> getEntries() {
        return entries;
    }

    @Override
    public void startDocument() throws SAXException{
        Log.d("FF", "start parsing");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if (cnt > N) return;
        Log.d("FF", "qNAme = " +qName);
        if (qName.equals("item"))
            item = true;
        name = qName;
        if (name.equals("media:thumbnail")) {
            url = atts.getValue("url");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (!item) return;
        if (cnt > N) return;

        if (name.equals("title")) {
            title += new String(ch, start, length);
        } else if (name.equals("description")) {
            description += new String(ch, start, length);
            Log.d("FF", description);
        } else if (name.equals("link")) {
            link += new String(ch, start, length);
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (cnt > N) return;
        if (qName.equals("item")) {
            item = false;
            entries.add(new Entry(title, description, link, url));
            cnt++;
            name = "";
            url = "";
            link = "";
            title = "";
            description = "";
        }
    }

    @Override
    public void endDocument() {
        Log.d("FF", "end parsing");
    }

}
