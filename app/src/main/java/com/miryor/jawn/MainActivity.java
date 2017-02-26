package com.miryor.jawn;

import android.app.NotificationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.miryor.jawn.R;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static String EMOJI_SUN = "\u2600";
    private static String EMOJI_CLOUD = "\u2601";
    private static String EMOJI_SUN_BEHIND_CLOUD = "\u26C5";
    private static String EMOJI_CLOUD_LIGHTNING_RAIN = "\u26C8";

    public static String EMOJI_SNOWFLAKE = "\u2744";
    public static String EMOJI_SNOWMAN = "\u26C4";
    private static String EMOJI_CLOUD_SNOW = "\uD83C\uDF28";
    private static String EMOJI_CLOUD_RAIN = "\uD83C\uDF27";
    private static String EMOJI_SUN_BEHIND_RAIN_CLOUD = "\uD83C\uDF26";

    public static String EMOJI_CRESCENTMOON = "\uD83C\uDF19";
    public static String EMOJI_NIGHTSKY = "\uD83C\uDF03";
    public static String EMOJI_CITYDUSK = "\uD83C\uDF06";
    public static String EMOJI_MILKYWAY = "\uD83C\uDF0C";

    static final String[] EMOJIS = new String[]{
            EMOJI_SUN + " " + EMOJI_CLOUD + " " +
                    EMOJI_SUN_BEHIND_CLOUD + " " + EMOJI_CLOUD_LIGHTNING_RAIN,
            EMOJI_SNOWFLAKE + " " + EMOJI_SNOWMAN + " " + EMOJI_CLOUD_SNOW + " " +
                    EMOJI_CLOUD_RAIN + " " + EMOJI_SUN_BEHIND_RAIN_CLOUD,
            EMOJI_CRESCENTMOON + " " + EMOJI_NIGHTSKY + " " +
                    EMOJI_CITYDUSK + " " + EMOJI_MILKYWAY
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, EMOJIS);
        ListView listView = (ListView) findViewById(R.id.list_notifications);
        listView.setOnItemClickListener(listNotificationsClickListener);
        listView.setAdapter(adapter);
    }

    private AdapterView.OnItemClickListener listNotificationsClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            Object o = parent.getItemAtPosition(position);
            String emoji = o.toString();

            Toast.makeText(parent.getContext(), "You have chosen: " + emoji, Toast.LENGTH_LONG).show();

            NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(parent.getContext());
            mBuilder
                .setSmallIcon(R.drawable.ic_wb_sunny_black_24dp)
                .setContentTitle("My notification")
                .setContentText(emoji);

            // Sets an ID for the notification
            int mNotificationId = 001;
            // Gets an instance of the NotificationManager service
            NotificationManager mNotifyMgr =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // Builds the notification and issues it.
            mNotifyMgr.notify(mNotificationId, mBuilder.build());
        }
    };

}
