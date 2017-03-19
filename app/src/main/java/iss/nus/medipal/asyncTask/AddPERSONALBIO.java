package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;
import iss.nus.medipal.AppFolder.Personal_Bio;
import iss.nus.medipal.dao.PERSONALBIODAO;

/**
 * Created by nus on 11/3/17.
 */

public class AddPERSONALBIO extends AsyncTask<Personal_Bio, Void, Long>  {

    private PERSONALBIODAO personalbiodao;

    public AddPERSONALBIO(Context context) {
        this.personalbiodao = new PERSONALBIODAO(context);
    }

    @Override
    protected Long doInBackground(Personal_Bio... params) {
        long result = personalbiodao.save(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        if(personalbiodao != null) {
            personalbiodao.close();
        }
    }
}
