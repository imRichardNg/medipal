package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import iss.nus.medipal.AppFolder.Medicine;
import iss.nus.medipal.dao.MedicineDAO;


/**
 * Created by RobbinM on 4/3/17.
 */

public class GetMedicineList extends AsyncTask<Void, Void, List<Medicine>> {
    private MedicineDAO medicineDAO;

    public GetMedicineList(Context context) {
        this.medicineDAO = new MedicineDAO(context);
    }

    @Override
    protected List<Medicine> doInBackground(Void... args0) {
        List<Medicine> result = medicineDAO.getAll();
        return result;
    }

    @Override
    protected void onPostExecute(List<Medicine> medicineList) {
        if (medicineDAO != null) {
            medicineDAO.close();
        }
    }
}
