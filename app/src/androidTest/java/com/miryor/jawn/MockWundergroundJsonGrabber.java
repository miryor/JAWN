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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by royrim on 11/22/16.
 */

public class MockWundergroundJsonGrabber implements WeatherJsonGrabber {

    private static String JSON = "{\n" +
            "  \"response\": {\n" +
            "  \"version\":\"0.1\",\n" +
            "  \"termsofService\":\"http://www.wunderground.com/weather/api/d/terms.html\",\n" +
            "  \"features\": {\n" +
            "  \"hourly\": 1\n" +
            "  }\n" +
            "\t}\n" +
            "\t\t,\n" +
            "\t\"hourly_forecast\": [\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"20\",\"hour_padded\": \"20\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"20\",\"mday_padded\": \"20\",\"yday\": \"324\",\"isdst\": \"0\",\"epoch\": \"1479700800\",\"pretty\": \"8:00 PM PST on November 20, 2016\",\"civil\": \"8:00 PM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Sunday\",\"weekday_name_night\": \"Sunday Night\",\"weekday_name_abbrev\": \"Sun\",\"weekday_name_unlang\": \"Sunday\",\"weekday_name_night_unlang\": \"Sunday Night\",\"ampm\": \"PM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"56\", \"metric\": \"13\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"54\", \"metric\": \"12\"},\n" +
            "\t\t\"condition\": \"Chance of Rain\",\n" +
            "\t\t\"icon\": \"chancerain\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_chancerain.gif\",\n" +
            "\t\t\"fctcode\": \"12\",\n" +
            "\t\t\"sky\": \"63\",\n" +
            "\t\t\"wspd\": {\"english\": \"3\", \"metric\": \"5\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"W\", \"degrees\": \"280\"},\n" +
            "\t\t\"wx\": \"Few Showers\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"91\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"56\", \"metric\": \"13\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"30\",\n" +
            "\t\t\"mslp\": {\"english\": \"29.93\", \"metric\": \"1014\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"21\",\"hour_padded\": \"21\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"20\",\"mday_padded\": \"20\",\"yday\": \"324\",\"isdst\": \"0\",\"epoch\": \"1479704400\",\"pretty\": \"9:00 PM PST on November 20, 2016\",\"civil\": \"9:00 PM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Sunday\",\"weekday_name_night\": \"Sunday Night\",\"weekday_name_abbrev\": \"Sun\",\"weekday_name_unlang\": \"Sunday\",\"weekday_name_night_unlang\": \"Sunday Night\",\"ampm\": \"PM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"56\", \"metric\": \"13\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"54\", \"metric\": \"12\"},\n" +
            "\t\t\"condition\": \"Chance of Rain\",\n" +
            "\t\t\"icon\": \"chancerain\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_chancerain.gif\",\n" +
            "\t\t\"fctcode\": \"12\",\n" +
            "\t\t\"sky\": \"47\",\n" +
            "\t\t\"wspd\": {\"english\": \"2\", \"metric\": \"3\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"290\"},\n" +
            "\t\t\"wx\": \"Few Showers\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"92\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"56\", \"metric\": \"13\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"30\",\n" +
            "\t\t\"mslp\": {\"english\": \"29.94\", \"metric\": \"1014\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"22\",\"hour_padded\": \"22\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"20\",\"mday_padded\": \"20\",\"yday\": \"324\",\"isdst\": \"0\",\"epoch\": \"1479708000\",\"pretty\": \"10:00 PM PST on November 20, 2016\",\"civil\": \"10:00 PM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Sunday\",\"weekday_name_night\": \"Sunday Night\",\"weekday_name_abbrev\": \"Sun\",\"weekday_name_unlang\": \"Sunday\",\"weekday_name_night_unlang\": \"Sunday Night\",\"ampm\": \"PM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"55\", \"metric\": \"13\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"53\", \"metric\": \"12\"},\n" +
            "\t\t\"condition\": \"Mostly Cloudy\",\n" +
            "\t\t\"icon\": \"mostlycloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_mostlycloudy.gif\",\n" +
            "\t\t\"fctcode\": \"3\",\n" +
            "\t\t\"sky\": \"66\",\n" +
            "\t\t\"wspd\": {\"english\": \"3\", \"metric\": \"5\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"295\"},\n" +
            "\t\t\"wx\": \"Mostly Cloudy\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"92\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"55\", \"metric\": \"13\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"20\",\n" +
            "\t\t\"mslp\": {\"english\": \"29.94\", \"metric\": \"1014\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"23\",\"hour_padded\": \"23\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"20\",\"mday_padded\": \"20\",\"yday\": \"324\",\"isdst\": \"0\",\"epoch\": \"1479711600\",\"pretty\": \"11:00 PM PST on November 20, 2016\",\"civil\": \"11:00 PM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Sunday\",\"weekday_name_night\": \"Sunday Night\",\"weekday_name_abbrev\": \"Sun\",\"weekday_name_unlang\": \"Sunday\",\"weekday_name_night_unlang\": \"Sunday Night\",\"ampm\": \"PM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"55\", \"metric\": \"13\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"52\", \"metric\": \"11\"},\n" +
            "\t\t\"condition\": \"Partly Cloudy\",\n" +
            "\t\t\"icon\": \"partlycloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_partlycloudy.gif\",\n" +
            "\t\t\"fctcode\": \"2\",\n" +
            "\t\t\"sky\": \"59\",\n" +
            "\t\t\"wspd\": {\"english\": \"2\", \"metric\": \"3\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"286\"},\n" +
            "\t\t\"wx\": \"Partly Cloudy\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"91\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"55\", \"metric\": \"13\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"8\",\n" +
            "\t\t\"mslp\": {\"english\": \"29.95\", \"metric\": \"1014\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"0\",\"hour_padded\": \"00\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479715200\",\"pretty\": \"12:00 AM PST on November 21, 2016\",\"civil\": \"12:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"55\", \"metric\": \"13\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"condition\": \"Mostly Cloudy\",\n" +
            "\t\t\"icon\": \"mostlycloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_mostlycloudy.gif\",\n" +
            "\t\t\"fctcode\": \"3\",\n" +
            "\t\t\"sky\": \"75\",\n" +
            "\t\t\"wspd\": {\"english\": \"3\", \"metric\": \"5\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"W\", \"degrees\": \"278\"},\n" +
            "\t\t\"wx\": \"Mostly Cloudy\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"87\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"55\", \"metric\": \"13\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"10\",\n" +
            "\t\t\"mslp\": {\"english\": \"29.95\", \"metric\": \"1014\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"1\",\"hour_padded\": \"01\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479718800\",\"pretty\": \"1:00 AM PST on November 21, 2016\",\"civil\": \"1:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"54\", \"metric\": \"12\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"condition\": \"Overcast\",\n" +
            "\t\t\"icon\": \"cloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_cloudy.gif\",\n" +
            "\t\t\"fctcode\": \"4\",\n" +
            "\t\t\"sky\": \"84\",\n" +
            "\t\t\"wspd\": {\"english\": \"3\", \"metric\": \"5\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"284\"},\n" +
            "\t\t\"wx\": \"Cloudy\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"89\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"54\", \"metric\": \"12\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"11\",\n" +
            "\t\t\"mslp\": {\"english\": \"29.95\", \"metric\": \"1014\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"2\",\"hour_padded\": \"02\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479722400\",\"pretty\": \"2:00 AM PST on November 21, 2016\",\"civil\": \"2:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"54\", \"metric\": \"12\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"condition\": \"Overcast\",\n" +
            "\t\t\"icon\": \"cloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_cloudy.gif\",\n" +
            "\t\t\"fctcode\": \"4\",\n" +
            "\t\t\"sky\": \"80\",\n" +
            "\t\t\"wspd\": {\"english\": \"3\", \"metric\": \"5\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"294\"},\n" +
            "\t\t\"wx\": \"Cloudy\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"88\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"54\", \"metric\": \"12\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"11\",\n" +
            "\t\t\"mslp\": {\"english\": \"29.96\", \"metric\": \"1015\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"3\",\"hour_padded\": \"03\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479726000\",\"pretty\": \"3:00 AM PST on November 21, 2016\",\"civil\": \"3:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"54\", \"metric\": \"12\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"condition\": \"Overcast\",\n" +
            "\t\t\"icon\": \"cloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_cloudy.gif\",\n" +
            "\t\t\"fctcode\": \"4\",\n" +
            "\t\t\"sky\": \"81\",\n" +
            "\t\t\"wspd\": {\"english\": \"3\", \"metric\": \"5\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"295\"},\n" +
            "\t\t\"wx\": \"Cloudy\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"89\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"54\", \"metric\": \"12\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"8\",\n" +
            "\t\t\"mslp\": {\"english\": \"29.96\", \"metric\": \"1015\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"4\",\"hour_padded\": \"04\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479729600\",\"pretty\": \"4:00 AM PST on November 21, 2016\",\"civil\": \"4:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"53\", \"metric\": \"12\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"condition\": \"Overcast\",\n" +
            "\t\t\"icon\": \"cloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_cloudy.gif\",\n" +
            "\t\t\"fctcode\": \"4\",\n" +
            "\t\t\"sky\": \"80\",\n" +
            "\t\t\"wspd\": {\"english\": \"3\", \"metric\": \"5\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"NW\", \"degrees\": \"316\"},\n" +
            "\t\t\"wx\": \"Cloudy\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"90\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"53\", \"metric\": \"12\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"11\",\n" +
            "\t\t\"mslp\": {\"english\": \"29.97\", \"metric\": \"1015\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"5\",\"hour_padded\": \"05\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479733200\",\"pretty\": \"5:00 AM PST on November 21, 2016\",\"civil\": \"5:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"53\", \"metric\": \"12\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"25\",\n" +
            "\t\t\"wspd\": {\"english\": \"3\", \"metric\": \"5\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"NW\", \"degrees\": \"316\"},\n" +
            "\t\t\"wx\": \"Mostly Clear\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"93\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"53\", \"metric\": \"12\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"8\",\n" +
            "\t\t\"mslp\": {\"english\": \"29.98\", \"metric\": \"1015\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"6\",\"hour_padded\": \"06\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479736800\",\"pretty\": \"6:00 AM PST on November 21, 2016\",\"civil\": \"6:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"53\", \"metric\": \"12\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"50\", \"metric\": \"10\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"19\",\n" +
            "\t\t\"wspd\": {\"english\": \"3\", \"metric\": \"5\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"NW\", \"degrees\": \"316\"},\n" +
            "\t\t\"wx\": \"Clear\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"92\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"53\", \"metric\": \"12\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"8\",\n" +
            "\t\t\"mslp\": {\"english\": \"29.99\", \"metric\": \"1016\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"7\",\"hour_padded\": \"07\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479740400\",\"pretty\": \"7:00 AM PST on November 21, 2016\",\"civil\": \"7:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"53\", \"metric\": \"12\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"50\", \"metric\": \"10\"},\n" +
            "\t\t\"condition\": \"Mostly Cloudy\",\n" +
            "\t\t\"icon\": \"mostlycloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/mostlycloudy.gif\",\n" +
            "\t\t\"fctcode\": \"3\",\n" +
            "\t\t\"sky\": \"69\",\n" +
            "\t\t\"wspd\": {\"english\": \"4\", \"metric\": \"6\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"NW\", \"degrees\": \"318\"},\n" +
            "\t\t\"wx\": \"Mostly Cloudy\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"92\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"53\", \"metric\": \"12\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"8\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.0\", \"metric\": \"1016\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"8\",\"hour_padded\": \"08\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479744000\",\"pretty\": \"8:00 AM PST on November 21, 2016\",\"civil\": \"8:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"54\", \"metric\": \"12\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"17\",\n" +
            "\t\t\"wspd\": {\"english\": \"4\", \"metric\": \"6\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"NNW\", \"degrees\": \"331\"},\n" +
            "\t\t\"wx\": \"Sunny\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"89\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"54\", \"metric\": \"12\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"7\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.03\", \"metric\": \"1017\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"9\",\"hour_padded\": \"09\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479747600\",\"pretty\": \"9:00 AM PST on November 21, 2016\",\"civil\": \"9:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"55\", \"metric\": \"13\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"52\", \"metric\": \"11\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"19\",\n" +
            "\t\t\"wspd\": {\"english\": \"5\", \"metric\": \"8\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"NNW\", \"degrees\": \"328\"},\n" +
            "\t\t\"wx\": \"Sunny\",\n" +
            "\t\t\"uvi\": \"1\",\n" +
            "\t\t\"humidity\": \"87\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"55\", \"metric\": \"13\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"6\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.05\", \"metric\": \"1018\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"10\",\"hour_padded\": \"10\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479751200\",\"pretty\": \"10:00 AM PST on November 21, 2016\",\"civil\": \"10:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"56\", \"metric\": \"13\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"21\",\n" +
            "\t\t\"wspd\": {\"english\": \"5\", \"metric\": \"8\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"NNW\", \"degrees\": \"329\"},\n" +
            "\t\t\"wx\": \"Mostly Sunny\",\n" +
            "\t\t\"uvi\": \"2\",\n" +
            "\t\t\"humidity\": \"83\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"56\", \"metric\": \"13\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"4\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.06\", \"metric\": \"1018\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"11\",\"hour_padded\": \"11\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479754800\",\"pretty\": \"11:00 AM PST on November 21, 2016\",\"civil\": \"11:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"57\", \"metric\": \"14\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"condition\": \"Mostly Cloudy\",\n" +
            "\t\t\"icon\": \"mostlycloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/mostlycloudy.gif\",\n" +
            "\t\t\"fctcode\": \"3\",\n" +
            "\t\t\"sky\": \"67\",\n" +
            "\t\t\"wspd\": {\"english\": \"6\", \"metric\": \"10\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"NW\", \"degrees\": \"322\"},\n" +
            "\t\t\"wx\": \"Mostly Cloudy\",\n" +
            "\t\t\"uvi\": \"2\",\n" +
            "\t\t\"humidity\": \"81\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"57\", \"metric\": \"14\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"3\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.06\", \"metric\": \"1018\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"12\",\"hour_padded\": \"12\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479758400\",\"pretty\": \"12:00 PM PST on November 21, 2016\",\"civil\": \"12:00 PM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"PM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"58\", \"metric\": \"14\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"condition\": \"Partly Cloudy\",\n" +
            "\t\t\"icon\": \"partlycloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/partlycloudy.gif\",\n" +
            "\t\t\"fctcode\": \"2\",\n" +
            "\t\t\"sky\": \"49\",\n" +
            "\t\t\"wspd\": {\"english\": \"8\", \"metric\": \"13\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"NW\", \"degrees\": \"309\"},\n" +
            "\t\t\"wx\": \"Partly Cloudy\",\n" +
            "\t\t\"uvi\": \"3\",\n" +
            "\t\t\"humidity\": \"77\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"58\", \"metric\": \"14\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"3\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.06\", \"metric\": \"1018\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"13\",\"hour_padded\": \"13\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479762000\",\"pretty\": \"1:00 PM PST on November 21, 2016\",\"civil\": \"1:00 PM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"PM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"59\", \"metric\": \"15\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"condition\": \"Partly Cloudy\",\n" +
            "\t\t\"icon\": \"partlycloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/partlycloudy.gif\",\n" +
            "\t\t\"fctcode\": \"2\",\n" +
            "\t\t\"sky\": \"52\",\n" +
            "\t\t\"wspd\": {\"english\": \"10\", \"metric\": \"16\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"295\"},\n" +
            "\t\t\"wx\": \"Partly Cloudy\",\n" +
            "\t\t\"uvi\": \"2\",\n" +
            "\t\t\"humidity\": \"75\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"59\", \"metric\": \"15\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"3\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.06\", \"metric\": \"1018\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"14\",\"hour_padded\": \"14\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479765600\",\"pretty\": \"2:00 PM PST on November 21, 2016\",\"civil\": \"2:00 PM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"PM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"59\", \"metric\": \"15\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"condition\": \"Partly Cloudy\",\n" +
            "\t\t\"icon\": \"partlycloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/partlycloudy.gif\",\n" +
            "\t\t\"fctcode\": \"2\",\n" +
            "\t\t\"sky\": \"47\",\n" +
            "\t\t\"wspd\": {\"english\": \"10\", \"metric\": \"16\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"295\"},\n" +
            "\t\t\"wx\": \"Partly Cloudy\",\n" +
            "\t\t\"uvi\": \"2\",\n" +
            "\t\t\"humidity\": \"73\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"59\", \"metric\": \"15\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"3\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.06\", \"metric\": \"1018\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"15\",\"hour_padded\": \"15\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479769200\",\"pretty\": \"3:00 PM PST on November 21, 2016\",\"civil\": \"3:00 PM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"PM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"59\", \"metric\": \"15\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"condition\": \"Partly Cloudy\",\n" +
            "\t\t\"icon\": \"partlycloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/partlycloudy.gif\",\n" +
            "\t\t\"fctcode\": \"2\",\n" +
            "\t\t\"sky\": \"41\",\n" +
            "\t\t\"wspd\": {\"english\": \"11\", \"metric\": \"18\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"288\"},\n" +
            "\t\t\"wx\": \"Partly Cloudy\",\n" +
            "\t\t\"uvi\": \"1\",\n" +
            "\t\t\"humidity\": \"74\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"59\", \"metric\": \"15\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"4\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.06\", \"metric\": \"1018\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"16\",\"hour_padded\": \"16\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479772800\",\"pretty\": \"4:00 PM PST on November 21, 2016\",\"civil\": \"4:00 PM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"PM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"59\", \"metric\": \"15\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"24\",\n" +
            "\t\t\"wspd\": {\"english\": \"11\", \"metric\": \"18\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"290\"},\n" +
            "\t\t\"wx\": \"Mostly Sunny\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"73\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"59\", \"metric\": \"15\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"4\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.07\", \"metric\": \"1018\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"17\",\"hour_padded\": \"17\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479776400\",\"pretty\": \"5:00 PM PST on November 21, 2016\",\"civil\": \"5:00 PM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"PM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"58\", \"metric\": \"14\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"50\", \"metric\": \"10\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"13\",\n" +
            "\t\t\"wspd\": {\"english\": \"11\", \"metric\": \"18\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"292\"},\n" +
            "\t\t\"wx\": \"Clear\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"75\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"58\", \"metric\": \"14\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"5\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.09\", \"metric\": \"1019\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"18\",\"hour_padded\": \"18\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479780000\",\"pretty\": \"6:00 PM PST on November 21, 2016\",\"civil\": \"6:00 PM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"PM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"57\", \"metric\": \"14\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"50\", \"metric\": \"10\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"24\",\n" +
            "\t\t\"wspd\": {\"english\": \"10\", \"metric\": \"16\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"294\"},\n" +
            "\t\t\"wx\": \"Mostly Clear\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"77\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"57\", \"metric\": \"14\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"5\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.1\", \"metric\": \"1019\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"19\",\"hour_padded\": \"19\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479783600\",\"pretty\": \"7:00 PM PST on November 21, 2016\",\"civil\": \"7:00 PM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"PM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"57\", \"metric\": \"14\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"49\", \"metric\": \"9\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"29\",\n" +
            "\t\t\"wspd\": {\"english\": \"9\", \"metric\": \"14\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"298\"},\n" +
            "\t\t\"wx\": \"Mostly Clear\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"76\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"57\", \"metric\": \"14\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"5\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.11\", \"metric\": \"1020\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"20\",\"hour_padded\": \"20\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479787200\",\"pretty\": \"8:00 PM PST on November 21, 2016\",\"civil\": \"8:00 PM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"PM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"56\", \"metric\": \"13\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"48\", \"metric\": \"9\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"26\",\n" +
            "\t\t\"wspd\": {\"english\": \"8\", \"metric\": \"13\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"302\"},\n" +
            "\t\t\"wx\": \"Mostly Clear\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"76\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"56\", \"metric\": \"13\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"6\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.13\", \"metric\": \"1020\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"21\",\"hour_padded\": \"21\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479790800\",\"pretty\": \"9:00 PM PST on November 21, 2016\",\"civil\": \"9:00 PM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"PM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"55\", \"metric\": \"13\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"48\", \"metric\": \"9\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"22\",\n" +
            "\t\t\"wspd\": {\"english\": \"6\", \"metric\": \"10\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"303\"},\n" +
            "\t\t\"wx\": \"Mostly Clear\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"77\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"55\", \"metric\": \"13\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"11\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.14\", \"metric\": \"1021\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"22\",\"hour_padded\": \"22\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479794400\",\"pretty\": \"10:00 PM PST on November 21, 2016\",\"civil\": \"10:00 PM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"PM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"54\", \"metric\": \"12\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"47\", \"metric\": \"8\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"20\",\n" +
            "\t\t\"wspd\": {\"english\": \"5\", \"metric\": \"8\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"NW\", \"degrees\": \"304\"},\n" +
            "\t\t\"wx\": \"Mostly Clear\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"77\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"54\", \"metric\": \"12\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"5\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.15\", \"metric\": \"1021\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"23\",\"hour_padded\": \"23\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"21\",\"mday_padded\": \"21\",\"yday\": \"325\",\"isdst\": \"0\",\"epoch\": \"1479798000\",\"pretty\": \"11:00 PM PST on November 21, 2016\",\"civil\": \"11:00 PM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Monday\",\"weekday_name_night\": \"Monday Night\",\"weekday_name_abbrev\": \"Mon\",\"weekday_name_unlang\": \"Monday\",\"weekday_name_night_unlang\": \"Monday Night\",\"ampm\": \"PM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"53\", \"metric\": \"12\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"47\", \"metric\": \"8\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"21\",\n" +
            "\t\t\"wspd\": {\"english\": \"4\", \"metric\": \"6\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"NW\", \"degrees\": \"305\"},\n" +
            "\t\t\"wx\": \"Mostly Clear\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"80\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"53\", \"metric\": \"12\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"5\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.16\", \"metric\": \"1021\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"0\",\"hour_padded\": \"00\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"22\",\"mday_padded\": \"22\",\"yday\": \"326\",\"isdst\": \"0\",\"epoch\": \"1479801600\",\"pretty\": \"12:00 AM PST on November 22, 2016\",\"civil\": \"12:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Tuesday\",\"weekday_name_night\": \"Tuesday Night\",\"weekday_name_abbrev\": \"Tue\",\"weekday_name_unlang\": \"Tuesday\",\"weekday_name_night_unlang\": \"Tuesday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"53\", \"metric\": \"12\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"47\", \"metric\": \"8\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"23\",\n" +
            "\t\t\"wspd\": {\"english\": \"3\", \"metric\": \"5\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"300\"},\n" +
            "\t\t\"wx\": \"Mostly Clear\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"82\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"53\", \"metric\": \"12\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"7\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.16\", \"metric\": \"1021\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"1\",\"hour_padded\": \"01\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"22\",\"mday_padded\": \"22\",\"yday\": \"326\",\"isdst\": \"0\",\"epoch\": \"1479805200\",\"pretty\": \"1:00 AM PST on November 22, 2016\",\"civil\": \"1:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Tuesday\",\"weekday_name_night\": \"Tuesday Night\",\"weekday_name_abbrev\": \"Tue\",\"weekday_name_unlang\": \"Tuesday\",\"weekday_name_night_unlang\": \"Tuesday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"52\", \"metric\": \"11\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"47\", \"metric\": \"8\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"24\",\n" +
            "\t\t\"wspd\": {\"english\": \"3\", \"metric\": \"5\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"294\"},\n" +
            "\t\t\"wx\": \"Mostly Clear\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"82\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"52\", \"metric\": \"11\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"7\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.16\", \"metric\": \"1021\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"2\",\"hour_padded\": \"02\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"22\",\"mday_padded\": \"22\",\"yday\": \"326\",\"isdst\": \"0\",\"epoch\": \"1479808800\",\"pretty\": \"2:00 AM PST on November 22, 2016\",\"civil\": \"2:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Tuesday\",\"weekday_name_night\": \"Tuesday Night\",\"weekday_name_abbrev\": \"Tue\",\"weekday_name_unlang\": \"Tuesday\",\"weekday_name_night_unlang\": \"Tuesday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"52\", \"metric\": \"11\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"47\", \"metric\": \"8\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"23\",\n" +
            "\t\t\"wspd\": {\"english\": \"3\", \"metric\": \"5\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WNW\", \"degrees\": \"287\"},\n" +
            "\t\t\"wx\": \"Mostly Clear\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"84\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"52\", \"metric\": \"11\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"8\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.16\", \"metric\": \"1021\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"3\",\"hour_padded\": \"03\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"22\",\"mday_padded\": \"22\",\"yday\": \"326\",\"isdst\": \"0\",\"epoch\": \"1479812400\",\"pretty\": \"3:00 AM PST on November 22, 2016\",\"civil\": \"3:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Tuesday\",\"weekday_name_night\": \"Tuesday Night\",\"weekday_name_abbrev\": \"Tue\",\"weekday_name_unlang\": \"Tuesday\",\"weekday_name_night_unlang\": \"Tuesday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"47\", \"metric\": \"8\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"23\",\n" +
            "\t\t\"wspd\": {\"english\": \"3\", \"metric\": \"5\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"W\", \"degrees\": \"277\"},\n" +
            "\t\t\"wx\": \"Mostly Clear\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"85\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"13\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.16\", \"metric\": \"1021\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"4\",\"hour_padded\": \"04\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"22\",\"mday_padded\": \"22\",\"yday\": \"326\",\"isdst\": \"0\",\"epoch\": \"1479816000\",\"pretty\": \"4:00 AM PST on November 22, 2016\",\"civil\": \"4:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Tuesday\",\"weekday_name_night\": \"Tuesday Night\",\"weekday_name_abbrev\": \"Tue\",\"weekday_name_unlang\": \"Tuesday\",\"weekday_name_night_unlang\": \"Tuesday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"47\", \"metric\": \"8\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"24\",\n" +
            "\t\t\"wspd\": {\"english\": \"2\", \"metric\": \"3\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"W\", \"degrees\": \"271\"},\n" +
            "\t\t\"wx\": \"Mostly Clear\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"86\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"51\", \"metric\": \"11\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"7\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.16\", \"metric\": \"1021\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"5\",\"hour_padded\": \"05\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"22\",\"mday_padded\": \"22\",\"yday\": \"326\",\"isdst\": \"0\",\"epoch\": \"1479819600\",\"pretty\": \"5:00 AM PST on November 22, 2016\",\"civil\": \"5:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Tuesday\",\"weekday_name_night\": \"Tuesday Night\",\"weekday_name_abbrev\": \"Tue\",\"weekday_name_unlang\": \"Tuesday\",\"weekday_name_night_unlang\": \"Tuesday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"50\", \"metric\": \"10\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"47\", \"metric\": \"8\"},\n" +
            "\t\t\"condition\": \"Clear\",\n" +
            "\t\t\"icon\": \"clear\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_clear.gif\",\n" +
            "\t\t\"fctcode\": \"1\",\n" +
            "\t\t\"sky\": \"26\",\n" +
            "\t\t\"wspd\": {\"english\": \"2\", \"metric\": \"3\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"WSW\", \"degrees\": \"249\"},\n" +
            "\t\t\"wx\": \"Mostly Clear\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"89\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"50\", \"metric\": \"10\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"7\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.16\", \"metric\": \"1021\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"6\",\"hour_padded\": \"06\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"22\",\"mday_padded\": \"22\",\"yday\": \"326\",\"isdst\": \"0\",\"epoch\": \"1479823200\",\"pretty\": \"6:00 AM PST on November 22, 2016\",\"civil\": \"6:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Tuesday\",\"weekday_name_night\": \"Tuesday Night\",\"weekday_name_abbrev\": \"Tue\",\"weekday_name_unlang\": \"Tuesday\",\"weekday_name_night_unlang\": \"Tuesday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"50\", \"metric\": \"10\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"47\", \"metric\": \"8\"},\n" +
            "\t\t\"condition\": \"Mostly Cloudy\",\n" +
            "\t\t\"icon\": \"mostlycloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_mostlycloudy.gif\",\n" +
            "\t\t\"fctcode\": \"3\",\n" +
            "\t\t\"sky\": \"62\",\n" +
            "\t\t\"wspd\": {\"english\": \"2\", \"metric\": \"3\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"SW\", \"degrees\": \"214\"},\n" +
            "\t\t\"wx\": \"Mostly Cloudy\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"90\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"50\", \"metric\": \"10\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"7\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.17\", \"metric\": \"1022\"}\n" +
            "\t\t}\n" +
            "\t\t,\n" +
            "\t\t{\n" +
            "\t\t\"FCTTIME\": {\n" +
            "\t\t\"hour\": \"7\",\"hour_padded\": \"07\",\"min\": \"00\",\"min_unpadded\": \"0\",\"sec\": \"0\",\"year\": \"2016\",\"mon\": \"11\",\"mon_padded\": \"11\",\"mon_abbrev\": \"Nov\",\"mday\": \"22\",\"mday_padded\": \"22\",\"yday\": \"326\",\"isdst\": \"0\",\"epoch\": \"1479826800\",\"pretty\": \"7:00 AM PST on November 22, 2016\",\"civil\": \"7:00 AM\",\"month_name\": \"November\",\"month_name_abbrev\": \"Nov\",\"weekday_name\": \"Tuesday\",\"weekday_name_night\": \"Tuesday Night\",\"weekday_name_abbrev\": \"Tue\",\"weekday_name_unlang\": \"Tuesday\",\"weekday_name_night_unlang\": \"Tuesday Night\",\"ampm\": \"AM\",\"tz\": \"\",\"age\": \"\",\"UTCDATE\": \"\"\n" +
            "\t\t},\n" +
            "\t\t\"temp\": {\"english\": \"50\", \"metric\": \"10\"},\n" +
            "\t\t\"dewpoint\": {\"english\": \"48\", \"metric\": \"9\"},\n" +
            "\t\t\"condition\": \"Mostly Cloudy\",\n" +
            "\t\t\"icon\": \"mostlycloudy\",\n" +
            "\t\t\"icon_url\":\"http://icons.wxug.com/i/c/k/mostlycloudy.gif\",\n" +
            "\t\t\"fctcode\": \"3\",\n" +
            "\t\t\"sky\": \"70\",\n" +
            "\t\t\"wspd\": {\"english\": \"2\", \"metric\": \"3\"},\n" +
            "\t\t\"wdir\": {\"dir\": \"SSE\", \"degrees\": \"164\"},\n" +
            "\t\t\"wx\": \"Mostly Cloudy\",\n" +
            "\t\t\"uvi\": \"0\",\n" +
            "\t\t\"humidity\": \"92\",\n" +
            "\t\t\"windchill\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"heatindex\": {\"english\": \"-9999\", \"metric\": \"-9999\"},\n" +
            "\t\t\"feelslike\": {\"english\": \"50\", \"metric\": \"10\"},\n" +
            "\t\t\"qpf\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"snow\": {\"english\": \"0.0\", \"metric\": \"0\"},\n" +
            "\t\t\"pop\": \"7\",\n" +
            "\t\t\"mslp\": {\"english\": \"30.18\", \"metric\": \"1022\"}\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}";

    @Override
    public InputStream getWeatherJsonInputStream() throws IOException {
        return new ByteArrayInputStream(JSON.getBytes("UTF-8"));
    }
}
