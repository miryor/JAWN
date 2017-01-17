package com.miryor.jawn;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.miryor.jawn.model.HourlyForecast;
import com.miryor.jawn.model.Notifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;

/**
 * Created by royrim on 12/9/16.
 */

public class WeatherNotificationIntentService extends IntentService {

    public WeatherNotificationIntentService() {
        super( "WeatherNoticationIntentService" );
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d( "JAWN", "onHandleIntent " + intent.getClass().getName() );

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notifier notifier = (Notifier) intent.getParcelableExtra(Notifier.EXTRA_NAME);

        Log.d( "JAWN", "WeatherNotificationIntentService to get weather for " + notifier.getPostalCode() );

        String provider = notifier.getProvider();
        String zipCode = notifier.getPostalCode();
        int dow = notifier.getDaysOfWeek();
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
            if (
                    provider.equals(JawnContract.WEATHER_API_PROVIDER_JAWNREST) ||
                    provider.equals(JawnContract.WEATHER_API_PROVIDER_WUNDERGROUND)
            ) {
                try {
                    String result = loadJsonFromNetwork(this, notifier, provider, zipCode);
                    Utils.sendNotification(this, result);
                }
                catch (IOException e) {
                    Log.e( "JAWN", getResources().getString(R.string.connection_error), e );
                    Utils.sendNotification(this, getResources().getString(R.string.connection_error));
                }
            }
            else {
                Utils.sendNotification(this, "Wrong provider set: " + provider + ", could not download weather");
            }
        }
        else {
            Log.d( "JAWN", "Notification not set for " + calDayOfWeek );
        }

        NotificationPublisher.completeWakefulIntent(intent);
    }

    private String loadJsonFromNetwork(Context context, Notifier notifier, String provider, String location) throws IOException {
        WeatherJsonGrabber g = null;
        if ( provider.equals(JawnContract.WEATHER_API_PROVIDER_JAWNREST) ) g = new JawnRestWeatherJsonGrabber(location);
        else g = new WundergroundWeatherJsonGrabber(location);

        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(g.getWeatherJsonInputStream(), "UTF-8"));
            char[] chars = new char[1024];
            int len;
            while ((len = reader.read(chars)) != -1) {
                builder.append(chars, 0, len);
            }
        }
        finally {
            if ( reader != null ) try { reader.close(); } catch ( IOException e ) {}
        }
        String forecast = builder.toString();
        notifier.setForecast( forecast );
        JawnContract.updateNotifierForecast(context, notifier);

        WeatherJsonParser p = null;
        if ( provider.equals(JawnContract.WEATHER_API_PROVIDER_JAWNREST) ) p = new JawnRestWeatherJsonParser( forecast );
        else p = new WundergroundWeatherJsonParser( forecast );

        List<HourlyForecast> list = p.parseHourlyForecast();
        builder = new StringBuilder();
        for ( HourlyForecast hf : list ) {
            if ( builder.length() > 0 ) builder.append( ", " );
            JawnContract.formatForecastForNotification(builder, hf);
        }
        return builder.toString();
    }

}
