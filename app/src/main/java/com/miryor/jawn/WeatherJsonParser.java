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
    public static String EMOJI_CLOUD = "\u2601";
    public static String EMOJI_SUN_BEHIND_CLOUD = "\u26C5";
    public static String EMOJI_CLOUD_LIGHTNING_RAIN = "\u26C8";
    public static String EMOJI_SNOWFLAKE = "\u2744";
    public static String EMOJI_UMBRELLA = "\u2614";
    public static String EMOJI_QUESTION = "\u2753";

    public static String[] SNOW_WORDS = {
            "hail", "flurries", "freezing", "ice", "sleet", "snow"
    };

    public static String[] RAIN_WORDS = {
            "drizzle", "rain"
    };

    public static String[] CLOUDY_WORDS = {
            "cloudy", "fog", "haze", "hazy", "mist", "partly sunny"
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
