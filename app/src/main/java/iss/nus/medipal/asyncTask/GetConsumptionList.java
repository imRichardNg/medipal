package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import iss.nus.medipal.AppFolder.Consumption;
import iss.nus.medipal.AppFolder.SpinnerObject;
import iss.nus.medipal.dao.ConsumptionDAO;

public class GetConsumptionList extends AsyncTask<Void, Void, List<Consumption>> {

    private ConsumptionDAO consumptionDAO;

    public GetConsumptionList(Context context) {
        this.consumptionDAO = new ConsumptionDAO(context);
    }

    public List<SpinnerObject> getConsumptionMedicineList(){
       return consumptionDAO.getAllMedicine();
    }

    @Override
    protected List<Consumption> doInBackground(Void... args0) {
        List<Consumption> result = consumptionDAO.getAll();
        return result;
    }

    @Override
    protected void onPostExecute(List<Consumption> appointmentList) {
        if (consumptionDAO != null) {
            consumptionDAO.close();
        }
    }
}
