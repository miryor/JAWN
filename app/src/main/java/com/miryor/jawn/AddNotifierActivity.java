package com.miryor.jawn;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
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
                if (checked) selectedDaysOfWeek |= JawnContract.JawnNotifier.DOW_SUNDAY;
                else selectedDaysOfWeek ^= JawnContract.JawnNotifier.DOW_SUNDAY;
                break;
            case R.id.notifier_daysofweek_monday:
                if (checked) selectedDaysOfWeek |= JawnContract.JawnNotifier.DOW_MONDAY;
                else selectedDaysOfWeek ^= JawnContract.JawnNotifier.DOW_MONDAY;
                break;
            case R.id.notifier_daysofweek_tuesday:
                if (checked) selectedDaysOfWeek |= JawnContract.JawnNotifier.DOW_TUESDAY;
                else selectedDaysOfWeek ^= JawnContract.JawnNotifier.DOW_TUESDAY;
                break;
            case R.id.notifier_daysofweek_wednesday:
                if (checked) selectedDaysOfWeek |= JawnContract.JawnNotifier.DOW_WEDNESDAY;
                else selectedDaysOfWeek ^= JawnContract.JawnNotifier.DOW_WEDNESDAY;
                break;
            case R.id.notifier_daysofweek_thursday:
                if (checked) selectedDaysOfWeek |= JawnContract.JawnNotifier.DOW_THURSDAY;
                else selectedDaysOfWeek ^= JawnContract.JawnNotifier.DOW_THURSDAY;
                break;
            case R.id.notifier_daysofweek_friday:
                if (checked) selectedDaysOfWeek |= JawnContract.JawnNotifier.DOW_FRIDAY;
                else selectedDaysOfWeek ^= JawnContract.JawnNotifier.DOW_FRIDAY;
                break;
            case R.id.notifier_daysofweek_saturday:
                if (checked) selectedDaysOfWeek |= JawnContract.JawnNotifier.DOW_SATURDAY;
                else selectedDaysOfWeek ^= JawnContract.JawnNotifier.DOW_SATURDAY;
                break;

        }
        notifier.setDaysOfWeek(selectedDaysOfWeek);
    }
}
