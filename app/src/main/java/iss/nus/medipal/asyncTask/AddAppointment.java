package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import iss.nus.medipal.AppFolder.Appointment;
import iss.nus.medipal.AppFolder.Reminder;
import iss.nus.medipal.dao.AppointmentDAO;
import iss.nus.medipal.dao.ReminderDAO;

public class AddAppointment extends AsyncTask<Appointment, Void, Long> {
    private AppointmentDAO appointmentDAO;

    public AddAppointment(Context context) {
        this.appointmentDAO = new AppointmentDAO(context);
    }

    @Override
    protected Long doInBackground(Appointment... params) {
        long result = appointmentDAO.save(params[0]);

        return result;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        if(appointmentDAO != null) {
            appointmentDAO.close();
        }
    }

}

