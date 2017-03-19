package iss.nus.medipal.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.List;

import iss.nus.medipal.AppFolder.SpinnerObject;
import iss.nus.medipal.R;
import iss.nus.medipal.application.App;

public class AddConsumptionActivity extends AppCompatActivity {

    private Spinner spnMedicineType;
    private EditText etQuantity;
    private EditText etConsumedOnDate;
    private EditText etConsumedOnTime;

    private Button btnAddConsumption;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());

    private Calendar currentCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_consumption);

        spnMedicineType = (Spinner) findViewById(R.id.spn_medicineType);

       /* List<SpinnerObject> medicineLabels = App.medipal.getAllConsumptionMedicineList();

        ArrayAdapter<SpinnerObject> spnMedicineTypeAdapter = new ArrayAdapter<SpinnerObject>(this,
                android.R.layout.simple_spinner_item, medicineLabels);

        spnMedicineTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMedicineType.setAdapter(spnMedicineTypeAdapter);*/

        loadSpinnerData();

        etQuantity=(EditText) findViewById(R.id.et_quantity);
        etConsumedOnDate = (EditText) findViewById(R.id.et_consumedOnDate);
        etConsumedOnTime = (EditText) findViewById(R.id.et_consumedOnTime);

        btnAddConsumption = (Button) findViewById(R.id.btn_addConsumption);

        etConsumedOnDate.setText(dateFormatter.format(currentCalendar.getTime()));
        etConsumedOnDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog.OnDateSetListener onDateSetListener =
                    (view, year, month, dayOfMonth) -> {
                        calendar.set(year, month, dayOfMonth);
                        etConsumedOnDate.setText(dateFormatter.format(calendar.getTime()));
                    };

            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(AddConsumptionActivity.this, onDateSetListener,
                            currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH),
                            currentCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        etConsumedOnTime.setText(timeFormatter.format(currentCalendar.getTime()));
        etConsumedOnTime.setOnClickListener((v -> {
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog.OnTimeSetListener onTimeSetListener =
                    (view, hourOfDay, minute) -> {
                        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);
                        etConsumedOnTime.setText(timeFormatter.format(calendar.getTime()));
                    };

            TimePickerDialog timePickerDialog =
                    new TimePickerDialog(AddConsumptionActivity.this, onTimeSetListener,
                            currentCalendar.get(Calendar.HOUR_OF_DAY), currentCalendar.get(Calendar.MINUTE), false);
            timePickerDialog.show();
        }));

        btnAddConsumption.setOnClickListener(v -> {
            if (isValidConsumption()) {
                Calendar selectedDate = Calendar.getInstance();
                Calendar selectedTime = Calendar.getInstance();

                try {
                    selectedDate.setTime(dateFormatter.parse(etConsumedOnDate.getText().toString()));
                    selectedTime.setTime(timeFormatter.parse(etConsumedOnTime.getText().toString()));

                    selectedDate.set(Calendar.HOUR, selectedTime.get(Calendar.HOUR));
                    selectedDate.set(Calendar.MINUTE, selectedTime.get(Calendar.MINUTE));
                    selectedDate.set(Calendar.AM_PM, selectedTime.get(Calendar.AM_PM));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                int medicine = (int) spnMedicineType.getSelectedItemId();
                System.out.print(spnMedicineType.getSelectedItem().toString());

                spnMedicineType.getSelectedItemId();

                String quantity = etQuantity.getText().toString().trim();
                int quantityInt = TextUtils.isEmpty(quantity) ? 0 : Integer.valueOf(quantity);

                App.medipal.addConsumption(medicine,quantityInt,selectedDate.getTime(),getApplicationContext());
                finish();
            }
        });
    }

    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerData() {

        /*List <SpinnerObject> lables = new ArrayList<SpinnerObject>();
        lables.add((new SpinnerObject(1,"Acetaminophen")));
        lables.add((new SpinnerObject(2,"Diclofenac")));
        lables.add((new SpinnerObject(3,"Humulin")));*/

        List <SpinnerObject> lables = App.medipal.getAllConsumptionMedicineList(getApplicationContext());

        // Creating adapter for spinner
        ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spnMedicineType.setAdapter(dataAdapter);
    }

    private boolean isValidConsumption() {
        etQuantity.setError(null);

        String quantity = etQuantity.getText().toString().trim();
        int quantitylInt = -1;
        if (quantitylInt > 0) {
            if (TextUtils.isEmpty(quantity)) {
                etQuantity.setError("Please set quantity.");
            } else {
                quantitylInt = Integer.valueOf(quantity);

                if (quantitylInt < 1) {
                    etQuantity.setError("Minimum quantity is 1.");
                }
            }
        }

        return TextUtils.isEmpty(etQuantity.getError());
    }
}
