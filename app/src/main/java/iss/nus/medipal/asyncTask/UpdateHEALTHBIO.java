package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import iss.nus.medipal.AppFolder.Health_Bio;
import iss.nus.medipal.dao.HEALTHBIODAO;

/**
 * Created by nus on 12/3/17.
 */

public class UpdateHEALTHBIO extends AsyncTask< Health_Bio, Void, Long> {

    private HEALTHBIODAO healthbiodao;

    public UpdateHEALTHBIO (Context context)
    {
        healthbiodao= new HEALTHBIODAO(context);

    }
    @Override
    protected Long doInBackground(Health_Bio... params) {
        long result = healthbiodao.update(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        if(healthbiodao != null) {
            healthbiodao.close();
        }
    }
}
