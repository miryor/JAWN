package com.miryor.jawn;

import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.JsonReader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.miryor.jawn.R;
import com.miryor.jawn.model.HourlyForecast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String EMOJI_SUN = "\u2600";
    private static String EMOJI_CLOUD = "\u2601";
    private static String EMOJI_SUN_BEHIND_CLOUD = "\u26C5";
    private static String EMOJI_CLOUD_LIGHTNING_RAIN = "\u26C8";
    /*
    private static String EMOJI_CLOUD_SNOW = "\uD83C\uDF28";
    private static String EMOJI_CLOUD_RAIN = "\uD83C\uDF27";
    private static String EMOJI_SUN_BEHIND_RAIN_CLOUD = "\uD83C\uDF26";
    */
    private static final String URL = "http://api.wunderground.com/api/502f7c0bd4a4257d/hourly/q/CA/San_Francisco.json";

    static final String[] PENS = new String[]{
            "MONT Blanc",
            "Gucci ",
            "Parker",
            "Sailor",
            "Porsche Design",
            "Rotring",
            "Sheaffer",
            "Waterman",
            EMOJI_SUN + " " + EMOJI_CLOUD + " " +
                EMOJI_SUN_BEHIND_CLOUD + " " + EMOJI_CLOUD_LIGHTNING_RAIN,
            /*EMOJI_CLOUD_LIGHTNING_RAIN + " " + EMOJI_CLOUD_SNOW + " " +
                    EMOJI_CLOUD_RAIN + " " + EMOJI_SUN_BEHIND_RAIN_CLOUD
            */
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, PENS);
        ListView listView = (ListView) findViewById(R.id.list_notifications);
        listView.setOnItemClickListener(listNotificationsClickListener);
        listView.setAdapter(adapter);
    }

    private AdapterView.OnItemClickListener listNotificationsClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            Object o = parent.getItemAtPosition(position);
            String pen = o.toString();

            // Toast.makeText(parent.getContext(), "You have chosen the pen: " + pen, Toast.LENGTH_LONG).show();
        new DownloadWeatherTask(parent.getContext()).execute(URL);


        }
    };

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
                return getResources().getString(R.string.connection_error);
            }
        }

        @Override
        protected void onPostExecute(String result) {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context);
            mBuilder
                    .setSmallIcon(R.drawable.ic_wb_sunny_black_24dp)
                    .setContentTitle("My notification")
                    .setContentText(result);

            // Sets an ID for the notification
            int mNotificationId = 001;
            // Gets an instance of the NotificationManager service
            NotificationManager mNotifyMgr =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // Builds the notification and issues it.
            mNotifyMgr.notify(mNotificationId, mBuilder.build());
        }
    }


    private String loadJsonFromNetwork(String url) throws IOException {
        WundergroundWeatherJsonGrabber g = new WundergroundWeatherJsonGrabber(url);
        WundergroundWeatherJsonParser p = new WundergroundWeatherJsonParser(g.getWeatherJsonInputStream());
        List<HourlyForecast> list = p.parseHourlyForecast();
        StringBuilder builder = new StringBuilder();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd HH");
        for ( HourlyForecast hf : list ) {
            if ( builder.length() > 0 ) builder.append( ", " );
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
