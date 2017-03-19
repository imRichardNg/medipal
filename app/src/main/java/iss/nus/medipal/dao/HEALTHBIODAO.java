package iss.nus.medipal.dao;

/**
 * Created by nus on 12/3/17.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import iss.nus.medipal.AppFolder.Health_Bio;

public class HEALTHBIODAO extends DBDAO implements DAOInterface<Health_Bio> {
    public HEALTHBIODAO(Context context) {
        super(context);
    }
    private String WHERE_ID = DataBaseHelper.HealthBio_ID + "=?";

    private static final SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    @Override
    public long save(Health_Bio health_bio) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.Condition, health_bio.getCondition());
        values.put(DataBaseHelper.StartDate, dateformat.format(health_bio.getStrartDate()));
        values.put(DataBaseHelper.ConditionType, health_bio.getConditionType());


        return database.insert(DataBaseHelper.Health_Bio_TABLE, null, values);
    }

    @Override
    public long update(Health_Bio health_bio) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.Condition, health_bio.getCondition());
        values.put(DataBaseHelper.StartDate, dateformat.format(health_bio.getStrartDate()));

        values.put(DataBaseHelper.ConditionType, health_bio.getConditionType());


        return database.update(DataBaseHelper.Health_Bio_TABLE, values, WHERE_ID, new String[]{health_bio.getHealthBioId() + "" });    }

    @Override
    public long delete(Health_Bio health_bio) {


        return database.delete(DataBaseHelper.Health_Bio_TABLE, WHERE_ID, new String[]{health_bio.getHealthBioId() + "" });

}

    @Override
    public Health_Bio get(Health_Bio health_bio) {
        return null;
    }

    @Override
    public List<Health_Bio> getAll() {
        List<Health_Bio> health_bioList = new ArrayList<Health_Bio>();

        String sql = "SELECT *  FROM " + DataBaseHelper.Health_Bio_TABLE;

        Cursor cursor = database.rawQuery(sql, null);


        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String condition = cursor.getString(1);
            Date date = null;
            String conditionType = cursor.getString(3);

            try {
                date = dateformat.parse(cursor.getString(2));// this is used to detech error in case, error occour at datae convention.
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
            Health_Bio health_bio = new Health_Bio();
            health_bio.setHealthBioId(id);
            health_bio.setCondition(condition);
            health_bio.setStrartDate(date);
            health_bio.setConditionType(conditionType);

            health_bioList.add(health_bio);// extrect from db add to list array

        }
        return health_bioList;
    }
}
