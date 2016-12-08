package com.miryor.jawn;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.miryor.jawn.model.Notifier;

import java.util.Calendar;
import java.util.List;

/**
 * Created by royrim on 12/5/16.
 */

public class OnBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d( "JAWN", "Getting list of notificatiers and settings alarms" );
        // if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            List<Notifier> list = JawnContract.listNotifiers(context);
            Calendar calendar = Calendar.getInstance();
            for ( Notifier notifier : list ) {
                Log.d( "JAWN", "Setting alarm for " + notifier.getPostalCode() + " at " + notifier.getHour() + ":" + notifier.getMinute() );

                Utils.setNotificationAlarm(context, notifier);

            }
        // }
    }
}
