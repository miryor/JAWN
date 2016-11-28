package com.miryor.jawn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.miryor.jawn.model.Notifier;

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
        CheckBox enabled;
        TextView daysOfWeek;
        TextView time;
        TextView postalCode;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        final Notifier n = getItem(position);

        if ( row == null ) {
            LayoutInflater inflator = ((Activity) context).getLayoutInflater();
            row = inflator.inflate(layoutResourceId, parent, false);
            CheckBox enabled = (CheckBox) row.findViewById(R.id.notifier_enabled);
            TextView daysOfWeek = (TextView) row.findViewById(R.id.notifier_daysofweek);
            TextView time = (TextView) row.findViewById(R.id.notifier_time);
            TextView postalCode = (TextView) row.findViewById(R.id.notifier_postalcode);

            holder = new ViewHolder();
            holder.enabled = enabled;
            holder.enabled.setChecked( n.isEnabled() );
            holder.enabled.setTag(position);
            holder.enabled.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkbox = (CheckBox) v;
                    getItem(position).setEnabled(checkbox.isChecked());
                    Toast.makeText(context, "Checkbox from row " + position + " was checked", Toast.LENGTH_LONG).show();
                }
            });
            holder.daysOfWeek = daysOfWeek;
            holder.daysOfWeek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddNotifierActivity.class);
                    intent.putExtra( Notifier.EXTRA_NAME, n );
                    ((Activity) context).startActivityForResult(intent, Notifier.RESULT_SAVED);
                    /*TextView view = (TextView) v;
                    Toast.makeText(context, "Days of Week from row " + position + " was pressed: " + view.getText(), Toast.LENGTH_LONG).show();*/
                }
            });
            holder.time = time;
            holder.time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddNotifierActivity.class);
                    intent.putExtra( Notifier.EXTRA_NAME, n );
                    ((Activity) context).startActivityForResult(intent, Notifier.RESULT_SAVED);
                    /*TextView view = (TextView) v;
                    Toast.makeText(context, "Time from row " + position + " was pressed: " + view.getText(), Toast.LENGTH_LONG).show();*/
                }
            });
            holder.postalCode = postalCode;
            holder.postalCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddNotifierActivity.class);
                    intent.putExtra( Notifier.EXTRA_NAME, n );
                    ((Activity) context).startActivityForResult(intent, Notifier.RESULT_SAVED);
                    /*TextView view = (TextView) v;
                    Toast.makeText(context, "Postal Code from row " + position + " was pressed: " + view.getText(), Toast.LENGTH_LONG).show();*/
                }
            });

            row.setTag(holder);
        }
        else {
            holder = (ViewHolder)row.getTag();
        }

        holder.enabled.setEnabled(n.isEnabled());
        holder.daysOfWeek.setText(Integer.toString(n.getDaysOfWeek()));
        holder.time.setText( JawnContract.formatTime( n.getHour(), n.getMinute() ) );
        holder.postalCode.setText(n.getPostalCode());

        return row;

    }
}
