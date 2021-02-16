package com.example.reminder;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;
import java.util.Date;


public class NewReminder extends AppCompatActivity implements View.OnClickListener{

    public static TextView selectedDate;
    public static TextView selectedTime;
    public static Task task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);
        selectedDate = (TextView)findViewById(R.id.setedDate);
        selectedTime = (TextView)findViewById(R.id.setedTime);
        task = new Task();
        createNotificationChannels();
    }

    public void showTimePickerDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(),"timePicker");

    }
   /* private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePicker =
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month , int day) {
                        selectedDate = (TextView)findViewById(R.id.setedDate);
                        selectedDate.setText(day+"-"+month+"-"+year);
                    }
                }, year, month, day);

        datePicker.show();
            }
*/
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.DateButton:
                showDatePickerDialog();
                break;
            case R.id.timeButton:
                showTimePickerDialog();
                break;
            case R.id.addReminderButton:
                addReminder();
                break;
            default:
                System.out.println("NO ON CLICK ACTION FOUND!!");
        }
    }
    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }
    public void addReminder() {
        long timeAtClicking = System.currentTimeMillis();
        System.out.println("Timr at click "+ timeAtClicking);
        long timeDifference = (task.calendar.getTime().getTime()) - (new Date().getTime());
        System.out.println("Timr differe  "+ timeDifference);
        System.out.println("WAIT FOR   "+ timeDifference+timeAtClicking);
        //System.out.println("TIME fron calender "+(task.calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()));
        EditText titleText = findViewById(R.id.titleText);
        Switch isImportant = findViewById(R.id.important);
        task.title = titleText.getText().toString();
        task.important = isImportant.isChecked();

        //Clean all inputs to re-enter another reminder's data
        titleText.setText("");
        selectedDate.setText("");
        selectedTime.setText("");
        isImportant.setChecked(false);

        Intent reminderIntent = new Intent(this, ReminderManager.class);

        PendingIntent onTappedNotification = PendingIntent.getBroadcast(this, 1, reminderIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,  timeDifference, onTappedNotification);


        Toast.makeText(this,"The reminder was set successfully!",Toast.LENGTH_SHORT).show();
    }
    public void createNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel lowImportantChannel = new NotificationChannel("low_important_channel",
                    "Low Important Reminder Notification", NotificationManager.IMPORTANCE_LOW);
            NotificationChannel highImportantChannel = new NotificationChannel("high_important_channel",
                    "High Important Reminder Notification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(lowImportantChannel);
            manager.createNotificationChannel(highImportantChannel);
        }

    }

}
