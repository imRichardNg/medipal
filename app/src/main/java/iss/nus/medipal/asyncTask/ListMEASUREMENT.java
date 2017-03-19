package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import iss.nus.medipal.AppFolder.Measurement;
import iss.nus.medipal.AppFolder.MeasurementData;
import iss.nus.medipal.dao.MEASUREMENTDAO;

/**
 * Created by nus on 17/3/17.
 */

public class ListMEASUREMENT extends AsyncTask<Void, Void, List<MeasurementData>> {
    private MEASUREMENTDAO measurementdao;

    public ListMEASUREMENT(Context context) {
        this.measurementdao = new MEASUREMENTDAO(context);// initailisation of personal bio
    }
    @Override
    protected List<MeasurementData> doInBackground(Void... args0) {
        List<MeasurementData> result = measurementdao.getAll();
        return result;
    }

    @Override
    protected void onPostExecute(List<MeasurementData> iceList) {
        if (measurementdao != null) {
            measurementdao.close();
        }

    }
}
