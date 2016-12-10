package com.miryor.jawn;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.miryor.jawn.model.HourlyForecast;
import com.miryor.jawn.model.Notifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by royrim on 12/2/16.
 */

public class NotificationPublisher extends WakefulBroadcastReceiver {

    private static final String WUNDERGROUND_URL = "http://api.wunderground.com/api/502f7c0bd4a4257d/hourly/q/";
    private static final String JSON_URL = ".json";

    @Override
    public void onReceive(Context context, Intent intent) {
        Notifier notifier = intent.getParcelableExtra( Notifier.EXTRA_NAME );
        Log.d( "JAWN", "Received alarm for " + notifier.getPostalCode() );
        Intent service = new Intent(context, WeatherNotificationIntentService.class);
        service.putExtra( Notifier.EXTRA_NAME, notifier );
        startWakefulService(context, service);
        setResultCode(Activity.RESULT_OK);

        /*NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notifier notifier = (Notifier) intent.getParcelableExtra(Notifier.EXTRA_NAME);

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
            if (provider.equals(JawnContract.WEATHER_API_PROVIDER_WUNDERGROUND)) {
                Log.d("JAWN", "Getting weather from: " + WUNDERGROUND_URL + zipCode + JSON_URL);
                new DownloadWeatherTask(context,notifier).execute(WUNDERGROUND_URL + zipCode + JSON_URL);
            } else {
                notifyError(context, "Wrong provider set: " + provider + ", could not download weather");
            }
        }
        else {
            Log.d( "JAWN", "Notification not set for " + calDayOfWeek );
        }*/
    }

    private void notifyError(Context context, String error) {
        Utils.sendNotification(context, error);
    }

    private class DownloadWeatherTask extends AsyncTask<String, Void, String> {
        Context context;
        Notifier notifier;
        private DownloadWeatherTask( Context context, Notifier notifier ) {
            this.context = context;
            this.notifier = notifier;
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                return loadJsonFromNetwork(context, notifier, urls[0]);
            }
            catch (IOException e) {
                Log.e( "JAWN", context.getResources().getString(R.string.connection_error), e );
                return context.getResources().getString(R.string.connection_error);
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Utils.sendNotification(context, result);
        }
    }


    private String loadJsonFromNetwork(Context context, Notifier notifier, String url) throws IOException {
        WundergroundWeatherJsonGrabber g = new WundergroundWeatherJsonGrabber(url);
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
        WundergroundWeatherJsonParser p = new WundergroundWeatherJsonParser( forecast );
        List<HourlyForecast> list = p.parseHourlyForecast();
        builder = new StringBuilder();
        for ( HourlyForecast hf : list ) {
            if ( builder.length() > 0 ) builder.append( ", " );
            JawnContract.formatForecastForNotification(builder, hf);
        }
        return builder.toString();
    }

}
