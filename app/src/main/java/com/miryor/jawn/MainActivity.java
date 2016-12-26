package com.miryor.jawn;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.miryor.jawn.model.HourlyForecast;
import com.miryor.jawn.model.Notifier;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * To read:
 * http://stackoverflow.com/questions/8999375/cancel-alarm-manager-pending-intent-according-to-uniqueid-android
 *
 * To read:
 * https://developers.google.com/identity/sign-in/android/backend-auth
 * about signing
 * https://developer.android.com/studio/publish/app-signing.html
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "JAWN";

    private static String EMOJI_SUN = "\u2600";
    private static String EMOJI_CLOUD = "\u2601";
    private static String EMOJI_SUN_BEHIND_CLOUD = "\u26C5";
    private static String EMOJI_CLOUD_LIGHTNING_RAIN = "\u26C8";
    /*
    private static String EMOJI_CLOUD_SNOW = "\uD83C\uDF28";
    private static String EMOJI_CLOUD_RAIN = "\uD83C\uDF27";
    private static String EMOJI_SUN_BEHIND_RAIN_CLOUD = "\uD83C\uDF26";
    */
    private static final String WUNDERGROUND_URL = "http://http://api.wunderground.com/api/502f7c0bd4a4257d/hourly/q/";
    private static final String JSON_URL = ".json";

    private static List<Notifier> notifierList = null;
    private static NotifierArrayAdapter adapter = null;

    private GoogleApiClient mGoogleApiClient;

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

        // JawnContract.reset(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivityForResult( new Intent(this, SignInActivity.class), Utils.SIGNIN_SUCCESS );
    }

    public void checkSignin(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        intent.putExtra( "test", Boolean.TRUE);
        startActivityForResult( intent, Utils.SIGNIN_SUCCESS );
    }

    public void displayList() {
        notifierList = JawnContract.listNotifiers(this);
        adapter = new NotifierArrayAdapter(this, R.layout.notifier_row, notifierList);
        ListView listView = (ListView) findViewById(R.id.list_notifications);
        listView.setOnItemClickListener(listNotificationsClickListener);
        listView.setAdapter(adapter);
    }

    public void addNotification(View view) {
        Intent intent = new Intent(this, AddNotifierActivity.class);
        intent.putExtra( Notifier.EXTRA_NAME, new Notifier(0L, "", JawnContract.DOW_EVERYDAY, 0, 0, JawnContract.WEATHER_API_PROVIDER_WUNDERGROUND, "") );
        startActivityForResult(intent, Utils.RESULT_SAVED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("JAWN", "onActivityResult " + resultCode );
        // Toast.makeText(this, "toast " + resultCode, Toast.LENGTH_LONG).show();
        if ( resultCode == Utils.RESULT_IGNORE ) {
        }
        else if ( resultCode == Utils.SIGNIN_CANCEL ) {
            startActivityForResult( new Intent(this, SignInActivity.class), Utils.SIGNIN_SUCCESS );
        }
        else if ( resultCode == Utils.SIGNIN_SUCCESS ) {
            // Signed in successfully, show authenticated UI.
            displayList();
        }
        else if ( resultCode == Utils.RESULT_SAVED ) {
            adapter.clear();
            adapter.addAll( JawnContract.listNotifiers(this) );
            adapter.notifyDataSetChanged();
            Notifier n = (Notifier) data.getParcelableExtra( Notifier.EXTRA_NAME );
            Toast.makeText(this, "Saved " + n.getPostalCode(), Toast.LENGTH_LONG).show();
        }
        else if ( resultCode == Utils.RESULT_DOESNTEXIST ) {
            Toast.makeText(this, "No forecast for this notifier yet", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Cancelled " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    private AdapterView.OnItemClickListener listNotificationsClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
        Object o = parent.getItemAtPosition(position);
        String pen = o.toString();

        Toast.makeText(parent.getContext(), "You have chosen the pen: " + pen, Toast.LENGTH_LONG).show();

        new DownloadWeatherTask(parent.getContext()).execute( WUNDERGROUND_URL + "11233" + JSON_URL );


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
