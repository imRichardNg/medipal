package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import iss.nus.medipal.AppFolder.ICE;
import iss.nus.medipal.dao.ICEDAO;

/**
 * Created by richard on 4/3/17.
 */

public class AddICE extends AsyncTask<ICE, Void, Long> {
    private ICEDAO iceDAO;

    public AddICE(Context context) {
        this.iceDAO = new ICEDAO(context);
    }

    @Override
    protected Long doInBackground(ICE... params) {
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
