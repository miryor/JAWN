package com.miryor.jawn;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddNotifierActivity extends AppCompatActivity {

    int selectedDaysOfWeek = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notifier);
    }

    public void showTimePickerDialog(View view) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                TextView time = (TextView) findViewById(R.id.notifier_time);
                time.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    public void onDayOfWeekClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

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
    }
}
