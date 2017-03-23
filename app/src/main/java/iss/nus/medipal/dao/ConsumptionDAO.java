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

import iss.nus.medipal.AppFolder.Consumption;
import iss.nus.medipal.AppFolder.SpinnerObject;

import static iss.nus.medipal.dao.DataBaseHelper.WHERE_ID_EQUALS;

public class ConsumptionDAO extends DBDAO implements DAOInterface<Consumption> {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy H:mm", Locale.ENGLISH);

    public ConsumptionDAO(Context context) {
        super(context);
    }

    @Override
    public long save(Consumption consumption) {
        ContentValues values = new ContentValues();

        System.out.println("Add comsumption: " + consumption);

        values.put(DataBaseHelper.MEDICINEID, consumption.getMedicineId());
        values.put(DataBaseHelper.DOSAGEQUANTITY, consumption.getQuantity());
        values.put(DataBaseHelper.CONSUMEDON, formatter.format(consumption.getConsumedOn()));

        return database.insert(DataBaseHelper.CONSUMPTION_TABLE, null, values);
    }

    @Override
    public long update(Consumption consumption) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.ID, consumption.getId());
        values.put(DataBaseHelper.MEDICINEID, consumption.getMedicineId());
        values.put(DataBaseHelper.DOSAGEQUANTITY, consumption.getQuantity());
        values.put(DataBaseHelper.CONSUMEDON, formatter.format(consumption.getConsumedOn()));

        return database.update(DataBaseHelper.CONSUMPTION_TABLE, values, WHERE_ID_EQUALS, new String[]{String.valueOf(consumption.getId())});
    }

    @Override
    public long delete(Consumption consumption) {
        return database.delete(DataBaseHelper.CONSUMPTION_TABLE, WHERE_ID_EQUALS,
                new String[]{consumption.getId() + ""});
    }

    @Override
    public Consumption get(Consumption consumption) {

        String sql = "SELECT " + DataBaseHelper.ID + ", "
                + DataBaseHelper.MEDICINEID + ", "
                + DataBaseHelper.DOSAGEQUANTITY + ", "
                + DataBaseHelper.CONSUMEDON
                + " FROM " + DataBaseHelper.CONSUMPTION_TABLE
                + " WHERE " + WHERE_ID_EQUALS;

        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(consumption.getId())});

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int medicineId = cursor.getInt(1);
            int quantity = cursor.getInt(2);
            Date consumedOn = null;

            try {
                consumedOn = formatter.parse(cursor.getString(3));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return new Consumption(id, medicineId, quantity, consumedOn);
        }

        return new Consumption();
    }

    @Override
    public List<Consumption> getAll() {

        List<Consumption> consumptions = new ArrayList<Consumption>();

        String sql = "SELECT " + DataBaseHelper.ID + ", "
                + DataBaseHelper.MEDICINEID + ", "
                + DataBaseHelper.DOSAGEQUANTITY + ", "
                + DataBaseHelper.CONSUMEDON
                + " FROM " + DataBaseHelper.CONSUMPTION_TABLE;

        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int medicineId = cursor.getInt(1);
            int quantity = cursor.getInt(2);
            Date consumedOn = null;

            try {
                consumedOn = formatter.parse(cursor.getString(3));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Consumption consumption = new Consumption(id, medicineId, quantity, consumedOn);
            consumptions.add(consumption);
        }
        return consumptions;
    }

    public List<SpinnerObject> getAllMedicine() {
        List<SpinnerObject> lables = new ArrayList<SpinnerObject>();

        // db portion
        /*String sql = "SELECT " + DataBaseHelper.ID + ", "
                + DataBaseHelper.MEDICINE_NAME
                + " FROM " + DataBaseHelper.MEDICINE_TABLE;

        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            labels.add ( new SpinnerObject(cursor.getInt(0),cursor.getString(1)));
        }*/

        lables.add((new SpinnerObject(1, "Acetaminophen")));
        lables.add((new SpinnerObject(2, "Diclofenac")));
        lables.add((new SpinnerObject(3, "Humulin")));
        return lables;
    }
}
