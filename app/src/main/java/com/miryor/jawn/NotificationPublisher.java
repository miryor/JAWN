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

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle hackBundle = intent.getBundleExtra("hackBundle");
        Notifier notifier = hackBundle.getParcelable(Notifier.EXTRA_NAME);
        //Notifier notifier = intent.getParcelableExtra( Notifier.EXTRA_NAME );
        Log.d( "JAWN", "Received alarm for " + notifier.getPostalCode() );
        Intent service = new Intent(context, WeatherNotificationIntentService.class);
        service.putExtra( Notifier.EXTRA_NAME, notifier );
        startWakefulService(context, service);
    }

    private void notifyError(Context context, String error) {
        Utils.sendNotification(context, Utils.RESULT_ERROR, error);
    }


}
