package iss.nus.medipal.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import iss.nus.medipal.R;

/**
 * Created by richard on 19/3/17.
 */

public class NotificationScheduler {
    private static List<PendingIntent> activePendingIntent = new ArrayList<PendingIntent>();

    public static void scheduleNotification(Context context, String content, Calendar calendar) {
        Intent notificationIntent = new Intent(context, NotificationPublisher.class);

        Notification notification = createNotification(context, content);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (new Random()).nextInt(), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        activePendingIntent.add(pendingIntent);
    }

    private static Notification createNotification(final Context context, String content) {
        final Resources res = context.getResources();

        final String title = res.getString(
                R.string.reminder_notification_title_template);

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setTicker(content)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true);

        return builder.build();
    }

    public static void removeAllNotifications(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        for (int i = 0; i < activePendingIntent.size(); i++) {
            alarmManager.cancel(activePendingIntent.get(i));
        }
    }
}
