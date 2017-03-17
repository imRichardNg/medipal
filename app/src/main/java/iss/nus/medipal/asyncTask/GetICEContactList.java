package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import iss.nus.medipal.AppFolder.ICEContact;
import iss.nus.medipal.dao.ICEContactDAO;

/**
 * Created by richard on 4/3/17.
 */

public class GetICEContactList extends AsyncTask<Void, Void, List<ICEContact>> {
    private ICEContactDAO iceContactDAO;

    public GetICEContactList(Context context) {
        this.iceContactDAO = new ICEContactDAO(context);
    }

    @Override
    protected List<ICEContact> doInBackground(Void... args0) {
        List<ICEContact> result = iceContactDAO.getAll();
        return result;
    }

    @Override
    protected void onPostExecute(List<ICEContact> iceContactList) {
        if (iceContactDAO != null) {
            iceContactDAO.close();
        }
    }
}
