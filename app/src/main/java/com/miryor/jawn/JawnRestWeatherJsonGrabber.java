package com.miryor.jawn;

import android.content.Context;
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

    Context context;
    String location;

    public JawnRestWeatherJsonGrabber(Context context, String location) {
        this.context = context;
        this.location = location;
    }

    public InputStream getWeatherJsonInputStream() throws IOException {
        Log.d("JAWN", "Getting weather from: " + URL + "?location=" + location + "&version=" + VERSION );
        URL url = new URL(URL + "?token=" + Utils.getTokenId(context) + "&location=" + location + "&version=" + VERSION );
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(30000 /* milliseconds */);
        conn.setConnectTimeout(60000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }

}
