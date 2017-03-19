package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import iss.nus.medipal.AppFolder.Measurement;
import iss.nus.medipal.AppFolder.MeasurementData;
import iss.nus.medipal.dao.MEASUREMENTDAO;

/**
 * Created by nus on 17/3/17.
 */

public class UpdateMEASUREMENT extends AsyncTask <MeasurementData, Void, Long>{
    private MEASUREMENTDAO measurementdao;

    public UpdateMEASUREMENT (Context context)
    {
        measurementdao= new MEASUREMENTDAO(context);

    }
    @Override
    protected Long doInBackground(MeasurementData... params) {
        long result = measurementdao.update(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        if(measurementdao != null) {
            measurementdao.close();
        }
    }
}
