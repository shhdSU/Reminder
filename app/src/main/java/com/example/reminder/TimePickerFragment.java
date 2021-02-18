package com.example.reminder;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

import static com.example.reminder.NewReminder.selectedTime;
import static com.example.reminder.NewReminder.task;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current time as the default values for the picker
       final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        selectedTime.setText(view.getHour()+":"+view.getMinute());
        task.time = view.getHour()+":"+view.getMinute();
        task.calendar.set(Calendar.HOUR_OF_DAY,view.getHour());
        task.calendar.set(Calendar.MINUTE,view.getMinute());
        task.calendar.set(Calendar.SECOND,0);
        task.calendar.set(Calendar.MILLISECOND,0);


    }


}


