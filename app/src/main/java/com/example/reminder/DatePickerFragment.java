package com.example.reminder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import static com.example.reminder.NewReminder.selectedDate;
import static com.example.reminder.NewReminder.task;


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        selectedDate.setText(day+"-"+(1+month)+"-"+year);
        task.calendar.set(Calendar.YEAR,year);
        task.calendar.set(Calendar.MONTH,month);
        task.calendar.set(Calendar.DAY_OF_MONTH,day);

       // System.out.println("Date in date picker is "+task.calendar.getTime());
    }
}
