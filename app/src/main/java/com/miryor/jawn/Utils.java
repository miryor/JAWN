package com.miryor.jawn;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.miryor.jawn.model.Notifier;

import java.util.Calendar;

/**
 * Created by royrim on 12/8/16.
 */

public class Utils {
    public static final String JSON_URL = ".json";

    public static final int RESULT_SAVED = 100;
    public static final int RESULT_CANCELLED = 200;
    public static final int RESULT_VIEWED = 101;
    public static final int RESULT_DOESNTEXIST = 102;
    public static final int RESULT_IGNORE = 300;
    public static final int SIGNIN_SUCCESS = 500;
    public static final int SIGNIN_CANCEL = 600;
    public static final int RESULT_ERROR = 700;

    public static void setNotificationAlarm(Context context, Notifier notifier) {
        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        Bundle hackBundle = new Bundle();
        hackBundle.putParcelable(Notifier.EXTRA_NAME, notifier);
        notificationIntent.putExtra("hackBundle", hackBundle);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int)notifier.getId(), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        long current = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR_OF_DAY, notifier.getHour());
        calendar.set(Calendar.MINUTE, notifier.getMinute());
        long alarmTime = calendar.getTimeInMillis();
        if ( alarmTime < current ) {
            calendar.add( Calendar.DAY_OF_YEAR, 1 );
            alarmTime = calendar.getTimeInMillis();
        }
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, alarmTime, AlarmManager.INTERVAL_DAY, pendingIntent);

        Log.d( "JAWN", "Added alarm for " + notifier.getId() + "/" + notifier.getPostalCode() + " at " + calendar.getTime() );

    }

    public static void sendNotification(Context context, String result) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context);
        mBuilder
                .setSmallIcon(R.drawable.ic_wb_sunny_black_24dp)
                .setContentTitle("Weather Notification")
                .setContentText(result);

        int notificationId = NotificationId.getId(context);
        Log.d( "JAWN", "Sending notification with " + notificationId + ", " + result );
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify( notificationId, mBuilder.build());
    }
}
