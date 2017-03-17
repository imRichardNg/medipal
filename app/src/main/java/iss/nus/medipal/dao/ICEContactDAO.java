package iss.nus.medipal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import iss.nus.medipal.AppFolder.ICEContact;

import static iss.nus.medipal.dao.DataBaseHelper.WHERE_ID_EQUALS;

/**
 * Created by richard on 4/3/17.
 */

public class ICEContactDAO extends DBDAO implements DAOInterface<ICEContact> {
    public ICEContactDAO(Context context) {
        super(context);
    }

    @Override
    public long save(ICEContact iceContact) {
        ContentValues values = new ContentValues();

        System.out.println("Add ICE Contact: " + iceContact);

        values.put(DataBaseHelper.NAME, iceContact.getName());
        values.put(DataBaseHelper.CONTACT_NO, iceContact.getContactNo());
        values.put(DataBaseHelper.CONTACT_TYPE, iceContact.getContactType());
        values.put(DataBaseHelper.DESCRIPTION, iceContact.getDescription());
        values.put(DataBaseHelper.SEQUENCE, iceContact.getSequence());

        return database.insert(DataBaseHelper.ICE_TABLE, null, values);
    }

    @Override
    public long update(ICEContact iceContact) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.ID, iceContact.getId());
        values.put(DataBaseHelper.NAME, iceContact.getName());
        values.put(DataBaseHelper.CONTACT_NO, iceContact.getContactNo());
        values.put(DataBaseHelper.CONTACT_TYPE, iceContact.getContactType());
        values.put(DataBaseHelper.DESCRIPTION, iceContact.getDescription());
        values.put(DataBaseHelper.SEQUENCE, iceContact.getSequence());

        return database.update(DataBaseHelper.ICE_TABLE, values, WHERE_ID_EQUALS, new String[]{String.valueOf(iceContact.getId())});
    }

    @Override
    public long delete(ICEContact iceContact) {
        return database.delete(DataBaseHelper.ICE_TABLE, WHERE_ID_EQUALS,
                new String[]{iceContact.getId() + ""});
    }

    @Override
    public ICEContact get(ICEContact iceContact) {
        String sql = "SELECT " + DataBaseHelper.ID + ", "
                + DataBaseHelper.NAME + ", "
                + DataBaseHelper.CONTACT_NO + ", "
                + DataBaseHelper.CONTACT_TYPE + ", "
                + DataBaseHelper.DESCRIPTION + ", "
                + DataBaseHelper.SEQUENCE
                + " FROM " + DataBaseHelper.ICE_TABLE
                + " WHERE " + WHERE_ID_EQUALS;

        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(iceContact.getId())});

        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String contactNo = cursor.getString(2);
            int contactType = cursor.getInt(3);
            String description = cursor.getString(4);
            int sequence = cursor.getInt(5);

            return new ICEContact(id, name, contactNo, contactType, description, sequence);
        }

        return new ICEContact();
    }

    @Override
    public List<ICEContact> getAll() {
        List<ICEContact> iceContacts = new ArrayList<ICEContact>();

        String sql = "SELECT " + DataBaseHelper.ID + ", "
                + DataBaseHelper.NAME + ", "
                + DataBaseHelper.CONTACT_NO + ", "
                + DataBaseHelper.CONTACT_TYPE + ", "
                + DataBaseHelper.DESCRIPTION + ", "
                + DataBaseHelper.SEQUENCE
                + " FROM " + DataBaseHelper.ICE_TABLE;

        Cursor cursor = database.rawQuery(sql, null);


        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String contactNo = cursor.getString(2);
            int contactType = cursor.getInt(3);
            String description = cursor.getString(4);
            int sequence = cursor.getInt(5);

            ICEContact iceContact = new ICEContact(id, name, contactNo, contactType, description, sequence);
            iceContacts.add(iceContact);
        }

        return iceContacts;
    }
}
