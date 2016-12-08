package com.miryor.jawn;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by royrim on 12/8/16.
 */

public class NotificationId {

    public static int getId(Context context) {
        SharedPreferences p = context.getSharedPreferences( context.getString(R.string.preference_jawnnotifier), Context.MODE_PRIVATE );
        int lastNotificationId = p.getInt( context.getString(R.string.preference_lastnotificationid), 0 );
        lastNotificationId++;
        SharedPreferences.Editor e = p.edit();
        e.putInt( context.getString(R.string.preference_jawnnotifier), lastNotificationId );
        e.commit();
        return lastNotificationId;
    }
}
