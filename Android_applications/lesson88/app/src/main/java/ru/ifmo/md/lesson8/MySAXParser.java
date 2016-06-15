package ru.ifmo.md.lesson8;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * Created by Daria on 29.11.2014.
 */
public class MySAXParser extends DefaultHandler {

    final static int N = 20;

    String city = null;
    String date = null;
    String temp = null;
    String comment = null;
    String sunrise = null;
    String sunset = null;
//    List<Days> days;
    boolean item = false;
    int cnt = 0;
    Weather weather;

    public Weather getEntries() {
        return weather;
    }

    @Override
    public void startDocument() throws SAXException{
        Log.d("FF", "start parsing");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if (cnt > N) return;
        Log.d("FF", "qNAme = " +qName);

        if (qName.equals("yweather:location")) {
            if (atts.getValue("region").isEmpty()) {
                city = atts.getValue("city") + ", " + atts.getValue("country");
            } else {
                city = atts.getValue("city") + ", " + atts.getValue("region") + ", " + atts.getValue("country");
            }
        }
        if (qName.equals("yweather:astronomy")) {
            sunrise = atts.getValue("sunrise");
            sunset = atts.getValue("sunset");
        }
        if (qName.equals("yweather:condition")) {
            comment = atts.getValue("text");
            temp = atts.getValue("temp");
            date = atts.getValue("date");
            weather = new Weather(city, date, temp, comment, sunrise, sunset);
        }
        if (qName.equals("yweather:forecast")) {
            weather.addDay(atts.getValue("day"), atts.getValue("date"),
                    atts.getValue("low"), atts.getValue("high"), atts.getValue("text"));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
    }

    @Override
    public void endDocument() {
        Log.d("FF", "end parsing");
    }

}
