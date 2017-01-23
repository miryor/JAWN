package com.miryor.jawn;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.miryor.jawn.model.HourlyForecast;
import com.miryor.jawn.model.Notifier;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by royrim on 11/25/16.
 * Clues from:
 * http://www.ezzylearning.com/tutorial/customizing-android-listview-items-with-custom-arrayadapter
 * http://blog-emildesign.rhcloud.com/?p=495
 *
 * To read:
 * http://stackoverflow.com/questions/4540754/dynamically-add-elements-to-a-listview-android
 */

public class NotifierArrayAdapter extends ArrayAdapter<Notifier> {
    private int layoutResourceId;
    private Context context;

    public NotifierArrayAdapter(Context context, int layoutResourceId, List<Notifier> list) {
        super(context, layoutResourceId, list);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
    }

    static class ViewHolder {
        ImageButton delete;
        // CheckBox enabled;
        TextView daysOfWeek;
        TextView time;
        TextView postalCode;
        ImageButton notifier;
        ImageButton viewForecast;
    }

    public void cancelNotifierAlarm(Notifier notifier) {
        Log.d( "JAWN", "Cancel alarm for " + notifier.getId() + "/" + notifier.getPostalCode() );
        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        notificationIntent.putExtra( Notifier.EXTRA_NAME, notifier );
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int)notifier.getId(), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        final Notifier n = getItem(position);

        if ( row == null ) {
            LayoutInflater inflator = ((Activity) context).getLayoutInflater();
            row = inflator.inflate(layoutResourceId, parent, false);

            ImageButton delete = (ImageButton) row.findViewById(R.id.notifier_delete);
            // CheckBox enabled = (CheckBox) row.findViewById(R.id.notifier_enabled);
            TextView daysOfWeek = (TextView) row.findViewById(R.id.notifier_daysofweek);
            TextView time = (TextView) row.findViewById(R.id.notifier_time);
            TextView postalCode = (TextView) row.findViewById(R.id.notifier_postalcode);
            ImageButton notifer = (ImageButton) row.findViewById(R.id.notifier_notify);
            ImageButton viewForecast = (ImageButton) row.findViewById(R.id.notifier_view);

            holder = new ViewHolder();
            holder.delete = delete;
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JawnContract.deleteNotifier(context, getItem(position).getId());
                    cancelNotifierAlarm(getItem(position));
                    clear();
                    addAll( JawnContract.listNotifiers(context) );
                    notifyDataSetChanged();
                    Toast.makeText(context, "Deleted notifier", Toast.LENGTH_LONG).show();
                }
            });
            holder.daysOfWeek = daysOfWeek;
            holder.daysOfWeek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent intent = new Intent(context, AddNotifierActivity.class);
                    intent.putExtra( Notifier.EXTRA_NAME, n );
                    ((Activity) context).startActivityForResult(intent, Notifier.RESULT_SAVED);*/
                    /*TextView view = (TextView) v;
                    Toast.makeText(context, "Days of Week from row " + position + " was pressed: " + view.getText(), Toast.LENGTH_LONG).show();*/
                }
            });
            holder.time = time;
            holder.time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent intent = new Intent(context, AddNotifierActivity.class);
                    intent.putExtra( Notifier.EXTRA_NAME, n );
                    ((Activity) context).startActivityForResult(intent, Notifier.RESULT_SAVED);*/
                    /*TextView view = (TextView) v;
                    Toast.makeText(context, "Time from row " + position + " was pressed: " + view.getText(), Toast.LENGTH_LONG).show();*/
                }
            });
            holder.postalCode = postalCode;
            holder.postalCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent intent = new Intent(context, AddNotifierActivity.class);
                    intent.putExtra( Notifier.EXTRA_NAME, n );
                    ((Activity) context).startActivityForResult(intent, Notifier.RESULT_SAVED);*/
                    /*TextView view = (TextView) v;
                    Toast.makeText(context, "Postal Code from row " + position + " was pressed: " + view.getText(), Toast.LENGTH_LONG).show();*/
                }
            });
            holder.notifier = notifer;
            holder.notifier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Notifier n = getItem(position);
                    // reload notifier in case we got forecast in background
                    n = JawnContract.getNotifier(context, n.getId());
                    String provider = n.getProvider();
                    if ( provider == null ) provider = "";
                    String forecast = n.getForecast();
                    if ( forecast == null ) forecast = "";
                    if ( provider.length() == 0 || forecast.length() == 0 ) {
                        Toast.makeText(context, "No forecast received yet", Toast.LENGTH_LONG).show();
                    }
                    else {
                        try {
                            StringBuilder builder = new StringBuilder();
                            Log.d( "JAWN", "Sending existing forecast to notification " + n.getProvider() );
                            if ( provider.equals(JawnContract.WEATHER_API_PROVIDER_WUNDERGROUND) ) {
                                WeatherJsonParser parser = new WundergroundWeatherJsonParser(forecast);
                                List<HourlyForecast> list = parser.parseHourlyForecast();
                                for (HourlyForecast hf : list) {
                                    JawnContract.formatForecastForNotification(builder, hf);
                                }
                                Utils.sendNotification(context, builder.toString());
                                Toast.makeText(context, "Sent notification", Toast.LENGTH_LONG).show();
                            }
                            else if ( provider.equals(JawnContract.WEATHER_API_PROVIDER_JAWNREST) ) {
                                WeatherJsonParser parser = new JawnRestWeatherJsonParser(forecast);
                                List<HourlyForecast> list = parser.parseHourlyForecast();
                                for (HourlyForecast hf : list) {
                                    JawnContract.formatForecastForNotification(builder, hf);
                                }
                                Utils.sendNotification(context, builder.toString());
                                Toast.makeText(context, "Sent notification", Toast.LENGTH_LONG).show();
                            }
                        } catch ( Exception e ) {
                            Log.e("JAWN", "Error parsing forecast", e);
                            Toast.makeText(context, "Error sending notification", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
            holder.viewForecast = viewForecast;
            holder.viewForecast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ViewHourlyForecastActivity.class);
                    intent.putExtra(Notifier.EXTRA_NAME, n);
                    ((Activity) context).startActivityForResult(intent, Utils.RESULT_DOESNTEXIST);
                }
            });

            row.setTag(holder);
        }
        else {
            holder = (ViewHolder)row.getTag();
        }

        // holder.enabled.setEnabled(n.isEnabled());
        holder.daysOfWeek.setText(Html.fromHtml( JawnContract.formatDaysOfWeek( n.getDaysOfWeek() ) ) );
        holder.time.setText( JawnContract.formatTime( n.getHour(), n.getMinute() ) );
        holder.postalCode.setText(n.getPostalCode());

        return row;

    }
}
