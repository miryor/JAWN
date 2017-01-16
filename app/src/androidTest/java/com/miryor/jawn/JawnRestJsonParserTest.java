package com.miryor.jawn;

import com.miryor.jawn.model.HourlyForecast;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by royrim on 1/15/17.
 */

public class JawnRestJsonParserTest {

    @Test
    public void parseJson() throws Exception {
        MockJawnRestJsonGrabber g = new MockJawnRestJsonGrabber();
        JawnRestWeatherJsonParser p = new JawnRestWeatherJsonParser(g.getWeatherJsonInputStream());
        List<HourlyForecast> list = p.parseHourlyForecast();
        assertEquals( list.size(), 36 );
        HourlyForecast hf = list.get(1);
        assertEquals(11, hf.getMonth());
        assertEquals(56, hf.getTempF());
        assertEquals(54, hf.getDewPointEnglish());
        assertEquals("Chance of Rain", hf.getCondition());
        assertEquals(47, hf.getSky());
        assertEquals(2, hf.getWindSpeedEnglish());
        assertEquals("WNW", hf.getWindDirection());
        assertEquals(290, hf.getWindDegrees());
        assertEquals("Few Showers", hf.getWx());
        assertEquals(0, hf.getUvi());
        assertEquals(92, hf.getHumidity());
        assertEquals(-9999, hf.getWindChillEnglish());
        assertEquals(-9999, hf.getHeatIndexEnglish());
        assertEquals(0.0d, hf.getQpfEnglish(),0);
        assertEquals(0.0, hf.getSnowEnglish(),0);
        assertEquals(30, hf.getPop());
        assertEquals(29.94, hf.getMslpEnglish(),0);
    }
}
