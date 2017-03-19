package iss.nus.medipal.notification;

import android.app.Notification;
import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import iss.nus.medipal.AppFolder.Reminder;
import iss.nus.medipal.application.App;

/**
 * Created by richard on 19/3/17.
 */

public class MedipalNotificationManager {
    private static List<Notification> notification = new ArrayList<Notification>();
    private static Calendar calendar = Calendar.getInstance();

    private static List<Long> activeReminders = new ArrayList<>();

    public static void refreshAlarm(Context context) {
        NotificationScheduler.removeAllNotifications(context);

        List<Reminder> reminders = App.medipal.getReminderList(context);
        for (int i = 0; i < reminders.size(); i++) {
            System.out.println(reminders.get(i));
            scheduleNotificationFromReminder(context, reminders.get(i), null);
        }
    }

    private static void scheduleNotificationFromReminder(Context context, Reminder reminder, ReminderType reminderType) {
        Calendar currTime = Calendar.getInstance();
        List<Long> remindersInMillis = new ArrayList<Long>();
        calendar.setTime(reminder.getStartDate());

        for (int i = 0; i < reminder.getFrequency(); i++) {
            calendar.add(Calendar.HOUR, i * reminder.getInterval());

            if (calendar.getTimeInMillis() > currTime.getTimeInMillis()) {
                if (ReminderType.MEDICINE == reminderType) {
                    System.out.println("==> eat medicine @ " + calendar.getTime().toString());
                    NotificationScheduler.scheduleNotification(context, "Remember to eat your medicine!", calendar);
                } else {
                    System.out.println("==> reminder event @ " + calendar.getTime().toString());
                    NotificationScheduler.scheduleNotification(context, "Reminder for your upcoming event!", calendar);
                }
            }
        }
    }
}
