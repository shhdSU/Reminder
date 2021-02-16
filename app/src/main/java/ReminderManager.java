import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.reminder.R;

import static com.example.reminder.NewReminder.task;

public class ReminderManager extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager manager = createNotificationChannels();
        if (task.important) {
            NotificationCompat.Builder notifyBuilder =
                    new NotificationCompat.Builder(context, "high_important_channel")
                            .setContentTitle("REMINDER!")
                            .setContentText(task.title)
                            .setSmallIcon(R.drawable.reminder)
                            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.reminder))
                            .setContentIntent(intent)
                            .setAutoCancel(true)
                            .setPriority(NotificationCompat.PRIORITY_HIGH);

            manager.notify(1, notifyBuilder.build());
        } else {
            NotificationCompat.Builder notifyBuilder =
                    new NotificationCompat.Builder(context, "low_important_channel")
                            .setContentTitle("REMINDER!")
                            .setContentText(task.title)
                            .setSmallIcon(R.drawable.reminder)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.reminder))
                            .setContentIntent(intent)
                            .setAutoCancel(true)
                            .setPriority(NotificationCompat.PRIORITY_LOW);
            manager.notify(2, notifyBuilder.build());
        }

    }

}
