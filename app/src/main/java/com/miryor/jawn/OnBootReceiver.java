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
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            List<Notifier> list = JawnContract.listNotifiers(context);
            Calendar calendar = Calendar.getInstance();
            for ( Notifier notifier : list ) {
                Log.d( "JAWN", "Setting alarm for " + notifier.getPostalCode() + " at " + notifier.getHour() + ":" + notifier.getMinute() );
                Intent notificationIntent = new Intent(context, NotificationPublisher.class);
                notificationIntent.putExtra( Notifier.EXTRA_NAME, notifier);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int)notifier.getId(), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                long current = System.currentTimeMillis();
                calendar.setTimeInMillis(current);
                calendar.set(Calendar.HOUR_OF_DAY, notifier.getHour());
                calendar.set(Calendar.MINUTE, notifier.getMinute());
                long alarmTime = calendar.getTimeInMillis();
                if ( alarmTime < current ) {
                    calendar.add( Calendar.DAY_OF_YEAR, 1 );
                    alarmTime = calendar.getTimeInMillis();
                }
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, alarmTime, AlarmManager.INTERVAL_DAY, pendingIntent);

            }
        }
    }
}
