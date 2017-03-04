package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import iss.nus.medipal.application.ice.ICE;
import iss.nus.medipal.dao.ICEDAO;

/**
 * Created by richard on 4/3/17.
 */

public class ListICE extends AsyncTask<Void, Void, List<ICE>> {
    private ICEDAO iceDAO;

    public ListICE(Context context) {
        this.iceDAO = new ICEDAO(context);
    }

    @Override
    protected List<ICE> doInBackground(Void... args0) {
        List<ICE> result = iceDAO.getAll();
        return result;
    }

    @Override
    protected void onPostExecute(List<ICE> iceList) {
        if (iceDAO != null) {
            iceDAO.close();
        }
    }
}
