package com.miryor.jawn;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by royrim on 1/15/17.
 */

public class MockJawnRestJsonGrabber implements WeatherJsonGrabber {
    public static String JSON = "[{\"hour\":20,\"hourPadded\":\"20\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":20,\"dayPadded\":\"20\",\"tempF\":56,\"tempM\":13,\"condition\":\"Chance of Rain\",\"feelsLikeF\":56,\"feelsLikeM\":13,\"humidity\":91,\"epoch\":1479700800,\"dewPointEnglish\":54,\"dewPointMetric\":12,\"sky\":63,\"windSpeedEnglish\":3,\"windSpeedMetric\":5,\"windDirection\":\"W\",\"windDegrees\":280,\"wx\":\"Few Showers\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":30,\"mslpEnglish\":29.93,\"mslpMetric\":1014}," +
            "{\"hour\":21,\"hourPadded\":\"21\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":20,\"dayPadded\":\"20\",\"tempF\":56,\"tempM\":13,\"condition\":\"Chance of Rain\",\"feelsLikeF\":56,\"feelsLikeM\":13,\"humidity\":92,\"epoch\":1479704400,\"dewPointEnglish\":54,\"dewPointMetric\":12,\"sky\":47,\"windSpeedEnglish\":2,\"windSpeedMetric\":3,\"windDirection\":\"WNW\",\"windDegrees\":290,\"wx\":\"Few Showers\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":30,\"mslpEnglish\":29.94,\"mslpMetric\":1014}," +
            "{\"hour\":22,\"hourPadded\":\"22\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":20,\"dayPadded\":\"20\",\"tempF\":55,\"tempM\":13,\"condition\":\"Mostly Cloudy\",\"feelsLikeF\":55,\"feelsLikeM\":13,\"humidity\":92,\"epoch\":1479708000,\"dewPointEnglish\":53,\"dewPointMetric\":12,\"sky\":66,\"windSpeedEnglish\":3,\"windSpeedMetric\":5,\"windDirection\":\"WNW\",\"windDegrees\":295,\"wx\":\"Mostly Cloudy\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":20,\"mslpEnglish\":29.94,\"mslpMetric\":1014}," +
            "{\"hour\":23,\"hourPadded\":\"23\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":20,\"dayPadded\":\"20\",\"tempF\":55,\"tempM\":13,\"condition\":\"Partly Cloudy\",\"feelsLikeF\":55,\"feelsLikeM\":13,\"humidity\":91,\"epoch\":1479711600,\"dewPointEnglish\":52,\"dewPointMetric\":11,\"sky\":59,\"windSpeedEnglish\":2,\"windSpeedMetric\":3,\"windDirection\":\"WNW\",\"windDegrees\":286,\"wx\":\"Partly Cloudy\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":8,\"mslpEnglish\":29.95,\"mslpMetric\":1014}," +
            "{\"hour\":0,\"hourPadded\":\"00\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":55,\"tempM\":13,\"condition\":\"Mostly Cloudy\",\"feelsLikeF\":55,\"feelsLikeM\":13,\"humidity\":87,\"epoch\":1479715200,\"dewPointEnglish\":51,\"dewPointMetric\":11,\"sky\":75,\"windSpeedEnglish\":3,\"windSpeedMetric\":5,\"windDirection\":\"W\",\"windDegrees\":278,\"wx\":\"Mostly Cloudy\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":10,\"mslpEnglish\":29.95,\"mslpMetric\":1014}," +
            "{\"hour\":1,\"hourPadded\":\"01\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":54,\"tempM\":12,\"condition\":\"Overcast\",\"feelsLikeF\":54,\"feelsLikeM\":12,\"humidity\":89,\"epoch\":1479718800,\"dewPointEnglish\":51,\"dewPointMetric\":11,\"sky\":84,\"windSpeedEnglish\":3,\"windSpeedMetric\":5,\"windDirection\":\"WNW\",\"windDegrees\":284,\"wx\":\"Cloudy\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":11,\"mslpEnglish\":29.95,\"mslpMetric\":1014}," +
            "{\"hour\":2,\"hourPadded\":\"02\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":54,\"tempM\":12,\"condition\":\"Overcast\",\"feelsLikeF\":54,\"feelsLikeM\":12,\"humidity\":88,\"epoch\":1479722400,\"dewPointEnglish\":51,\"dewPointMetric\":11,\"sky\":80,\"windSpeedEnglish\":3,\"windSpeedMetric\":5,\"windDirection\":\"WNW\",\"windDegrees\":294,\"wx\":\"Cloudy\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":11,\"mslpEnglish\":29.96,\"mslpMetric\":1015}," +
            "{\"hour\":3,\"hourPadded\":\"03\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":54,\"tempM\":12,\"condition\":\"Overcast\",\"feelsLikeF\":54,\"feelsLikeM\":12,\"humidity\":89,\"epoch\":1479726000,\"dewPointEnglish\":51,\"dewPointMetric\":11,\"sky\":81,\"windSpeedEnglish\":3,\"windSpeedMetric\":5,\"windDirection\":\"WNW\",\"windDegrees\":295,\"wx\":\"Cloudy\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":8,\"mslpEnglish\":29.96,\"mslpMetric\":1015}," +
            "{\"hour\":4,\"hourPadded\":\"04\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":53,\"tempM\":12,\"condition\":\"Overcast\",\"feelsLikeF\":53,\"feelsLikeM\":12,\"humidity\":90,\"epoch\":1479729600,\"dewPointEnglish\":51,\"dewPointMetric\":11,\"sky\":80,\"windSpeedEnglish\":3,\"windSpeedMetric\":5,\"windDirection\":\"NW\",\"windDegrees\":316,\"wx\":\"Cloudy\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":11,\"mslpEnglish\":29.97,\"mslpMetric\":1015}," +
            "{\"hour\":5,\"hourPadded\":\"05\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":53,\"tempM\":12,\"condition\":\"Clear\",\"feelsLikeF\":53,\"feelsLikeM\":12,\"humidity\":93,\"epoch\":1479733200,\"dewPointEnglish\":51,\"dewPointMetric\":11,\"sky\":25,\"windSpeedEnglish\":3,\"windSpeedMetric\":5,\"windDirection\":\"NW\",\"windDegrees\":316,\"wx\":\"Mostly Clear\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":8,\"mslpEnglish\":29.98,\"mslpMetric\":1015}," +
            "{\"hour\":6,\"hourPadded\":\"06\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":53,\"tempM\":12,\"condition\":\"Clear\",\"feelsLikeF\":53,\"feelsLikeM\":12,\"humidity\":92,\"epoch\":1479736800,\"dewPointEnglish\":50,\"dewPointMetric\":10,\"sky\":19,\"windSpeedEnglish\":3,\"windSpeedMetric\":5,\"windDirection\":\"NW\",\"windDegrees\":316,\"wx\":\"Clear\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":8,\"mslpEnglish\":29.99,\"mslpMetric\":1016}," +
            "{\"hour\":7,\"hourPadded\":\"07\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":53,\"tempM\":12,\"condition\":\"Mostly Cloudy\",\"feelsLikeF\":53,\"feelsLikeM\":12,\"humidity\":92,\"epoch\":1479740400,\"dewPointEnglish\":50,\"dewPointMetric\":10,\"sky\":69,\"windSpeedEnglish\":4,\"windSpeedMetric\":6,\"windDirection\":\"NW\",\"windDegrees\":318,\"wx\":\"Mostly Cloudy\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":8,\"mslpEnglish\":30.0,\"mslpMetric\":1016}," +
            "{\"hour\":8,\"hourPadded\":\"08\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":54,\"tempM\":12,\"condition\":\"Clear\",\"feelsLikeF\":54,\"feelsLikeM\":12,\"humidity\":89,\"epoch\":1479744000,\"dewPointEnglish\":51,\"dewPointMetric\":11,\"sky\":17,\"windSpeedEnglish\":4,\"windSpeedMetric\":6,\"windDirection\":\"NNW\",\"windDegrees\":331,\"wx\":\"Sunny\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":7,\"mslpEnglish\":30.03,\"mslpMetric\":1017}," +
            "{\"hour\":9,\"hourPadded\":\"09\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":55,\"tempM\":13,\"condition\":\"Clear\",\"feelsLikeF\":55,\"feelsLikeM\":13,\"humidity\":87,\"epoch\":1479747600,\"dewPointEnglish\":52,\"dewPointMetric\":11,\"sky\":19,\"windSpeedEnglish\":5,\"windSpeedMetric\":8,\"windDirection\":\"NNW\",\"windDegrees\":328,\"wx\":\"Sunny\",\"uvi\":1,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":6,\"mslpEnglish\":30.05,\"mslpMetric\":1018}," +
            "{\"hour\":10,\"hourPadded\":\"10\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":56,\"tempM\":13,\"condition\":\"Clear\",\"feelsLikeF\":56,\"feelsLikeM\":13,\"humidity\":83,\"epoch\":1479751200,\"dewPointEnglish\":51,\"dewPointMetric\":11,\"sky\":21,\"windSpeedEnglish\":5,\"windSpeedMetric\":8,\"windDirection\":\"NNW\",\"windDegrees\":329,\"wx\":\"Mostly Sunny\",\"uvi\":2,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":4,\"mslpEnglish\":30.06,\"mslpMetric\":1018}," +
            "{\"hour\":11,\"hourPadded\":\"11\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":57,\"tempM\":14,\"condition\":\"Mostly Cloudy\",\"feelsLikeF\":57,\"feelsLikeM\":14,\"humidity\":81,\"epoch\":1479754800,\"dewPointEnglish\":51,\"dewPointMetric\":11,\"sky\":67,\"windSpeedEnglish\":6,\"windSpeedMetric\":10,\"windDirection\":\"NW\",\"windDegrees\":322,\"wx\":\"Mostly Cloudy\",\"uvi\":2,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":3,\"mslpEnglish\":30.06,\"mslpMetric\":1018}," +
            "{\"hour\":12,\"hourPadded\":\"12\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":58,\"tempM\":14,\"condition\":\"Partly Cloudy\",\"feelsLikeF\":58,\"feelsLikeM\":14,\"humidity\":77,\"epoch\":1479758400,\"dewPointEnglish\":51,\"dewPointMetric\":11,\"sky\":49,\"windSpeedEnglish\":8,\"windSpeedMetric\":13,\"windDirection\":\"NW\",\"windDegrees\":309,\"wx\":\"Partly Cloudy\",\"uvi\":3,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":3,\"mslpEnglish\":30.06,\"mslpMetric\":1018}," +
            "{\"hour\":13,\"hourPadded\":\"13\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":59,\"tempM\":15,\"condition\":\"Partly Cloudy\",\"feelsLikeF\":59,\"feelsLikeM\":15,\"humidity\":75,\"epoch\":1479762000,\"dewPointEnglish\":51,\"dewPointMetric\":11,\"sky\":52,\"windSpeedEnglish\":10,\"windSpeedMetric\":16,\"windDirection\":\"WNW\",\"windDegrees\":295,\"wx\":\"Partly Cloudy\",\"uvi\":2,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":3,\"mslpEnglish\":30.06,\"mslpMetric\":1018}," +
            "{\"hour\":14,\"hourPadded\":\"14\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":59,\"tempM\":15,\"condition\":\"Partly Cloudy\",\"feelsLikeF\":59,\"feelsLikeM\":15,\"humidity\":73,\"epoch\":1479765600,\"dewPointEnglish\":51,\"dewPointMetric\":11,\"sky\":47,\"windSpeedEnglish\":10,\"windSpeedMetric\":16,\"windDirection\":\"WNW\",\"windDegrees\":295,\"wx\":\"Partly Cloudy\",\"uvi\":2,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":3,\"mslpEnglish\":30.06,\"mslpMetric\":1018}," +
            "{\"hour\":15,\"hourPadded\":\"15\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":59,\"tempM\":15,\"condition\":\"Partly Cloudy\",\"feelsLikeF\":59,\"feelsLikeM\":15,\"humidity\":74,\"epoch\":1479769200,\"dewPointEnglish\":51,\"dewPointMetric\":11,\"sky\":41,\"windSpeedEnglish\":11,\"windSpeedMetric\":18,\"windDirection\":\"WNW\",\"windDegrees\":288,\"wx\":\"Partly Cloudy\",\"uvi\":1,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":4,\"mslpEnglish\":30.06,\"mslpMetric\":1018}," +
            "{\"hour\":16,\"hourPadded\":\"16\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":59,\"tempM\":15,\"condition\":\"Clear\",\"feelsLikeF\":59,\"feelsLikeM\":15,\"humidity\":73,\"epoch\":1479772800,\"dewPointEnglish\":51,\"dewPointMetric\":11,\"sky\":24,\"windSpeedEnglish\":11,\"windSpeedMetric\":18,\"windDirection\":\"WNW\",\"windDegrees\":290,\"wx\":\"Mostly Sunny\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":4,\"mslpEnglish\":30.07,\"mslpMetric\":1018},{\"hour\":17,\"hourPadded\":\"17\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":58,\"tempM\":14,\"condition\":\"Clear\",\"feelsLikeF\":58,\"feelsLikeM\":14,\"humidity\":75,\"epoch\":1479776400,\"dewPointEnglish\":50,\"dewPointMetric\":10,\"sky\":13,\"windSpeedEnglish\":11,\"windSpeedMetric\":18,\"windDirection\":\"WNW\",\"windDegrees\":292,\"wx\":\"Clear\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":5,\"mslpEnglish\":30.09,\"mslpMetric\":1019},{\"hour\":18,\"hourPadded\":\"18\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":57,\"tempM\":14,\"condition\":\"Clear\",\"feelsLikeF\":57,\"feelsLikeM\":14,\"humidity\":77,\"epoch\":1479780000,\"dewPointEnglish\":50,\"dewPointMetric\":10,\"sky\":24,\"windSpeedEnglish\":10,\"windSpeedMetric\":16,\"windDirection\":\"WNW\",\"windDegrees\":294,\"wx\":\"Mostly Clear\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":5,\"mslpEnglish\":30.1,\"mslpMetric\":1019},{\"hour\":19,\"hourPadded\":\"19\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":57,\"tempM\":14,\"condition\":\"Clear\",\"feelsLikeF\":57,\"feelsLikeM\":14,\"humidity\":76,\"epoch\":1479783600,\"dewPointEnglish\":49,\"dewPointMetric\":9,\"sky\":29,\"windSpeedEnglish\":9,\"windSpeedMetric\":14,\"windDirection\":\"WNW\",\"windDegrees\":298,\"wx\":\"Mostly Clear\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":5,\"mslpEnglish\":30.11,\"mslpMetric\":1020},{\"hour\":20,\"hourPadded\":\"20\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":56,\"tempM\":13,\"condition\":\"Clear\",\"feelsLikeF\":56,\"feelsLikeM\":13,\"humidity\":76,\"epoch\":1479787200,\"dewPointEnglish\":48,\"dewPointMetric\":9,\"sky\":26,\"windSpeedEnglish\":8,\"windSpeedMetric\":13,\"windDirection\":\"WNW\",\"windDegrees\":302,\"wx\":\"Mostly Clear\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":6,\"mslpEnglish\":30.13,\"mslpMetric\":1020},{\"hour\":21,\"hourPadded\":\"21\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":55,\"tempM\":13,\"condition\":\"Clear\",\"feelsLikeF\":55,\"feelsLikeM\":13,\"humidity\":77,\"epoch\":1479790800,\"dewPointEnglish\":48,\"dewPointMetric\":9,\"sky\":22,\"windSpeedEnglish\":6,\"windSpeedMetric\":10,\"windDirection\":\"WNW\",\"windDegrees\":303,\"wx\":\"Mostly Clear\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":11,\"mslpEnglish\":30.14,\"mslpMetric\":1021},{\"hour\":22,\"hourPadded\":\"22\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":54,\"tempM\":12,\"condition\":\"Clear\",\"feelsLikeF\":54,\"feelsLikeM\":12,\"humidity\":77,\"epoch\":1479794400,\"dewPointEnglish\":47,\"dewPointMetric\":8,\"sky\":20,\"windSpeedEnglish\":5,\"windSpeedMetric\":8,\"windDirection\":\"NW\",\"windDegrees\":304,\"wx\":\"Mostly Clear\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":5,\"mslpEnglish\":30.15,\"mslpMetric\":1021},{\"hour\":23,\"hourPadded\":\"23\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":21,\"dayPadded\":\"21\",\"tempF\":53,\"tempM\":12,\"condition\":\"Clear\",\"feelsLikeF\":53,\"feelsLikeM\":12,\"humidity\":80,\"epoch\":1479798000,\"dewPointEnglish\":47,\"dewPointMetric\":8,\"sky\":21,\"windSpeedEnglish\":4,\"windSpeedMetric\":6,\"windDirection\":\"NW\",\"windDegrees\":305,\"wx\":\"Mostly Clear\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":5,\"mslpEnglish\":30.16,\"mslpMetric\":1021},{\"hour\":0,\"hourPadded\":\"00\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":22,\"dayPadded\":\"22\",\"tempF\":53,\"tempM\":12,\"condition\":\"Clear\",\"feelsLikeF\":53,\"feelsLikeM\":12,\"humidity\":82,\"epoch\":1479801600,\"dewPointEnglish\":47,\"dewPointMetric\":8,\"sky\":23,\"windSpeedEnglish\":3,\"windSpeedMetric\":5,\"windDirection\":\"WNW\",\"windDegrees\":300,\"wx\":\"Mostly Clear\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":7,\"mslpEnglish\":30.16,\"mslpMetric\":1021},{\"hour\":1,\"hourPadded\":\"01\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":22,\"dayPadded\":\"22\",\"tempF\":52,\"tempM\":11,\"condition\":\"Clear\",\"feelsLikeF\":52,\"feelsLikeM\":11,\"humidity\":82,\"epoch\":1479805200,\"dewPointEnglish\":47,\"dewPointMetric\":8,\"sky\":24,\"windSpeedEnglish\":3,\"windSpeedMetric\":5,\"windDirection\":\"WNW\",\"windDegrees\":294,\"wx\":\"Mostly Clear\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":7,\"mslpEnglish\":30.16,\"mslpMetric\":1021},{\"hour\":2,\"hourPadded\":\"02\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":22,\"dayPadded\":\"22\",\"tempF\":52,\"tempM\":11,\"condition\":\"Clear\",\"feelsLikeF\":52,\"feelsLikeM\":11,\"humidity\":84,\"epoch\":1479808800,\"dewPointEnglish\":47,\"dewPointMetric\":8,\"sky\":23,\"windSpeedEnglish\":3,\"windSpeedMetric\":5,\"windDirection\":\"WNW\",\"windDegrees\":287,\"wx\":\"Mostly Clear\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":8,\"mslpEnglish\":30.16,\"mslpMetric\":1021},{\"hour\":3,\"hourPadded\":\"03\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":22,\"dayPadded\":\"22\",\"tempF\":51,\"tempM\":11,\"condition\":\"Clear\",\"feelsLikeF\":51,\"feelsLikeM\":11,\"humidity\":85,\"epoch\":1479812400,\"dewPointEnglish\":47,\"dewPointMetric\":8,\"sky\":23,\"windSpeedEnglish\":3,\"windSpeedMetric\":5,\"windDirection\":\"W\",\"windDegrees\":277,\"wx\":\"Mostly Clear\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":13,\"mslpEnglish\":30.16,\"mslpMetric\":1021},{\"hour\":4,\"hourPadded\":\"04\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":22,\"dayPadded\":\"22\",\"tempF\":51,\"tempM\":11,\"condition\":\"Clear\",\"feelsLikeF\":51,\"feelsLikeM\":11,\"humidity\":86,\"epoch\":1479816000,\"dewPointEnglish\":47,\"dewPointMetric\":8,\"sky\":24,\"windSpeedEnglish\":2,\"windSpeedMetric\":3,\"windDirection\":\"W\",\"windDegrees\":271,\"wx\":\"Mostly Clear\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":7,\"mslpEnglish\":30.16,\"mslpMetric\":1021},{\"hour\":5,\"hourPadded\":\"05\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":22,\"dayPadded\":\"22\",\"tempF\":50,\"tempM\":10,\"condition\":\"Clear\",\"feelsLikeF\":50,\"feelsLikeM\":10,\"humidity\":89,\"epoch\":1479819600,\"dewPointEnglish\":47,\"dewPointMetric\":8,\"sky\":26,\"windSpeedEnglish\":2,\"windSpeedMetric\":3,\"windDirection\":\"WSW\",\"windDegrees\":249,\"wx\":\"Mostly Clear\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":7,\"mslpEnglish\":30.16,\"mslpMetric\":1021},{\"hour\":6,\"hourPadded\":\"06\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":22,\"dayPadded\":\"22\",\"tempF\":50,\"tempM\":10,\"condition\":\"Mostly Cloudy\",\"feelsLikeF\":50,\"feelsLikeM\":10,\"humidity\":90,\"epoch\":1479823200,\"dewPointEnglish\":47,\"dewPointMetric\":8,\"sky\":62,\"windSpeedEnglish\":2,\"windSpeedMetric\":3,\"windDirection\":\"SW\",\"windDegrees\":214,\"wx\":\"Mostly Cloudy\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":7,\"mslpEnglish\":30.17,\"mslpMetric\":1022},{\"hour\":7,\"hourPadded\":\"07\",\"min\":0,\"minPadded\":\"00\",\"year\":2016,\"month\":11,\"monthPadded\":\"11\",\"day\":22,\"dayPadded\":\"22\",\"tempF\":50,\"tempM\":10,\"condition\":\"Mostly Cloudy\",\"feelsLikeF\":50,\"feelsLikeM\":10,\"humidity\":92,\"epoch\":1479826800,\"dewPointEnglish\":48,\"dewPointMetric\":9,\"sky\":70,\"windSpeedEnglish\":2,\"windSpeedMetric\":3,\"windDirection\":\"SSE\",\"windDegrees\":164,\"wx\":\"Mostly Cloudy\",\"uvi\":0,\"windChillEnglish\":-9999,\"windChillMetric\":-9999,\"heatIndexEnglish\":-9999,\"heatIndexMetric\":-9999,\"qpfEnglish\":0.0,\"qpfMetric\":0,\"snowEnglish\":0.0,\"snowMetric\":0,\"pop\":7,\"mslpEnglish\":30.18,\"mslpMetric\":1022}]";

    @Override
    public InputStream getWeatherJsonInputStream() throws IOException {
        return new ByteArrayInputStream(JSON.getBytes("UTF-8"));
    }
}