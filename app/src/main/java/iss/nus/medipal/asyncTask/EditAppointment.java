package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import iss.nus.medipal.AppFolder.Appointment;
import iss.nus.medipal.dao.AppointmentDAO;

public class EditAppointment  extends AsyncTask<Appointment, Void, Long>{

    private AppointmentDAO appointmentDAO;

    public EditAppointment(Context context) {
        this.appointmentDAO = new AppointmentDAO(context);
    }

    @Override
    protected Long doInBackground(Appointment... params) {
        long result = appointmentDAO.update(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        if (appointmentDAO != null) {
            appointmentDAO.close();
        }
    }
}
