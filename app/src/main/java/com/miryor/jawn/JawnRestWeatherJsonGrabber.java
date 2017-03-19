/*
 * Copyright (c) 2017.
 *
 * JAWN is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
    String token;

    public JawnRestWeatherJsonGrabber(Context context, String token, String location) {
        this.context = context;
        this.token = token;
        this.location = location;
    }

    public InputStream getWeatherJsonInputStream() throws IOException {
        Log.d("JAWN", "Getting weather from: " + URL + "?location=" + location + "&version=" + VERSION );
        URL url = new URL(URL);
        byte[] postDataBytes = ( "token=" + token + "&location=" + location + "&version=" + VERSION ).getBytes("UTF-8");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setReadTimeout(15000 /* milliseconds */); // first has to google sign in check then get weather
        conn.setConnectTimeout(15000 /* milliseconds */); // 15 seconds is safe according to heroku, heroku times out at 30 seconds
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.getOutputStream().write(postDataBytes);
        conn.connect();
        int responseCode = conn.getResponseCode();
        if ( 403 == responseCode ) throw new InvalidTokenException( "JAWN-REST decided token was invalid " + token );
        if ( 200 != responseCode ) throw new IOException( "Invalid response code " + responseCode );
        return conn.getInputStream();
    }

}
