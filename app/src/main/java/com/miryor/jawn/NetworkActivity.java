package com.miryor.jawn;

import android.app.Activity;

/**
 * Created by royrim on 11/20/16.
 */

public class NetworkActivity extends Activity {
    public static final String WIFI = "Wi-Fi";
    public static final String ANY = "Any";
    private static final String URL = "http://api.wunderground.com/api/502f7c0bd4a4257d/hourly/q/CA/San_Francisco.json";

    private static boolean wifiConnected = false;
    private static boolean mobileConnected = false;

    public static boolean refreshDisplay = true;
    public static String sPref = null;

    public void getWeather() {
    }
}
