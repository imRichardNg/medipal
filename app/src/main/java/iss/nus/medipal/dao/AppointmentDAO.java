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

import iss.nus.medipal.AppFolder.Appointment;

import static iss.nus.medipal.dao.DataBaseHelper.WHERE_ID_EQUALS;

public class AppointmentDAO extends DBDAO implements DAOInterface<Appointment>{

    private static final SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy H:mm", Locale.ENGLISH);
    public AppointmentDAO(Context context) {
        super(context);
    }

    @Override
    public long save(Appointment appointment) {
        ContentValues values = new ContentValues();

        System.out.println("Add Appointment: " + appointment);

        values.put(DataBaseHelper.LOCATION, appointment.getLocation());
        values.put(DataBaseHelper.APPOINTMENT, formatter.format(appointment.getAppointmentDT()));
        values.put(DataBaseHelper.DESCRIPTION, appointment.getDescription());

        return database.insert(DataBaseHelper.APPOINTMENT_TABLE, null, values);
    }

    @Override
    public long update(Appointment appointment) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.ID, appointment.getId());
        values.put(DataBaseHelper.LOCATION, appointment.getLocation());
        values.put(DataBaseHelper.APPOINTMENT, formatter.format(appointment.getAppointmentDT()));
        values.put(DataBaseHelper.DESCRIPTION, appointment.getDescription());

        return database.update(DataBaseHelper.APPOINTMENT_TABLE, values, WHERE_ID_EQUALS, new String[]{String.valueOf(appointment.getId())});
    }

    @Override
    public long delete(Appointment appointment) {
        return database.delete(DataBaseHelper.APPOINTMENT_TABLE, WHERE_ID_EQUALS,
                new String[]{appointment.getId() + ""});
    }

    @Override
    public Appointment get(Appointment appointment) {

        String sql = "SELECT " + DataBaseHelper.ID + ", "
                + DataBaseHelper.LOCATION + ", "
                + DataBaseHelper.APPOINTMENT + ", "
                + DataBaseHelper.DESCRIPTION
                + " FROM " + DataBaseHelper.APPOINTMENT_TABLE
                + " WHERE " + WHERE_ID_EQUALS;

        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(appointment.getId())});

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String location = cursor.getString(1);
            Date appointmentDT = null;
            String description = cursor.getString(3);

            try {
                appointmentDT = formatter.parse(cursor.getString(2));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return new Appointment(id, location,appointmentDT,description);
        }

        return new Appointment();
    }

    @Override
    public List<Appointment> getAll() {

        List<Appointment> appointments = new ArrayList<Appointment>();

        String sql = "SELECT " + DataBaseHelper.ID + ", "
                + DataBaseHelper.LOCATION + ", "
                + DataBaseHelper.APPOINTMENT + ", "
                + DataBaseHelper.DESCRIPTION
                + " FROM " + DataBaseHelper.APPOINTMENT_TABLE;

        Cursor cursor = database.rawQuery(sql, null);


        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String location = cursor.getString(1);
            Date appointmentDT = null;
            String description = cursor.getString(3);

            try {
                appointmentDT = formatter.parse(cursor.getString(2));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Appointment appointment = new Appointment(id,location,appointmentDT,description);
            appointments.add(appointment);
        }

        return appointments;
    }
}
