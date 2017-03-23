package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;
import iss.nus.medipal.dao.MedicineDAO;


import iss.nus.medipal.AppFolder.Medicine;

/**
 * Created by richard on 4/3/17.
 */

public class AddMedicine extends AsyncTask<Medicine, Void, Long> {
    private MedicineDAO medicineDAO;

    public AddMedicine(Context context) {
        this.medicineDAO = new MedicineDAO(context);
    }

    @Override
    protected Long doInBackground(Medicine... params) {
        long result = medicineDAO.save(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        if(medicineDAO != null) {
            medicineDAO.close();
        }
    }
}
