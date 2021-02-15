package com.example.reminder;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class setAlarmView extends AppCompatActivity implements View.OnClickListener{

    public static TextView selectedDate;
    public static TextView selectedTime;
    public String title;
    private Button setDateButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm_view);
        selectedDate = (TextView)findViewById(R.id.setedDate);
        selectedTime = (TextView)findViewById(R.id.setedTime);
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
        }
    }
    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }
public void createNotificationChannel(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                "Mascot Notification", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager mNotificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableVibration(true);
        notificationChannel.setDescription("Notification from Mascot");
    }
}

}
