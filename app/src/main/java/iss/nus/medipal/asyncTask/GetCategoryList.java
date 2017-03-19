package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import iss.nus.medipal.AppFolder.Category;
import iss.nus.medipal.dao.CategoryDAO;

/**
 * Created by richard on 17/3/17.
 */

public class GetCategoryList extends AsyncTask<Void, Void, List<Category>> {
    private CategoryDAO categoryDAO;

    public GetCategoryList(Context context) {
        this.categoryDAO = new CategoryDAO(context);
    }

    @Override
    protected List<Category> doInBackground(Void... params) {
        List<Category> result = categoryDAO.getAll();
        return result;
    }

    @Override
    protected void onPostExecute(List<Category> reminderList) {
        if (categoryDAO != null) {
            categoryDAO.close();
        }
    }
}
