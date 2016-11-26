package com.miryor.jawn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.miryor.jawn.model.Notifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by royrim on 11/25/16.
 */

public class JawnContract {
    private JawnContract() {}

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "JAWN.db";

    public static class JawnNotifier implements BaseColumns {
        public static final String TABLE_NAME = "notifier";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_POSTALCODE = "postal_code";
        public static final String COLUMN_NAME_DAYSOFWEEK = "days_of_week";
        public static final String COLUMN_NAME_ENABLED = "enabled";

        public static final int DOW_SUNDAY = 1;
        public static final int DOW_MONDAY = 2;
        public static final int DOW_TUESDAY = 4;
        public static final int DOW_WEDNESDAY = 8;
        public static final int DOW_THURSDAY = 16;
        public static final int DOW_FRIDAY = 32;
        public static final int DOW_SATURDAY = 64;
    }

    public static class JawnNotifierDbHelper extends SQLiteOpenHelper {
        private static final String SQL_CREATE_NOTIFIER = "CREATE TABLE " +
                JawnNotifier.TABLE_NAME + " (" +
                JawnNotifier._ID + " INTEGER PRIMARY KEY," +
                JawnNotifier.COLUMN_NAME_POSTALCODE + " TEXT," +
                JawnNotifier.COLUMN_NAME_DAYSOFWEEK + " INTEGER," +
                JawnNotifier.COLUMN_NAME_TIME + " INTEGER," +
                JawnNotifier.COLUMN_NAME_ENABLED + " INTEGER )";

        public JawnNotifierDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_NOTIFIER);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    public static void reset(Context context) {
        JawnNotifierDbHelper dbHelper = new JawnNotifierDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL( "DROP TABLE IF EXISTS " + JawnNotifier.TABLE_NAME );
        dbHelper.onCreate(db);
    }

    public static long insertNotifier(Context context, Notifier n) {
        JawnNotifierDbHelper dbHelper = new JawnNotifierDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(JawnNotifier.COLUMN_NAME_POSTALCODE, n.getPostalCode());
        values.put(JawnNotifier.COLUMN_NAME_DAYSOFWEEK, n.getDaysOfWeek());
        values.put(JawnNotifier.COLUMN_NAME_TIME, n.getTime());
        values.put(JawnNotifier.COLUMN_NAME_ENABLED, new Integer(1));

        long rowId = db.insert(JawnNotifier.TABLE_NAME, null, values);
        return rowId;
    }

    public static void deleteNotifier(Context context, long id) {
        JawnNotifierDbHelper dbHelper = new JawnNotifierDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = JawnNotifier._ID + " = ?";
        String[] selectionArgs = { Long.toString(id) };
        db.delete( JawnNotifier.TABLE_NAME, selection, selectionArgs );
    }

    public static List<Notifier> listNotifiers(Context context) {
        JawnNotifierDbHelper dbHelper = new JawnNotifierDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                JawnNotifier._ID,
                JawnNotifier.COLUMN_NAME_POSTALCODE,
                JawnNotifier.COLUMN_NAME_DAYSOFWEEK,
                JawnNotifier.COLUMN_NAME_TIME,
                JawnNotifier.COLUMN_NAME_ENABLED
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
                int time = c.getInt(c.getColumnIndex(JawnNotifier.COLUMN_NAME_TIME));
                int enabled = c.getInt(c.getColumnIndex(JawnNotifier.COLUMN_NAME_ENABLED));
                list.add( new Notifier(id, postalCode, daysOfWeek, time, ((enabled==1) ? true:false) ) );
            } while (c.moveToNext());
        }
        c.close();

        return list;
    }
}
