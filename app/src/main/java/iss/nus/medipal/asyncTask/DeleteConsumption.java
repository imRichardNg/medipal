package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import iss.nus.medipal.AppFolder.Consumption;
import iss.nus.medipal.dao.ConsumptionDAO;

public class DeleteConsumption extends AsyncTask<Consumption, Void, Long> {
    private ConsumptionDAO consumptionDAO;

    public DeleteConsumption(Context context) {
        this.consumptionDAO = new ConsumptionDAO(context);
    }

    @Override
    protected Long doInBackground(Consumption... params) {
        long result = consumptionDAO.delete(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        if (consumptionDAO != null) {
            consumptionDAO.close();
        }
    }
}

