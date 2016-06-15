//package ru.ifmo.md.lesson8;
//import java.util.Date;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
///**
// * Created by Daria on 14.12.2014.
// */
//public class MyLocation extends Activity{
//    String tvEnabledGPS;
//    String tvStatusGPS;
//    String tvLocationGPS;
//    String tvEnabledNet;
//    String tvStatusNet;
//    String tvLocationNet;
//
//    private LocationManager locationManager;
//    StringBuilder sbGPS = new StringBuilder();
//    StringBuilder sbNet = new StringBuilder();
//
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        checkEnabled();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        locationManager.removeUpdates(locationListener);
//    }
//
//
//
//
//
//
//    public void onClickLocationSettings(View view) {
//        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//    };
//
//}
