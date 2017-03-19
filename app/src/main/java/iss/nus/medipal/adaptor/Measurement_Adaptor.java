package iss.nus.medipal.adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import iss.nus.medipal.AppFolder.BloodPressure;
import iss.nus.medipal.AppFolder.Measurement;
import iss.nus.medipal.AppFolder.MeasurementData;
import iss.nus.medipal.AppFolder.Pulse;
import iss.nus.medipal.AppFolder.Temperature;
import iss.nus.medipal.AppFolder.Weight;
import iss.nus.medipal.R;

/**
 * Created by nus on 18/3/17.
 */

public class Measurement_Adaptor extends ArrayAdapter<MeasurementData> implements View.OnClickListener {
    private List<MeasurementData> measurementDataList;
    private Context context;
    private static final SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private int lsPstion = -1; //lastpostion

    public Measurement_Adaptor(List<MeasurementData> measurementDataList, Context context) {
        super(context, R.layout.measurement_rowlayout, measurementDataList);
        this.measurementDataList = measurementDataList;
        this.context = context;
    }

    public static class ViewMeasurementData {
        TextView txtSystolic;
        TextView txtDiastolic;
        TextView txtPulse;
        TextView txtWeight;
        TextView txtTemperature;
        TextView txtMeasurementOn;

    }


    @Override
    public void onClick(View view) {

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MeasurementData measurementData = getItem(position);
        ViewMeasurementData viewMeasurementData;
        Pulse pulse = new Pulse();
        BloodPressure bloodPressure = new BloodPressure();
        Temperature temperature = new Temperature();
        Weight weight = new Weight();
        Measurement measurement = new Measurement();

        if (convertView == null) {
            viewMeasurementData = new ViewMeasurementData();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.measurement_rowlayout, parent, false);
            viewMeasurementData.txtSystolic = (TextView) convertView.findViewById(R.id.rowSystolic);
            viewMeasurementData.txtDiastolic = (TextView) convertView.findViewById(R.id.rowDiastolic);
            viewMeasurementData.txtPulse = (TextView) convertView.findViewById(R.id.rowPulse);
            viewMeasurementData.txtWeight = (TextView) convertView.findViewById(R.id.rowWeight);
            viewMeasurementData.txtTemperature = (TextView) convertView.findViewById(R.id.rowTemperature);
            viewMeasurementData.txtMeasurementOn = (TextView) convertView.findViewById(R.id.rowMeasuredOn);


            convertView.setTag(viewMeasurementData);

        } else {
            viewMeasurementData = (ViewMeasurementData) convertView.getTag();
        }


       measurement = measurementData.getMeasurement();
        bloodPressure= measurementData.getBloodPressure();
        temperature=measurementData.getTemp();
        weight= measurementData.getWeight();
        pulse = measurementData.getPulse();


        lsPstion = position;
        viewMeasurementData.txtSystolic.setText(String.valueOf(bloodPressure.getSystolic()));
        viewMeasurementData.txtMeasurementOn.setText(dateformat.format(measurement.getMeasuredOn()));
        viewMeasurementData.txtDiastolic.setText(String.valueOf(bloodPressure.getDiastolic()));
        viewMeasurementData.txtPulse.setText(String.valueOf(pulse.getPulse()));
        viewMeasurementData.txtWeight.setText(String.valueOf(weight.getWeight()));
        viewMeasurementData.txtTemperature.setText(String.valueOf(temperature.getTemperature()));


        return convertView;
    }



}
