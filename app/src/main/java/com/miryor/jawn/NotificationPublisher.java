package com.miryor.jawn;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.miryor.jawn.model.HourlyForecast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by royrim on 12/2/16.
 */

public class NotificationPublisher extends BroadcastReceiver {

    public static final String WEATHER_API_PROVIDER = "WEATHER_API_PROVIDER";
    public static final String WEATHER_API_PROVIDER_WUNDERGROUND = "WUNDERGROUND";
    private static final String WUNDERGROUND_URL = "http://api.wunderground.com/api/502f7c0bd4a4257d/hourly/q/";
    private static final String JSON_URL = ".json";
    private static final int NOTIFICATION_ID = 15968902;

    public static final String PASSED_POSTALCODE = "PASSED_POSTALCODE";
    public static final String PASSED_DOW = "PASSED_DOW";
    public static final String PASSED_HOUR = "PASSED_HOUR";
    public static final String PASSED_MINUTE = "PASSED_MINUTE";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String provider = intent.getStringExtra( WEATHER_API_PROVIDER );
        String zipCode = intent.getStringExtra(PASSED_POSTALCODE);
        int dow = intent.getIntExtra(PASSED_DOW,0);
        Calendar cal = Calendar.getInstance();
        int calDayOfWeek = cal.get( Calendar.DAY_OF_WEEK );
        if (
                ( calDayOfWeek == Calendar.SUNDAY && JawnContract.isDayOfWeek( dow, JawnContract.DOW_SUNDAY ) ) ||
                ( calDayOfWeek == Calendar.MONDAY && JawnContract.isDayOfWeek( dow, JawnContract.DOW_MONDAY ) ) ||
                ( calDayOfWeek == Calendar.TUESDAY && JawnContract.isDayOfWeek( dow, JawnContract.DOW_TUESDAY ) ) ||
                ( calDayOfWeek == Calendar.WEDNESDAY && JawnContract.isDayOfWeek( dow, JawnContract.DOW_WEDNESDAY ) ) ||
                ( calDayOfWeek == Calendar.THURSDAY && JawnContract.isDayOfWeek( dow, JawnContract.DOW_THURSDAY ) ) ||
                ( calDayOfWeek == Calendar.FRIDAY && JawnContract.isDayOfWeek( dow, JawnContract.DOW_FRIDAY ) ) ||
                ( calDayOfWeek == Calendar.SATURDAY && JawnContract.isDayOfWeek( dow, JawnContract.DOW_SATURDAY ) )
        ){
            if (provider.equals(WEATHER_API_PROVIDER_WUNDERGROUND)) {
                Log.d("JAWN", "Getting weather from: " + WUNDERGROUND_URL + zipCode + JSON_URL);
                new DownloadWeatherTask(context).execute(WUNDERGROUND_URL + zipCode + JSON_URL);
            } else {
                notifyError(context, "Wrong provider set: " + provider + ", could not download weather");
            }
        }
        else {
            Log.d( "JAWN", "Notification not set for " + calDayOfWeek );
        }
    }

    private void notifyError(Context context, String error) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context);
        mBuilder
                .setSmallIcon(R.drawable.ic_wb_sunny_black_24dp)
                .setContentTitle("JAWN ERROR")
                .setContentText(error);

        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(NOTIFICATION_ID, mBuilder.build());
    }

    private class DownloadWeatherTask extends AsyncTask<String, Void, String> {
        Context context;
        private DownloadWeatherTask( Context context ) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                return loadJsonFromNetwork(urls[0]);
            }
            catch (IOException e) {
                Log.e( "JAWN", context.getResources().getString(R.string.connection_error), e );
                return context.getResources().getString(R.string.connection_error);
            }
        }

        @Override
        protected void onPostExecute(String result) {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context);
            mBuilder
                    .setSmallIcon(R.drawable.ic_wb_sunny_black_24dp)
                    .setContentTitle("Weather Notification")
                    .setContentText(result);

            // Gets an instance of the NotificationManager service
            NotificationManager mNotifyMgr =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            // Builds the notification and issues it.
            mNotifyMgr.notify(NOTIFICATION_ID, mBuilder.build());
        }
    }


    private String loadJsonFromNetwork(String url) throws IOException {
        WundergroundWeatherJsonGrabber g = new WundergroundWeatherJsonGrabber(url);
        WundergroundWeatherJsonParser p = new WundergroundWeatherJsonParser(g.getWeatherJsonInputStream());
        List<HourlyForecast> list = p.parseHourlyForecast();
        StringBuilder builder = new StringBuilder();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd HH");
        int max = list.size();
        if ( max > 3 ) max = 3;
        for ( int i = 0; i < max; i++ ) {
            HourlyForecast hf = list.get(i);
            String condition = hf.getCondition().toLowerCase();
            boolean found = false;
            for ( int x = 0; x < WeatherJsonParser.STORM_WORDS.length; x++ ) {
                if ( condition.indexOf( WeatherJsonParser.STORM_WORDS[x] ) >= 0 ) {
                    builder.append( WeatherJsonParser.EMOJI_CLOUD_LIGHTNING_RAIN );
                    found = true;
                    break;
                }
            }
            if ( found ) continue;
            for ( int x = 0; x < WeatherJsonParser.CLOUDY_WORDS.length; x++ ) {
                if ( condition.indexOf( WeatherJsonParser.CLOUDY_WORDS[x] ) >= 0 ) {
                    builder.append( WeatherJsonParser.EMOJI_SUN_BEHIND_CLOUD );
                    found = true;
                    break;
                }
            }
            if ( found ) continue;
            for ( int x = 0; x < WeatherJsonParser.SNOW_WORDS.length; x++ ) {
                if ( condition.indexOf( WeatherJsonParser.SNOW_WORDS[x] ) >= 0 ) {
                    builder.append( WeatherJsonParser.EMOJI_SNOWFLAKE );
                    found = true;
                    break;
                }
            }
            if ( found ) continue;
            for ( int x = 0; x < WeatherJsonParser.RAIN_WORDS.length; x++ ) {
                if ( condition.indexOf( WeatherJsonParser.RAIN_WORDS[x] ) >= 0 ) {
                    builder.append( WeatherJsonParser.EMOJI_UMBRELLA );
                    found = true;
                    break;
                }
            }
            if ( found ) continue;
            for ( int x = 0; x < WeatherJsonParser.SUNNY_WORDS.length; x++ ) {
                if ( condition.indexOf( WeatherJsonParser.SUNNY_WORDS[x] ) >= 0 ) {
                    builder.append( WeatherJsonParser.EMOJI_SUN );
                    found = true;
                    break;
                }
            }
            if ( found ) continue;
            for ( int x = 0; x < WeatherJsonParser.CLEAR_WORDS.length; x++ ) {
                if ( condition.indexOf( WeatherJsonParser.CLEAR_WORDS[x] ) >= 0 ) {
                    found = true;
                    break;
                }
            }
            if ( !found) builder.append( WeatherJsonParser.EMOJI_QUESTION );
        }
        if ( builder.length() > 0 ) builder.append( " " );
        for ( int i = 0; i < max; i++ ) {
            HourlyForecast hf = list.get(i);
            if ( i > 0 ) builder.append( ", " );
            builder.append( df.format(new Date(hf.getEpoch()*1000)) );
            builder.append( " " );
            builder.append( hf.getTempF() );
            builder.append( "\u00B0" );
            builder.append( " " );
            builder.append( hf.getCondition() );
        }
        return builder.toString();
    }

}
