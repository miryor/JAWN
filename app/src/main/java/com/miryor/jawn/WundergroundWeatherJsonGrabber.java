package com.miryor.jawn;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by royrim on 11/22/16.
 */

public class WundergroundWeatherJsonGrabber implements WeatherJsonGrabber {
    public static final String WUNDERGROUND_URL = "http://api.wunderground.com/api/502f7c0bd4a4257d/hourly/q/";

    String location;

    public WundergroundWeatherJsonGrabber(String url) {
        this.location = location;
    }

    public InputStream getWeatherJsonInputStream() throws IOException {
        Log.d("JAWN", "Getting weather from: " + WUNDERGROUND_URL + location + Utils.JSON_URL );
        URL url = new URL(WUNDERGROUND_URL + location + Utils.JSON_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(30000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }

}
