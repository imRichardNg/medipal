package iss.nus.medipal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import iss.nus.medipal.AppFolder.Category;

import static iss.nus.medipal.dao.DataBaseHelper.WHERE_ID_EQUALS;

/**
 * Created by richard on 4/3/17.
 */

public class CategoryDAO extends DBDAO implements DAOInterface<Category> {
    public CategoryDAO(Context context) {
        super(context);
    }

    @Override
    public long save(Category category) {
        ContentValues values = new ContentValues();

        System.out.println("Add category: " + category);

        values.put(DataBaseHelper.CATEGORY, category.getCategory());
        values.put(DataBaseHelper.CODE, category.getCode());
        values.put(DataBaseHelper.CAT_DESCRIPTION, category.getCategoryDescription());
        values.put(DataBaseHelper.REMIND, category.isReminder()? 1 : 0);
        return database.insert(DataBaseHelper.CATEGORY_TABLE, null, values);
    }

    @Override
    public long update(Category category) {
        ContentValues values = new ContentValues();

        System.out.println("Update category: " + category);

        values.put(DataBaseHelper.CAT_ID, category.getCategoryID());
        values.put(DataBaseHelper.CATEGORY, category.getCategory());
        values.put(DataBaseHelper.CODE, category.getCode());
        values.put(DataBaseHelper.CAT_DESCRIPTION, category.getCategoryDescription());
        values.put(DataBaseHelper.REMIND, category.isReminder()? 1 : 0);
        return database.update(DataBaseHelper.CATEGORY_TABLE, values, WHERE_ID_EQUALS, new String[]{String.valueOf(category.getCategoryID())});
    }

    @Override
    public long delete(Category category) {
        return database.delete(DataBaseHelper.CATEGORY_TABLE, WHERE_ID_EQUALS,
                new String[]{category.getCategoryID() + ""});
    }

    @Override
    public Category get(Category category) {
        String sql = "SELECT " + DataBaseHelper.CAT_ID + ", "
                + DataBaseHelper.CATEGORY + ", "
                + DataBaseHelper.CODE + ", "
                + DataBaseHelper.CAT_DESCRIPTION + ", "
                + DataBaseHelper.REMIND
                + " FROM " + DataBaseHelper.CATEGORY_TABLE
                + " WHERE " + WHERE_ID_EQUALS;

        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(category.getCategoryID())});

        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String catName = cursor.getString(1);
            String code = cursor.getString(2);
            int catDescription = cursor.getInt(3);
        }

        return new Category();
    }

    @Override
    public List<Category> getAll() {
        System.out.println("category.getAll() is called.");
        List<Category> categoryList = new ArrayList<Category>();

        String sql = "SELECT " + DataBaseHelper.CAT_ID + ", "
                + DataBaseHelper.CATEGORY + ", "
                + DataBaseHelper.CODE + ", "
                + DataBaseHelper.CAT_DESCRIPTION + ", "
                + DataBaseHelper.REMIND
                + " FROM " + DataBaseHelper.CATEGORY_TABLE;


        Cursor cursor = database.rawQuery(sql, null);


        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String catName = cursor.getString(1);
            String code = cursor.getString(2);
            String catDescription = cursor.getString(3);
            boolean isReminder = cursor.getInt(4) == 1;

            Category category = new Category(id, catName, code, catDescription, isReminder);
            System.out.println("category: " +category);
            categoryList.add(category);
        }

        return categoryList;
    }
}
