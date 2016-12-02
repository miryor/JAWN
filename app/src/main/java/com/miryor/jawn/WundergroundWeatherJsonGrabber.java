package com.miryor.jawn;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by royrim on 11/22/16.
 */

public class WundergroundWeatherJsonGrabber implements WeatherJsonGrabber {
    String urlString;

    public WundergroundWeatherJsonGrabber(String url) {
        this.urlString = url;
    }

    public InputStream getWeatherJsonInputStream() throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(30000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }

}
