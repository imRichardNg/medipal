package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;


import iss.nus.medipal.AppFolder.Personal_Bio;
import iss.nus.medipal.dao.PERSONALBIODAO;

/**
 * Created by nus on 11/3/17.
 */

public class ListPERSONALBIO extends AsyncTask<Void,Void,List<Personal_Bio>> {
    private PERSONALBIODAO personalbiodao;

    public ListPERSONALBIO(Context context) {
        this.personalbiodao = new PERSONALBIODAO(context);// initailisation of personal bio
    }
    @Override
    protected List<Personal_Bio> doInBackground(Void... args0) {
        List<Personal_Bio> result = personalbiodao.getAll();
        return result;
    }

    @Override
    protected void onPostExecute(List<Personal_Bio> iceList) {
        if (personalbiodao != null) {
            personalbiodao.close();
        }

    }
}
