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

import com.miryor.jawn.model.HourlyForecast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by royrim on 11/22/16.
 */

public interface WeatherJsonParser {
    public static String EMOJI_SUN = "\u2600";
    public static String EMOJI_CRESCENTMOON = "\uD83C\uDF19";
    public static String EMOJI_NIGHTSKY = "\uD83C\uDF03";
    public static String EMOJI_CITYDUSK = "\uD83C\uDF06";
    public static String EMOJI_CLOUD = "\u2601";
    public static String EMOJI_SUN_BEHIND_CLOUD = "\u26C5";
    public static String EMOJI_CLOUD_LIGHTNING_RAIN = "\u26C8";
    public static String EMOJI_SNOWMAN = "\u26CA";
    public static String EMOJI_UMBRELLA = "\u2614";
    public static String EMOJI_QUESTION = "\u2753";

    public static String[] SNOW_WORDS = {
            "hail", "flurries", "freezing", "ice", "sleet", "snow"
    };

    public static String[] RAIN_WORDS = {
            "drizzle", "rain"
    };

    public static String[] CLOUDY_WORDS = {
            "cloudy", "fog", "haze", "hazy", "mist", "overcast", "partly sunny"
    };

    public static String[] STORM_WORDS = {
            "thunderstorm"
    };

    public static String[] SUNNY_WORDS = {
            "sunny"
    };

    public static String[] CLEAR_WORDS = {
            "clear"
    };

    public List<HourlyForecast> parseHourlyForecast() throws IOException;
}
