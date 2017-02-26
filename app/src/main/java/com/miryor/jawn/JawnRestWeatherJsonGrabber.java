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
        URL url = new URL(URL + "?token=" + token + "&location=" + location + "&version=" + VERSION );
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(30000 /* milliseconds */);
        conn.setConnectTimeout(60000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }

}
