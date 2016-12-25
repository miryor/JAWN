package com.miryor.jawn;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.miryor.jawn.model.HourlyForecast;
import com.miryor.jawn.model.Notifier;

import java.io.IOException;
import java.util.ArrayList;
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
            notifier = new Notifier( 0L, "", 0, 0, 0, JawnContract.WEATHER_API_PROVIDER_WUNDERGROUND, "" );
        }

        // reload notifier in case we got new info
        notifier = JawnContract.getNotifier(this, n.getId());
        String provider = notifier.getProvider();
        String forecast = notifier.getForecast();
        if ( provider == null ) provider = "";
        if ( forecast == null ) forecast = "";

        if ( provider.length() == 0 || forecast.length() == 0 ) {
            Intent goback = new Intent();
            goback.putExtra( Notifier.EXTRA_NAME, notifier );
            setResult(Utils.RESULT_DOESNTEXIST, goback);
            finish();
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
            adapter = new HourlyForecastArrayAdapter( this, R.layout.forecast_row, list );
            ListView listView = (ListView) findViewById(R.id.list_hourlyforecasts);
            listView.setAdapter(adapter);

            Intent goback = new Intent();
            goback.putExtra( Notifier.EXTRA_NAME, notifier );
            setResult(Utils.RESULT_IGNORE, goback);
        }

    }

}
