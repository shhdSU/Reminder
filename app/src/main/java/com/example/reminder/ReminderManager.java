package com.example.reminder;


import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import static com.example.reminder.MainActivity.DB;

public class ReminderManager extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        Intent reminderIntent = new Intent(context, ReminderDetails.class);
        int id =intent.getIntExtra("id",-5);
        Task newTask = DB.retrieveReminder(id);
        reminderIntent.putExtra("reminderTitle", newTask.title);
        reminderIntent.putExtra("reminderDate", newTask.date);
        reminderIntent.putExtra("reminderTime", newTask.time);
        reminderIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,id,reminderIntent,0);

        if (newTask.important) {
            NotificationCompat.Builder notifyBuilder =
                    new NotificationCompat.Builder(context, "high_important_channel")
                            .setContentTitle("REMINDER!")
                            .setContentText(newTask.title)
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
                            .setContentText(newTask.title)
                            .setSmallIcon(R.drawable.reminder)
                            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.reminder))
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true)
                            .setPriority(NotificationCompat.PRIORITY_LOW);
            manager.notify(2, notifyBuilder.build());
        }

    }

}
