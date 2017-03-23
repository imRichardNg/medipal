package iss.nus.medipal.asyncTask;

/**
 * Created by Kyaw on 3/21/2017.
 */

import android.content.Context;
import android.os.AsyncTask;

import iss.nus.medipal.AppFolder.Medicine;
import iss.nus.medipal.dao.MedicineDAO;

/**
 * Created by richard on 16/3/17.
 */
public class EditMedicine extends AsyncTask<Medicine, Void, Long> {
    private MedicineDAO medicineDAO;

    public EditMedicine(Context context) {
        this.medicineDAO = new MedicineDAO(context);
    }

    @Override
    protected Long doInBackground(Medicine... params) {
        long result = medicineDAO.update(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        if (medicineDAO != null) {
            medicineDAO.close();
        }
    }
}