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

import android.util.JsonReader;
import android.util.Log;

import com.miryor.jawn.model.HourlyForecast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by royrim on 11/22/16.
 */

public class WundergroundWeatherJsonParser implements WeatherJsonParser {
    private InputStream inputStream;
    public WundergroundWeatherJsonParser(String s) {
        try {
            this.inputStream = new ByteArrayInputStream(s.getBytes("UTF-8"));
        }
        catch ( UnsupportedEncodingException e ) {
            // i guess its possible?
            Log.e( "JAWN", "This shouldn't happen", e );
        }
    }
    public WundergroundWeatherJsonParser(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    public List<HourlyForecast> parseHourlyForecast() throws IOException {
        List<HourlyForecast> list = new ArrayList<HourlyForecast>();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            //String s = new String( "{ \"blah\": \"value\" }" );
            //reader = new JsonReader( new StringReader( s ) );

            reader.beginObject();
            while ( reader.hasNext() ) {
                String name = reader.nextName();
                Log.d( "JAWN", "found " + name );
                if (name.equals("hourly_forecast")) {
                    reader.beginArray();
                    while(reader.hasNext()) {
                        list.add(parseHourlyForecast(reader));
                    }
                    reader.endArray();
                }
                else {
                    reader.skipValue();
                }
            }
            reader.endObject();
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
            if ( name.equals("FCTTIME") ) {
                parseFctTime(reader,builder);
            }
            else if ( name.equals("temp") ) {
                parseTemp(reader,builder);
            }
            else if ( name.equals("condition") ) {
                builder.setCondition(reader.nextString());
            }
            else if ( name.equals("humidity") ) {
                builder.setHumidity(reader.nextInt());
            }
            else if ( name.equals("feelslike") ) {
                parseFeelsLike(reader,builder);
            }
            else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return builder.build();
    }

    private void parseFctTime(JsonReader reader, HourlyForecast.HourlyForecastBuilder builder) throws IOException {
        reader.beginObject();
        while ( reader.hasNext() ) {
            String name = reader.nextName();
            if ( name.equals("hour") ) {
                builder.setHour( reader.nextInt() );
            }
            else if ( name.equals("hour_padded") ) {
                builder.setHourPadded( reader.nextString() );
            }
            else if ( name.equals("min") ) {
                builder.setMinPadded( reader.nextString() );
            }
            else if ( name.equals("min_unpadded") ) {
                builder.setMin( reader.nextInt() );
            }
            else if ( name.equals("year") ) {
                builder.setYear( reader.nextInt() );
            }
            else if ( name.equals("mon") ) {
                builder.setMonth( reader.nextInt() );
            }
            else if ( name.equals("mon_padded") ) {
                builder.setMonthPadded( reader.nextString() );
            }
            else if ( name.equals("mday") ) {
                builder.setDay( reader.nextInt() );
            }
            else if ( name.equals("mday_padded") ) {
                builder.setDayPadded( reader.nextString() );
            }
            else if ( name.equals("epoch") ) {
                builder.setEpoch( reader.nextLong() );
            }
            else {
                reader.skipValue();
            }


        }
        reader.endObject();
    }

    private void parseTemp(JsonReader reader, HourlyForecast.HourlyForecastBuilder builder) throws IOException {
        reader.beginObject();
        while ( reader.hasNext() ) {
            String name = reader.nextName();
            if ( name.equals("english") ) {
                builder.setTempF(Integer.parseInt(reader.nextString()));
            }
            else if ( name.equals("metric" ) ) {
                builder.setTempM(Integer.parseInt(reader.nextString()));
            }
            else {
                reader.skipValue();
            }
        }
        reader.endObject();

    }

    private void parseFeelsLike(JsonReader reader, HourlyForecast.HourlyForecastBuilder builder) throws IOException {
        reader.beginObject();
        while ( reader.hasNext() ) {
            String name = reader.nextName();
            if ( name.equals("english") ) {
                builder.setFeelsLikeF(Integer.parseInt(reader.nextString()));
            }
            else if ( name.equals("metric" ) ) {
                builder.setFeelsLikeM(Integer.parseInt(reader.nextString()));
            }
            else {
                reader.skipValue();
            }
        }
        reader.endObject();

    }

}
