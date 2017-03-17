package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import iss.nus.medipal.AppFolder.ICEContact;
import iss.nus.medipal.dao.ICEContactDAO;

/**
 * Created by richard on 4/3/17.
 */

public class AddICEContact extends AsyncTask<ICEContact, Void, Long> {
    private ICEContactDAO iceDAO;

    public AddICEContact(Context context) {
        this.iceDAO = new ICEContactDAO(context);
    }

    @Override
    protected Long doInBackground(ICEContact... params) {
        long result = iceDAO.save(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        if(iceDAO != null) {
            iceDAO.close();
        }
    }
}
