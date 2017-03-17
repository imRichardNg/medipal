package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import iss.nus.medipal.AppFolder.ICEContact;
import iss.nus.medipal.dao.ICEContactDAO;

/**
 * Created by richard on 16/3/17.
 */
public class EditICEContact extends AsyncTask<ICEContact, Void, Long> {
    private ICEContactDAO iceContactDAO;

    public EditICEContact(Context context) {
        this.iceContactDAO = new ICEContactDAO(context);
    }

    @Override
    protected Long doInBackground(ICEContact... params) {
        long result = iceContactDAO.update(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        if (iceContactDAO != null) {
            iceContactDAO.close();
        }
    }
}
