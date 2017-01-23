package com.miryor.jawn;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by royrim on 1/15/17.
 */

public class JawnRestWeatherJsonGrabber implements WeatherJsonGrabber {
    private static String URL = BuildConfig.JAWN_REST_URL;
    private static String VERSION = BuildConfig.VERSION_NAME;

    String location;

    public JawnRestWeatherJsonGrabber(String location) {
        this.location = location;
    }

    public InputStream getWeatherJsonInputStream() throws IOException {
        Log.d("JAWN", "Getting weather from: " + URL + "?location=" + location + "&version=" + VERSION );
        URL url = new URL(URL + "?location=" + location + "&version=" + VERSION );
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(30000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }

}
