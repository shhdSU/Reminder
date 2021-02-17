package com.example.reminder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import static com.example.reminder.NewReminder.remindersCount;
import static com.example.reminder.NewReminder.selectedDate;
import static com.example.reminder.NewReminder.selectedTime;
import static com.example.reminder.NewReminder.task;

public class ReminderManager extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        Intent reminderIntent = new Intent(context, ReminderDetails.class);
        reminderIntent.putExtra("reminderTitle", task.title);
        String reminderDate = selectedDate.getText().toString();
        String reminderTime = selectedTime.getText().toString();
        reminderIntent.putExtra("reminderDate", reminderDate);
        reminderIntent.putExtra("reminderTime", reminderTime);
        reminderIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,remindersCount,reminderIntent,0);

        if (task.important) {
            NotificationCompat.Builder notifyBuilder =
                    new NotificationCompat.Builder(context, "high_important_channel")
                            .setContentTitle("REMINDER!")
                            .setContentText(task.title)
                            .setSmallIcon(R.drawable.reminder)
                            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.reminder))
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true)
                            .setPriority(NotificationCompat.PRIORITY_HIGH);

            manager.notify(1, notifyBuilder.build());
        } else {
            NotificationCompat.Builder notifyBuilder =
                    new NotificationCompat.Builder(context, "low_important_channel")
                            .setContentTitle("REMINDER!")
                            .setContentText(task.title)
                            .setSmallIcon(R.drawable.reminder)
                            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.reminder))
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true)
                            .setPriority(NotificationCompat.PRIORITY_LOW);
            manager.notify(2, notifyBuilder.build());
        }

    }

}
