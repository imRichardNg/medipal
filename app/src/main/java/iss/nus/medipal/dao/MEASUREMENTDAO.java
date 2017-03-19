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

import iss.nus.medipal.AppFolder.MeasurementData;
import iss.nus.medipal.AppFolder.Measurement;
import iss.nus.medipal.AppFolder.BloodPressure;
import iss.nus.medipal.AppFolder.Temperature;
import iss.nus.medipal.AppFolder.Weight;
import iss.nus.medipal.AppFolder.Pulse;

/**
 * Created by nus on 17/3/17.
 */

public class MEASUREMENTDAO extends DBDAO implements DAOInterface<MeasurementData>{
    public MEASUREMENTDAO(Context context){super(context);}
    private static final SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private String WHERE_ID = DataBaseHelper.Measurement_ID + "=?";


    @Override
    public long save(MeasurementData measurementData) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.MeasuredOn, dateformat.format(measurementData.getMeasurement().getMeasuredOn()));
        values.put(DataBaseHelper.Pulse,measurementData.getPulse().getPulse());
        values.put(DataBaseHelper.Systolic,measurementData.getBloodPressure().getSystolic());
        values.put(DataBaseHelper.Diastolic,measurementData.getBloodPressure().getDiastolic());
        values.put(DataBaseHelper.Weight,measurementData.getWeight().getWeight());
        values.put(DataBaseHelper.Temperature,measurementData.getTemp().getTemperature());
        return database.insert(DataBaseHelper.Measurement_TABLE, null, values);
    }

    @Override
    public long update(MeasurementData measurementData) {

        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.MeasuredOn, dateformat.format(measurementData.getMeasurement().getMeasuredOn()));
        values.put(DataBaseHelper.Pulse,measurementData.getPulse().getPulse());
        values.put(DataBaseHelper.Systolic,measurementData.getBloodPressure().getSystolic());
        values.put(DataBaseHelper.Diastolic,measurementData.getBloodPressure().getDiastolic());
        values.put(DataBaseHelper.Weight,measurementData.getWeight().getWeight());
        values.put(DataBaseHelper.Temperature,measurementData.getTemp().getTemperature());

        return database.update(DataBaseHelper.Measurement_TABLE, values, WHERE_ID, new String[]{measurementData.getMeasurement().getId() + "" });    }


    @Override
    public long delete(MeasurementData measurementData) {
        return database.delete(DataBaseHelper.Measurement_TABLE, WHERE_ID, new String[]{measurementData.getMeasurement().getId() + "" });

    }

    @Override
    public MeasurementData get(MeasurementData measurementData) {
        return null;
    }

    @Override
    public List<MeasurementData> getAll() {
        String sql = "SELECT *  FROM " + DataBaseHelper.Measurement_TABLE;

        List<MeasurementData> measurementDataList= new ArrayList<MeasurementData>();

        Cursor cursor = database.rawQuery(sql, null);


        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int systolic=cursor.getInt(1);
            int diastolic=cursor.getInt(2);
            int pulse=cursor.getInt(3);
            double temperature=cursor.getDouble(4);
            int weight=cursor.getInt(5);
            Date date=null;


            try {
                date = dateformat.parse(cursor.getString(6));// this is used to detect error in case, error occour at datae convention.
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
            MeasurementData measurementData = new MeasurementData();
            Measurement measurement= new Measurement();
            BloodPressure bloodPressure= new BloodPressure();
            Pulse pulse1=new Pulse();
            Temperature temperature1 =new Temperature();
            Weight weight1=new Weight();


            measurement.setId(id);
            bloodPressure.setSystolic(systolic);
            bloodPressure.setDiastolic(diastolic);
            pulse1.setPulse(pulse);
            temperature1.setTemperature(temperature);
            measurement.setMeasuredOn(date);
            weight1.setWeight(weight);
            measurementData.setMeasurement(measurement);
            measurementData.setBloodPressure(bloodPressure);
            measurementData.setPulse(pulse1);
            measurementData.setTemp(temperature1);
            measurementData.setWeight(weight1);



            measurementDataList.add(measurementData);// extrect from db add to list array

        }
        return measurementDataList;    }
}
