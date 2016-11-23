package com.miryor.jawn;

import com.miryor.jawn.model.HourlyForecast;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by royrim on 11/22/16.
 */

public class WundergroundJsonParserTest {

    @Test
    public void parseJson() throws Exception {
        MockWundergroundJsonGrabber g = new MockWundergroundJsonGrabber();
        WundergroundWeatherJsonParser p = new WundergroundWeatherJsonParser(g.getWeatherJsonInputStream());
        List<HourlyForecast> list = p.parseHourlyForecast();
        HourlyForecast hf = list.get(0);
        assertEquals(hf.getMonth(), 11);
    }
}
