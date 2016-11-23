package com.miryor.jawn;

import com.miryor.jawn.model.HourlyForecast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by royrim on 11/22/16.
 */

public interface WeatherJsonParser {
    public List<HourlyForecast> parseHourlyForecast() throws IOException;
}
