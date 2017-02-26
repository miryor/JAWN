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

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStreamReader;
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
        assertEquals( list.size(), 36 );
        HourlyForecast hf = list.get(0);
        assertEquals(hf.getMonth(), 11);
    }
}
