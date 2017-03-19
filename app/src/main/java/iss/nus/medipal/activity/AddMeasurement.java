package iss.nus.medipal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import iss.nus.medipal.AppFolder.BloodPressure;
import iss.nus.medipal.AppFolder.Measurement;
import iss.nus.medipal.AppFolder.MeasurementData;
import iss.nus.medipal.AppFolder.Medipal;
import iss.nus.medipal.AppFolder.Pulse;
import iss.nus.medipal.AppFolder.Temperature;
import iss.nus.medipal.AppFolder.Weight;
import iss.nus.medipal.R;
import iss.nus.medipal.asyncTask.AddMEASUREMENT;

/**
 * Created by nus on 18/3/17.
 */

public class AddMeasurement extends AppCompatActivity {

    private EditText txtSystolic;
    private EditText txtDiastolic;
    private EditText txtPulse;
    private EditText txtTemperature;
    private EditText txtWeight;
    private EditText txtMeasureOn;
    private static final SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private Button submit;
    Medipal medipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_measurement);
        txtSystolic = (EditText)findViewById(R.id.txtSystolic);
        txtDiastolic = (EditText)findViewById(R.id.txtDiastolic);
        txtPulse= (EditText)findViewById(R.id.txtPulse);
        txtTemperature=(EditText)findViewById(R.id.txtTemperature);
        txtWeight=(EditText)findViewById(R.id.txtWeight);
        txtMeasureOn=(EditText)findViewById(R.id.txtMeasuredOn);
        submit=(Button)findViewById(R.id.btnMeasurementsubmit);


    }

    public void measurementSubmit(View view){

        Pulse pulse = new Pulse();
        BloodPressure bloodPressure = new BloodPressure();
        Temperature temperature = new Temperature();
        Weight weight = new Weight();
        Measurement measurement = new Measurement();
        MeasurementData measurementData = new MeasurementData();

        if(isValid()==true){

            bloodPressure.setSystolic(txtSystolic.getText().toString().isEmpty() ? 0 : Integer.parseInt(txtSystolic.getText().toString()));
            bloodPressure.setDiastolic(txtDiastolic.getText().toString().isEmpty() ? 0:Integer.parseInt(txtDiastolic.getText().toString()));
            pulse.setPulse(txtPulse.getText().toString().isEmpty() ? 0 :Integer.parseInt(txtPulse.getText().toString()));
            temperature.setTemperature(txtTemperature.getText().toString().isEmpty() ? 0:Double.parseDouble(txtTemperature.getText().toString()));
            weight.setWeight(txtWeight.getText().toString().isEmpty()? 0 :Integer.parseInt(txtWeight.getText().toString()));

            try {
                measurement.setMeasuredOn(dateformat.parse(txtMeasureOn.getText().toString()));
            } catch (ParseException e) {
                // To be notified to user
            }

            measurementData.setMeasurement(measurement);
            measurementData.setBloodPressure(bloodPressure);
            measurementData.setPulse(pulse);
            measurementData.setMeasurement(measurement);
            measurementData.setTemp(temperature);
            measurementData.setWeight(weight);
            medipal=new Medipal();

            medipal.addMEASUREMENT(measurementData,this);


            Intent intent = new Intent(AddMeasurement.this, MainActivity.class);
            startActivity(intent);
            Toast toast = Toast.makeText(this, "Record is successfully Added.", Toast.LENGTH_LONG);
            toast.show();

            Log.i("Measurement", "SUBMIT CLICKED.");

        }

    }


    public boolean isValid(){
        if(txtMeasureOn.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(this, "Date cannot be empty.", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        if(txtSystolic.getText().toString().isEmpty()){
            txtSystolic.setText(0);

        }

        return true;
    }
}
