package iss.nus.medipal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import iss.nus.medipal.AppFolder.Reminder;
import iss.nus.medipal.AppFolder.SpinnerObject;

import static iss.nus.medipal.dao.DataBaseHelper.WHERE_ID_EQUALS;

/**
 * Created by richard on 17/3/17.
 */

public class ReminderDAO extends DBDAO implements DAOInterface<Reminder> {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy H:mm", Locale.ENGLISH);

    public ReminderDAO(Context context) {
        super(context);
    }

    @Override
    public long save(Reminder reminder) {
        ContentValues values = new ContentValues();

        System.out.println("Add Reminder: " + reminder);

        values.put(DataBaseHelper.START_TIME, formatter.format(reminder.getStartDate()));
        values.put(DataBaseHelper.FREQUENCY, reminder.getFrequency());
        values.put(DataBaseHelper.INTERVAL, reminder.getInterval());

        return database.insert(DataBaseHelper.REMINDER_TABLE, null, values);
    }

    @Override
    public long update(Reminder reminder) {

        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.ID, reminder.getId());
        values.put(DataBaseHelper.START_TIME, formatter.format(reminder.getStartDate()));
        values.put(DataBaseHelper.FREQUENCY, reminder.getFrequency());
        values.put(DataBaseHelper.INTERVAL, reminder.getInterval());

        return database.update(DataBaseHelper.REMINDER_TABLE, values, WHERE_ID_EQUALS, new String[]{String.valueOf(reminder.getId())});
    }

    @Override
    public long delete(Reminder reminder) {
        return database.delete(DataBaseHelper.REMINDER_TABLE, WHERE_ID_EQUALS,
                new String[]{String.valueOf(reminder.getId())});
    }

    @Override
    public Reminder get(Reminder reminder) {
        String sql = "SELECT " + DataBaseHelper.ID + ", "
                + DataBaseHelper.START_TIME + ", "
                + DataBaseHelper.FREQUENCY + ", "
                + DataBaseHelper.INTERVAL
                + " FROM " + DataBaseHelper.REMINDER_TABLE
                + " WHERE " + WHERE_ID_EQUALS;

        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(reminder.getId())});

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            Date startDate = null;
            int frequency = cursor.getInt(2);
            int interval = cursor.getInt(3);

            try {
                startDate = formatter.parse(cursor.getString(1));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return new Reminder(id, startDate, frequency, interval);
        }

        return new Reminder();
    }

    @Override
    public List<Reminder> getAll() {
        List<Reminder> reminders = new ArrayList<Reminder>();

        String sql = "SELECT " + DataBaseHelper.ID + ", "
                + DataBaseHelper.START_TIME + ", "
                + DataBaseHelper.FREQUENCY + ", "
                + DataBaseHelper.INTERVAL
                + " FROM " + DataBaseHelper.REMINDER_TABLE;

        Cursor cursor = database.rawQuery(sql, null);


        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            Date startDate = null;
            int frequency = cursor.getInt(2);
            int interval = cursor.getInt(3);

            try {
                startDate = formatter.parse(cursor.getString(1));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Reminder reminder = new Reminder(id, startDate, frequency, interval);
            reminders.add(reminder);
        }

        return reminders;
    }


    public List<SpinnerObject> getAllReminder() {
        List<SpinnerObject> lables = new ArrayList<SpinnerObject>();

        // db portion
        String sql = "SELECT " + DataBaseHelper.ID + ", "
                + DataBaseHelper.FREQUENCY
                + " FROM " + DataBaseHelper.REMINDER_TABLE;

        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            lables.add(new SpinnerObject(cursor.getInt(0), cursor.getInt(0) + "_" + cursor.getString(1)));
        }

        if (cursor.getCount() == 0) {
            lables.add((new SpinnerObject(1, "1_2")));
            lables.add((new SpinnerObject(2, "2_5")));
            lables.add((new SpinnerObject(3, "3_3")));
        }

        return lables;
    }
}
