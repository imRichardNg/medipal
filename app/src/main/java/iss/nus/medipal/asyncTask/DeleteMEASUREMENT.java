package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import iss.nus.medipal.AppFolder.Health_Bio;
import iss.nus.medipal.AppFolder.Measurement;
import iss.nus.medipal.AppFolder.MeasurementData;
import iss.nus.medipal.dao.HEALTHBIODAO;
import iss.nus.medipal.dao.MEASUREMENTDAO;

/**
 * Created by nus on 12/3/17.
 */

public class DeleteMEASUREMENT extends AsyncTask<MeasurementData, Void, Long> {

    private MEASUREMENTDAO measurementdao;

    public DeleteMEASUREMENT(Context context)
    {
        measurementdao= new MEASUREMENTDAO(context);

    }
    @Override
    protected Long doInBackground(MeasurementData... params) {
        long result = measurementdao.delete(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        if(measurementdao != null) {
            measurementdao.close();
        }
    }
}
