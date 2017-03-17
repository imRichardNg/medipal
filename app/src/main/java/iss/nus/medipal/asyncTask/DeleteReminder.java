package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import iss.nus.medipal.AppFolder.Reminder;
import iss.nus.medipal.dao.ReminderDAO;

/**
 * Created by richard on 17/3/17.
 */
public class DeleteReminder extends AsyncTask<Reminder, Void, Long> {
    private ReminderDAO reminderDAO;

    public DeleteReminder(Context context) {
        reminderDAO = new ReminderDAO(context);
    }

    @Override
    protected Long doInBackground(Reminder... params) {
        long result = reminderDAO.delete(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        if (reminderDAO != null) {
            reminderDAO.close();
        }
    }
}
