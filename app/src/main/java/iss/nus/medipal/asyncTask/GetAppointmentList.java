package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import iss.nus.medipal.AppFolder.Appointment;
import iss.nus.medipal.dao.AppointmentDAO;

public class GetAppointmentList extends AsyncTask<Void, Void, List<Appointment>> {

    private AppointmentDAO appointmentDAO;

    public GetAppointmentList(Context context) {
        this.appointmentDAO = new AppointmentDAO(context);
    }

    @Override
    protected List<Appointment> doInBackground(Void... args0) {
        List<Appointment> result = appointmentDAO.getAll();
        return result;
    }

    @Override
    protected void onPostExecute(List<Appointment> appointmentList) {
        if (appointmentDAO != null) {
            appointmentDAO.close();
        }
    }
}
