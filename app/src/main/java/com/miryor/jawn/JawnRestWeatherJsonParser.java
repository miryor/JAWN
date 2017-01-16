package com.miryor.jawn;

import android.util.JsonReader;
import android.util.Log;

import com.miryor.jawn.model.HourlyForecast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by royrim on 1/15/17.
 */

public class JawnRestWeatherJsonParser implements WeatherJsonParser {
    private InputStream inputStream;
    public JawnRestWeatherJsonParser(String s) {
        try {
            this.inputStream = new ByteArrayInputStream(s.getBytes("UTF-8"));
        }
        catch ( UnsupportedEncodingException e ) {
            // i guess its possible?
            Log.e( "JAWN", "This shouldn't happen", e );
        }
    }
    public JawnRestWeatherJsonParser(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    public List<HourlyForecast> parseHourlyForecast() throws IOException {
        List<HourlyForecast> list = new ArrayList<HourlyForecast>();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

            reader.beginArray();
            while(reader.hasNext()) {
                list.add(parseHourlyForecast(reader));
            }
            reader.endArray();

            Log.d( "JAWN", "done" );
        }
        finally {
            if ( reader != null ) reader.close();
        }

        return list;
    }

    private HourlyForecast parseHourlyForecast(JsonReader reader) throws IOException {
        reader.beginObject();
        HourlyForecast.HourlyForecastBuilder builder = new HourlyForecast.HourlyForecastBuilder();
        while( reader.hasNext() ) {
            String name = reader.nextName();
            if ( name.equals("hour") ) {
                builder.setHour(reader.nextInt());
            }
            else if ( name.equals("hourPadded") ) {
                builder.setHourPadded(reader.nextString());
            }
            else if ( name.equals("min") ) {
                builder.setMin(reader.nextInt());
            }
            else if ( name.equals("minPadded") ) {
                builder.setMinPadded(reader.nextString());
            }
            else if ( name.equals("year") ) {
                builder.setYear(reader.nextInt());
            }
            else if ( name.equals("month") ) {
                builder.setMonth(reader.nextInt());
            }
            else if ( name.equals("monthPadded") ) {
                builder.setMonthPadded(reader.nextString());
            }
            else if ( name.equals("day") ) {
                builder.setDay(reader.nextInt());
            }
            else if ( name.equals("dayPadded") ) {
                builder.setDayPadded(reader.nextString());
            }
            else if ( name.equals("tempF") ) {
                builder.setTempF(reader.nextInt());
            }
            else if ( name.equals("tempM") ) {
                builder.setTempM(reader.nextInt());
            }
            else if ( name.equals("condition") ) {
                builder.setCondition(reader.nextString());
            }
            else if ( name.equals("feelsLikeF") ) {
                builder.setFeelsLikeF(reader.nextInt());
            }
            else if ( name.equals("feelsLikeM") ) {
                builder.setFeelsLikeM(reader.nextInt());
            }
            else if ( name.equals("humidity") ) {
                builder.setHumidity(reader.nextInt());
            }
            else if ( name.equals("epoch") ) {
                builder.setEpoch(reader.nextLong());
            }
            else if ( name.equals("dewPointEnglish") ) {
                builder.setDewPointEnglish(reader.nextInt());
            }
            else if ( name.equals("dewPointMetric") ) {
                builder.setDewPointMetric(reader.nextInt());
            }
            else if ( name.equals("sky") ) {
                builder.setSky(reader.nextInt());
            }
            else if ( name.equals("windSpeedEnglish") ) {
                builder.setWindSpeedEnglish(reader.nextInt());
            }
            else if ( name.equals("windSpeedMetric") ) {
                builder.setWindSpeedMetric(reader.nextInt());
            }
            else if ( name.equals("windDirection") ) {
                builder.setWindDirection(reader.nextString());
            }
            else if ( name.equals("windDegrees") ) {
                builder.setWindDegrees(reader.nextInt());
            }
            else if ( name.equals("wx") ) {
                builder.setWx(reader.nextString());
            }
            else if ( name.equals("uvi") ) {
                builder.setUvi(reader.nextInt());
            }
            else if ( name.equals("windChillEnglish") ) {
                builder.setWindChillEnglish(reader.nextInt());
            }
            else if ( name.equals("windChillMetric") ) {
                builder.setWindChillMetric(reader.nextInt());
            }
            else if ( name.equals("heatIndexEnglish") ) {
                builder.setHeatIndexEnglish(reader.nextInt());
            }
            else if ( name.equals("heatIndexMetric") ) {
                builder.setHeatIndexMetric(reader.nextInt());
            }
            else if ( name.equals("qpfEnglish") ) {
                builder.setQpfEnglish(reader.nextDouble());
            }
            else if ( name.equals("qpfMetric") ) {
                builder.setQpfMetric(reader.nextInt());
            }
            else if ( name.equals("snowEnglish") ) {
                builder.setSnowEnglish(reader.nextDouble());
            }
            else if ( name.equals("snowMetric") ) {
                builder.setSnowMetric(reader.nextInt());
            }
            else if ( name.equals("pop") ) {
                builder.setPop(reader.nextInt());
            }
            else if ( name.equals("mslpEnglish") ) {
                builder.setMslpEnglish(reader.nextDouble());
            }
            else if ( name.equals("mslpMetric") ) {
                builder.setMslpMetric(reader.nextInt());
            }
            else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return builder.build();
    }
}
