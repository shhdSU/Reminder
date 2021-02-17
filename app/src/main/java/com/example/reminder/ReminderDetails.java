package com.example.reminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReminderDetails extends AppCompatActivity {
    TextView titleText;
    TextView dateText;
    TextView timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_details);
        titleText = findViewById(R.id.titleText);
        dateText = findViewById(R.id.dateText);
        timeText = findViewById(R.id.timeText);
        Intent intent = getIntent();
        titleText.setText(intent.getStringExtra("reminderTitle"));
        dateText.setText(intent.getStringExtra("reminderDate"));
        timeText.setText(intent.getStringExtra("reminderTime"));
    }
}