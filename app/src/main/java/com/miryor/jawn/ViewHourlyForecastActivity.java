package com.miryor.jawn;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.miryor.jawn.model.HourlyForecast;
import com.miryor.jawn.model.Notifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewHourlyForecastActivity extends AppCompatActivity {

    Notifier notifier = null;
    HourlyForecastArrayAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hourly_forecast);

        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        Intent intent = getIntent();
        Notifier n = (Notifier) intent.getParcelableExtra(Notifier.EXTRA_NAME);
        if ( n != null ) {
            notifier = n;
        }
        else {
            notifier = new Notifier( 0L, "", 0, 0, 0, JawnContract.WEATHER_API_PROVIDER_JAWNREST, "" );
        }

        // reload notifier in case we got new info
        notifier = JawnContract.getNotifier(this, n.getId());
        String provider = notifier.getProvider();
        String forecast = notifier.getForecast();
        if ( provider == null ) provider = "";
        if ( forecast == null ) forecast = "";

        if ( provider.length() == 0 ) {
            Intent goback = new Intent();
            goback.putExtra( Notifier.EXTRA_NAME, notifier );
            setResult(Utils.RESULT_DOESNTEXIST, goback);
            finish();
        }
        else if ( forecast.length() == 0 ) {
            new DownloadWeatherTask(getApplicationContext()).execute( provider, notifier.getPostalCode() );
        }
        else {
            List<HourlyForecast> list = null;
            WeatherJsonParser parser = new WundergroundWeatherJsonParser(forecast);
            try {
                list = parser.parseHourlyForecast();
            } catch (IOException e) {
                Log.e("JAWN", "Could not parse forecast", e);
                list = new ArrayList<HourlyForecast>();
            }
            adapter = new HourlyForecastArrayAdapter(this, R.layout.forecast_row, list);
            ListView listView = (ListView) findViewById(R.id.list_hourlyforecasts);
            listView.setAdapter(adapter);

            Intent goback = new Intent();
            goback.putExtra(Notifier.EXTRA_NAME, notifier);
            setResult(Utils.RESULT_IGNORE, goback);
        }

    }

    private class DownloadWeatherTask extends AsyncTask<String, Void, List<HourlyForecast>> {
        Context context;
        private DownloadWeatherTask( Context context ) {
            this.context = context;
        }

        @Override
        protected List<HourlyForecast> doInBackground(String... strings) {
            try {
                loadJsonFromNetwork(strings[0], strings[1]);
            }
            catch (IOException e) {
                Log.e( "JAWN", getResources().getString(R.string.connection_error) );
            }

            return new ArrayList<HourlyForecast>();
        }

        @Override
        protected void onPostExecute(List<HourlyForecast> list) {
            adapter = new HourlyForecastArrayAdapter(context, R.layout.forecast_row, list);
            ListView listView = (ListView) findViewById(R.id.list_hourlyforecasts);
            listView.setAdapter(adapter);

            Intent goback = new Intent();
            goback.putExtra(Notifier.EXTRA_NAME, notifier);
            setResult(Utils.RESULT_IGNORE, goback);
        }
    }

    private List<HourlyForecast> loadJsonFromNetwork(String provider, String location) throws IOException {
        WeatherJsonGrabber g = null;
        if ( provider.equals( JawnContract.WEATHER_API_PROVIDER_JAWNREST ) ) g = new JawnRestWeatherJsonGrabber(location);
        else g = new WundergroundWeatherJsonGrabber(location);

        WeatherJsonParser p = null;
        if ( provider.equals( JawnContract.WEATHER_API_PROVIDER_JAWNREST ) ) p = new JawnRestWeatherJsonParser(g.getWeatherJsonInputStream());
        else p = new WundergroundWeatherJsonParser(g.getWeatherJsonInputStream());

        List<HourlyForecast> list = p.parseHourlyForecast();
        return list;
    }


}
