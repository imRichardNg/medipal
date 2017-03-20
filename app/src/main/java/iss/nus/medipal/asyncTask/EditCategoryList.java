package iss.nus.medipal.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import iss.nus.medipal.AppFolder.Category;
import iss.nus.medipal.dao.CategoryDAO;

/**
 * Created by richard on 17/3/17.
 */
public class EditCategoryList extends AsyncTask<Category, Void, Long> {
    private CategoryDAO categoryDAO;

    public EditCategoryList(Context context) {
        categoryDAO = new CategoryDAO(context);
    }

    @Override
    protected Long doInBackground(Category... params) {
        long result = categoryDAO.update(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        if (categoryDAO != null) {
            categoryDAO.close();
        }
    }
}
