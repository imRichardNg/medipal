package iss.nus.medipal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import iss.nus.medipal.AppFolder.Personal_Bio;

/**
 * Created by nus on 11/3/17.
 */

public class PERSONALBIODAO extends DBDAO implements DAOInterface<Personal_Bio> {
    private static final SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    private String WHERE_ID = DataBaseHelper.Personal_ID + "=?";

    public PERSONALBIODAO(Context context) {
        super(context);
    }

    @Override
    public long save(Personal_Bio personal_bio) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.Personal_NAME, personal_bio.getPersonalName());
        values.put(DataBaseHelper.DOB, dateformat.format(personal_bio.getDob()));
        values.put(DataBaseHelper.IDNo, personal_bio.getIdNo());
        values.put(DataBaseHelper.Address, personal_bio.getAddress());
        values.put(DataBaseHelper.PostalCode, personal_bio.getPostalcode());
        values.put(DataBaseHelper.Height, personal_bio.getHeight());
        values.put(DataBaseHelper.BloodType, personal_bio.getBloodType());


        return database.insert(DataBaseHelper.Personal_Bio_TABLE, null, values);
    }


    @Override
    public long update(Personal_Bio personal_bio) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.Personal_NAME, personal_bio.getPersonalName());
        values.put(DataBaseHelper.DOB, dateformat.format(personal_bio.getDob()));
        values.put(DataBaseHelper.IDNo, personal_bio.getIdNo());
        values.put(DataBaseHelper.Address, personal_bio.getAddress());
        values.put(DataBaseHelper.PostalCode, personal_bio.getPostalcode());
        values.put(DataBaseHelper.Height, personal_bio.getHeight());
        values.put(DataBaseHelper.BloodType, personal_bio.getBloodType());


        return database.update(DataBaseHelper.Personal_Bio_TABLE, values, WHERE_ID, new String[]{personal_bio.getPersonalId() + "" });
    }

    @Override
    public long delete(Personal_Bio personal_bio) {
        return 0;
    }

    @Override
    public Personal_Bio get(Personal_Bio personal_bio) {
        return null;
    }

    @Override
    public List getAll() {
        List<Personal_Bio> personal_bios = new ArrayList<Personal_Bio>();

        String sql = "SELECT *  FROM " + DataBaseHelper.Personal_Bio_TABLE;

        Cursor cursor = database.rawQuery(sql, null);


        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            Date date = null;
            String idNo = cursor.getString(3);
            String address = cursor.getString(4);
            String postalCode = cursor.getString(5);
            int height = cursor.getInt(6);
            String bloodType = cursor.getString(7);

            try {
                date = dateformat.parse(cursor.getString(2));// this is used to detech error in case, error occour at datae convention.
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
            Personal_Bio personal_bio = new Personal_Bio();
            personal_bio.setPersonalId(id);
            personal_bio.setPersonalName(name);
            personal_bio.setDob(date);
            personal_bio.setIdNo(idNo);
            personal_bio.setAddress(address);
            personal_bio.setPostalcode(postalCode);
            personal_bio.setHeight(height);
            personal_bio.setBloodType(bloodType);

            personal_bios.add(personal_bio);


        }
        return personal_bios;


    }

}
