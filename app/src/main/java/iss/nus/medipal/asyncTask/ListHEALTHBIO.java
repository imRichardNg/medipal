package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import iss.nus.medipal.AppFolder.Health_Bio;
import iss.nus.medipal.AppFolder.Personal_Bio;
import iss.nus.medipal.dao.HEALTHBIODAO;
import iss.nus.medipal.dao.PERSONALBIODAO;

/**
 * Created by nus on 12/3/17.
 */

public class ListHEALTHBIO extends AsyncTask<Void ,Void ,List <Health_Bio>> {

    private HEALTHBIODAO healthbiodao;

    public ListHEALTHBIO(Context context) {
        this.healthbiodao = new HEALTHBIODAO(context);// initailisation of personal bio
    }
    @Override
    protected List<Health_Bio> doInBackground(Void... args0) {
        List<Health_Bio> result = healthbiodao.getAll();
        return result;
    }

    @Override
    protected void onPostExecute(List<Health_Bio> iceList) {

        if (healthbiodao != null) {
            healthbiodao.close();
        }

    }
}
