package com.miryor.jawn.model;

/**
 * Created by royrim on 11/20/16.
 */

public class HourlyForecast {

    private int hour;
    private String hourPadded;
    private int min;
    private String minPadded;
    private int year;
    private int month;
    private String monthPadded;
    private int day;
    private String dayPadded;
    private int tempF;
    private int tempM;
    private String condition;
    private int feelsLikeF;
    private int feelsLikeM;
    private int humidity;
    private long epoch;

    private int dewPointEnglish;
    private int dewPointMetric;
    private int sky;
    private int windSpeedEnglish;
    private int windSpeedMetric;
    private String windDirection;
    private int windDegrees;
    private String wx;
    private int uvi;
    private int windChillEnglish;
    private int windChillMetric;
    private int heatIndexEnglish;
    private int heatIndexMetric;
    private double qpfEnglish;
    private int qpfMetric;
    private double snowEnglish;
    private int snowMetric;
    private int pop;
    private double mslpEnglish;
    private int mslpMetric;

    public long getEpoch() {
        return epoch;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getHourPadded() {
        return hourPadded;
    }

    public void setHourPadded(String hourPadded) {
        this.hourPadded = hourPadded;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getMinPadded() {
        return minPadded;
    }

    public void setMinPadded(String minPadded) {
        this.minPadded = minPadded;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getMonthPadded() {
        return monthPadded;
    }

    public void setMonthPadded(String monthPadded) {
        this.monthPadded = monthPadded;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDayPadded() {
        return dayPadded;
    }

    public void setDayPadded(String dayPadded) {
        this.dayPadded = dayPadded;
    }

    public int getTempF() {
        return tempF;
    }

    public void setTempF(int tempF) {
        this.tempF = tempF;
    }

    public int getTempM() {
        return tempM;
    }

    public void setTempM(int tempM) {
        this.tempM = tempM;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getFeelsLikeF() {
        return feelsLikeF;
    }

    public void setFeelsLikeF(int feelsLikeF) {
        this.feelsLikeF = feelsLikeF;
    }

    public int getFeelsLikeM() {
        return feelsLikeM;
    }

    public void setFeelsLikeM(int feelsLikeM) {
        this.feelsLikeM = feelsLikeM;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getDewPointEnglish() {
        return dewPointEnglish;
    }

    public void setDewPointEnglish(int dewPointEnglish) {
        this.dewPointEnglish = dewPointEnglish;
    }

    public int getDewPointMetric() {
        return dewPointMetric;
    }

    public void setDewPointMetric(int dewPointMetric) {
        this.dewPointMetric = dewPointMetric;
    }

    public int getSky() {
        return sky;
    }

    public void setSky(int sky) {
        this.sky = sky;
    }

    public int getWindSpeedEnglish() {
        return windSpeedEnglish;
    }

    public void setWindSpeedEnglish(int windSpeedEnglish) {
        this.windSpeedEnglish = windSpeedEnglish;
    }

    public int getWindSpeedMetric() {
        return windSpeedMetric;
    }

    public void setWindSpeedMetric(int windSpeedMetric) {
        this.windSpeedMetric = windSpeedMetric;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public int getWindDegrees() {
        return windDegrees;
    }

    public void setWindDegrees(int windDegrees) {
        this.windDegrees = windDegrees;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public int getUvi() {
        return uvi;
    }

    public void setUvi(int uvi) {
        this.uvi = uvi;
    }

    public int getWindChillEnglish() {
        return windChillEnglish;
    }

    public void setWindChillEnglish(int windChillEnglish) {
        this.windChillEnglish = windChillEnglish;
    }

    public int getWindChillMetric() {
        return windChillMetric;
    }

    public void setWindChillMetric(int windChillMetric) {
        this.windChillMetric = windChillMetric;
    }

    public int getHeatIndexEnglish() {
        return heatIndexEnglish;
    }

    public void setHeatIndexEnglish(int heatIndexEnglish) {
        this.heatIndexEnglish = heatIndexEnglish;
    }

    public int getHeatIndexMetric() {
        return heatIndexMetric;
    }

    public void setHeatIndexMetric(int heatIndexMetric) {
        this.heatIndexMetric = heatIndexMetric;
    }

    public double getQpfEnglish() {
        return qpfEnglish;
    }

    public void setQpfEnglish(double qpfEnglish) {
        this.qpfEnglish = qpfEnglish;
    }

    public int getQpfMetric() {
        return qpfMetric;
    }

    public void setQpfMetric(int qpfMetric) {
        this.qpfMetric = qpfMetric;
    }

    public double getSnowEnglish() {
        return snowEnglish;
    }

    public void setSnowEnglish(double snowEnglish) {
        this.snowEnglish = snowEnglish;
    }

    public int getSnowMetric() {
        return snowMetric;
    }

    public void setSnowMetric(int snowMetric) {
        this.snowMetric = snowMetric;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public double getMslpEnglish() {
        return mslpEnglish;
    }

    public void setMslpEnglish(double mslpEnglish) {
        this.mslpEnglish = mslpEnglish;
    }

    public int getMslpMetric() {
        return mslpMetric;
    }

    public void setMslpMetric(int mslpMetric) {
        this.mslpMetric = mslpMetric;
    }

    public HourlyForecast(HourlyForecastBuilder b) {
        hour = b.hour;
        hourPadded = b.hourPadded;
        min = b.min;
        minPadded = b.minPadded;
        year = b.year;
        month = b.month;
        monthPadded = b.monthPadded;
        day = b.day;
        dayPadded = b.dayPadded;
        tempF = b.tempF;
        tempM = b.tempM;
        condition = b.condition;
        feelsLikeF = b.feelsLikeF;
        feelsLikeM = b.feelsLikeM;
        humidity = b.humidity;
        epoch = b.epoch;

        dewPointEnglish = b.dewPointEnglish;
        dewPointMetric = b.dewPointMetric;
        sky = b.sky;
        windSpeedEnglish = b.windSpeedEnglish;
        windSpeedMetric = b.windSpeedMetric;
        windDirection = b.windDirection;
        windDegrees = b.windDegrees;
        wx = b.wx;
        uvi = b.uvi;
        windChillEnglish = b.windChillEnglish;
        windChillMetric = b.windChillMetric;
        heatIndexEnglish = b.heatIndexEnglish;
        heatIndexMetric = b.heatIndexMetric;
        qpfEnglish = b.qpfEnglish;
        qpfMetric = b.qpfMetric;
        snowEnglish = b.snowEnglish;
        snowMetric = b.snowMetric;
        pop = b.pop;
        mslpEnglish = b.mslpEnglish;
        mslpMetric = b.mslpMetric;
    }

    @Override
    public String toString() {
        return monthPadded + "/" + dayPadded + " " + tempF;
    }

    public static class HourlyForecastBuilder {
        private int hour;
        private String hourPadded;
        private int min;
        private String minPadded;
        private int year;
        private int month;
        private String monthPadded;
        private int day;
        private String dayPadded;
        private int tempF;
        private int tempM;
        private String condition;
        private int feelsLikeF;
        private int feelsLikeM;
        private int humidity;
        private long epoch;

        private int dewPointEnglish;
        private int dewPointMetric;
        private int sky;
        private int windSpeedEnglish;
        private int windSpeedMetric;
        private String windDirection;
        private int windDegrees;
        private String wx;
        private int uvi;
        private int windChillEnglish;
        private int windChillMetric;
        private int heatIndexEnglish;
        private int heatIndexMetric;
        private double qpfEnglish;
        private int qpfMetric;
        private double snowEnglish;
        private int snowMetric;
        private int pop;
        private double mslpEnglish;
        private int mslpMetric;

        public int getHour() {
            return hour;
        }

        public String getHourPadded() {
            return hourPadded;
        }

        public int getMin() {
            return min;
        }

        public String getMinPadded() {
            return minPadded;
        }

        public int getYear() {
            return year;
        }

        public int getMonth() {
            return month;
        }

        public String getMonthPadded() {
            return monthPadded;
        }

        public int getDay() {
            return day;
        }

        public String getDayPadded() {
            return dayPadded;
        }

        public int getTempF() {
            return tempF;
        }

        public int getTempM() {
            return tempM;
        }

        public String getCondition() {
            return condition;
        }

        public int getFeelsLikeF() {
            return feelsLikeF;
        }

        public int getFeelsLikeM() {
            return feelsLikeM;
        }

        public int getHumidity() {
            return humidity;
        }

        public int getDewPointEnglish() {
            return dewPointEnglish;
        }

        public void setDewPointEnglish(int dewPointEnglish) {
            this.dewPointEnglish = dewPointEnglish;
        }

        public int getDewPointMetric() {
            return dewPointMetric;
        }

        public void setDewPointMetric(int dewPointMetric) {
            this.dewPointMetric = dewPointMetric;
        }

        public int getSky() {
            return sky;
        }

        public void setSky(int sky) {
            this.sky = sky;
        }

        public int getWindSpeedEnglish() {
            return windSpeedEnglish;
        }

        public void setWindSpeedEnglish(int windSpeedEnglish) {
            this.windSpeedEnglish = windSpeedEnglish;
        }

        public int getWindSpeedMetric() {
            return windSpeedMetric;
        }

        public void setWindSpeedMetric(int windSpeedMetric) {
            this.windSpeedMetric = windSpeedMetric;
        }

        public String getWindDirection() {
            return windDirection;
        }

        public void setWindDirection(String windDirection) {
            this.windDirection = windDirection;
        }

        public int getWindDegrees() {
            return windDegrees;
        }

        public void setWindDegrees(int windDegrees) {
            this.windDegrees = windDegrees;
        }

        public String getWx() {
            return wx;
        }

        public void setWx(String wx) {
            this.wx = wx;
        }

        public int getUvi() {
            return uvi;
        }

        public void setUvi(int uvi) {
            this.uvi = uvi;
        }

        public int getWindChillEnglish() {
            return windChillEnglish;
        }

        public void setWindChillEnglish(int windChillEnglish) {
            this.windChillEnglish = windChillEnglish;
        }

        public int getWindChillMetric() {
            return windChillMetric;
        }

        public void setWindChillMetric(int windChillMetric) {
            this.windChillMetric = windChillMetric;
        }

        public int getHeatIndexEnglish() {
            return heatIndexEnglish;
        }

        public void setHeatIndexEnglish(int heatIndexEnglish) {
            this.heatIndexEnglish = heatIndexEnglish;
        }

        public int getHeatIndexMetric() {
            return heatIndexMetric;
        }

        public void setHeatIndexMetric(int heatIndexMetric) {
            this.heatIndexMetric = heatIndexMetric;
        }

        public double getQpfEnglish() {
            return qpfEnglish;
        }

        public void setQpfEnglish(double qpfEnglish) {
            this.qpfEnglish = qpfEnglish;
        }

        public int getQpfMetric() {
            return qpfMetric;
        }

        public void setQpfMetric(int qpfMetric) {
            this.qpfMetric = qpfMetric;
        }

        public double getSnowEnglish() {
            return snowEnglish;
        }

        public void setSnowEnglish(double snowEnglish) {
            this.snowEnglish = snowEnglish;
        }

        public int getSnowMetric() {
            return snowMetric;
        }

        public void setSnowMetric(int snowMetric) {
            this.snowMetric = snowMetric;
        }

        public int getPop() {
            return pop;
        }

        public void setPop(int pop) {
            this.pop = pop;
        }

        public double getMslpEnglish() {
            return mslpEnglish;
        }

        public void setMslpEnglish(double mslpEnglish) {
            this.mslpEnglish = mslpEnglish;
        }

        public int getMslpMetric() {
            return mslpMetric;
        }

        public void setMslpMetric(int mslpMetric) {
            this.mslpMetric = mslpMetric;
        }

        public long getEpoch() {
            return epoch;
        }

        public void setEpoch(long epoch) {
            this.epoch = epoch;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public void setHourPadded(String hourPadded) {
            this.hourPadded = hourPadded;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public void setMinPadded(String minPadded) {
            this.minPadded = minPadded;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public void setMonthPadded(String monthPadded) {
            this.monthPadded = monthPadded;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public void setDayPadded(String dayPadded) {
            this.dayPadded = dayPadded;
        }

        public void setTempF(int tempF) {
            this.tempF = tempF;
        }

        public void setTempM(int tempM) {
            this.tempM = tempM;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public void setFeelsLikeF(int feelsLikeF) {
            this.feelsLikeF = feelsLikeF;
        }

        public void setFeelsLikeM(int feelsLikeM) {
            this.feelsLikeM = feelsLikeM;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public HourlyForecast build() {
            HourlyForecast f = new HourlyForecast(this);
            return f;
        }
    }
}
