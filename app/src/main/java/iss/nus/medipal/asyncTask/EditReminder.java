package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import iss.nus.medipal.AppFolder.Reminder;
import iss.nus.medipal.dao.ReminderDAO;

/**
 * Created by richard on 17/3/17.
 */
public class EditReminder extends AsyncTask<Reminder, Void, Long> {
    private ReminderDAO reminderDAO;

    public EditReminder(Context context) {
        reminderDAO = new ReminderDAO(context);
    }

    @Override
    protected Long doInBackground(Reminder... params) {
        long result = reminderDAO.update(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        if (reminderDAO != null) {
            reminderDAO.close();
        }
    }
}
