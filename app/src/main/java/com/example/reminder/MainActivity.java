package com.example.reminder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create 2 channels for the 2 types of reminders
        createNotificationChannels();
        addButton = findViewById(R.id.button2);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //go to NewReminder activity
                openSetAlarmView();
            }
        });


    }

    public void openSetAlarmView(){
        Intent intent = new Intent(this, NewReminder.class);
        startActivity(intent);
    }
    public void createNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //create low Important Channel with id = low_important_channel
            NotificationChannel lowImportantChannel = new NotificationChannel("low_important_channel",
                    "Low Important Reminder Notification", NotificationManager.IMPORTANCE_LOW);
            //create High Important Channel with id = high_important_channel
            NotificationChannel highImportantChannel = new NotificationChannel("high_important_channel",
                    "High Important Reminder Notification", NotificationManager.IMPORTANCE_HIGH);
            //create notification manager
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(lowImportantChannel);
            manager.createNotificationChannel(highImportantChannel);
        }

    }
}