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

import iss.nus.medipal.AppFolder.Medicine;

import static iss.nus.medipal.dao.DataBaseHelper.WHERE_ID_EQUALS;

/**
 * Created by richard on 4/3/17.
 */

public class MedicineDAO extends DBDAO implements DAOInterface<Medicine> {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy H:mm", Locale.ENGLISH);

    public MedicineDAO(Context context) {
        super(context);
    }

    @Override
    public long save(Medicine medicine) {
        ContentValues values = new ContentValues();

        System.out.println("Add Medicine: " + medicine);

        values.put(DataBaseHelper.MEDICINE_NAME, medicine.getMedicineName());
        values.put(DataBaseHelper.MEDICINE_DESCRIPTION, medicine.getMedicineDescription());
        values.put(DataBaseHelper.MEDICINE_CATEGORY_ID, medicine.getCategoryID());
        values.put(DataBaseHelper.REMIND, medicine.isRemind());
        values.put(DataBaseHelper.REMINDER_ID, medicine.getReminderID());
        values.put(DataBaseHelper.QUANTITY, medicine.getQuantity());
        values.put(DataBaseHelper.DOSAGE, medicine.getDosage());
        values.put(DataBaseHelper.CONSUMED_QUANTITY, medicine.getConsumedQuantity());
        values.put(DataBaseHelper.THERSHOLD, medicine.getThereshold());
        values.put(DataBaseHelper.DATE_ISSUED, formatter.format(medicine.getIssuedDate()));
        values.put(DataBaseHelper.EXPIRE_FACTOR, medicine.getExpireFactor());


        return database.insert(DataBaseHelper.MEDICINE_TABLE, null, values);
    }

    @Override
    public long update(Medicine medicine) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.MEDICINE_NAME, medicine.getMedicineName());
        values.put(DataBaseHelper.MEDICINE_DESCRIPTION, medicine.getMedicineDescription());
        values.put(DataBaseHelper.MEDICINE_CATEGORY_ID, medicine.getCategoryID());
        values.put(DataBaseHelper.REMIND, medicine.isRemind());
        values.put(DataBaseHelper.REMINDER_ID, medicine.getReminderID());
        values.put(DataBaseHelper.QUANTITY, medicine.getQuantity());
        values.put(DataBaseHelper.DOSAGE, medicine.getDosage());
        values.put(DataBaseHelper.CONSUMED_QUANTITY, medicine.getConsumedQuantity());
        values.put(DataBaseHelper.THERSHOLD, medicine.getThereshold());
        values.put(DataBaseHelper.DATE_ISSUED, formatter.format(medicine.getIssuedDate()));
        values.put(DataBaseHelper.EXPIRE_FACTOR, medicine.getExpireFactor());

        System.out.println(" update medicine " + String.valueOf(medicine.getMedicineId()));
        return database.update(DataBaseHelper.MEDICINE_TABLE, values, DataBaseHelper.WHERE_ID_EQUALS, new String[]{String.valueOf(medicine.getMedicineId())});
    }

    @Override
    public long delete(Medicine medicine) {
       long ret =  database.delete(DataBaseHelper.MEDICINE_TABLE, DataBaseHelper.WHERE_ID_EQUALS,
                new String[]{medicine.getMedicineId() + ""});
        if(ret > 0) {
            System.out.println("Delete medicine");

            ret =  database.delete(DataBaseHelper.CONSUMPTION_TABLE, DataBaseHelper.WHERE_MEDID_EQUALS,
                    new String[]{medicine.getMedicineId() + ""});
            System.out.println("Delete Consumption by medicineId");
        }
        return ret;
    }

    @Override
    public Medicine get(Medicine medicine) {
        String sql = "SELECT " + DataBaseHelper.ID + ", "
                + DataBaseHelper.MEDICINE_NAME + ", "
                + DataBaseHelper.MEDICINE_DESCRIPTION + ", "
                + DataBaseHelper.MEDICINE_CATEGORY_ID + ", "
                + DataBaseHelper.REMIND + ", "
                + DataBaseHelper.REMINDER_ID + ", "
                + DataBaseHelper.QUANTITY + ", "
                + DataBaseHelper.DOSAGE + ", "
                + DataBaseHelper.CONSUMED_QUANTITY + ", "
                + DataBaseHelper.THERSHOLD + ", "
                + DataBaseHelper.DATE_ISSUED + ", "
                + DataBaseHelper.EXPIRE_FACTOR
                + " FROM " + DataBaseHelper.MEDICINE_TABLE
                + " WHERE " + WHERE_ID_EQUALS;

        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(medicine.getMedicineId())});

        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);

            String medicineName = cursor.getString(1);
            String medicineDesc = cursor.getString(2);
            int catID = cursor.getInt(3);
            String reminder = cursor.getString(4);
            int reminderID = cursor.getInt(5);
            int quantity = cursor.getInt(6);
            int dosage  = cursor.getInt(7);
            int consumedQuantity = cursor.getInt(8);
            int thershold = cursor.getInt(9);
            Date issueDate = null;
            try {
                issueDate = formatter.parse(cursor.getString(10));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int expireFactor = cursor.getInt(11);
            return new Medicine(id,medicineName, medicineDesc, true, catID, reminderID, quantity,dosage,consumedQuantity,thershold,expireFactor,issueDate);
        }

        return new Medicine();
    }

    @Override
    public List<Medicine> getAll() {
        List<Medicine> medicines = new ArrayList<Medicine>();

        String sql = "SELECT " + DataBaseHelper.ID + ", "
                + DataBaseHelper.MEDICINE_NAME + ", "
                + DataBaseHelper.MEDICINE_DESCRIPTION + ", "
                + DataBaseHelper.MEDICINE_CATEGORY_ID + ", "
                + DataBaseHelper.REMIND + ", "
                + DataBaseHelper.REMINDER_ID + ", "
                + DataBaseHelper.QUANTITY + ", "
                + DataBaseHelper.DOSAGE + ", "
                + DataBaseHelper.CONSUMED_QUANTITY + ", "
                + DataBaseHelper.THERSHOLD + ", "
                + DataBaseHelper.DATE_ISSUED + ", "
                + DataBaseHelper.EXPIRE_FACTOR
                + " FROM " + DataBaseHelper.MEDICINE_TABLE;
                //+ " WHERE " + WHERE_ID_EQUALS;

        Cursor cursor = database.rawQuery(sql, null);


        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String medicineName = cursor.getString(1);
            String medicineDesc = cursor.getString(2);
            int catID = cursor.getInt(3);
            String reminder = cursor.getString(4);
            int reminderID = cursor.getInt(5);
            int quantity = cursor.getInt(6);
            int dosage  = cursor.getInt(7);
            int consumedQuantity = cursor.getInt(8);
            int thershold = cursor.getInt(9);
            int expireFactor = cursor.getInt(11);
            Date issueDate = null;
            try {
                issueDate = formatter.parse(cursor.getString(10));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            boolean bIsRemind = false;
            if(reminder.equals("1"))
                bIsRemind = true;

            Medicine medicine = new Medicine(id,medicineName, medicineDesc, bIsRemind, catID, reminderID, quantity,dosage,consumedQuantity,thershold,expireFactor,issueDate);
            medicines.add(medicine);
        }

        return medicines;
    }
}
