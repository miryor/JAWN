package com.miryor.jawn;

import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Visibility;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.miryor.jawn.model.Notifier;

import java.util.Calendar;

public class AddNotifierActivity extends AppCompatActivity {

    Notifier notifier = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notifier);

        Intent intent = getIntent();
        Notifier n = (Notifier) intent.getParcelableExtra(Notifier.EXTRA_NAME);
        if ( n != null ) {
            notifier = n;
        }
        else {
            notifier = new Notifier( 0L, "", 0, 0, 0, true );
        }
        if ( notifier.getId() > 0L ) {
            CheckBox cb = (CheckBox) findViewById(R.id.notifier_enabled);
            cb.setVisibility(View.VISIBLE);
            cb.setChecked( n.isEnabled() );

            CheckBox day = (CheckBox) findViewById(R.id.notifier_daysofweek_sunday);
            day.setChecked( (notifier.getDaysOfWeek() & JawnContract.DOW_SUNDAY) == JawnContract.DOW_SUNDAY );
            day = (CheckBox) findViewById(R.id.notifier_daysofweek_monday);
            day.setChecked( (notifier.getDaysOfWeek() & JawnContract.DOW_MONDAY) == JawnContract.DOW_MONDAY );
            day = (CheckBox) findViewById(R.id.notifier_daysofweek_tuesday);
            day.setChecked( (notifier.getDaysOfWeek() & JawnContract.DOW_TUESDAY) == JawnContract.DOW_TUESDAY );
            day = (CheckBox) findViewById(R.id.notifier_daysofweek_wednesday);
            day.setChecked( (notifier.getDaysOfWeek() & JawnContract.DOW_WEDNESDAY) == JawnContract.DOW_WEDNESDAY );
            day = (CheckBox) findViewById(R.id.notifier_daysofweek_thursday);
            day.setChecked( (notifier.getDaysOfWeek() & JawnContract.DOW_THURSDAY) == JawnContract.DOW_THURSDAY );
            day = (CheckBox) findViewById(R.id.notifier_daysofweek_friday);
            day.setChecked( (notifier.getDaysOfWeek() & JawnContract.DOW_FRIDAY) == JawnContract.DOW_FRIDAY );
            day = (CheckBox) findViewById(R.id.notifier_daysofweek_saturday);
            day.setChecked( (notifier.getDaysOfWeek() & JawnContract.DOW_SATURDAY) == JawnContract.DOW_SATURDAY );

            TextView time = (TextView) findViewById(R.id.notifier_time);
            time.setText( JawnContract.formatTime( n.getHour(), n.getMinute() ) );

            TextView postalCode = (TextView) findViewById(R.id.notifier_postalcode);
            postalCode.setText( n.getPostalCode() );

            Toast.makeText(this, notifier.getPostalCode(), Toast.LENGTH_LONG).show();
        }

    }

    public void showTimePickerDialog(View view) {
        final Notifier n = notifier;
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                TextView time = (TextView) findViewById(R.id.notifier_time);
                time.setText( selectedHour + ":" + selectedMinute);
                n.setHour(selectedHour);
                n.setMinute(selectedMinute);
            }
        }, notifier.getHour(), notifier.getMinute(), true);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    public void cancelNotifier(View view) {
        setResult(Notifier.RESULT_CANCELLED);
        finish();
    }

    public void saveNotifier(View view) {
        notifier.setPostalCode( ((TextView) findViewById(R.id.notifier_postalcode) ).getText().toString() );
        long id = JawnContract.saveNotifier( this, notifier );
        notifier.setId(id);

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra( NotificationPublisher.WEATHER_API_PROVIDER, NotificationPublisher.WEATHER_API_PROVIDER_WUNDERGROUND );
        notificationIntent.putExtra( NotificationPublisher.PASSED_POSTALCODE, notifier.getPostalCode() );
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, (int)notifier.getId(), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + 10000;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);

        Intent intent = new Intent();
        intent.putExtra( Notifier.EXTRA_NAME, notifier );
        setResult(Notifier.RESULT_SAVED, intent);
        finish();
    }

    public void onDayOfWeekClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        int selectedDaysOfWeek = notifier.getDaysOfWeek();

        switch ( view.getId() ) {
            case R.id.notifier_daysofweek_sunday:
                if (checked) selectedDaysOfWeek |= JawnContract.DOW_SUNDAY;
                else selectedDaysOfWeek ^= JawnContract.DOW_SUNDAY;
                break;
            case R.id.notifier_daysofweek_monday:
                if (checked) selectedDaysOfWeek |= JawnContract.DOW_MONDAY;
                else selectedDaysOfWeek ^= JawnContract.DOW_MONDAY;
                break;
            case R.id.notifier_daysofweek_tuesday:
                if (checked) selectedDaysOfWeek |= JawnContract.DOW_TUESDAY;
                else selectedDaysOfWeek ^= JawnContract.DOW_TUESDAY;
                break;
            case R.id.notifier_daysofweek_wednesday:
                if (checked) selectedDaysOfWeek |= JawnContract.DOW_WEDNESDAY;
                else selectedDaysOfWeek ^= JawnContract.DOW_WEDNESDAY;
                break;
            case R.id.notifier_daysofweek_thursday:
                if (checked) selectedDaysOfWeek |= JawnContract.DOW_THURSDAY;
                else selectedDaysOfWeek ^= JawnContract.DOW_THURSDAY;
                break;
            case R.id.notifier_daysofweek_friday:
                if (checked) selectedDaysOfWeek |= JawnContract.DOW_FRIDAY;
                else selectedDaysOfWeek ^= JawnContract.DOW_FRIDAY;
                break;
            case R.id.notifier_daysofweek_saturday:
                if (checked) selectedDaysOfWeek |= JawnContract.DOW_SATURDAY;
                else selectedDaysOfWeek ^= JawnContract.DOW_SATURDAY;
                break;

        }
        notifier.setDaysOfWeek(selectedDaysOfWeek);
    }
}
