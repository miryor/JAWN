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

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.miryor.jawn.model.HourlyForecast;
import com.miryor.jawn.model.HourlyForecastFormatted;
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

    private static final int MAX_ATTEMPTS = 3;

    public WeatherNotificationIntentService() {
        super( "WeatherNoticationIntentService" );
    }

    // TODO: Need to figure out DST

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d( "JAWN", "onHandleIntent " + intent.getClass().getName() );

        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.server_client_id))
                .build();
        // [END configure_signin]

        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        // [END build_client]

        try {
            Log.d("JAWN", "Connecting to Google API");
            ConnectionResult result = mGoogleApiClient.blockingConnect();
            if (result.isSuccess()) {
                GoogleSignInResult googleSignInResult =
                        Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient).await();
                handleSignInResult(googleSignInResult, intent);
            }
            else {
                Log.e("JAWN", "Could not connect to Google API " + result.getErrorCode() + ": " + result.getErrorMessage());
            }
        } finally {
            mGoogleApiClient.disconnect();
        }

        /*OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (!opr.isDone()) {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                                      @Override
                                      public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                                          handleSignInResult(googleSignInResult, intent);
                                      }
                                  }
            );
        }
        else {
            handleSignInResult(opr.get(), intent);
        }*/

    }

    private void handleSignInResult(GoogleSignInResult singInResult, Intent intent) {
        if (!singInResult.isSuccess()) {
            Utils.sendNotification(this, Utils.RESULT_ERROR, "You are not signed in.");
        }
        else {
            GoogleSignInAccount acct = singInResult.getSignInAccount();
            String token = acct.getIdToken();

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notifier notifier = (Notifier) intent.getParcelableExtra(Notifier.EXTRA_NAME);

            Log.d("JAWN", "WeatherNotificationIntentService to get weather for " + notifier.getPostalCode());

            String provider = notifier.getProvider();
            String zipCode = notifier.getPostalCode();
            int dow = notifier.getDaysOfWeek();
            Calendar cal = Calendar.getInstance();
            int calDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            if (
                    (calDayOfWeek == Calendar.SUNDAY && JawnContract.isDayOfWeek(dow, JawnContract.DOW_SUNDAY)) ||
                            (calDayOfWeek == Calendar.MONDAY && JawnContract.isDayOfWeek(dow, JawnContract.DOW_MONDAY)) ||
                            (calDayOfWeek == Calendar.TUESDAY && JawnContract.isDayOfWeek(dow, JawnContract.DOW_TUESDAY)) ||
                            (calDayOfWeek == Calendar.WEDNESDAY && JawnContract.isDayOfWeek(dow, JawnContract.DOW_WEDNESDAY)) ||
                            (calDayOfWeek == Calendar.THURSDAY && JawnContract.isDayOfWeek(dow, JawnContract.DOW_THURSDAY)) ||
                            (calDayOfWeek == Calendar.FRIDAY && JawnContract.isDayOfWeek(dow, JawnContract.DOW_FRIDAY)) ||
                            (calDayOfWeek == Calendar.SATURDAY && JawnContract.isDayOfWeek(dow, JawnContract.DOW_SATURDAY))
                    ) {
                if (
                        provider.equals(JawnContract.WEATHER_API_PROVIDER_JAWNREST) ||
                                provider.equals(JawnContract.WEATHER_API_PROVIDER_WUNDERGROUND)
                        ) {
                    int attempts = 0;
                    while ( attempts < MAX_ATTEMPTS ) {
                        try {
                            HourlyForecastFormatted result = loadJsonFromNetwork(this, notifier, provider, token, zipCode);
                            Utils.sendNotification(this, notifier.getId(), result);
                            // we got the weather, no further attempts needed
                            break;
                        }
                        catch (InvalidTokenException e) {
                            Log.e("JAWN", getResources().getString(R.string.connection_error), e);
                            Utils.sendNotification(this, Utils.RESULT_ERROR, getResources().getString(R.string.connection_error) + ", invalid token");
                            // token is invalid, no further attempts needed
                            break;
                        }
                        catch (IOException e) {
                            Log.e("JAWN", getResources().getString(R.string.connection_error), e);
                            // we got some weird exception, sleep for a minute*attempts
                            try { Thread.sleep( (60000 * attempts) ); } catch ( InterruptedException e2 ) { break; }
                        }
                        attempts++;
                        if ( attempts >= MAX_ATTEMPTS ) Utils.sendNotification(this, Utils.RESULT_ERROR, getResources().getString(R.string.connection_error));
                    }
                } else {
                    Utils.sendNotification(this, Utils.RESULT_ERROR, "Wrong provider set: " + provider + ", could not download weather");
                }
            } else {
                Log.d("JAWN", "Notification not set for " + calDayOfWeek);
            }
        }

        NotificationPublisher.completeWakefulIntent(intent);
    }

    private HourlyForecastFormatted loadJsonFromNetwork(Context context, Notifier notifier, String provider, String token, String location) throws IOException {
        WeatherJsonGrabber g = null;
        if ( provider.equals(JawnContract.WEATHER_API_PROVIDER_JAWNREST) ) g = new JawnRestWeatherJsonGrabber(context, token, location);
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
        return Utils.formatHourlyForecastForNotification(list);
    }

}
