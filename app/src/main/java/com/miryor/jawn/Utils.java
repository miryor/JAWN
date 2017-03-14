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

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.miryor.jawn.model.HourlyForecast;
import com.miryor.jawn.model.HourlyForecastFormatted;
import com.miryor.jawn.model.Notifier;

import java.util.Calendar;
import java.util.List;

/**
 * Created by royrim on 12/8/16.
 */

public class Utils {
    public static final String JSON_URL = ".json";

    public static final int RESULT_SAVED = 100;
    public static final int RESULT_CANCELLED = 200;
    public static final int RESULT_TOOMANY = 2001;
    public static final int RESULT_VIEWED = 101;
    public static final int RESULT_DOESNTEXIST = 102;
    public static final int RESULT_IGNORE = 300;
    public static final int SIGNIN_SUCCESS = 500;
    public static final int SIGNIN_CANCEL = 600;
    public static final int RESULT_ERROR = 700;

    public static String EMOJI_SUN = "\u2600";
    public static String EMOJI_CRESCENTMOON = "\uD83C\uDF19";
    public static String EMOJI_NIGHTSKY = "\uD83C\uDF03";
    public static String EMOJI_CITYDUSK = "\uD83C\uDF06";
    public static String EMOJI_CLOUD = "\u2601";
    public static String EMOJI_SUN_BEHIND_CLOUD = "\u26C5";
    public static String EMOJI_CLOUD_LIGHTNING_RAIN = "\u26C8";
    public static String EMOJI_SNOWMAN = "\u26C4";
    public static String EMOJI_UMBRELLA = "\u2614";
    public static String EMOJI_QUESTION = "\u2753";

    public static String[] SNOW_WORDS = {
            "hail", "flurries", "freezing", "ice", "sleet", "snow"
    };

    public static String[] RAIN_WORDS = {
            "drizzle", "rain"
    };

    public static String[] CLOUDY_WORDS = {
            "cloudy", "fog", "haze", "hazy", "mist", "overcast", "partly sunny"
    };

    public static String[] STORM_WORDS = {
            "thunderstorm"
    };

    public static String[] SUNNY_WORDS = {
            "sunny"
    };

    public static String[] CLEAR_WORDS = {
            "clear"
    };

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

    public static void sendNotification(Context context, long notificationId, String result) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context);
        mBuilder
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("JAWN")
                .setContentText(result);

        Log.d( "JAWN", "Sending notification with " + notificationId + ", " + result );
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyMgr.cancel((int)notificationId);
        // Builds the notification and issues it.
        mNotifyMgr.notify( (int)notificationId, mBuilder.build());
    }

    public static void sendNotification(Context context, long notificationId, HourlyForecastFormatted result) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context);
        mBuilder
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(result.getEmojiSummary())
                .setContentText(result.getHourlyForecastFormatted());

        Log.d( "JAWN", "Sending notification with " + notificationId + ", " + result );
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyMgr.cancel((int)notificationId);
        // Builds the notification and issues it.
        mNotifyMgr.notify( (int)notificationId, mBuilder.build());
    }

    public static String getEmoji(String condition, int hour) {
        String emoji = EMOJI_QUESTION;
        for (int x = 0; x < STORM_WORDS.length; x++) {
            if (condition.indexOf(STORM_WORDS[x]) >= 0) {
                emoji = EMOJI_CLOUD_LIGHTNING_RAIN;
                return emoji;
            }
        }
        for (int x = 0; x < CLOUDY_WORDS.length; x++) {
            if (condition.indexOf(CLOUDY_WORDS[x]) >= 0) {
                emoji = EMOJI_CLOUD;
                return emoji;
            }
        }
        for (int x = 0; x < SNOW_WORDS.length; x++) {
            if (condition.indexOf(SNOW_WORDS[x]) >= 0) {
                emoji = EMOJI_SNOWMAN;
                return emoji;
            }
        }
        for (int x = 0; x < RAIN_WORDS.length; x++) {
            if (condition.indexOf(RAIN_WORDS[x]) >= 0) {
                emoji = EMOJI_UMBRELLA;
                return emoji;
            }
        }
        for (int x = 0; x < SUNNY_WORDS.length; x++) {
            if (condition.indexOf(SUNNY_WORDS[x]) >= 0) {
                emoji = EMOJI_SUN;
                return emoji;
            }
        }
        for (int x = 0; x < CLEAR_WORDS.length; x++) {
            if (condition.indexOf(CLEAR_WORDS[x]) >= 0) {
                if (hour >= 6 && hour <= 18) emoji = EMOJI_SUN;
                else emoji = EMOJI_NIGHTSKY;
                return emoji;
            }
        }
        return emoji;
    }

    public static void formatForecastTimeAndTempNotification( StringBuilder builder, HourlyForecast hf ) {
        int hour = hf.getHour();
        if ( hour < 12 ) {
            builder.append(hour);
            builder.append("AM");
        }
        else if ( hour == 12 ) {
            builder.append( "12PM" );
        }
        else {
            builder.append( hour - 12 );
            builder.append( "PM" );
        }
        builder.append( " " );
        builder.append( hf.getFeelsLikeF() );
        builder.append( "\u00B0" );
    }

    public static HourlyForecastFormatted formatHourlyForecastForNotification(List<HourlyForecast> list ) {
        StringBuilder builder = new StringBuilder();
        StringBuilder emojiBuilder = new StringBuilder();
        String previousEmoji = "";
        for ( HourlyForecast hf : list ) {
            if ( builder.length() > 0 ) builder.append( "," );
            String condition = hf.getCondition().toLowerCase();
            String emoji = getEmoji(condition, hf.getHour());
            if ( !emoji.equals(previousEmoji) ) {
                builder.append(emoji);
                emojiBuilder.append(emoji);
            }
            formatForecastTimeAndTempNotification(builder, hf);
            previousEmoji = emoji;
        }
        return new HourlyForecastFormatted(emojiBuilder.toString(), builder.toString());
    }
}
