package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import iss.nus.medipal.AppFolder.Reminder;
import iss.nus.medipal.AppFolder.SpinnerObject;
import iss.nus.medipal.dao.ReminderDAO;

/**
 * Created by richard on 17/3/17.
 */

public class GetReminderList extends AsyncTask<Void, Void, List<Reminder>> {
    private ReminderDAO reminderDAO;

    public GetReminderList(Context context) {
        this.reminderDAO = new ReminderDAO(context);
    }

    @Override
    protected List<Reminder> doInBackground(Void... params) {
        List<Reminder> result = reminderDAO.getAll();
        return result;
    }

    @Override
    protected void onPostExecute(List<Reminder> reminderList) {
        if (reminderDAO != null) {
            reminderDAO.close();
        }
    }

    public List<SpinnerObject> getReminderList() {
        return reminderDAO.getAllReminder();
    }
}
