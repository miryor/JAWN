package com.miryor.jawn;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;
import android.support.v7.app.NotificationCompat;

import com.miryor.jawn.model.HourlyForecast;
import com.miryor.jawn.model.Notifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by royrim on 11/25/16.
 */

public class JawnContract {
    private JawnContract() {}

    public static final int NOTIFICATION_ID = 15968902;

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "JAWN.db";

    public static final int DOW_SUNDAY = 1;
    public static final int DOW_MONDAY = 2;
    public static final int DOW_TUESDAY = 4;
    public static final int DOW_WEDNESDAY = 8;
    public static final int DOW_THURSDAY = 16;
    public static final int DOW_FRIDAY = 32;
    public static final int DOW_SATURDAY = 64;
    public static final int DOW_EVERYDAY = 127;

    public static final String DOW_ENABLED_COLOR = "#00ff00";
    public static final String DOW_DISABLED_COLOR = "#ff0000";

    public static final String WEATHER_API_PROVIDER_WUNDERGROUND = "WUNDERGROUND";

    public static class JawnNotifier implements BaseColumns {
        public static final String TABLE_NAME = "notifier";
        public static final String COLUMN_NAME_HOUR = "hour";
        public static final String COLUMN_NAME_MINUTE = "minute";
        public static final String COLUMN_NAME_POSTALCODE = "postal_code";
        public static final String COLUMN_NAME_DAYSOFWEEK = "days_of_week";
        public static final String COLUMN_NAME_PROVIDER = "provider";
        public static final String COLUMN_NAME_FORECAST = "forecast";
    }

    public static class JawnNotifierDbHelper extends SQLiteOpenHelper {
        private static final String SQL_CREATE_NOTIFIER = "CREATE TABLE " +
                JawnNotifier.TABLE_NAME + " (" +
                JawnNotifier._ID + " INTEGER PRIMARY KEY," +
                JawnNotifier.COLUMN_NAME_POSTALCODE + " TEXT," +
                JawnNotifier.COLUMN_NAME_DAYSOFWEEK + " INTEGER," +
                JawnNotifier.COLUMN_NAME_HOUR + " INTEGER," +
                JawnNotifier.COLUMN_NAME_MINUTE + " INTEGER," +
                JawnNotifier.COLUMN_NAME_PROVIDER + " TEXT," +
                JawnNotifier.COLUMN_NAME_FORECAST + " TEXT )";

        private static final String SQL_DELETE_NOTIFIER = "DROP TABLE IF EXISTS " +
                JawnNotifier.TABLE_NAME;

        public JawnNotifierDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_DELETE_NOTIFIER);
            db.execSQL(SQL_CREATE_NOTIFIER);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_NOTIFIER);
            db.execSQL(SQL_CREATE_NOTIFIER);
        }
    }

    public static boolean isDayOfWeek( int dow, int day ) {
        if ( ( dow & day ) == day ) return true;
        else return false;
    }

    public static String formatTime( int hour, int minute ) {
        String paddedHour = Integer.toString(hour);
        String paddedMinute = Integer.toString(minute);
        if ( paddedHour.length() == 1 ) paddedHour = "0" + paddedHour;
        if ( paddedMinute.length() == 1 ) paddedMinute = "0" + paddedMinute;
        return paddedHour + ":" + paddedMinute;
    }

    public static String formatDaysOfWeek( int daysOfWeek ) {
        StringBuilder b = new StringBuilder();
        b.append( "<font color=\"" );
        if ( ( daysOfWeek & DOW_SUNDAY ) == DOW_SUNDAY ) b.append( DOW_ENABLED_COLOR );
        else b.append( DOW_DISABLED_COLOR );
        b.append( "\">S</font> ");
        b.append( "<font color=\"" );
        if ( ( daysOfWeek & DOW_MONDAY ) == DOW_MONDAY ) b.append( DOW_ENABLED_COLOR );
        else b.append( DOW_DISABLED_COLOR );
        b.append( "\">M</font> ");
        b.append( "<font color=\"" );
        if ( ( daysOfWeek & DOW_TUESDAY ) == DOW_TUESDAY ) b.append( DOW_ENABLED_COLOR );
        else b.append( DOW_DISABLED_COLOR );
        b.append( "\">T</font> ");
        b.append( "<font color=\"" );
        if ( ( daysOfWeek & DOW_WEDNESDAY ) == DOW_WEDNESDAY ) b.append( DOW_ENABLED_COLOR );
        else b.append( DOW_DISABLED_COLOR );
        b.append( "\">W</font> ");
        b.append( "<font color=\"" );
        if ( ( daysOfWeek & DOW_THURSDAY ) == DOW_THURSDAY ) b.append( DOW_ENABLED_COLOR );
        else b.append( DOW_DISABLED_COLOR );
        b.append( "\">T</font> ");
        b.append( "<font color=\"" );
        if ( ( daysOfWeek & DOW_FRIDAY ) == DOW_FRIDAY ) b.append( DOW_ENABLED_COLOR );
        else b.append( DOW_DISABLED_COLOR );
        b.append( "\">F</font> ");
        b.append( "<font color=\"" );
        if ( ( daysOfWeek & DOW_SATURDAY ) == DOW_SATURDAY ) b.append( DOW_ENABLED_COLOR );
        else b.append( DOW_DISABLED_COLOR );
        b.append( "\">S</font>");
        return b.toString();
    }

    public static void reset(Context context) {
        JawnNotifierDbHelper dbHelper = new JawnNotifierDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL( JawnNotifierDbHelper.SQL_DELETE_NOTIFIER );
        db.close();
        dbHelper.onCreate(db);
    }

    private static long getMaxId(Context context) {
        JawnNotifierDbHelper dbHelper = new JawnNotifierDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        final SQLiteStatement stmt = db
                .compileStatement("SELECT MAX(_ID) FROM " + JawnNotifier.TABLE_NAME);

        return (long) stmt.simpleQueryForLong();
    }

    public static long saveNotifier(Context context, Notifier n) {
        if (n.getId() > 0L) {
            int count = updateNotifier(context, n);
            return n.getId();
        }
        else {
            return insertNotifier(context,n);
        }
    }

    public static long insertNotifier(Context context, Notifier n) {
        long max = getMaxId(context);

        JawnNotifierDbHelper dbHelper = new JawnNotifierDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(JawnNotifier._ID, max+1);
        values.put(JawnNotifier.COLUMN_NAME_POSTALCODE, n.getPostalCode());
        values.put(JawnNotifier.COLUMN_NAME_DAYSOFWEEK, n.getDaysOfWeek());
        values.put(JawnNotifier.COLUMN_NAME_HOUR, n.getHour());
        values.put(JawnNotifier.COLUMN_NAME_MINUTE, n.getMinute());
        values.put(JawnNotifier.COLUMN_NAME_PROVIDER, n.getProvider());
        values.put(JawnNotifier.COLUMN_NAME_FORECAST, "");

        long rowId = db.insert(JawnNotifier.TABLE_NAME, null, values);

        db.close();
        return rowId;
    }

    public static int updateNotifier(Context context, Notifier n) {
        JawnNotifierDbHelper dbHelper = new JawnNotifierDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(JawnNotifier.COLUMN_NAME_POSTALCODE, n.getPostalCode());
        values.put(JawnNotifier.COLUMN_NAME_DAYSOFWEEK, n.getDaysOfWeek());
        values.put(JawnNotifier.COLUMN_NAME_HOUR, n.getHour());
        values.put(JawnNotifier.COLUMN_NAME_MINUTE, n.getMinute());
        values.put(JawnNotifier.COLUMN_NAME_PROVIDER, n.getProvider());

        String selection = JawnNotifier._ID + " = ?";
        String[] selectionArgs = { Long.toString( n.getId() ) };

        return db.update(
                JawnNotifier.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public static int updateNotifierForecast( Context context, Notifier n ) {
        JawnNotifierDbHelper dbHelper = new JawnNotifierDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        // values.put(JawnNotifier.COLUMN_NAME_PROVIDER, n.getProvider());
        values.put(JawnNotifier.COLUMN_NAME_FORECAST, n.getForecast());

        String selection = JawnNotifier._ID + " = ?";
        String[] selectionArgs = { Long.toString( n.getId() ) };

        int updated = db.update(
                JawnNotifier.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        db.close();
        return updated;
    }

    public static void deleteNotifier(Context context, long id) {
        JawnNotifierDbHelper dbHelper = new JawnNotifierDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = JawnNotifier._ID + " = ?";
        String[] selectionArgs = { Long.toString(id) };
        db.delete( JawnNotifier.TABLE_NAME, selection, selectionArgs );
        db.close();
    }

    public static List<Notifier> listNotifiers(Context context) {
        JawnNotifierDbHelper dbHelper = new JawnNotifierDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                JawnNotifier._ID,
                JawnNotifier.COLUMN_NAME_POSTALCODE,
                JawnNotifier.COLUMN_NAME_DAYSOFWEEK,
                JawnNotifier.COLUMN_NAME_HOUR,
                JawnNotifier.COLUMN_NAME_MINUTE,
                JawnNotifier.COLUMN_NAME_PROVIDER,
                JawnNotifier.COLUMN_NAME_FORECAST
        };
        Cursor c = db.query(JawnNotifier.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
                );
        List<Notifier> list = new ArrayList<Notifier>();
        if (c.moveToFirst()) {
            do {
                int id = c.getInt(c.getColumnIndex(JawnNotifier._ID));
                String postalCode = c.getString(c.getColumnIndex(JawnNotifier.COLUMN_NAME_POSTALCODE));
                int daysOfWeek = c.getInt(c.getColumnIndex(JawnNotifier.COLUMN_NAME_DAYSOFWEEK));
                int hour = c.getInt(c.getColumnIndex(JawnNotifier.COLUMN_NAME_HOUR));
                int minute = c.getInt(c.getColumnIndex(JawnNotifier.COLUMN_NAME_MINUTE));
                String provider = c.getString(c.getColumnIndex(JawnNotifier.COLUMN_NAME_PROVIDER));
                String forecast = c.getString(c.getColumnIndex(JawnNotifier.COLUMN_NAME_FORECAST));
                list.add( new Notifier(id, postalCode, daysOfWeek, hour, minute, provider, forecast ) );
            } while (c.moveToNext());
        }
        c.close();

        db.close();
        return list;
    }

    public static Notifier getNotifier(Context context, long id) {
        JawnNotifierDbHelper dbHelper = new JawnNotifierDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                JawnNotifier._ID,
                JawnNotifier.COLUMN_NAME_POSTALCODE,
                JawnNotifier.COLUMN_NAME_DAYSOFWEEK,
                JawnNotifier.COLUMN_NAME_HOUR,
                JawnNotifier.COLUMN_NAME_MINUTE,
                JawnNotifier.COLUMN_NAME_PROVIDER,
                JawnNotifier.COLUMN_NAME_FORECAST
        };
        String selection = JawnNotifier._ID + " = ?";
        String[] selectionArgs = { Long.toString(id) };
        Cursor c = db.query(JawnNotifier.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        Notifier n = null;
        if (c.moveToFirst()) {
            do {
                id = c.getInt(c.getColumnIndex(JawnNotifier._ID));
                String postalCode = c.getString(c.getColumnIndex(JawnNotifier.COLUMN_NAME_POSTALCODE));
                int daysOfWeek = c.getInt(c.getColumnIndex(JawnNotifier.COLUMN_NAME_DAYSOFWEEK));
                int hour = c.getInt(c.getColumnIndex(JawnNotifier.COLUMN_NAME_HOUR));
                int minute = c.getInt(c.getColumnIndex(JawnNotifier.COLUMN_NAME_MINUTE));
                String provider = c.getString(c.getColumnIndex(JawnNotifier.COLUMN_NAME_PROVIDER));
                String forecast = c.getString(c.getColumnIndex(JawnNotifier.COLUMN_NAME_FORECAST));
                n = new Notifier(id, postalCode, daysOfWeek, hour, minute, provider, forecast );
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return n;
    }

    public static String getEmoji(String condition) {
        String emoji = WeatherJsonParser.EMOJI_QUESTION;
        for ( int x = 0; x < WeatherJsonParser.STORM_WORDS.length; x++ ) {
            if ( condition.indexOf( WeatherJsonParser.STORM_WORDS[x] ) >= 0 ) {
                emoji = WeatherJsonParser.EMOJI_CLOUD_LIGHTNING_RAIN;
                return emoji;
            }
        }
        for (int x = 0; x < WeatherJsonParser.CLOUDY_WORDS.length; x++) {
            if (condition.indexOf(WeatherJsonParser.CLOUDY_WORDS[x]) >= 0) {
                emoji = WeatherJsonParser.EMOJI_SUN_BEHIND_CLOUD;
                return emoji;
            }
        }
        for ( int x = 0; x < WeatherJsonParser.SNOW_WORDS.length; x++ ) {
            if ( condition.indexOf( WeatherJsonParser.SNOW_WORDS[x] ) >= 0 ) {
                emoji = WeatherJsonParser.EMOJI_SNOWFLAKE;
                return emoji;
            }
        }
        for (int x = 0; x < WeatherJsonParser.RAIN_WORDS.length; x++) {
            if (condition.indexOf(WeatherJsonParser.RAIN_WORDS[x]) >= 0) {
                emoji = WeatherJsonParser.EMOJI_UMBRELLA;
                return emoji;
            }
        }
        for (int x = 0; x < WeatherJsonParser.SUNNY_WORDS.length; x++) {
            if (condition.indexOf(WeatherJsonParser.SUNNY_WORDS[x]) >= 0) {
                emoji = WeatherJsonParser.EMOJI_SUN;
                return emoji;
            }
        }
        for (int x = 0; x < WeatherJsonParser.CLEAR_WORDS.length; x++) {
            if (condition.indexOf(WeatherJsonParser.CLEAR_WORDS[x]) >= 0) {
                emoji = " ";
                return emoji;
            }
        }
        return emoji;
    }

    public static void formatForecastForNotification( StringBuilder builder, HourlyForecast hf ) {
        String condition = hf.getCondition().toLowerCase();
        builder.append( getEmoji(condition) );
        builder.append( " " );
        builder.append( hf.getHour() );
        builder.append( "H " );
        builder.append( hf.getFeelsLikeF() );
        builder.append( "\u00B0" );
    }

}
