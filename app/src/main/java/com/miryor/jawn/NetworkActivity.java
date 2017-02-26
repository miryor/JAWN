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
