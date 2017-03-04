package iss.nus.medipal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import iss.nus.medipal.AppFolder.ICE;

/**
 * Created by richard on 4/3/17.
 */

public class ICEDAO extends DBDAO implements DAOInterface<ICE> {
    public ICEDAO(Context context) {
        super(context);
    }

    @Override
    public long save(ICE ice) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.NAME, ice.getName());

        return database.insert(DataBaseHelper.ICE_TABLE, null, values);
    }

    @Override
    public long update(ICE ice) {
        return 0;
    }

    @Override
    public long delete(ICE ice) {
        return 0;
    }

    @Override
    public long get(ICE ice) {
        return 0;
    }

    @Override
    public List<ICE> getAll() {
        List<ICE> ices = new ArrayList<ICE>();

        String sql = "SELECT " + DataBaseHelper.ID + ", "
                + DataBaseHelper.NAME + " FROM " + DataBaseHelper.ICE_TABLE;

        Cursor cursor = database.rawQuery(sql, null);


        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);

            ICE ice = new ICE(name);
            ice.setId(id);
            ices.add(ice);
        }
        return ices;
    }
}
