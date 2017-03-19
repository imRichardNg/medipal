package iss.nus.medipal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import iss.nus.medipal.AppFolder.MeasurementData;
import iss.nus.medipal.AppFolder.Medipal;
import iss.nus.medipal.R;
import iss.nus.medipal.adaptor.Measurement_Adaptor;

/**
 * Created by nus on 11/3/17.
 */

public class ManageMeasurementActivity extends AppCompatActivity {
    private ListView measurementDataListView;
    private SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private EditText txtFilteredDate;

    Medipal medipal;
    List<MeasurementData> measurementDataList;
    List<MeasurementData> filteredMeasurementDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement);// this is compoulsory

        measurementDataListView = (ListView) findViewById(R.id.litMeasurement);

        List<String> measurementData = new ArrayList<>();
        txtFilteredDate = (EditText) findViewById(R.id.txtFilteredDate);

        medipal = new Medipal();
        filteredMeasurementDataList = new ArrayList<MeasurementData>();
        measurementDataList = medipal.getMeasurementData(this);
        for (MeasurementData data : measurementDataList) {
            filteredMeasurementDataList.add(data);
        }

        FloatingActionButton fltAddMeasurement = (FloatingActionButton) findViewById(R.id.fltAddMeasurement);
        fltAddMeasurement.setOnClickListener(new View.OnClickListener()


        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddMeasurement.class);
                startActivity(intent);
            }
        });
    }


    public void filterClick(View view) {
        filteredMeasurementDataList.clear();
        try {
            Date filteredDate = dateformat.parse(txtFilteredDate.getText().toString());
            for (MeasurementData data : measurementDataList) {
                Log.i(String.valueOf(data.getMeasurement().getMeasuredOn().getTime()), String.valueOf(filteredDate.getTime()));
                if (data.getMeasurement().getMeasuredOn().getTime() >= filteredDate.getTime()) {
                    filteredMeasurementDataList.add(data);
                }
            }
            loadDataList();
        } catch (ParseException e) {
            Log.e("ERROR", e.getMessage());
        }

    }

    @Override
    protected void onStart() {

        loadDataList();
        super.onStart();
    }

    private void loadDataList() {
        Measurement_Adaptor measurement_adaptor = new Measurement_Adaptor(filteredMeasurementDataList, this);
        measurementDataListView.setAdapter(measurement_adaptor);
    }
}