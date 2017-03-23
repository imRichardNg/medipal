package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import iss.nus.medipal.AppFolder.ICEContact;
import iss.nus.medipal.AppFolder.Medicine;
import iss.nus.medipal.dao.MedicineDAO;


/**
 * Created by richard on 16/3/17.
 */
public class DeleteMedicine extends AsyncTask<Medicine, Void, Long> {
    private MedicineDAO medicineDAO;

    public DeleteMedicine(Context context) {
        this.medicineDAO = new MedicineDAO(context);
    }

    @Override
    protected Long doInBackground(Medicine... params) {
        long result = medicineDAO.delete(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        if (medicineDAO != null) {
            medicineDAO.close();
        }
    }
}
