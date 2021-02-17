package com.example.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class NewReminder extends AppCompatActivity implements View.OnClickListener{
    public static int remindersCount = 0;
    public static TextView selectedDate; // text view to show the selected date from the date picker
    public static TextView selectedTime; // text view to show the selected time from the time picker
    public static Task task; // the current task object which will be used in the code
    final String FILE_NAME = "Reminders.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);
        selectedDate = findViewById(R.id.setedDate);
        selectedTime = findViewById(R.id.setedTime);
        task = new Task();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.DateButton: // on click on the "Set date" button
                showDatePickerDialog();
                break;
            case R.id.timeButton:// on click on the "Set time" button
                showTimePickerDialog();
                break;
            case R.id.addReminderButton:// on click on the "Add reminder" button
                addReminder();
                break;
            default:
                System.out.println("NO ON CLICK ACTION FOUND!!");
        }
    }
    public void showTimePickerDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(),"timePicker");
    }
    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    public void addReminder() {

        EditText titleText = findViewById(R.id.titleText); // edit text element to enter the reminder title
        Switch isImportant = findViewById(R.id.important); // switch element to indicate the level of importance for the reminder
        task.title = titleText.getText().toString(); // assign the entered title to the current task object
        task.important = isImportant.isChecked(); // assign the importance of the reminder as BOOLEAN in the current task object

        //Clean all inputs to re-enter another reminder's data
        titleText.setText("");
        selectedDate.setText("");
        selectedTime.setText("");
        isImportant.setChecked(false);

        //Prepare the intents to be used to set the alarm
        Intent reminderIntent = new Intent(this, ReminderManager.class);
        PendingIntent onTappedNotification = PendingIntent.getBroadcast(this, ++remindersCount, reminderIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //Create the alarm manager to schedule the reminder's notification based on its date and time
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,  task.calendar.getTimeInMillis(), onTappedNotification);
        writeToRemindersFile();
        Toast.makeText(this,"The reminder was set successfully!",Toast.LENGTH_SHORT).show();
    }

    public void writeToRemindersFile(){
        try {
            FileOutputStream toWrite = openFileOutput(FILE_NAME,Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
