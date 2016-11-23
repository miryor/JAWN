package com.miryor.jawn;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by royrim on 11/22/16.
 */

public interface WeatherJsonGrabber {
    public InputStream getWeatherJsonInputStream() throws IOException;
}
